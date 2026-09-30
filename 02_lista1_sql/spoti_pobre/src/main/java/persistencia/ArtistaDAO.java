package persistencia;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import negocio.Artista;

// DAO: Data Acess Object
public class ArtistaDAO {

    public ArrayList<Artista> listar() throws SQLException {
        ArrayList<Artista> vetArtista = new ArrayList<Artista>();
        String sql = "SELECT * FROM artista ORDER BY id;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        ResultSet rs = instrucaoSQL.executeQuery();
        while (rs.next()) {
            Artista artista = new Artista();
            artista.setId(rs.getInt("id"));
            artista.setNome(rs.getString("nome"));
            vetArtista.add(artista);
        }
        conexao.close();
        return vetArtista;
    }

    public Artista obter(int id) throws SQLException {
        Artista artista = new Artista();
        String sql = "SELECT * FROM artista where id = ?;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        instrucaoSQL.setInt(1, id);
        ResultSet rs = instrucaoSQL.executeQuery();
        if (rs.next()) {
            artista.setId(rs.getInt("id"));
            artista.setNome(rs.getString("nome"));
        }
        conexao.close();
        return artista;
    }

    public boolean salvar(Artista artista) throws SQLException {
        String sql = "INSERT INTO artista (nome) VALUES (?) RETURNING id;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        instrucaoSQL.setString(1, artista.getNome());
        ResultSet rs = instrucaoSQL.executeQuery();
        if (rs.next()) {
            artista.setId(rs.getInt("id"));
        }
        conexao.close();
        return artista.getId() != 0;

    }

    public void deletar(int id) throws SQLException {
        String sql = "BEGIN;" +
                "DELETE FROM album_artista WHERE artista_id = ? ;" +
                "DELETE FROM artista WHERE id = ?;" +
                "COMMIT;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        instrucaoSQL.setInt(1, id);
        instrucaoSQL.setInt(2, id);
        instrucaoSQL.execute();
        conexao.close();
    }

    public boolean atualizar(Artista artista) throws SQLException {
        String sql = "UPDATE artista SET nome = ? where id = ?;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        instrucaoSQL.setString(1, artista.getNome());
        instrucaoSQL.setInt(2, artista.getId());

        int nroLinhasAfetadas = instrucaoSQL.executeUpdate();
        conexao.close();
        return nroLinhasAfetadas == 1;

    }

    public ArrayList<Artista> listar(int album_id) throws SQLException {
        ArrayList<Artista> vetArtista = new ArrayList<Artista>();
        String sql = "SELECT * FROM artista where id in (select artista_id from album_artista where album_id = ?) ORDER BY id;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        instrucaoSQL.setInt(1, album_id);
        ResultSet rs = instrucaoSQL.executeQuery();
        while (rs.next()) {
            Artista artista = new Artista();
            artista.setId(rs.getInt("id"));
            artista.setNome(rs.getString("nome"));
            vetArtista.add(artista);
        }
        conexao.close();
        return vetArtista;
     
    }

    public boolean removerArtista(int artista_id, int album_id) throws SQLException {
       String sql = "DELETE FROM album_artista where artista_id = ? and album_id = ?;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        instrucaoSQL.setInt(1, artista_id);
        instrucaoSQL.setInt(2, album_id);
        int nroLinhasAfetadas = instrucaoSQL.executeUpdate();
        conexao.close();
        return nroLinhasAfetadas == 1;
    }

}
