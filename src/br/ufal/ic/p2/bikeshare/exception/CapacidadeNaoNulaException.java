package br.ufal.ic.p2.bikeshare.exception;

public class CapacidadeNaoNulaException extends Exception {
    public CapacidadeNaoNulaException() {
        super("Capacidade nao pode ser nula.");
    }
}
