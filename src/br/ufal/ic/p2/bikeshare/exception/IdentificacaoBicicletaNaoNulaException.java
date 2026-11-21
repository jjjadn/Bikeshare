package br.ufal.ic.p2.bikeshare.exception;

public class IdentificacaoBicicletaNaoNulaException extends Exception {
    public IdentificacaoBicicletaNaoNulaException() {
        super("Identificacao da bicicleta nao pode ser nula.");
    }
}
