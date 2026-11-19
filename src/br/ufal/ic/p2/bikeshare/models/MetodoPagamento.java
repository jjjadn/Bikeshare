package br.ufal.ic.p2.bikeshare.models;
import java.io.Serializable;

public interface MetodoPagamento extends Serializable {
    String getTipo();

    String getNumero() throws Exception;
    String getValidade() throws Exception;

    String getChave() throws Exception;

    
    double getSaldo() throws Exception;
    void adicionarSaldo(double valor) throws Exception;
}