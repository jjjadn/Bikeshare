package br.ufal.ic.p2.bikeshare.exception;

public class ValidadeCartaoInvalidaException extends Exception {
    public ValidadeCartaoInvalidaException() {
        super("Validade do cartao invalida.");
    }
}
