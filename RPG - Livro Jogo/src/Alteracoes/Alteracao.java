package Alteracoes;

import Personagem.*;

public abstract class Alteracao {
    int id;
    int atributo;
    int valor;
    int idoutro;

    public Alteracao(int id, int atributo, int valor, int idoutro) {
        this.id = id;
        this.atributo = atributo;
        this.valor = valor;
        this.idoutro = idoutro;
    }
    
    public abstract PersonagemJogador alteracao(PersonagemJogador persona);

    // caso a captura de id seja utilizada
    public int getIdOutro(){
        return idoutro;
    }
}
