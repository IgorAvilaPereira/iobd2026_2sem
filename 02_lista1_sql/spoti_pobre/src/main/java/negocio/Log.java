package negocio;

import java.time.LocalDateTime;
import java.util.UUID;

import com.google.gson.JsonObject;


public class Log {
    private UUID id;
    private String comentario;
    private JsonObject detalhes;
    private LocalDateTime dataHora;

    public Log(){
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public JsonObject getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(JsonObject detalhes) {
        this.detalhes = detalhes;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Log{");
        sb.append("id=").append(id);
        sb.append(", comentario=").append(comentario);
        sb.append(", detalhes=").append(detalhes);
        sb.append(", dataHora=").append(dataHora);
        sb.append('}');
        return sb.toString();
    }


    
    
}
