package br.ufal.ic.p2.bikeshare.exception;

public class ValorDeveSerPositivoException extends Exception {
    public ValorDeveSerPositivoException() {
        super("Valor deve ser positivo.");
    }
}
