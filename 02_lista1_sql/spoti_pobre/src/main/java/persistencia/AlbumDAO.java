package persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import negocio.Album;
import negocio.Artista;
import negocio.Genero;

// DAO: Data Acess Object
public class AlbumDAO {

    public ArrayList<Album> listar() throws SQLException {
        ArrayList<Album> vetAlbum = new ArrayList<Album>();
        String sql = "SELECT album.capa, album.id, album.titulo, album.data_lancamento, genero.id as genero_id, genero.nome as genero_nome FROM album JOIN genero ON album.genero_id = genero.id;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        ResultSet rs = instrucaoSQL.executeQuery();
        while (rs.next()) {
            Album album = new Album();
            album.setId(rs.getInt("id"));
            album.setTitulo(rs.getString("titulo"));
            album.setDataLancamento(rs.getDate("data_lancamento").toLocalDate());
            album.setCapa(rs.getBytes("capa"));

            Genero genero = new Genero();
            genero.setId(rs.getInt("genero_id"));
            genero.setNome(rs.getString("genero_nome"));
            album.setGenero(genero);
            PreparedStatement instrucaoArtistasSQL = conexao.prepareStatement(
                    "SELECT id, nome FROM artista join album_artista on artista.id = album_artista.artista_id where album_artista.album_id = ? ");
            instrucaoArtistasSQL.setInt(1, album.getId());
            ResultSet rsArtistas = instrucaoArtistasSQL.executeQuery();
            ArrayList<Artista> vetArtista = new ArrayList<Artista>();
            while (rsArtistas.next()) {
                Artista artista = new Artista();
                artista.setId(rsArtistas.getInt("id"));
                artista.setNome(rsArtistas.getString("nome"));
                vetArtista.add(artista);
            }
            album.setArtistas(vetArtista);
            vetAlbum.add(album);
        }
        conexao.close();
        return vetAlbum;
    }

    public Album obter(int id) throws SQLException {
        Album album = new Album();
        String sql = "SELECT capa, album.id as album_id, titulo, data_lancamento, genero.id as genero_id, genero.nome as genero_nome FROM album INNER JOIN genero ON genero.id = album.genero_id where album.id = ?;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        instrucaoSQL.setInt(1, id);
        ResultSet rs = instrucaoSQL.executeQuery();
        if (rs.next()) {
            album.setId(rs.getInt("album_id"));
            album.setTitulo(rs.getString("titulo"));
            album.setDataLancamento(rs.getDate("data_lancamento").toLocalDate());
            album.setCapa(rs.getBytes("capa"));
            Genero genero = new Genero();
            genero.setId(rs.getInt("genero_id"));
            genero.setNome(rs.getString("genero_nome"));
            album.setGenero(genero);
        }
        conexao.close();
        return album;
    }

    public boolean salvar(Album album) throws SQLException {
        String sql = "INSERT INTO album (titulo, data_lancamento, genero_id, capa) VALUES (?, ?, ?, ?) RETURNING id;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        instrucaoSQL.setString(1, album.getTitulo());
        instrucaoSQL.setDate(2, Date.valueOf(album.getDataLancamento()));
        instrucaoSQL.setInt(3, album.getGenero().getId());
        instrucaoSQL.setBytes(4, album.getCapa());
        ResultSet rs = instrucaoSQL.executeQuery();
        if (rs.next()) {
            album.setId(rs.getInt("id"));
        }
        conexao.close();
        return album.getId() != 0;

    }

    public boolean salvar(Album album, String diretorio) throws SQLException, IOException {
        String sql = "INSERT INTO album (titulo, data_lancamento, genero_id, capa) VALUES (?, ?, ?, ?) RETURNING id;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        instrucaoSQL.setString(1, album.getTitulo());
        instrucaoSQL.setDate(2, Date.valueOf(album.getDataLancamento()));
        instrucaoSQL.setInt(3, album.getGenero().getId());

        File file = new File(diretorio);
        FileInputStream fis = new FileInputStream(file);
        instrucaoSQL.setBinaryStream(4, fis, file.length());
        album.setCapa(fis.readAllBytes());

        ResultSet rs = instrucaoSQL.executeQuery();
        if (rs.next()) {
            album.setId(rs.getInt("id"));
        }
        conexao.close();
        return album.getId() != 0;

    }

    public void deletar(int id) throws SQLException {
        String sql = "BEGIN;" +
                "DELETE FROM album_artista WHERE album_id = ? ;" +
                "DELETE FROM album_musica WHERE album_id = ? ;" +
                "DELETE FROM album WHERE id = ?;" +
                "COMMIT;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        instrucaoSQL.setInt(1, id);
        instrucaoSQL.setInt(2, id);
        instrucaoSQL.setInt(3, id);

        instrucaoSQL.execute();
        conexao.close();
    }

    public boolean atualizar(Album album) throws SQLException {
        String sql = "UPDATE album SET titulo = ?, data_lancamento = ?, genero_id = ?, capa = ? where id = ?;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        instrucaoSQL.setString(1, album.getTitulo());
        instrucaoSQL.setDate(2, Date.valueOf(album.getDataLancamento()));
        instrucaoSQL.setInt(3, album.getGenero().getId());
        instrucaoSQL.setBytes(4, album.getCapa());
        instrucaoSQL.setInt(5, album.getId());
        int nroLinhasAfetadas = instrucaoSQL.executeUpdate();
        conexao.close();
        return nroLinhasAfetadas == 1;

    }

    public boolean adicionarArtista(int album_id, int artista_id) throws SQLException {
        String sql = "INSERT INTO album_artista (artista_id, album_id) VALUES (?, ?)";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        instrucaoSQL.setInt(1, artista_id);
        instrucaoSQL.setInt(2, album_id);
        int nroLinhasAfetadas = 0;
        try {
            nroLinhasAfetadas = instrucaoSQL.executeUpdate();
        } catch (SQLException e) {
            conexao.close();
            System.out.println("deu xabum!");
            return false;
        }
        conexao.close();
        return nroLinhasAfetadas == 1;



    }

}
