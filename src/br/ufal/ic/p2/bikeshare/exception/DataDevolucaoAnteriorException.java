package br.ufal.ic.p2.bikeshare.exception;

public class DataDevolucaoAnteriorException extends Exception {
    public DataDevolucaoAnteriorException() { super("Data de devolucao anterior a data de retirada."); }
}
