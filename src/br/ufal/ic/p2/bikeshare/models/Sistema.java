package br.ufal.ic.p2.bikeshare.models;

import java.io.Serializable;
import java.util.Map;

public class Sistema implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<String, Usuario> usuarios;
    private int contadorId;
    private Map<String, Estacao> estacoes;
    private int contadorEstacaoId;
    private Map<String, Bicicleta> bicicletas;
    private Map<String, Aluguel> alugueis;
    private int contadorAluguelId;

    public Sistema(Map<String, Usuario> usuarios, int contadorId, Map<String, Estacao> estacoes, int contadorEstacaoId, Map<String, Bicicleta> bicicletas, Map<String, Aluguel> alugueis, int contadorAluguelId) {
        this.usuarios = usuarios;
        this.contadorId = contadorId;
        this.estacoes = estacoes;
        this.contadorEstacaoId = contadorEstacaoId;
        this.bicicletas = bicicletas;
        this.alugueis = alugueis;
        this.contadorAluguelId = contadorAluguelId;
    }

    public Map<String, Usuario> getUsuarios() { return usuarios; }
    public Map<String, Estacao> getEstacoes(){return estacoes;}
    public Map<String, Bicicleta> getBicicletas() {return bicicletas; }
    public Map<String, Aluguel> getAlugueis(){return alugueis;}

    public int getContadorId() { return contadorId; }
    public int getContadorEstacaoId(){return contadorEstacaoId;}
    public int getContadorAluguelId(){return contadorAluguelId;}


}