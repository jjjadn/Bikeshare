package br.ufal.ic.p2.bikeshare.exception;

public class NivelBateriaNaoNuloException extends Exception {
    public NivelBateriaNaoNuloException() {
        super("Nivel de bateria nao pode ser nulo.");
    }
}
