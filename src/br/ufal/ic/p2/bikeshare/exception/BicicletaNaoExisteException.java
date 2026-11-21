package br.ufal.ic.p2.bikeshare.exception;

public class BicicletaNaoExisteException extends Exception {
    public BicicletaNaoExisteException() {
        super("Bicicleta nao existe.");
    }
}
