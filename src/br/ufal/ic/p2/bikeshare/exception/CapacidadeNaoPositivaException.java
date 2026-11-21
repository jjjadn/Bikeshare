package br.ufal.ic.p2.bikeshare.exception;

public class CapacidadeNaoPositivaException extends Exception {
    public CapacidadeNaoPositivaException() {
        super("Capacidade deve ser positiva.");
    }
}
