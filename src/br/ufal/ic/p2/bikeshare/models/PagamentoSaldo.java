package br.ufal.ic.p2.bikeshare.models;
import br.ufal.ic.p2.bikeshare.exception.*;

public class PagamentoSaldo implements MetodoPagamento {

    private double saldo = 0.0;

    @Override public String getTipo() { return "saldoPreCarregado"; }

    @Override public double getSaldo() { return saldo; }

    @Override public void adicionarSaldo(double valor) { this.saldo += valor; }



    @Override public String getNumero() throws Exception { throw new UsuarioNaoUsaCartaoException(); }

    @Override public String getValidade() throws Exception { throw new UsuarioNaoUsaCartaoException(); }

    @Override public String getChave() throws Exception { throw new UsuarioNaoUsaPixException(); }
}