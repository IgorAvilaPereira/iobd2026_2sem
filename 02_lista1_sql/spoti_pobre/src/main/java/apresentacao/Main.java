package apresentacao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import io.javalin.Javalin;
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

        Javalin app = Javalin.create(config -> {
            config.routes.get("/", ctx -> {
                ArrayList<Usuario> vet = new UsuarioDAO().listar();
                String html = "<h1> Site do Igor </h1> <ul>";
                for (Usuario usuario : vet) {
                    html += "<li>" + usuario.getNome()+"</li>";
                }
                html += "</ul>";        
                ctx.html(html);
            });
        }).start(7070);

    }
}