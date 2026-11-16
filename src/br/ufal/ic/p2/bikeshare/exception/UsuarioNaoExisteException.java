package br.ufal.ic.p2.bikeshare.exception;

public class UsuarioNaoExisteException extends Exception {
    public UsuarioNaoExisteException() {
        super("Usuario nao existe.");
    }
}
