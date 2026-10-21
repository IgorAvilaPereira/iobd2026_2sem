package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import io.javalin.config.JavalinConfig;
import negocio.Usuario;
import persistencia.UsuarioDAO;

/**
 * UsuarioController
 */
public class UsuarioController extends Controller {

    public UsuarioController(JavalinConfig config) {
        config.routes.get("/", ctx -> {
            ArrayList<Usuario> vet = new UsuarioDAO().listar();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("vetUsuario", vet);
            ctx.render(PATH_TEMPLATES+"index.html", map);
        });

        config.routes.get("/usuarios/tela_adicionar", ctx -> {
            ctx.render(PATH_TEMPLATES+"usuarios/tela_adicionar.html");
        });

        config.routes.get("/usuarios/tela_alterar/{hash_id}", ctx -> {
            Usuario usuario = new UsuarioDAO().obter(UUID.fromString(ctx.pathParam("hash_id")));
            Map<String, Object> map = new HashMap<>();
            map.put("usuario", usuario);
            ctx.render(PATH_TEMPLATES+"usuarios/tela_alterar.html", map);
        });

        config.routes.post("/usuarios/alterar", ctx -> {
            UUID uuid = UUID.fromString(ctx.formParam("hashID"));
            String nome = ctx.formParam("nome");
            String email = ctx.formParam("email");
            String senha = ctx.formParam("senha");
            String dataNascimento = ctx.formParam("data_nascimento");
            Usuario usuarioNovo = new UsuarioDAO().obter(uuid);
            usuarioNovo.setNome(nome);
            usuarioNovo.setEmail(email);
            usuarioNovo.setSenha((senha.isBlank() || senha.isEmpty()) ? null : senha);
            usuarioNovo.setDataNascimento(LocalDate.parse(dataNascimento));
            boolean resultado = new UsuarioDAO().atualizar(usuarioNovo);
            if (resultado) {
                ctx.redirect("/");
            } else {
                ctx.html("deu xabum");
            }
        });

        config.routes.get("/usuarios/excluir/{hashID}", ctx -> {
            Usuario usuario = new UsuarioDAO().obter(UUID.fromString(ctx.pathParam("hashID")));
            new UsuarioDAO().deletar(usuario.getId());
            ctx.redirect("/");
        });

        config.routes.post("/usuarios/adicionar", ctx -> {
            String nome = ctx.formParam("nome");
            String email = ctx.formParam("email");
            String senha = ctx.formParam("senha");
            String dataNascimento = ctx.formParam("data_nascimento");
            Usuario usuarioNovo = new Usuario();
            usuarioNovo.setNome(nome);
            usuarioNovo.setEmail(email);
            usuarioNovo.setSenha(senha);
            usuarioNovo.setDataNascimento(LocalDate.parse(dataNascimento));

            boolean resultado = new UsuarioDAO().salvar(usuarioNovo);

            if (resultado) {
                ctx.redirect("/");
            } else {
                ctx.html("deu xabum");
            }
        });
    }

}
