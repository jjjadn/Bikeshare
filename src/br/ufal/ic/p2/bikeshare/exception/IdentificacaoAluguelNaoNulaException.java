package br.ufal.ic.p2.bikeshare.exception;

public class IdentificacaoAluguelNaoNulaException extends Exception {
    public IdentificacaoAluguelNaoNulaException() { super("Identificacao do aluguel nao pode ser nula."); }
}
