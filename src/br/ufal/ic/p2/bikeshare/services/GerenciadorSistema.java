package br.ufal.ic.p2.bikeshare.services;

//import br.ufal.ic.p2.bikeshare.exception.SistemaEncerradoException;
import br.ufal.ic.p2.bikeshare.models.Sistema;
import br.ufal.ic.p2.bikeshare.persistencia.PersistenciaXML;

public class GerenciadorSistema {
    private boolean encerrado = false;
    private GerenciadorUsuarios gerenciadorUsuarios;
    private GerenciadorEstacao gerenciadorEstacao;
    private GerenciadorBicicleta gerenciadorBicicleta;
    private GerenciadorAluguel gerenciadorAluguel;

    public GerenciadorSistema(GerenciadorUsuarios gerenciadorUsuarios, GerenciadorEstacao gerenciadorEstacao, GerenciadorBicicleta gerenciadorBicicleta, GerenciadorAluguel gerenciadorAluguel) {
        this.gerenciadorUsuarios = gerenciadorUsuarios;
        this.gerenciadorEstacao = gerenciadorEstacao;
        this.gerenciadorBicicleta = gerenciadorBicicleta;
        this.gerenciadorAluguel = gerenciadorAluguel;
    }

    public void verificarEncerrado() throws Exception {
        if (encerrado) {
//            throw new SistemaEncerradoException();
        }
    }

    public void zerarSistema() throws Exception {
        verificarEncerrado();
        gerenciadorUsuarios.zerarSistema();
        gerenciadorEstacao.zerarSistema();
        gerenciadorBicicleta.zerarSistema();
        gerenciadorAluguel.zerarSistema();
        PersistenciaXML.apagar();
    }

    public void encerrarSistema() {
        this.encerrado = true;
        Sistema sistema = new Sistema(
                gerenciadorUsuarios.getUsuarios(),
                gerenciadorUsuarios.getContadorId(),
                gerenciadorEstacao.getEstacoes(),
                gerenciadorEstacao.getContadorId(),
                gerenciadorBicicleta.getBicicletas(),
                gerenciadorAluguel.getAlugueis(), gerenciadorAluguel.getContadorId()
        );

        PersistenciaXML.salvar(sistema);
    }
}