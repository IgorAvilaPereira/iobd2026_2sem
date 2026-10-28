package apresentacao;

import java.sql.SQLException;
import java.util.UUID;

import com.google.gson.JsonObject;

import negocio.Log;
import persistencia.LogDAO;

public class MainTeste {
    public static void main(String[] args) throws SQLException {
        Log log = new Log();
        log.setComentario("meu teste no java");
        JsonObject objeto = new JsonObject();
        objeto.addProperty("oi", "ola");
        log.setDetalhes(objeto);
        // new LogDAO().inserir(log);
        // new LogDAO().remover(UUID.fromString("f792c310-becc-4b1c-aff7-17a35404446a"));
        // new LogDAO().listar().forEach(p -> System.out.println(p.getDetalhes().get("oi")));

        Log logAtualizar = new LogDAO().obter(UUID.fromString("5aa9c84e-59e7-47af-9fc5-ab98d83ff227"));
        logAtualizar.getDetalhes().addProperty("byebye", "deu top");
        new LogDAO().atualizar(logAtualizar);
        // new LogDAO().remover(logAtualizar.getId());
    }
    
}
