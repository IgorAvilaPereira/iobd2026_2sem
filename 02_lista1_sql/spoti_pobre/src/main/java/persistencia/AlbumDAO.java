package persistencia;

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
        String sql = "SELECT album.id, album.titulo, album.data_lancamento, genero.id as genero_id, genero.nome as genero_nome FROM album JOIN genero ON album.genero_id = genero.id;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        ResultSet rs = instrucaoSQL.executeQuery();
        while (rs.next()) {
            Album album = new Album();
            album.setId(rs.getInt("id"));
            album.setTitulo(rs.getString("titulo"));
            album.setDataLancamento(rs.getDate("data_lancamento").toLocalDate());
            Genero genero = new Genero();
            genero.setId(rs.getInt("genero_id"));
            genero.setNome(rs.getString("genero_nome"));
            album.setGenero(genero);
            PreparedStatement instrucaoArtistasSQL = conexao.prepareStatement("SELECT id, nome FROM artista join album_artista on artista.id = album_artista.artista_id where album_artista.album_id = ? ");
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
        String sql = "SELECT * FROM album where id = ?;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        instrucaoSQL.setInt(1, id);
        ResultSet rs = instrucaoSQL.executeQuery();
        if (rs.next()) {
            album.setId(rs.getInt("id"));
            // album.setNome(rs.getString("nome"));
        }
        conexao.close();
        return album;
    }

    public boolean salvar(Album album) throws SQLException {
        String sql = "INSERT INTO album (nome) VALUES (?) RETURNING id;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        // instrucaoSQL.setString(1, album.getNome());
        ResultSet rs = instrucaoSQL.executeQuery();
        if (rs.next()) {
            album.setId(rs.getInt("id"));
        }
        conexao.close();
        return album.getId() != 0;

    }

    public void deletar(int id) throws SQLException {
        String sql = "BEGIN;" +
                "DELETE FROM album_album WHERE album_id = ? ;" +
                "DELETE FROM album WHERE id = ?;" +
                "COMMIT;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        instrucaoSQL.setInt(1, id);
        instrucaoSQL.setInt(2, id);
        instrucaoSQL.execute();
        conexao.close();
    }

    public boolean atualizar(Album album) throws SQLException {
        String sql = "UPDATE album SET nome = ? where id = ?;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        // instrucaoSQL.setString(1, album.getNome());
        instrucaoSQL.setInt(2, album.getId());

        int nroLinhasAfetadas = instrucaoSQL.executeUpdate();
        conexao.close();
        return nroLinhasAfetadas == 1;

    }

}
