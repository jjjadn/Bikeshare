package br.ufal.ic.p2.bikeshare.models;
import br.ufal.ic.p2.bikeshare.exception.*;

public class PagamentoCartao implements MetodoPagamento {

    private String numero;
    private String validade;

    public PagamentoCartao(String numero, String validade) {
        this.numero = numero;
        this.validade = validade;
    }

    @Override public String getTipo() { return "cartao"; }

    @Override public String getNumero() { return numero; }

    @Override public String getValidade() { return validade; }


    @Override public String getChave() throws Exception { throw new UsuarioNaoUsaPixException(); }

    @Override public double getSaldo() throws Exception { throw new UsuarioNaoUsaSaldoException(); }

    @Override public void adicionarSaldo(double valor) throws Exception { throw new UsuarioNaoUsaSaldoException(); }
}