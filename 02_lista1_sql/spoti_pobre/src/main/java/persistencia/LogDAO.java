package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import negocio.Log;

public class LogDAO {

    public boolean atualizar(Log log) throws SQLException {
         try (Connection conexao = new ConexaoPostgreSQL().getConexao()) {
            String sql = "UPDATE backup.log SET comentario = ?, detalhes = ?::jsonb WHERE id::text = ?;";
            try (PreparedStatement instrucao = conexao.prepareStatement(sql)) {
                instrucao.setString(1, log.getComentario());
                instrucao.setString(2, log.getDetalhes().toString());
                instrucao.setString(3, log.getId().toString());
                return  instrucao.executeUpdate() == 1;
            }
        }
  
    } 

  public Log obter(UUID id) throws SQLException {
    Log log = new Log();
          try (Connection conexao = new ConexaoPostgreSQL().getConexao()) {
            String sql = "SELECT * FROM backup.log where id::text = ?;";
            try (PreparedStatement instrucao = conexao.prepareStatement(sql)) {
                instrucao.setString(1, id.toString());
                ResultSet rs = instrucao.executeQuery();
                while (rs.next()) {
                    log.setId(UUID.fromString(rs.getString("id")));
                    log.setComentario(rs.getString("comentario"));
                    log.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());
                    log.setDetalhes(JsonParser.parseString(rs.getString("detalhes")).getAsJsonObject());
                }
            }
        }
        return log;
    }


    public List<Log> listar() throws SQLException {
        List<Log> vetLog = new ArrayList<Log>();
          try (Connection conexao = new ConexaoPostgreSQL().getConexao()) {
            String sql = "SELECT * FROM backup.log ORDER BY data_hora DESC;";
            try (PreparedStatement instrucao = conexao.prepareStatement(sql)) {
                ResultSet rs = instrucao.executeQuery();
                while (rs.next()) {
                    Log log = new Log();
                    log.setId(UUID.fromString(rs.getString("id")));
                    log.setComentario(rs.getString("comentario"));
                    log.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());
                    log.setDetalhes(JsonParser.parseString(rs.getString("detalhes")).getAsJsonObject());
                    vetLog.add(log);
                }
            }
        }
        return vetLog;
    }

    public boolean remover(UUID id) throws SQLException {
         try (Connection conexao = new ConexaoPostgreSQL().getConexao()) {
            String sql = "DELETE FROM backup.log where id::text = ?;";
            try (PreparedStatement instrucao = conexao.prepareStatement(sql)) {
                instrucao.setString(1, id.toString());
                return  instrucao.executeUpdate() == 1;
            }
        }
    }

    public boolean inserir(Log log) throws SQLException{
        try (Connection conexao = new ConexaoPostgreSQL().getConexao()) {
            String sql = "INSERT INTO backup.log (comentario, detalhes) VALUES (?,?::jsonb) RETURNING id, data_hora;";
            try (PreparedStatement instrucao = conexao.prepareStatement(sql)) {
                instrucao.setString(1, log.getComentario());
                instrucao.setString(2, log.getDetalhes().toString());

                ResultSet rs = instrucao.executeQuery();
                if (rs.next()) {
                    log.setId(UUID.fromString(rs.getString("id")));
                    log.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());
                }
            }
        }
        return log.getId() != null;

    }

}
