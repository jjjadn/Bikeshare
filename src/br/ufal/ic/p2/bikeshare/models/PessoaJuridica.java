package br.ufal.ic.p2.bikeshare.models;

import br.ufal.ic.p2.bikeshare.exception.UsuarioNaoEhPessoaFisicaException;

public class PessoaJuridica extends Usuario {
    private String cnpj;
    private String razaoSocial;

    public PessoaJuridica() {} // Vazio para Serialização

    public PessoaJuridica(String id, String nome, String email, String senha, Plano plano, String cnpj, String razaoSocial) {
        super(id, nome, email, senha, plano);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
    }

    @Override
    public String getTipo() {
        return "pessoaJuridica";
    }

    @Override
    public String getCnpj() {
        return cnpj;
    }

    @Override
    public String getRazaoSocial() {
        return razaoSocial;
    }


    @Override
    public String getCpf() throws Exception {
        throw new UsuarioNaoEhPessoaFisicaException();
    }
}