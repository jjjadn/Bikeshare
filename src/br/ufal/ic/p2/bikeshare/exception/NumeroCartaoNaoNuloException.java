package br.ufal.ic.p2.bikeshare.exception;

public class NumeroCartaoNaoNuloException extends Exception {
    public NumeroCartaoNaoNuloException() {
        super("Numero do cartao nao pode ser nulo.");
    }
}
