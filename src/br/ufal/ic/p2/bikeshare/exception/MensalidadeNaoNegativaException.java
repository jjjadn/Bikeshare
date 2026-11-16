package br.ufal.ic.p2.bikeshare.exception;

public class MensalidadeNaoNegativaException extends Exception {
    public MensalidadeNaoNegativaException() {
        super("Mensalidade deve ser nao-negativa.");
    }
}
