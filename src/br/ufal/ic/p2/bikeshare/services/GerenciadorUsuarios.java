package br.ufal.ic.p2.bikeshare.services;

import br.ufal.ic.p2.bikeshare.exception.*;
import br.ufal.ic.p2.bikeshare.models.*;
import br.ufal.ic.p2.bikeshare.persistencia.PersistenciaXML;
import br.ufal.ic.p2.bikeshare.validacao.ValidadorUsuario;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class GerenciadorUsuarios {
    private Map<String, Usuario> usuarios;
    private int contadorId;


    public GerenciadorUsuarios(Map<String, Usuario> usuarios, int contadorId) {
        this.usuarios = usuarios;
        this.contadorId = contadorId;
    }

    public Map<String, Usuario> getUsuarios(){return usuarios;}
    public int getContadorId(){return contadorId;}



    public String criarUsuario(String nome, String email, String senha, String tipo, String cpf, String cnpj, String razaoSocial, String plano, String mensalidade) throws Exception {

        if(plano == null){
            plano = "avulso";
        }

        ValidadorUsuario.validarAtributos(nome, email, senha, tipo);

        if("pessoaFisica".equals(tipo)){
            if(cnpj != null || razaoSocial != null) throw new TipoInvalidoException();
            ValidadorUsuario.validarPessoaFisica(cpf);
        }else if("pessoaJuridica".equals(tipo)){
            if(cpf != null) throw new TipoInvalidoException();
            ValidadorUsuario.validarPessoaJuridica(cnpj, razaoSocial);
        }

        ValidadorUsuario.validarPlanoEMensalidade(plano, mensalidade);

        for(Usuario usuario : usuarios.values()){
            if(usuario.getEmail().equalsIgnoreCase(email)){
                throw new EmailJaCadastradoException();
            }
        }

        String idGerado = "U" + contadorId;
        Plano planoObj = criarObjetoPlano(plano, mensalidade);
        Usuario novoUsuario;

        if("pessoaFisica".equals(tipo)){
            novoUsuario = new PessoaFisica(idGerado, nome, email, senha, planoObj, cpf);
        }else{
            novoUsuario = new PessoaJuridica(idGerado, nome, email, senha, planoObj, cnpj, razaoSocial);

        }

        usuarios.put(idGerado, novoUsuario);
        contadorId++;
        return idGerado;
    }

    private Plano criarObjetoPlano(String plano, String mensalidade) throws Exception{

        if (plano == null || "avulso".equals(plano)) {
            return new PlanoAvulso();
        } else {
            double valor = Double.parseDouble(mensalidade.replace(",", "."));
            return new PlanoAssinante(valor);
        }
    }

    public Usuario buscarUsuario(String id) throws Exception{
        if(id == null || id.trim().isEmpty()){
            throw new IdentificacaoNaoNulaException();
        }

        Usuario usuario = usuarios.get(id);

        if(usuario == null){
            throw new UsuarioNaoExisteException();
        }
        return usuario;
    }


    public String getAtributoUsuario (String id, String atributo) throws Exception{
        Usuario usuario = buscarUsuario(id);

        switch (atributo){
            case "nome": return usuario.getNome();
            case "email": return usuario.getEmail();
            case "senha": return usuario.getSenha();
            case "estado": return usuario.getEstado();
            case "tipo": return usuario.getTipo();
            case "plano": return usuario.getPlano().getNome();
            case "mensalidade":
                Double val = usuario.getPlano().getMensalidade();
                return String.format(java.util.Locale.GERMANY, "%.2f", val);
            case "cpf": return usuario.getCpf();
            case "cnpj": return usuario.getCnpj();
            case "razaoSocial": return usuario.getRazaoSocial();
            case "aluguelAtivo": return usuario.getAluguelAtivo() == null ? "nenhum" : usuario.getAluguelAtivo();
            case "totalPendencias":
                return String.format(java.util.Locale.GERMANY, "%.2f", usuario.getTotalPendencias());
            case "metodoPagamento": return usuario.getMetodoPagamento() == null ? "nenhum" : usuario.getMetodoPagamento().getTipo();
            case "numeroCartao":
                if (usuario.getMetodoPagamento() == null) throw new UsuarioNaoUsaCartaoException();
                return usuario.getMetodoPagamento().getNumero();
            case "validadeCartao":
                if (usuario.getMetodoPagamento() == null) throw new UsuarioNaoUsaCartaoException();
                return usuario.getMetodoPagamento().getValidade();
            case "chavePix":
                if (usuario.getMetodoPagamento() == null) throw new UsuarioNaoUsaPixException();
                return usuario.getMetodoPagamento().getChave();
            case "saldo":
                if (usuario.getMetodoPagamento() == null) throw new UsuarioNaoUsaSaldoException();
                return String.format(java.util.Locale.GERMANY, "%.2f", usuario.getMetodoPagamento().getSaldo());
            default:
                throw new AtributoNaoExisteException();
        }

    }

    public String getUsuarioPorEmail(String email) throws Exception{
        if(email == null || email.trim().isEmpty()){
            throw new EmailNaoNuloException();
        }

        for(Usuario usuario : usuarios.values()){
            if(usuario.getEmail().equalsIgnoreCase(email.trim())){
                return usuario.getId();
            }
        }
        throw new UsuarioNaoExisteException();

    }







    public void alterarUsuario(String id, String atributo, String valor, String p4, String p5) throws Exception {

        Usuario usuario = buscarUsuario(id);

        if (!"metodoPagamento".equals(atributo) && (p4 != null || p5 != null)) {
            throw new AtributoNaoPodeSerAlteradoException();
        }

        ValidadorUsuario.validarAlteracao(atributo, valor, p4, p5);


        switch (atributo) {
            case "nome":
                usuario.setNome(valor);
                break;

            case "email":

                for (Usuario u : usuarios.values()) {
                    if (!u.getId().equals(id) && u.getEmail().equalsIgnoreCase(valor)) {
                        throw new EmailJaCadastradoException();
                    }
                }
                usuario.setEmail(valor);
                break;

            case "senha":
                usuario.setSenha(valor);
                break;

            case "plano":
                if ("assinante".equals(valor)) {


                    if (!"assinante".equals(usuario.getPlano().getNome())) {
                        usuario.setPlano(new PlanoAssinante(30.0));
                    }
                } else if ("avulso".equals(valor)) {
                    usuario.setPlano(new PlanoAvulso());
                }
                break;

            case "metodoPagamento":
                alterarMetodoPagamento(usuario, valor, p4, p5);
                break;

            case "saldo":

                if (usuario.getMetodoPagamento() == null || !"saldoPreCarregado".equals(usuario.getMetodoPagamento().getTipo())) {
                    throw new UsuarioNaoUsaSaldoException();
                }

                double v = Double.parseDouble(valor.replace(",", "."));
                usuario.getMetodoPagamento().adicionarSaldo(v);
                break;


            case "tipo":
            case "estado":
            case "totalPendencias":
            case "aluguelAtivo":
            default:
                throw new AtributoNaoPodeSerAlteradoException();
        }
    }

    private void alterarMetodoPagamento(Usuario usuario, String tipo, String p4, String p5) {
        switch (tipo) {

            case "cartao":
                usuario.setMetodoPagamento(new PagamentoCartao(p4, p5));
                break;

            case "pix":
                usuario.setMetodoPagamento(new PagamentoPix(p4));
                break;

            case "saldoPreCarregado":

                if (usuario.getContaSaldo() == null) {
                    usuario.setContaSaldo(new PagamentoSaldo());
                }

                usuario.setMetodoPagamento(usuario.getContaSaldo());
                break;
        }
    }

    public void removerUsuario(String id) throws Exception {
        Usuario usuario = buscarUsuario(id);

        if (usuario.getAluguelAtivo() != null) {
            throw new RemoverUsuarioComAluguelException();
        }

        usuarios.remove(id);
    }


    public void zerarSistema() {
        this.usuarios.clear();
        this.contadorId = 1;
    }

}
