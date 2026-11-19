package br.ufal.ic.p2.bikeshare.models;
import br.ufal.ic.p2.bikeshare.exception.*;

public class PagamentoPix implements MetodoPagamento {

    private String chave;

    public PagamentoPix(String chave) {
        this.chave = chave;
    }

    @Override public String getTipo() { return "pix"; }

    @Override public String getChave() { return chave; }


    @Override public String getNumero() throws Exception { throw new UsuarioNaoUsaCartaoException(); }

    @Override public String getValidade() throws Exception { throw new UsuarioNaoUsaCartaoException(); }

    @Override public double getSaldo() throws Exception { throw new UsuarioNaoUsaSaldoException(); }

    @Override public void adicionarSaldo(double valor) throws Exception { throw new UsuarioNaoUsaSaldoException(); }
}