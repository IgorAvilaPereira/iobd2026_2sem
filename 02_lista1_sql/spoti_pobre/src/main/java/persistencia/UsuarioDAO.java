package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import negocio.Usuario;

// DAO: Data Acess Object
public class UsuarioDAO {

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

}
