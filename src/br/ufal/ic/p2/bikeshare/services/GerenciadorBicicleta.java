package br.ufal.ic.p2.bikeshare.services;

import br.ufal.ic.p2.bikeshare.exception.*;
import br.ufal.ic.p2.bikeshare.models.*;
import br.ufal.ic.p2.bikeshare.validacao.ValidadorBicicleta;

import java.util.Map;

public class GerenciadorBicicleta {
    private Map<String, Bicicleta> bicicletas;
    private GerenciadorEstacao gerenciadorEstacao;

    public GerenciadorBicicleta(Map<String, Bicicleta> bicicletas, GerenciadorEstacao gerenciadorEstacao) {
        this.bicicletas = bicicletas;
        this.gerenciadorEstacao = gerenciadorEstacao;
    }

    public Map<String, Bicicleta> getBicicletas() { return bicicletas; }

    public void adicionarBicicleta(String estacaoId, String bikeId, String tipo, String nivelBateria) throws Exception {

        Estacao estacao = gerenciadorEstacao.buscarEstacao(estacaoId);

        ValidadorBicicleta.validarAtributos(bikeId, tipo, nivelBateria);

        if (bicicletas.containsKey(bikeId)) {
            throw new BicicletaJaExisteException();
        }

        if (estacao.getVagasDisponiveis() <= 0) {
            throw new EstacaoSemVagasException();
        }

        Bicicleta novaBike;

        if ("convencional".equals(tipo)) {
            novaBike = new BicicletaConvencional(bikeId, estacaoId);
        } else {
            novaBike = new BicicletaEletrica(bikeId, estacaoId, Integer.parseInt(nivelBateria.trim()));
        }

        bicicletas.put(bikeId, novaBike);
        estacao.getBicicletasIds().add(bikeId);
    }



    public void removerBicicleta(String estacaoId, String bikeId) throws Exception {

        Estacao estacao = gerenciadorEstacao.buscarEstacao(estacaoId);

        if (bikeId == null || bikeId.trim().isEmpty()) {
            throw new IdentificacaoBicicletaNaoNulaException();
        }

        if (!estacao.getBicicletasIds().contains(bikeId)) {
            if (!bicicletas.containsKey(bikeId)) throw new BicicletaNaoExisteException();
            throw new BicicletaNaoEstaNestaEstacaoException();
        }

        estacao.getBicicletasIds().remove(bikeId);
        bicicletas.remove(bikeId);
    }

    public String getAtributoBicicleta(String bikeId, String atributo) throws Exception {

        if (bikeId == null || bikeId.trim().isEmpty()) {
            throw new IdentificacaoBicicletaNaoNulaException();
        }

        Bicicleta bike = bicicletas.get(bikeId);

        if (bike == null) throw new BicicletaNaoExisteException();

        switch (atributo) {
            case "tipo": return bike.getTipo();
            case "estado": return bike.getEstado();
            case "estacao": return bike.getEstacaoId();
            case "nivelBateria": return bike.getNivelBateria();
            default: throw new AtributoNaoExisteException();
        }
    }


    public String getEstadoBicicleta(String bikeId) throws Exception {
        if (bikeId == null || bikeId.trim().isEmpty()) {
            throw new IdentificacaoBicicletaNaoNulaException();
        }
        Bicicleta bike = bicicletas.get(bikeId);
        if (bike == null) throw new BicicletaNaoExisteException();
        return bike.getEstado();
    }


    public String getBicicletasDisponiveis(String estacaoId) throws Exception {
        Estacao estacao = gerenciadorEstacao.buscarEstacao(estacaoId);

        int disponiveis = 0;


        for (String bikeId : estacao.getBicicletasIds()) {
            Bicicleta b = bicicletas.get(bikeId);

            if (b != null && "disponivel".equals(b.getEstado())) {

                if ("convencional".equals(b.getTipo())) {
                    disponiveis++;
                } else {

                    int bateria = Integer.parseInt(b.getNivelBateria());
                    if (bateria >= 15) {
                        disponiveis++;

                    }
                }
            }
        }

        return String.valueOf(disponiveis);
    }

    public void zerarSistema() {
        bicicletas.clear();
    }
}