package br.ufal.ic.p2.bikeshare.exception;

public class IdentificacaoNaoNulaException extends Exception {
    public IdentificacaoNaoNulaException() {
        super("Identificacao do usuario nao pode ser nula.");
    }
}
