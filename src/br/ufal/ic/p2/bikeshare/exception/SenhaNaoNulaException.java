package br.ufal.ic.p2.bikeshare.exception;

public class SenhaNaoNulaException extends Exception {
    public SenhaNaoNulaException() {
        super("Senha nao pode ser nula.");
    }
}
