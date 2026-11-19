package br.ufal.ic.p2.bikeshare.validacao;

import br.ufal.ic.p2.bikeshare.exception.*;
import java.util.regex.Pattern;

public class ValidadorUsuario {

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String CPF_PATTERN = "^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}$";
    private static final String CNPJ_PATTERN = "^[A-Z0-9]{2}\\.[A-Z0-9]{3}\\.[A-Z0-9]{3}/[A-Z0-9]{4}-[0-9]{2}$";

    private static final Pattern EMAIL_REGEX = Pattern.compile(EMAIL_PATTERN);
    private static final Pattern CPF_REGEX = Pattern.compile(CPF_PATTERN);
    private static final Pattern CNPJ_REGEX = Pattern.compile(CNPJ_PATTERN);


    public static void validarAtributos(String nome, String email, String senha, String tipo) throws Exception {

        if (nome == null || nome.trim().isEmpty()) {
            throw new NomeNaoNuloException();
        }

        if (email == null || email.trim().isEmpty()) {
            throw new EmailNaoNuloException();
        }

        if (!EMAIL_REGEX.matcher(email).matches()) {
            throw new EmailInvalidoException();
        }

        if (senha == null || senha.trim().isEmpty()) {
            throw new SenhaNaoNulaException();
        }

        if (!"pessoaFisica".equals(tipo) && !"pessoaJuridica".equals(tipo)) {
            throw new TipoInvalidoException();
        }
    }

    public static void validarPessoaFisica(String cpf) throws Exception {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new CpfNaoNuloException();
        }

        // if (!CPF_REGEX.matcher(cpf).matches()) throw new CpfInvalidoException();
    }

    public static void validarPessoaJuridica(String cnpj, String razaoSocial) throws Exception {
        if (cnpj == null || cnpj.trim().isEmpty()) {
            throw new CnpjNaoNuloException();

        }
        if (razaoSocial == null || razaoSocial.trim().isEmpty()) {
            throw new RazaoSocialNaoNulaException();
        }
    }

    public static void validarPlanoEMensalidade(String plano, String mensalidade) throws Exception {
        if (plano == null || plano.trim().isEmpty() || (!"avulso".equals(plano) && !"assinante".equals(plano))) {
            throw new PlanoInvalidoException();
        }

        if ("avulso".equals(plano)) {

            if (mensalidade != null && !mensalidade.trim().isEmpty()) {
                throw new PlanoNaoPossuiMensalidadeException();
            }
        }
        if ("assinante".equals(plano)) {

            if (mensalidade == null || mensalidade.trim().isEmpty()) {
                throw new MensalidadeNaoNulaException();
            }

            try {
                double valor = Double.parseDouble(mensalidade.replace(",", "."));

                if (valor < 0) {
                    throw new MensalidadeNaoNegativaException();
                }

            } catch (NumberFormatException e) {
                throw new MensalidadeNaoNumericaException();
            }
        }
    }

    public static void validarAlteracao(String atributo, String valor, String p4, String p5) throws Exception {
        switch (atributo) {

            case "nome":
                if (valor == null || valor.trim().isEmpty()) throw new NomeNaoNuloException();
                break;

            case "email":
                if (valor == null || valor.trim().isEmpty()) throw new EmailNaoNuloException();
                if (!EMAIL_REGEX.matcher(valor).matches()) throw new EmailInvalidoException();
                break;

            case "senha":

                if (valor == null || valor.trim().isEmpty()) throw new SenhaNaoNulaException();
                break;

            case "plano":
                if (!"avulso".equals(valor) && !"assinante".equals(valor)) throw new PlanoInvalidoException();
                break;

            case "metodoPagamento":
                validarMetodoPagamento(valor, p4, p5);
                break;

            case "saldo":
                if (valor == null || valor.trim().isEmpty()) throw new ValorNaoPodeSerNuloException();
                try {
                    double v = Double.parseDouble(valor.replace(",", "."));
                    if (v <= 0) throw new ValorDeveSerPositivoException();
                } catch (NumberFormatException e) {
                    throw new ValorDeveSerNumericoException();
                }
                break;

        }
    }

    private static void validarMetodoPagamento(String tipo, String p4, String p5) throws Exception {
        switch (tipo) {
            case "cartao":
                if (p4 == null || p5 == null) throw new ValorMetodoPagamentoInvalidoException();

                if (p4.trim().isEmpty()) throw new NumeroCartaoNaoNuloException();

                if (p5.trim().isEmpty()) throw new ValidadeCartaoNaoNulaException();

                if (!p5.matches("(0[1-9]|1[0-2])/\\d{4}")) throw new ValidadeCartaoInvalidaException();
                break;

            case "pix":

                if (p4 == null || p5 != null) throw new ValorMetodoPagamentoInvalidoException();

                if (p4.trim().isEmpty()) throw new ChavePixNaoNulaException();
                break;

            case "saldoPreCarregado":

                if (p4 != null || p5 != null) throw new ValorMetodoPagamentoInvalidoException();
                break;

            default:
                throw new ValorMetodoPagamentoInvalidoException();
        }
    }
}