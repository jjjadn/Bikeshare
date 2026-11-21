package br.ufal.ic.p2.bikeshare.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Estacao implements Serializable {

    private String id;
    private String nome;
    private String endereco;
    private int capacidade;

    private List<String> bicicletasIds = new ArrayList<>();


    public Estacao(){}


    public Estacao(String id, String nome, String endereco, int capacidade){
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.capacidade = capacidade;
    }

    public String getId() { return id; }

    public int getCapacidade() { return capacidade; }
    public String getEndereco() { return endereco; }
    public String getNome() { return nome; }

    public List<String> getBicicletasIds(){
        return bicicletasIds;
    }

    public int getVagasDisponiveis(){
        return this.capacidade - this.bicicletasIds.size();
    }
    public void setNome(String nome) { this.nome = nome; }
    public void setCapacidade(int capacidade) { this.capacidade = capacidade; }
    public void setEndereco(String endereco) { this.endereco = endereco; }


}