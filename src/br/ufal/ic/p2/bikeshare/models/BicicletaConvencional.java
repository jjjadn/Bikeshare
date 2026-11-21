package br.ufal.ic.p2.bikeshare.models;
import br.ufal.ic.p2.bikeshare.exception.BicicletaNaoPossuiBateriaException;

public class BicicletaConvencional extends Bicicleta {
    public BicicletaConvencional() {}
    public BicicletaConvencional(String id, String estacaoId) {
        super(id, estacaoId);
    }

    @Override public String getTipo() { return "convencional"; }

    @Override
    public String getNivelBateria() throws Exception {
        throw new BicicletaNaoPossuiBateriaException();
    }
}