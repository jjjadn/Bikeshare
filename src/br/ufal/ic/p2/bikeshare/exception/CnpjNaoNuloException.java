package br.ufal.ic.p2.bikeshare.exception;

public class CnpjNaoNuloException extends Exception {
    public CnpjNaoNuloException() {
        super("CNPJ nao pode ser nulo.");
    }
}
