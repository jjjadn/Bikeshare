package br.ufal.ic.p2.bikeshare.exception;

public class ValorNaoPodeSerNuloException extends Exception {
    public ValorNaoPodeSerNuloException() {
        super("Valor nao pode ser nulo.");
    }
}
