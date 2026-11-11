package negocio;

import java.time.LocalDateTime;
import java.time.LocalTime;

import controller.Controller;

public class Musica {
    private int id;
    private String nome;
    private LocalTime duracao;
    private LocalDateTime dataHoraLancamento;
    private byte[] arquivo;
        private String materialTipo;


         public void setMaterialTipo(String materialTipo) {
        this.materialTipo = materialTipo;
    }

    public String getMaterialTipo() {
        return materialTipo;
    }

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
    public LocalTime getDuracao() {
        return duracao;
    }
    public void setDuracao(LocalTime duracao) {
        this.duracao = duracao;
    }
    public LocalDateTime getDataHoraLancamento() {
        return dataHoraLancamento;
    }
    public void setDataHoraLancamento(LocalDateTime dataHoraLancamento) {
        this.dataHoraLancamento = dataHoraLancamento;
    }
    public byte[] getArquivo() {
        return arquivo;
    }
    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }


     public String arquivoEncode() {
        if (arquivo != null)
            return Controller.encodeToBase64(arquivo);
        return null;
    }
    
}
