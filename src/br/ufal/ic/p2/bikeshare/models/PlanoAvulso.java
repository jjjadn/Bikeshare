package br.ufal.ic.p2.bikeshare.models;

import br.ufal.ic.p2.bikeshare.exception.PlanoNaoPossuiMensalidadeException;



public class PlanoAvulso implements Plano{
    @Override
    public String getNome(){
        return "avulso";
    }
    @Override
    public Double getMensalidade() throws Exception{
        throw new PlanoNaoPossuiMensalidadeException();
    }
}
