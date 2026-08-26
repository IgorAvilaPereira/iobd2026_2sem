package apresentacao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.rendering.template.JavalinMustache;
import negocio.Usuario;
import persistencia.UsuarioDAO;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Usuario novo_usuario = new Usuario();
        // novo_usuario.setDataNascimento(LocalDate.of(1987, 01, 20));
        // novo_usuario.setNome("Joao");
        // novo_usuario.setEmail("joao@riogrande.ifrs.edu.br");
        // novo_usuario.setSenha("123");
        // new UsuarioDAO().salvar(novo_usuario);
        // new UsuarioDAO().deletar(22);
        // Usuario vanessa = new UsuarioDAO().obter(21);
        // vanessa.setNome("Vanessa Pereira");
        // new UsuarioDAO().atualizar(vanessa);
        // new UsuarioDAO().listar().forEach(u ->
        // System.out.println(u.getId()+";"+u.getNome()));

        Javalin.create(config -> {
            config.fileRenderer(new JavalinMustache());
            // config.staticFiles.add("/static", Location.CLASSPATH);
            config.routes.get("/", ctx -> {
                ArrayList<Usuario> vet = new UsuarioDAO().listar();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("vetUsuario", vet);
                ctx.render("/templates/index.html", map);
            });

            config.routes.get("/usuarios/tela_adicionar", ctx -> {
                ctx.render("/templates/usuarios/tela_adicionar.html");
            });

            config.routes.get("/usuarios/tela_alterar/{id}", ctx -> {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Usuario usuario = new UsuarioDAO().obter(id);
                Map<String, Object> map = new HashMap<>();
                map.put("usuario", usuario);
                ctx.render("/templates/usuarios/tela_alterar.html", map);
            });

            config.routes.post("/usuarios/alterar", ctx -> {
                int id = Integer.parseInt(ctx.formParam("id"));
                String nome = ctx.formParam("nome");
                String email = ctx.formParam("email");
                String senha = ctx.formParam("senha");
                String dataNascimento = ctx.formParam("data_nascimento");
                Usuario usuarioNovo = new Usuario();
                usuarioNovo.setId(id);
                usuarioNovo.setNome(nome);
                usuarioNovo.setEmail(email);
                usuarioNovo.setSenha((senha.isBlank() || senha.isEmpty()) ? null : senha);
                usuarioNovo.setDataNascimento(LocalDate.parse(dataNascimento));
                boolean resultado = new UsuarioDAO().atualizar(usuarioNovo);
                if (resultado) {
                    ctx.redirect("/");
                }
                else {
                    ctx.html("deu xabum");
                }
            });



            config.routes.get("/usuarios/excluir/{id}", ctx -> {
                int id = Integer.parseInt(ctx.pathParam("id"));
                new UsuarioDAO().deletar(id);
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
                }
                else {
                    ctx.html("deu xabum");
                }
            });

            config.routes.get("/janaiton", ctx -> {
                ctx.html("testando.");
            });

        }).start(7070);

    }
}