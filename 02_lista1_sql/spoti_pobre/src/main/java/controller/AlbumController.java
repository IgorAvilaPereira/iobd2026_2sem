package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.javalin.config.JavalinConfig;
import negocio.Album;
import negocio.Genero;
import persistencia.AlbumDAO;
import persistencia.GeneroDAO;

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
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("vetGenero", new GeneroDAO().listar());
            ctx.render("/templates/albuns/tela_adicionar.html", map);
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
            String titulo = ctx.formParam("titulo");
            LocalDate dataLancamento = LocalDate.parse(ctx.formParam("data_lancamento"));
            Genero genero = new GeneroDAO().obter(Integer.parseInt(ctx.formParam("genero_id")));
            Album album = new Album();
            album.setDataLancamento(dataLancamento);
            album.setTitulo(titulo);
            album.setGenero(genero);
            boolean resultado = new AlbumDAO().salvar(album);
            if (resultado) {
                ctx.redirect("/albuns/");
            } else {
                ctx.html("deu xabum");
            }
        });
    }

}
