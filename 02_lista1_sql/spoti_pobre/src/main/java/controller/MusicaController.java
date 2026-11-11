package controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.javalin.config.JavalinConfig;
import negocio.Musica;
import persistencia.MusicaDAO;

/**
 * MusicaController
 */
public class MusicaController extends  Controller {

    public MusicaController(JavalinConfig config) {
         config.routes.get("/musicas/", ctx -> {
            
            ArrayList<Musica> vet = new MusicaDAO().listar();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("vetMusica", vet);
            ctx.render(PATH_TEMPLATES+"musicas/index.html", map);
        });

        config.routes.get("/musicas/tela_adicionar", ctx -> {
            Map<String, Object> map = new HashMap<String, Object>();       
            ctx.render(PATH_TEMPLATES+"musicas/tela_adicionar.html", map);
        });

        config.routes.get("/musicas/tela_alterar/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Musica musica = new MusicaDAO().obter(id);
            Map<String, Object> map = new HashMap<>();
            map.put("musica", musica);
         
            ctx.render(PATH_TEMPLATES+"musicas/tela_alterar.html", map);
        });

        config.routes.post("/musicas/alterar", ctx -> {
            int id = Integer.parseInt(ctx.formParam("id"));
            String nome = ctx.formParam("nome");
            Musica musica = new MusicaDAO().obter(id);
            musica.setId(id);
            musica.setNome(nome);
            musica.setDataHoraLancamento(LocalDateTime.parse(ctx.formParam("data_hora_lancamento")));
            musica.setDuracao(LocalTime.parse(ctx.formParam("duracao"))); // corrigir


            if (ctx.formParam("manter_arquivo") == null) {
                musica.setArquivo((ctx.uploadedFile("arquivo").content().readAllBytes() != null) ? ctx.uploadedFile("arquivo").content().readAllBytes() : null);
                musica.setMaterialTipo((ctx.uploadedFile("arquivo").contentType() != null) ? ctx.uploadedFile("arquivo").contentType() : null);
            }
            boolean resultado = new MusicaDAO().atualizar(musica);
            if (resultado) {
                ctx.redirect("/musicas/");
            } else {
                ctx.html("deu xabum");
            }
        });

        config.routes.get("/musicas/excluir/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            new MusicaDAO().deletar(id);
            ctx.redirect("/musicas/");
        });


        config.routes.post("/musicas/adicionar", ctx -> {
            String nome = ctx.formParam("nome");
            LocalDateTime dataLancamento = LocalDateTime.parse(ctx.formParam("data_hora_lancamento"));
            Musica musica = new Musica();
            musica.setDataHoraLancamento(dataLancamento);
            musica.setNome(nome);
            musica.setArquivo((ctx.uploadedFile("arquivo").content().readAllBytes() != null) ? ctx.uploadedFile("arquivo").content().readAllBytes() : null);
            // System.out.println(ctx.uploadedFile("arquivo").contentType());
            musica.setMaterialTipo(ctx.uploadedFile("arquivo").contentType());
            musica.setDuracao(LocalTime.parse(ctx.formParam("duracao"))); // corrigir

            boolean resultado = new MusicaDAO().salvar(musica);
            if (resultado) {
                ctx.redirect("/musicas/");
            } else {
                ctx.html("deu xabum");
            }
        });
    }

}
