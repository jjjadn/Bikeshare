package br.ufal.ic.p2.bikeshare.validacao;

import br.ufal.ic.p2.bikeshare.exception.*;

public class ValidadorBicicleta {
    public static void validarAtributos(String bikeId, String tipo, String nivelBateria) throws Exception {

        if (bikeId == null || bikeId.trim().isEmpty()) {
            throw new IdentificacaoBicicletaNaoNulaException();
        }


        if (!"convencional".equals(tipo) && !"eletrica".equals(tipo)) {
            throw new TipoInvalidoException();
        }


        if ("convencional".equals(tipo)) {
            if (nivelBateria != null) {
                throw new BicicletaNaoPossuiBateriaException();
            }
        } else if ("eletrica".equals(tipo)) {

            if (nivelBateria == null || nivelBateria.trim().isEmpty()) {
                throw new NivelBateriaNaoNuloException();
            }

            try {

                int nivel = Integer.parseInt(nivelBateria.trim());

                if (nivel < 0 || nivel > 100) {
                    throw new NivelBateriaForaDoIntervaloException();
                }
            } catch (NumberFormatException e) {
                throw new NivelBateriaNaoNumericoException();
            }
        }
    }
}