package br.ufal.ic.p2.bikeshare.exception;

public class ChavePixNaoNulaException extends Exception {
    public ChavePixNaoNulaException() {
        super("Chave pix nao pode ser nula.");
    }
}
