package br.ufal.ic.p2.bikeshare.models;

public class BicicletaEletrica extends Bicicleta {

    private int nivelBateria;

    public BicicletaEletrica() {}
    public BicicletaEletrica(String id, String estacaoId, int nivelBateria) {
        super(id, estacaoId);
        this.nivelBateria = nivelBateria;
    }


    @Override public String getTipo() { return "eletrica"; }

    @Override
    public String getNivelBateria() {
        return String.valueOf(nivelBateria);
    }
}