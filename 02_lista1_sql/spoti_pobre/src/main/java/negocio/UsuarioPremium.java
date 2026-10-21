package negocio;

import java.time.LocalDate;

public class UsuarioPremium extends Usuario {
    private String plano;
    private double valorMensal;
    private LocalDate validade;

    public String getPlano() {
        return plano;
    }
    public void setPlano(String plano) {
        this.plano = plano;
    }
    public double getValorMensal() {
        return valorMensal;
    }
    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }
    public LocalDate getValidade() {
        return validade;
    }
    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    

}
