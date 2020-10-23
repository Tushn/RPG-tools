package Eventos;

import NPC.*;
import Personagem.*;
import XML.AcessoXML;

public class Encontros extends Eventos{
    int[] idfichas; // todos os ids
    int[] numOponentes; // numero de oponentes por id
    int outrosOponentes;
    Inimigo[] fichas;
    
    public Encontros(int[] idfichas, int[] numOponentes) {
        this.idfichas = idfichas;
        this.numOponentes = numOponentes;
    }
    
    private void iniciarEncontro(PersonagemJogador pj){
        new AcessoXML("inimigo.xml","raca.xml","kits.xml");
        int numTotalEnemies = 0;
        int contEnemy = 0;
        for(int i =0;i< this.idfichas.length;i++){
            numTotalEnemies = numTotalEnemies + numOponentes[i];
        }
        fichas = new Inimigo[numTotalEnemies];
        Inimigo enemy;
        for(int i = 0; i < this.idfichas.length;i++){
            enemy = (Inimigo) AcessoXML.data.get( i );
            for(int j = 0; j < this.numOponentes[i]; j++){
                fichas[contEnemy] = enemy;
                contEnemy++;
            }
        }
        /* 
            enemy[0] = (Inimigo) AcessoXML.data.get( 1 );
            enemy[1] = (Inimigo) AcessoXML.data.get( 0 );
            enemy[2] = (Inimigo) AcessoXML.data.get( 2 );
            System.out.println(enemy.length);
                Combate.luta(pj, enemy);*/
        Combate.luta(pj, fichas);
    }
    
    public int nextEvent(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
