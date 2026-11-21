package br.ufal.ic.p2.bikeshare.exception;

public class BicicletaJaExisteException extends Exception {
    public BicicletaJaExisteException() {
        super("Bicicleta ja existe.");
    }
}
