package negocio;

import java.time.LocalDate;
import java.util.UUID;

public class Usuario {
    protected int id;
    protected String nome;
    protected String senha;
    protected String email;
    protected LocalDate dataNascimento;
    protected UUID hashID;
     
    

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public UUID getHashID() {
        return hashID;
    }
    public void setHashID(UUID hashID) {
        this.hashID = hashID;
    }

    

}
