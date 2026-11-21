package br.ufal.ic.p2.bikeshare.validacao;

import br.ufal.ic.p2.bikeshare.exception.*;

public class ValidadorEstacao {
    public static void validarAtributos(String nome, String endereco, String capacidade) throws Exception {

        if(nome == null || nome.trim().isEmpty()){
            throw new NomeNaoNuloException();
        }
        if(endereco == null || endereco.trim().isEmpty()){
            throw new EnderecoNaoNuloException();
        }
        if (capacidade == null || capacidade.trim().isEmpty()) {
            throw new CapacidadeNaoNulaException();
        }


        try {
            int valorCapacidade = Integer.parseInt(capacidade.trim());

            if (valorCapacidade <= 0) {
                throw new CapacidadeNaoPositivaException();
            }

        } catch (NumberFormatException e) {
            throw new CapacidadeNaoNumericaException();
        }
    }
}