package br.ufal.ic.p2.bikeshare.models;

import java.io.Serializable;

public interface Plano extends Serializable {
    String getNome();
    Double getMensalidade() throws Exception;
}
