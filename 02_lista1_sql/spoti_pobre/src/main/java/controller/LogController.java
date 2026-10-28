package controller;

import java.util.UUID;

import io.javalin.config.JavalinConfig;
import negocio.Log;
import persistencia.LogDAO;

public class LogController extends Controller {

    public LogController(JavalinConfig config) {
        config.routes.get("/logs/{id}", ctx -> {
            try {

                ctx.html(new LogDAO().obter(UUID.fromString(ctx.pathParam("id"))).toString());
            } catch (Exception e) {
                ctx.html("camaro digitou errado");
            }
        });

          config.routes.get("/logs/", ctx -> {
            try {

                ctx.html(new LogDAO().listar().toString());
            } catch (Exception e) {
                ctx.html("camaro digitou errado");
            }
        });
    }

}
