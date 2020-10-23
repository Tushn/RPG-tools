package Eventos;

import NPC.*;
import Personagem.*;
import XML.*;

public class Encontros extends Eventos{
    int[] idfichas; // todos os ids
    int[] numOponentes; // numero de oponentes por id
    int outrosOponentes;
    Inimigo[] fichas;

    public Encontros(int[] idfichas, int[] numOponentes) {
        this.idfichas = idfichas;
        this.numOponentes = numOponentes;
    }
    
    private static void apareceInimigo(){
        new AcessoXML("inimigo.xml","raca.xml","kits.xml");
    }
    
    public int iniciarEncontro(PersonagemJogador pj){
        new AcessoXML("inimigo.xml","raca.xml","kits.xml");
        Inimigo enemy/* = (Inimigo) AcessoXML.data.get( 0 )*/;
        //System.out.println("Inimigo: "+enemy.nome);
        int numTotalEnemies = 0;
        int contEnemy = 0;
        for(int i =0;i< this.idfichas.length;i++){
            numTotalEnemies = numTotalEnemies + numOponentes[i];
        }
        fichas = new Inimigo[numTotalEnemies];
        
        for(int i = 0; i < this.idfichas.length;i++){
            new AcessoXML("inimigo.xml","raca.xml","kits.xml");
            enemy = (Inimigo) AcessoXML.data.get( idfichas[i] );
            for(int j = 0; j < this.numOponentes[i]; j++){
                //new AcessoXML("inimigo.xml","raca.xml","kits.xml");
                new AcessoXML("inimigo.xml","raca.xml","kits.xml");
                fichas[contEnemy] = (Inimigo) AcessoXML.data.get( idfichas[i] );
                contEnemy++;
            }
        }
        return Combate.luta(pj, fichas);
    }
    
    public int nextEvent(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static void main(String[] args){
        new AcessoXML("player.xml","raca.xml","kits.xml");
        PersonagemJogador pj = (PersonagemJogador) AcessoXML.data.get( 1 );
        new AcessoXML("encontros.xml",2 );
        Encontros enco = (Encontros) AcessoXML.data.get(0);
        enco.iniciarEncontro(pj);
        
        new AcessoXML("encontros.xml", 4 );
        enco = (Encontros) AcessoXML.data.get(0);
        enco.iniciarEncontro(pj);
    }
    
}
