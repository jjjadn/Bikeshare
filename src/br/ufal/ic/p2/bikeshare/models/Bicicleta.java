package br.ufal.ic.p2.bikeshare.models;
import java.io.Serializable;

public abstract class Bicicleta implements Serializable {
    private String id;
    private String estado;
    private String estacaoId;

    public Bicicleta() {}

    public Bicicleta(String id, String estacaoId) {
        this.id = id;
        this.estacaoId = estacaoId;
        this.estado = "disponivel";
    }

    public String getId() { return id; }
    public String getEstado() { return estado; }
    public String getEstacaoId() { return estacaoId; }
    public void setEstado(String estado) { this.estado = estado; }

    public abstract String getTipo();
    public abstract String getNivelBateria() throws Exception;
    public void setEstacaoId(String estacaoId) {this.estacaoId = estacaoId;
    }
}