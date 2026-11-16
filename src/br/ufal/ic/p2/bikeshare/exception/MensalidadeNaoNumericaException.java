package br.ufal.ic.p2.bikeshare.exception;

public class MensalidadeNaoNumericaException extends Exception {
    public MensalidadeNaoNumericaException() {
        super("Mensalidade deve ser numerica.");
    }
}
