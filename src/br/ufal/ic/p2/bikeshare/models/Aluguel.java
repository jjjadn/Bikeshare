package br.ufal.ic.p2.bikeshare.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import br.ufal.ic.p2.bikeshare.exception.AluguelEmAndamentoException;

public class Aluguel implements Serializable {
    private String id;
    private String usuarioId;
    private String bicicletaId;
    private String estacaoOrigemId;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private String estacaoDestinoId;
    private Double valorCobrado;
    private String estado;

    public Aluguel() {}


    public Aluguel(String id, String usuarioId, String bicicletaId, String estacaoOrigemId, LocalDateTime inicio) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.bicicletaId = bicicletaId;
        this.estacaoOrigemId = estacaoOrigemId;
        this.inicio = inicio;
        this.estado = "emAndamento";
    }

    public String getId() { return id; }
    public String getUsuarioId() { return usuarioId; }

    public String getBicicletaId() { return bicicletaId; }

    public String getEstacaoOrigemId() { return estacaoOrigemId; }
    public LocalDateTime getInicio() { return inicio; }

    public String getEstado() { return estado; }



    public String getFim() throws Exception {

        if ("emAndamento".equals(estado)) throw new AluguelEmAndamentoException();
        return String.format("%d/%d/%d %d:%02d", fim.getDayOfMonth(), fim.getMonthValue(), fim.getYear(), fim.getHour(), fim.getMinute());
    }

    public String getEstacaoDestino() throws Exception {

        if ("emAndamento".equals(estado)) throw new AluguelEmAndamentoException();

        return estacaoDestinoId;
    }

    public String getValorCobrado() throws Exception {

        if ("emAndamento".equals(estado)) throw new AluguelEmAndamentoException();

        return String.format(java.util.Locale.GERMANY, "%.2f", valorCobrado);
    }

    public void finalizar(LocalDateTime fim, String estacaoDestino, double valor, String estado) {
        this.fim = fim;
        this.estacaoDestinoId = estacaoDestino;
        this.valorCobrado = valor;
        this.estado = estado;
    }

    public void setId(String id) { this.id = id; }

    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }
    public void setBicicletaId(String bicicletaId) { this.bicicletaId = bicicletaId; }
    public void setEstacaoOrigemId(String estacaoOrigemId) { this.estacaoOrigemId = estacaoOrigemId; }
    public void setInicio(LocalDateTime inicio) { this.inicio = inicio; }
    public void setFim(LocalDateTime fim) { this.fim = fim; }

    public void setEstacaoDestinoId(String estacaoDestinoId) { this.estacaoDestinoId = estacaoDestinoId; }
    public void setValorCobrado(Double valorCobrado) { this.valorCobrado = valorCobrado; }
    public void setEstado(String estado) { this.estado = estado; }
}

