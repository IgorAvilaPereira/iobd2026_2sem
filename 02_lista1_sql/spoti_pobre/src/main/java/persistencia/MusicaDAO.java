package persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import negocio.Artista;
import negocio.Musica;

// DAO: Data Acess Object
public class MusicaDAO {

    public ArrayList<Musica> listar() throws SQLException {
        ArrayList<Musica> vetMusica = new ArrayList<Musica>();
        String sql = "SELECT * FROM musica ORDER BY id;";
        try (Connection conexao = new ConexaoPostgreSQL().getConexao()) {
            PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
            ResultSet rs = instrucaoSQL.executeQuery();
            while (rs.next()) {
                Musica musica = new Musica();
                musica.setId(rs.getInt("id"));
                musica.setNome(rs.getString("nome"));
                musica.setDuracao(rs.getTime("duracao").toLocalTime());
                musica.setDataHoraLancamento(rs.getTimestamp("data_hora_lancamento").toLocalDateTime());
                musica.setArquivo(rs.getBytes("arquivo"));
                musica.setMaterialTipo(rs.getString("material_tipo"));
                vetMusica.add(musica);
            }
        }
        return vetMusica;
    }

    public Musica obter(int id) throws SQLException {
        Musica musica = new Musica();
        String sql = "SELECT * FROM musica where id = ?;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        instrucaoSQL.setInt(1, id);
        ResultSet rs = instrucaoSQL.executeQuery();
        if (rs.next()) {
           musica.setId(rs.getInt("id"));
            musica.setNome(rs.getString("nome"));
            musica.setDuracao(rs.getTime("duracao").toLocalTime());
            musica.setDataHoraLancamento(rs.getTimestamp("data_hora_lancamento").toLocalDateTime());
            musica.setArquivo(rs.getBytes("arquivo"));
            musica.setMaterialTipo(rs.getString("material_tipo"));
        }
        conexao.close();
        return musica;
    }

    public boolean salvar(Musica musica) throws SQLException {
        String sql = "INSERT INTO musica (nome, data_hora_lancamento, duracao, arquivo, material_tipo) VALUES (?, ?, ?, ?, ?) RETURNING id;";
        try (Connection conexao = new ConexaoPostgreSQL().getConexao()) {
            PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
            instrucaoSQL.setString(1, musica.getNome());
            instrucaoSQL.setTimestamp(2, Timestamp.valueOf(musica.getDataHoraLancamento()));
            instrucaoSQL.setTime(3, Time.valueOf(musica.getDuracao()));
            instrucaoSQL.setBytes(4, musica.getArquivo());
            instrucaoSQL.setString(5, musica.getMaterialTipo());
            ResultSet rs = instrucaoSQL.executeQuery();
            if (rs.next()) {
                musica.setId(rs.getInt("id"));
            }
        }
        return musica.getId() != 0;

    }

    public boolean salvar(Musica musica, String diretorio) throws SQLException, IOException {
        String sql = "INSERT INTO musica (nome, data_hora_lancamento, duracao, arquivo, materia_tipo) VALUES (?, ?, ?, ?, ?) RETURNING id;";
        try (Connection conexao = new ConexaoPostgreSQL().getConexao()) {
            PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
            instrucaoSQL.setString(1, musica.getNome());
            instrucaoSQL.setTimestamp(2, Timestamp.valueOf(musica.getDataHoraLancamento()));
            instrucaoSQL.setTime(3, Time.valueOf(musica.getDuracao()));
            
            File file = new File(diretorio);
            FileInputStream fis = new FileInputStream(file);
            instrucaoSQL.setBinaryStream(4, fis, file.length());
            musica.setArquivo(fis.readAllBytes());
            instrucaoSQL.setString(5, musica.getMaterialTipo());
            
            ResultSet rs = instrucaoSQL.executeQuery();
            if (rs.next()) {
                musica.setId(rs.getInt("id"));
            }
        }
        return musica.getId() != 0;

    }

    public void deletar(int id) throws SQLException {
        String sql = "BEGIN;" +
                "DELETE FROM reproducao WHERE musica_id = ? ;" +
                "DELETE FROM playlist_musica WHERE musica_id = ? ;" +
                "DELETE FROM album_musica WHERE musica_id = ?;" +
                "DELETE FROM musica WHERE id = ?; COMMIT;";
        try (Connection conexao = new ConexaoPostgreSQL().getConexao()) {
            PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
            instrucaoSQL.setInt(1, id);
            instrucaoSQL.setInt(2, id);
            instrucaoSQL.setInt(3, id);
            instrucaoSQL.setInt(4, id);            
            instrucaoSQL.execute();
        }
    }

    public boolean atualizar(Musica musica) throws SQLException {
        String sql = "UPDATE musica SET nome = ?, data_hora_lancamento = ?, arquivo = ?, material_tipo = ?, duracao = ? where id = ?;";
        int nroLinhasAfetadas;
        try (Connection conexao = new ConexaoPostgreSQL().getConexao()) {
            PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
            instrucaoSQL.setString(1, musica.getNome());
            instrucaoSQL.setTimestamp(2, Timestamp.valueOf(musica.getDataHoraLancamento()));
            instrucaoSQL.setBytes(3, musica.getArquivo());
            instrucaoSQL.setString(4, musica.getMaterialTipo());
            instrucaoSQL.setTime(5, Time.valueOf(musica.getDuracao()));
            instrucaoSQL.setInt(6, musica.getId());
            
            nroLinhasAfetadas = instrucaoSQL.executeUpdate();
        }
        return nroLinhasAfetadas == 1;

    }

    public List<Musica> listar(int album_id) {
        ArrayList<Musica> vetMusica = new ArrayList<Musica>();
        String sql = "SELECT * FROM musica where id in (select musica_id from album_musica where album_id = ?) ORDER BY id;";
        try (Connection conexao = new ConexaoPostgreSQL().getConexao()) {
            PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
            instrucaoSQL.setInt(1, album_id);
            ResultSet rs = instrucaoSQL.executeQuery();
            while (rs.next()) {
                Musica musica = new Musica();
                 musica.setId(rs.getInt("id"));
                musica.setNome(rs.getString("nome"));
                musica.setDuracao(rs.getTime("duracao").toLocalTime());
                musica.setDataHoraLancamento(rs.getTimestamp("data_hora_lancamento").toLocalDateTime());
                musica.setArquivo(rs.getBytes("arquivo"));
                musica.setMaterialTipo(rs.getString("material_tipo"));
                vetMusica.add(musica);
            }
            conexao.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return vetMusica;
    }

    public boolean  removerMusica(int musica_id, int album_id) throws SQLException  {
         String sql = "DELETE FROM album_musica where musica_id = ? and album_id = ?;";
         int nroLinhasAfetadas;
        try (Connection conexao = new ConexaoPostgreSQL().getConexao()) {
            PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
            instrucaoSQL.setInt(1, musica_id);
            instrucaoSQL.setInt(2, album_id);
            nroLinhasAfetadas = instrucaoSQL.executeUpdate();
        }
        return nroLinhasAfetadas == 1;
    }


}
