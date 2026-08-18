package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import negocio.Usuario;

// DAO: Data Acess Object
public class UsuarioDAO {

    public ArrayList<Usuario> listar() throws SQLException {
        ArrayList<Usuario> vetUsuario = new ArrayList<Usuario>();
        String sql = "SELECT * FROM usuario ORDER BY id;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        ResultSet rs = instrucaoSQL.executeQuery();
        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt("id"));
            usuario.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
            usuario.setEmail(rs.getString("email"));
            usuario.setNome(rs.getString("nome"));
            // usuario.setSenha(rs.getString("senha"));
            vetUsuario.add(usuario);
        }
        conexao.close();
        return vetUsuario;
    }

    public Usuario obter(int id) throws SQLException {
        Usuario usuario = new Usuario();
        String sql = "SELECT * FROM usuario where id = ?;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        instrucaoSQL.setInt(1, id);
        ResultSet rs = instrucaoSQL.executeQuery();
        if (rs.next()) {
            usuario.setId(rs.getInt("id"));
            usuario.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
            usuario.setEmail(rs.getString("email"));
            usuario.setNome(rs.getString("nome"));
            usuario.setSenha(rs.getString("senha"));
        }
        conexao.close();
        return usuario;
    }

    public void salvar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (email, senha, nome, data_nascimento) VALUES (?,md5(?),?,?);";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        instrucaoSQL.setString(1, usuario.getEmail());
        instrucaoSQL.setString(2, usuario.getSenha());
        instrucaoSQL.setString(3, usuario.getNome());
        instrucaoSQL.setDate(4, Date.valueOf(usuario.getDataNascimento()));
        instrucaoSQL.execute();
        conexao.close();

    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id = ?;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        instrucaoSQL.setInt(1, id);
        instrucaoSQL.execute();
        conexao.close();
    }

    public void atualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET email = ?, senha = ?, nome = ?, data_nascimento = ? where id = ?;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        instrucaoSQL.setString(1, usuario.getEmail());
        instrucaoSQL.setString(2, usuario.getSenha());
        instrucaoSQL.setString(3, usuario.getNome());
        instrucaoSQL.setDate(4, Date.valueOf(usuario.getDataNascimento()));
        instrucaoSQL.setInt(5, usuario.getId());
        instrucaoSQL.execute();
        conexao.close();

    }

}
