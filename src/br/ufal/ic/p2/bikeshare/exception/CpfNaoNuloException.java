package br.ufal.ic.p2.bikeshare.exception;

public class CpfNaoNuloException extends Exception {
    public CpfNaoNuloException() {
        super("CPF nao pode ser nulo.");
    }
}
