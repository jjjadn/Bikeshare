package br.ufal.ic.p2.bikeshare.exception;

public class RazaoSocialNaoNulaException extends Exception {
    public RazaoSocialNaoNulaException() {
        super("Razao social nao pode ser nula.");
    }
}
