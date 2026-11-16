package br.ufal.ic.p2.bikeshare.exception;

public class EmailNaoNuloException extends Exception {
    public EmailNaoNuloException() {
        super("Email nao pode ser nulo.");
    }
}
