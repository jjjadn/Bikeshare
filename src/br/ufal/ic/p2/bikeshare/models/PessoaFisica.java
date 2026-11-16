package br.ufal.ic.p2.bikeshare.models;

import br.ufal.ic.p2.bikeshare.exception.UsuarioNaoEhPessoaJuridicaException;

public class PessoaFisica extends Usuario {

    private String cpf;

    public PessoaFisica() {}


    public PessoaFisica(String id, String nome, String email, String senha, Plano plano, String cpf) {
        super(id, nome, email, senha, plano);
        this.cpf = cpf;
    }

    @Override
    public String getTipo() {
        return "pessoaFisica";
    }

    @Override
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    @Override
    public String getCnpj() throws Exception {
        throw new UsuarioNaoEhPessoaJuridicaException();
    }

    @Override
    public String getRazaoSocial() throws Exception {
        throw new UsuarioNaoEhPessoaJuridicaException();
    }
}