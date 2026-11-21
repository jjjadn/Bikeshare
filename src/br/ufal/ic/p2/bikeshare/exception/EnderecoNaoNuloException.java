package br.ufal.ic.p2.bikeshare.exception;

public class EnderecoNaoNuloException extends Exception {
    public EnderecoNaoNuloException() {
        super("Endereco nao pode ser nulo.");
    }
}
