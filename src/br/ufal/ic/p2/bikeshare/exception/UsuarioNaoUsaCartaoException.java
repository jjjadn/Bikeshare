package br.ufal.ic.p2.bikeshare.exception;

public class UsuarioNaoUsaCartaoException extends Exception {
    public UsuarioNaoUsaCartaoException() {
        super("Usuario nao usa cartao.");
    }
}
