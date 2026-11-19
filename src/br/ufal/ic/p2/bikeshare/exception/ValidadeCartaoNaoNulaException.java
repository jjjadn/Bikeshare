package br.ufal.ic.p2.bikeshare.exception;

public class ValidadeCartaoNaoNulaException extends Exception {
    public ValidadeCartaoNaoNulaException() {
        super("Validade do cartao nao pode ser nula.");
    }
}
