package br.ufal.ic.p2.bikeshare.services;

//import br.ufal.ic.p2.bikeshare.exception.SistemaEncerradoException;
import br.ufal.ic.p2.bikeshare.models.Sistema;
import br.ufal.ic.p2.bikeshare.persistencia.PersistenciaXML;

public class GerenciadorSistema {
    private boolean encerrado = false;
    private GerenciadorUsuarios gerenciadorUsuarios;

    public GerenciadorSistema(GerenciadorUsuarios gerenciadorUsuarios) {
        this.gerenciadorUsuarios = gerenciadorUsuarios;
    }

    public void verificarEncerrado() throws Exception {
        if (encerrado) {
//            throw new SistemaEncerradoException();
        }
    }

    public void zerarSistema() throws Exception {
        verificarEncerrado();
        gerenciadorUsuarios.zerarSistema();
        PersistenciaXML.apagar();
    }

    public void encerrarSistema() {
        this.encerrado = true;
        Sistema sistema = new Sistema(gerenciadorUsuarios.getUsuarios(), gerenciadorUsuarios.getContadorId());
        PersistenciaXML.salvar(sistema);
    }
}