package apresentacao;

import java.sql.SQLException;

import persistencia.LogDAO;

public class Main {
    public static void main(String[] args) throws SQLException {
        LogDAO.salvar("Fazendo meu teste");
        
    }
}