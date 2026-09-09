package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


// DAO: Data Acess Object
public class LogDAO {   

    public static boolean salvar(String comentario) throws SQLException {
        String sql = "INSERT INTO backup.log (comentario) VALUES (?);";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        instrucaoSQL.setString(1, comentario);
        int nroLinhasAfetadas = instrucaoSQL.executeUpdate();
        conexao.close();
        return nroLinhasAfetadas != 0;

    }

 

}
