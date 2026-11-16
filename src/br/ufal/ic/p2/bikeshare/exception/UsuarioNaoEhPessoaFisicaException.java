package br.ufal.ic.p2.bikeshare.exception;

public class UsuarioNaoEhPessoaFisicaException extends Exception {
    public UsuarioNaoEhPessoaFisicaException() {
        super("Usuario nao eh pessoa fisica.");
    }
}
