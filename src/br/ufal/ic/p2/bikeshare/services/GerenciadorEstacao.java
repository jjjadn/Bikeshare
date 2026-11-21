package br.ufal.ic.p2.bikeshare.services;

import br.ufal.ic.p2.bikeshare.exception.*;
import br.ufal.ic.p2.bikeshare.models.Estacao;
import br.ufal.ic.p2.bikeshare.validacao.ValidadorEstacao;

import java.util.Map;

public class GerenciadorEstacao {

    private Map<String, Estacao> estacoes;
    private int contadorId = 1;


    public GerenciadorEstacao(Map<String, Estacao> estacoes){
        this.estacoes = estacoes;
    }


    public Map<String, Estacao> getEstacoes() { return estacoes; }
    public int getContadorId() { return contadorId; }
    public void setContadorId(int contadorId) { this.contadorId = contadorId; }



    public String criarEstacao(String nome, String endereco, String capacidade) throws Exception {
        ValidadorEstacao.validarAtributos(nome, endereco, capacidade);

        String idGerado = "E" + contadorId;

        int capInt = Integer.parseInt(capacidade.trim());

        Estacao novaEstacao = new Estacao(idGerado, nome, endereco, capInt);

        estacoes.put(idGerado, novaEstacao);
        contadorId++;

        return idGerado;
    }

    public Estacao buscarEstacao(String id) throws Exception {

        if (id == null || id.trim().isEmpty()) {
            throw new IdentificacaoEstacaoNaoNulaException();
        }

        Estacao estacao = estacoes.get(id);

        if (estacao == null) {
            throw new EstacaoNaoExisteException();
        }

        return estacao;
    }

    public String getVagasDisponiveis(String id) throws Exception {
        Estacao estacao = buscarEstacao(id);
        return String.valueOf(estacao.getCapacidade());
    }


    public void zerarSistema() {
        estacoes.clear();
        contadorId = 1;
    }
}