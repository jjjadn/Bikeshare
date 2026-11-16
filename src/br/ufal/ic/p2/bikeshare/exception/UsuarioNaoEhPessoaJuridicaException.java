package br.ufal.ic.p2.bikeshare.exception;

public class UsuarioNaoEhPessoaJuridicaException extends Exception {
    public UsuarioNaoEhPessoaJuridicaException() {
        super("Usuario nao eh pessoa juridica.");
    }
}
