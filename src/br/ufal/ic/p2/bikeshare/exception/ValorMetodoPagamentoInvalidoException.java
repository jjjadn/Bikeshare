package br.ufal.ic.p2.bikeshare.exception;

public class ValorMetodoPagamentoInvalidoException extends Exception {
    public ValorMetodoPagamentoInvalidoException() {
        super("Valor de metodo de pagamento invalido.");
    }
}
