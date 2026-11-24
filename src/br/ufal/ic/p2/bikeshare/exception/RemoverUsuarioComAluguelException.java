package br.ufal.ic.p2.bikeshare.exception;

public class RemoverUsuarioComAluguelException extends Exception {
    public RemoverUsuarioComAluguelException() { super("Nao e possivel remover usuario com aluguel em andamento."); }
}
