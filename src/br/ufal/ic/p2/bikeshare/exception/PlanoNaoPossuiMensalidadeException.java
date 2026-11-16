package br.ufal.ic.p2.bikeshare.exception;

public class PlanoNaoPossuiMensalidadeException extends Exception {
    public PlanoNaoPossuiMensalidadeException() {
        super("Plano nao possui mensalidade.");
    }
}
