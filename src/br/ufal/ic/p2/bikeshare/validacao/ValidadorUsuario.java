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

        if (plano == null || plano.trim().isEmpty() ||
                (!"avulso".equals(plano) && !"assinante".equals(plano))) {
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












}





























