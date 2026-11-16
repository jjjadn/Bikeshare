package br.ufal.ic.p2.bikeshare.services;

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
    }

    public void encerrarSistema(){
        this.encerrado = true;
    }
}
