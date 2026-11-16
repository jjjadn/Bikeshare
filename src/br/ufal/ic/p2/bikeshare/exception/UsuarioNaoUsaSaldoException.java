package br.ufal.ic.p2.bikeshare.exception;

public class UsuarioNaoUsaSaldoException extends Exception {
    public UsuarioNaoUsaSaldoException() {
        super("Usuario nao usa saldo pre-carregado.");
    }
}
