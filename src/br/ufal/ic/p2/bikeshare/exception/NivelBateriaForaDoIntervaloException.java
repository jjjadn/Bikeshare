package br.ufal.ic.p2.bikeshare.exception;

public class NivelBateriaForaDoIntervaloException extends Exception {
    public NivelBateriaForaDoIntervaloException() {
        super("Nivel de bateria deve estar entre 0 e 100.");
    }
}
