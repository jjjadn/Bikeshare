package br.ufal.ic.p2.bikeshare.exception;

public class BicicletaNaoEstaNestaEstacaoException extends Exception {
    public BicicletaNaoEstaNestaEstacaoException() {
        super("Bicicleta nao esta nesta estacao.");
    }
}
