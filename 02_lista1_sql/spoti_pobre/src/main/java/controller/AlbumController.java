package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import io.javalin.config.JavalinConfig;
import negocio.Album;
import negocio.Genero;
import persistencia.AlbumDAO;
import persistencia.ArtistaDAO;
import persistencia.GeneroDAO;

/**
 * AlbumController
 */
public class AlbumController extends  Controller {

    public AlbumController(JavalinConfig config) {
         config.routes.get("/albuns/", ctx -> {
            
            ArrayList<Album> vet = new AlbumDAO().listar();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("vetAlbum", vet);
            ctx.render(PATH_TEMPLATES+"albuns/index.html", map);
        });

        config.routes.get("/albuns/tela_adicionar", ctx -> {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("vetGenero", new GeneroDAO().listar());
            ctx.render(PATH_TEMPLATES+"albuns/tela_adicionar.html", map);
        });

        config.routes.get("/albuns/tela_alterar/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Album album = new AlbumDAO().obter(id);
            Map<String, Object> map = new HashMap<>();
            map.put("album", album);
            ArrayList<Genero> vetGenero = new GeneroDAO().listar();
            for (Genero genero : vetGenero) {
                // System.out.println(genero.getId());
                // System.out.println(album.);
                if (genero.getId() == album.getGenero().getId()) {
                    System.out.println("entrou!");
                    genero.setEhGenero(true);
                }
            }
            map.put("vetGenero", vetGenero);
         
            ctx.render(PATH_TEMPLATES+"albuns/tela_alterar.html", map);
        });

        config.routes.post("/albuns/alterar", ctx -> {
            int id = Integer.parseInt(ctx.formParam("id"));
            String titulo = ctx.formParam("titulo");
            Album album = new AlbumDAO().obter(id);
            album.setId(id);
            album.setTitulo(titulo);
            album.setDataLancamento(LocalDate.parse(ctx.formParam("data_lancamento")));
            album.setGenero(new GeneroDAO().obter(Integer.parseInt(ctx.formParam("genero_id"))));
            if (ctx.formParam("manter_capa") == null) {
                album.setCapa((ctx.uploadedFile("capa").content().readAllBytes() != null) ? ctx.uploadedFile("capa").content().readAllBytes() : null);
            }
            boolean resultado = new AlbumDAO().atualizar(album);
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

        config.routes.get("/albuns/tela_adicionar_artista/{id}", ctx -> {
            Map<String, Object> map = new HashMap<>();
            map.put("vetArtista", new ArtistaDAO().listar());
            map.put("id", Integer.parseInt(ctx.pathParam("id")));
            ctx.render(PATH_TEMPLATES+"albuns/tela_adicionar_artista.html", map);
        });

         config.routes.get("/albuns/tela_remover_artista/{id}", ctx -> {
            Map<String, Object> map = new HashMap<>();
            map.put("vetArtista", new ArtistaDAO().listar(Integer.parseInt(ctx.pathParam("id"))));
            map.put("id", Integer.parseInt(ctx.pathParam("id")));
            ctx.render(PATH_TEMPLATES+"albuns/tela_remover_artista.html", map);
        });

         config.routes.post("/albuns/remover_artista", ctx -> {
            int album_id = Integer.parseInt(ctx.formParam("id"));
            Iterator<String> iterator = ctx.formParams("vetArtista").iterator();   
            while (iterator.hasNext()) {
                new ArtistaDAO().removerArtista(Integer.parseInt(iterator.next()), album_id);
            }
            ctx.redirect("/albuns/");
        });

        config.routes.post("/albuns/adicionar_artista", ctx -> {
            int album_id = Integer.parseInt(ctx.formParam("id"));
            int artista_id = Integer.parseInt(ctx.formParam("artista_id"));            
            boolean resultado = new AlbumDAO().adicionarArtista(album_id, artista_id);
            if (resultado == false) {
                ctx.html("<script>alert('Artista ja cadastrado neste mesmo album'); location.href='/albuns/';</script>");
            } else {
                ctx.redirect("/albuns/");
            }

        });

        config.routes.post("/albuns/adicionar", ctx -> {
            String titulo = ctx.formParam("titulo");
            LocalDate dataLancamento = LocalDate.parse(ctx.formParam("data_lancamento"));
            Genero genero = new GeneroDAO().obter(Integer.parseInt(ctx.formParam("genero_id")));
            Album album = new Album();
            album.setDataLancamento(dataLancamento);
            album.setTitulo(titulo);
            album.setGenero(genero);
            album.setCapa((ctx.uploadedFile("capa").content().readAllBytes() != null) ? ctx.uploadedFile("capa").content().readAllBytes() : null);
            boolean resultado = new AlbumDAO().salvar(album);
            if (resultado) {
                ctx.redirect("/albuns/");
            } else {
                ctx.html("deu xabum");
            }
        });
    }

}
