package br.ufal.ic.p2.bikeshare.models;

public class PlanoAssinante implements Plano{
    private double mensalidade;

    public PlanoAssinante(double mensalidade){
        this.mensalidade = mensalidade;
    }

    @Override
    public String getNome(){
        return "assinante";
    }

    @Override
    public Double getMensalidade(){
        return mensalidade;
    }
}
