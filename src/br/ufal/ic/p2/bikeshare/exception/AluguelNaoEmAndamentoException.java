package br.ufal.ic.p2.bikeshare.exception;

public class AluguelNaoEmAndamentoException extends Exception {
    public AluguelNaoEmAndamentoException() { super("Aluguel nao esta em andamento."); }
}
