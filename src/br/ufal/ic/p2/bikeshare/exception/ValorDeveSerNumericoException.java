package br.ufal.ic.p2.bikeshare.exception;

public class ValorDeveSerNumericoException extends Exception {
    public ValorDeveSerNumericoException() {
        super("Valor deve ser numerico.");
    }
}
