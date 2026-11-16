package br.ufal.ic.p2.bikeshare.exception;

public class AtributoNaoExisteException extends Exception {
    public AtributoNaoExisteException() {
        super("Atributo nao existe.");
    }
}
