package br.ufal.ic.p2.bikeshare.exception;

public class IdentificacaoEstacaoNaoNulaException extends Exception {
    public IdentificacaoEstacaoNaoNulaException() {
        super("Identificacao da estacao nao pode ser nula.");
    }
}
