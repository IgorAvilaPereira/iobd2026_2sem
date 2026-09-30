package apresentacao;

import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

import controller.*;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.rendering.template.JavalinMustache;

public class Main {    
    public static void main(String[] args) throws SQLException {
        Javalin.create(config -> {
            config.fileRenderer(new JavalinMustache());
//             config.staticFiles.add(staticFiles -> {
//     staticFiles.hostedPath = "/";                   // change to host files on a subpath, like '/assets'
//     staticFiles.directory = Controller.PATH_PUBLIC;              // the directory where your files are located
//     staticFiles.location = Location.CLASSPATH;      // Location.CLASSPATH (jar) or Location.EXTERNAL (file system)
//     staticFiles.precompressMaxSize = 0;             // max size for pre-compression in bytes (-1 to disable, 0 for all sizes)
//     staticFiles.aliasCheck = null;                  // you can configure this to enable symlinks (= ContextHandler.ApproveAliases())
//     staticFiles.skipFileFunction = req -> false;    // you can use this to skip certain files in the dir, based on the HttpServletRequest
//   });
            new UsuarioController(config);
            new ArtistaController(config);
            new GeneroController(config);
            new AlbumController(config);
        }).start(7070);
    }
}