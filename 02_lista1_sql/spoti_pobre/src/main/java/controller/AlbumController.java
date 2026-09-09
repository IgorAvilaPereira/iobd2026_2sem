package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.javalin.config.JavalinConfig;
import negocio.Album;
import persistencia.AlbumDAO;

/**
 * AlbumController
 */
public class AlbumController {

    public AlbumController(JavalinConfig config) {
         config.routes.get("/albuns/", ctx -> {
            
            ArrayList<Album> vet = new AlbumDAO().listar();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("vetAlbum", vet);
            ctx.render("/templates/albuns/index.html", map);
        });

        config.routes.get("/albuns/tela_adicionar", ctx -> {
            ctx.render("/templates/albuns/tela_adicionar.html");
        });

        config.routes.get("/albuns/tela_alterar/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Album artista = new AlbumDAO().obter(id);
            Map<String, Object> map = new HashMap<>();
            map.put("artista", artista);
            ctx.render("/templates/albuns/tela_alterar.html", map);
        });

        config.routes.post("/albuns/alterar", ctx -> {
            int id = Integer.parseInt(ctx.formParam("id"));
            String nome = ctx.formParam("nome");
            Album artistaNovo = new Album();
            artistaNovo.setId(id);
            // artistaNovo.setNome(nome);
            boolean resultado = new AlbumDAO().atualizar(artistaNovo);
            if (resultado) {
                ctx.redirect("/albuns/");
            } else {
                ctx.html("deu xabum");
            }
        });

        config.routes.get("/albuns/excluir/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            new AlbumDAO().deletar(id);
            ctx.redirect("/albuns/");
        });

        config.routes.post("/albuns/adicionar", ctx -> {
            String nome = ctx.formParam("nome");
            Album artistaNovo = new Album();
            // artistaNovo.setNome(nome);
            boolean resultado = new AlbumDAO().salvar(artistaNovo);
            if (resultado) {
                ctx.redirect("/albuns/");
            } else {
                ctx.html("deu xabum");
            }
        });
    }

}
