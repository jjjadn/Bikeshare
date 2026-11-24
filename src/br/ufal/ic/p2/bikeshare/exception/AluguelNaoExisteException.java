package br.ufal.ic.p2.bikeshare.exception;

public class AluguelNaoExisteException extends Exception {
  public AluguelNaoExisteException() { super("Aluguel nao existe."); }
}
