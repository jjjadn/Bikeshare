package br.ufal.ic.p2.bikeshare.models;

import java.io.Serializable;
import java.util.Map;

public class Sistema implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<String, Usuario> usuarios;

    private int contadorId;


    public Sistema(Map<String, Usuario> usuarios, int contadorId) {
        this.usuarios = usuarios;
        this.contadorId = contadorId;
    }

    public Map<String, Usuario> getUsuarios() { return usuarios; }

    public int getContadorId() { return contadorId; }
}