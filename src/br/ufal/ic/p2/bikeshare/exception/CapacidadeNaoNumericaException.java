package br.ufal.ic.p2.bikeshare.exception;

public class CapacidadeNaoNumericaException extends Exception {
    public CapacidadeNaoNumericaException() {
        super("Capacidade deve ser numerica.");
    }
}
