/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Eventos;

/**
 *
 * @author Casa
 */
public abstract class Eventos {
    int id;
    String nome;
    String descricao;
    String listaComandos;

    public Eventos(int id, String nome, String descricao, String listaComandos) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.listaComandos = listaComandos;
    }
    
    public abstract int listaComandos();
    public abstract void nextEvent();
}
