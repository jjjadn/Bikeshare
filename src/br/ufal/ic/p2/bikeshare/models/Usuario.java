package br.ufal.ic.p2.bikeshare.models;


import java.io.Serializable;

public abstract class Usuario implements Serializable {
    private static final long serialVersionUID = 1l;

    private String id;
    private String nome;
    private String email;
    private String senha;
    private String tipo;
    private boolean estado;
    private Plano plano;
    private String metodoPagamento;



    public Usuario(){}

    public Usuario(String id, String nome, String email, String senha, Plano plano){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.plano = plano;
        this.metodoPagamento = null;
    }

    public String getId() {
        return id;
    }
    public String getNome(){
        return nome;
    }
    public String getEmail(){
        return email;
    }
    public String getSenha(){
        return senha;
    }

    public Plano getPlano(){
        return plano;
    }
    public String getMetodoPagamento(){
        return metodoPagamento;
    }
    public boolean getEstado(){return estado;}

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }



    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public void setMetodoPagamento(String metodoPagamento){
        this.metodoPagamento = metodoPagamento;
    }

    public void setEstado(Boolean estado){this.estado = estado;}

    public abstract String getTipo();
    public abstract String getCpf() throws Exception;
    public abstract String getCnpj() throws Exception;
    public abstract String getRazaoSocial()throws Exception;
}


