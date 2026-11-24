package br.ufal.ic.p2.bikeshare.models;


import java.io.Serializable;

public abstract class Usuario implements Serializable {
    private static final long serialVersionUID = 1l;

    private String id;
    private String nome;
    private String email;
    private String senha;
    private String tipo;
    private String estado = "ativo";
    private Plano plano;
    private MetodoPagamento metodoPagamento;
    private PagamentoSaldo contaSaldo;
    private String aluguelAtivo;
    private double totalPendencias = 0.0;





    public Usuario(){}

    public Usuario(String id, String nome, String email, String senha, Plano plano){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.plano = plano;
        this.metodoPagamento = null;
        this.aluguelAtivo = null;
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
    public MetodoPagamento getMetodoPagamento(){
        return metodoPagamento;
    }
    public String getEstado(){return estado;}
    public PagamentoSaldo getContaSaldo() { return contaSaldo; }
    public String getAluguelAtivo(){return aluguelAtivo;}
    public double getTotalPendencias(){return totalPendencias;}


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

    public void setMetodoPagamento(MetodoPagamento metodoPagamento){
        this.metodoPagamento = metodoPagamento;
    }


    public abstract String getTipo();
    public abstract String getCpf() throws Exception;
    public abstract String getCnpj() throws Exception;
    public abstract String getRazaoSocial()throws Exception;
    public void setContaSaldo(PagamentoSaldo contaSaldo) { this.contaSaldo = contaSaldo; }
    public void setAluguelAtivo(String aluguelAtivo){this.aluguelAtivo = aluguelAtivo;}
    public void setEstado(String estado){this.estado = estado;}
    public void setTotalPendencias(double totalPendencias){this.totalPendencias = totalPendencias;}
}


