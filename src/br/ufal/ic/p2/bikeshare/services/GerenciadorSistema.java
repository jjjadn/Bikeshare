package br.ufal.ic.p2.bikeshare.services;

import br.ufal.ic.p2.bikeshare.models.Sistema;
import br.ufal.ic.p2.bikeshare.persistencia.PersistenciaXML;

public class GerenciadorSistema {
    private boolean encerrado = false;
    private GerenciadorUsuarios gerenciadorUsuarios;

    public GerenciadorSistema(GerenciadorUsuarios gerenciadosUsuarios){
        this.gerenciadorUsuarios = gerenciadosUsuarios;

    }

//    public void verificarEncerrado() throws Exception{
//
//    }

    public void zerarSistema() throws Exception{
//        verificarEncerrado();
        gerenciadorUsuarios.zerarSistema();
        PersistenciaXML.apagar();
    }

    public void encerrarSistema(){

        this.encerrado = true;
        Sistema sistema = new Sistema(gerenciadorUsuarios.getUsuarios(), gerenciadorUsuarios.getContadorId());
        PersistenciaXML.salvar(sistema);
    }
}
