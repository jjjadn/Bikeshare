package br.ufal.ic.p2.bikeshare.services;

import br.ufal.ic.p2.bikeshare.exception.*;
import br.ufal.ic.p2.bikeshare.models.*;
import br.ufal.ic.p2.bikeshare.validacao.ValidadorUsuario;

import java.util.LinkedHashMap;
import java.util.Map;

public class GerenciadorUsuarios {
    private Map<String, Usuario> usuarios = new LinkedHashMap<>();
    private int contadorId = 1;

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

    private Usuario buscarUsuario(String id) throws Exception{
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
            case "estado": return "ativo";
            case "metodoPagamento": return usuario.getMetodoPagamento() == null ? "nenhum" : usuario.getMetodoPagamento();
            case "tipo": return usuario.getTipo();
            case "plano": return usuario.getPlano().getNome();
            case "mensalidade":
                Double val = usuario.getPlano().getMensalidade();
                return String.format(java.util.Locale.GERMANY, "%.2f", val);
            case "cpf": return usuario.getCpf();
            case "cnpj": return usuario.getCnpj();
            case "razaoSocial": return usuario.getRazaoSocial();
            default:
                throw new AtributoNaoExisteException();
        }

    }
    public String getUsuarioPorEmail(String email) throws Exception{
        if(email == null || email.trim().isEmpty()){
            throw new EmailNaoNuloException();
        }

        for(Usuario usuario : usuarios.values()){
            if(usuario.getEmail().equalsIgnoreCase(email)){
                return usuario.getId();
            }
        }
        throw new UsuarioNaoExisteException();

    }

    public void zerarSistema() {
        this.usuarios.clear();
        this.contadorId = 1;
    }




}
