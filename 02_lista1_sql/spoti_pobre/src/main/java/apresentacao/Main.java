package apresentacao;

import java.sql.SQLException;
import java.time.LocalDate;

import negocio.Usuario;
import persistencia.UsuarioDAO;

public class Main {
    public static void main(String[] args) throws SQLException {
        Usuario igor = new Usuario();
        igor.setDataNascimento(LocalDate.of(1987, 01, 20));
        igor.setNome("Igor Pereira");
        igor.setEmail("igor.pereira@riogrande.ifrs.edu.br");
        igor.setSenha("123");

        new UsuarioDAO().salvar(igor);
    }
}