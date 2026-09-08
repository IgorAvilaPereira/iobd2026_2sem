package apresentacao;

import java.sql.SQLException;
import controller.*;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinMustache;

public class Main {
    public static void main(String[] args) throws SQLException {
        Javalin.create(config -> {
            config.fileRenderer(new JavalinMustache());
            // config.staticFiles.add("/static", Location.CLASSPATH);
            new UsuarioController(config);
            new ArtistaController(config);
        }).start(7070);
    }
}