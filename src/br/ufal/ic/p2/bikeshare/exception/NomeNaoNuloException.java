package br.ufal.ic.p2.bikeshare.exception;

public class NomeNaoNuloException extends Exception {
    public NomeNaoNuloException() {
        super("Nome nao pode ser nulo.");
    }
}
