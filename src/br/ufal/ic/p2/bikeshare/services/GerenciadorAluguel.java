package br.ufal.ic.p2.bikeshare.services;

import br.ufal.ic.p2.bikeshare.exception.*;
import br.ufal.ic.p2.bikeshare.models.*;
import br.ufal.ic.p2.bikeshare.validacao.ValidadorAluguel;

import java.time.LocalDateTime;
import java.util.Map;

public class GerenciadorAluguel {
    private Map<String, Aluguel> alugueis;
    private int contadorId = 1;
    private GerenciadorUsuarios gerenciadorUsuarios;
    private GerenciadorEstacao gerenciadorEstacao;
    private GerenciadorBicicleta gerenciadorBicicleta;


    public GerenciadorAluguel(Map<String, Aluguel> alugueis, GerenciadorUsuarios gu, GerenciadorEstacao ge, GerenciadorBicicleta gb) {
        this.alugueis = alugueis;
        this.gerenciadorUsuarios = gu;
        this.gerenciadorEstacao = ge;
        this.gerenciadorBicicleta = gb;
    }

    public Map<String, Aluguel> getAlugueis() { return alugueis; }
    public int getContadorId() { return contadorId; }
    public void setContadorId(int id) { this.contadorId = id; }



    public String retirarBicicleta(String usuarioId, String estacaoId, String bikeId, String dataStr) throws Exception {

        Usuario user = gerenciadorUsuarios.buscarUsuario(usuarioId);
        Estacao estacao = gerenciadorEstacao.buscarEstacao(estacaoId);
        Bicicleta bike = gerenciadorBicicleta.buscarBicicleta(bikeId);


        LocalDateTime data = ValidadorAluguel.validarData(dataStr);



        if ("inadimplente".equals(user.getEstado())) {
            throw new UsuarioInadimplenteException();
        }


        if (user.getAluguelAtivo() != null) {
            throw new UsuarioJaPossuiAluguelException();
        }



        if (!estacao.getBicicletasIds().contains(bikeId)) {
            throw new BicicletaNaoEstaNestaEstacaoException();
        }


        if ("eletrica".equals(bike.getTipo()) && Integer.parseInt(bike.getNivelBateria()) < 15) {
            throw new BateriaInsuficienteException();
        }


        if (user.getMetodoPagamento() == null) {
            throw new MetodoPagamentoNaoCadastradoException();
        }



        if ("saldoPreCarregado".equals(user.getMetodoPagamento().getTipo())) {
            if (user.getMetodoPagamento().getSaldo() <= 0.0) {
                throw new SaldoInsuficienteException();
            }
        }

        String idGerado = "A" + contadorId;
        Aluguel novoAluguel = new Aluguel(idGerado, usuarioId, bikeId, estacaoId, data);

        alugueis.put(idGerado, novoAluguel);
        user.setAluguelAtivo(idGerado);

        bike.setEstado("emUso");

        bike.setEstacaoId(null);
        estacao.getBicicletasIds().remove(bikeId);

        contadorId++;
        return idGerado;
    }

    public String getAtributoAluguel(String aluguelId, String atributo) throws Exception {

        if (aluguelId == null || aluguelId.trim().isEmpty()) throw new IdentificacaoAluguelNaoNulaException();
        Aluguel a = alugueis.get(aluguelId);

        if (a == null) throw new AluguelNaoExisteException();


        switch (atributo) {
            case "estado": return a.getEstado();
            case "usuario": return a.getUsuarioId();
            case "bicicleta": return a.getBicicletaId();
            case "estacaoOrigem": return a.getEstacaoOrigemId();
            case "inicio": return String.format("%d/%d/%d %d:%02d", a.getInicio().getDayOfMonth(), a.getInicio().getMonthValue(), a.getInicio().getYear(), a.getInicio().getHour(), a.getInicio().getMinute());
            case "fim": return a.getFim();
            case "estacaoDestino": return a.getEstacaoDestino();
            case "valorCobrado": return a.getValorCobrado();
            default: throw new AtributoNaoExisteException();
        }
    }


    public void devolverBicicleta(String aluguelId, String estacaoDestinoId, String dataStr) throws Exception {

        if (aluguelId == null || aluguelId.trim().isEmpty()) throw new IdentificacaoAluguelNaoNulaException();
        Aluguel aluguel = alugueis.get(aluguelId);
        if (aluguel == null) throw new AluguelNaoExisteException();

        Estacao destino = gerenciadorEstacao.buscarEstacao(estacaoDestinoId);
        LocalDateTime dataFim = ValidadorAluguel.validarData(dataStr);

        if (!"emAndamento".equals(aluguel.getEstado())) throw new AluguelNaoEmAndamentoException();
        if (dataFim.isBefore(aluguel.getInicio())) throw new DataDevolucaoAnteriorException();


        if (destino.getVagasDisponiveis() <= 0) {
            throw new EstacaoCheiaException();
        }

        long minutos = java.time.Duration.between(aluguel.getInicio(), dataFim).toMinutes();
        Usuario user = gerenciadorUsuarios.buscarUsuario(aluguel.getUsuarioId());
        Bicicleta bike = gerenciadorBicicleta.buscarBicicleta(aluguel.getBicicletaId());

        String novoEstadoAluguel;
        double valorCobrado = 0.0;
        boolean bikeParaManutencao = false;


        if (minutos <= 2 && aluguel.getEstacaoOrigemId().equals(estacaoDestinoId)) {

            novoEstadoAluguel = "cancelado";
            valorCobrado = 0.0;

        } else if (minutos > 1440) {

            novoEstadoAluguel = "expirado";
            valorCobrado = 0.0;
            bikeParaManutencao = true;
            user.setEstado("inadimplente");
            user.setTotalPendencias(user.getTotalPendencias() + 10.00);
        } else {

            novoEstadoAluguel = "finalizado";
            boolean isAssinante = "assinante".equals(user.getPlano().getNome());
            boolean isEletrica = "eletrica".equals(bike.getTipo());


            if (minutos <= 60 && isAssinante) {
                valorCobrado += 0.0;
            } else {
                long minutosCobrados = isAssinante ? (minutos - 60) : minutos;
                double taxaMinuto = isAssinante ? 0.30 : 0.50;
                valorCobrado += (minutosCobrados * taxaMinuto);
            }


            if (isEletrica) {
                valorCobrado += (minutos * 0.20);
            }
        }


        if (valorCobrado > 0 && "saldoPreCarregado".equals(user.getMetodoPagamento().getTipo())) {

            double saldoAtual = user.getMetodoPagamento().getSaldo();

            if (saldoAtual < valorCobrado) {
                throw new SaldoInsuficienteException(); // Aborta devolução, aluguel segue emAndamento
            }

            user.getMetodoPagamento().adicionarSaldo(-valorCobrado);
        }

        aluguel.finalizar(dataFim, estacaoDestinoId, valorCobrado, novoEstadoAluguel);


        if (bikeParaManutencao) {
            bike.setEstado("emManutencao");
            bike.setEstacaoId(null);

        } else {
            bike.setEstado("disponivel");
            bike.setEstacaoId(estacaoDestinoId);
            destino.getBicicletasIds().add(bike.getId());
        }


        user.setAluguelAtivo(null);
    }


    public void zerarSistema() {
        this.alugueis.clear();
        this.contadorId = 1;
    }

}