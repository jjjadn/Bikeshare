package br.ufal.ic.p2.bikeshare.exception;

public class MensalidadeNaoNulaException extends Exception {
    public MensalidadeNaoNulaException() {
        super("Mensalidade nao pode ser nula.");
    }
}
