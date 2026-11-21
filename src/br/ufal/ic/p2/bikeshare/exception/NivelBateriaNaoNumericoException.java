package br.ufal.ic.p2.bikeshare.exception;

public class NivelBateriaNaoNumericoException extends Exception {
    public NivelBateriaNaoNumericoException() {
        super("Nivel de bateria deve ser numerico.");
    }
}
