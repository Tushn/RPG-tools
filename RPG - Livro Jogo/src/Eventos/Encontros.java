package Eventos;

import Personagem.Inimigo;
import Personagem.*;
import XML.*;

public class Encontros {
    int[] idfichas; // todos os ids
    int[] numOponentes; // numero de oponentes por id
    int outrosOponentes;
    Inimigo[] fichas;
    
    public Encontros(int[] idfichas, int[] numOponentes) {
        this.idfichas = idfichas;
        this.numOponentes = numOponentes;
    }
    
    public int iniciarEncontro(PersonagemJogador pj){
        new AcessoXML("inimigo.xml","raca.xml","kits.xml"); // carregar XML
        // inicializar alguma variaveis
        int numTotalEnemies = 0;
        int contEnemy = 0;
        for(int i =0;i< this.idfichas.length;i++){
            numTotalEnemies = numTotalEnemies + numOponentes[i];
        }
        fichas = new Inimigo[numTotalEnemies];
        
        // listar inimigos do combate em um array
        for(int i = 0; i < this.idfichas.length;i++){
            new AcessoXML("inimigo.xml","raca.xml","kits.xml");
            for(int j = 0; j < this.numOponentes[i]; j++){
                new AcessoXML("inimigo.xml","raca.xml","kits.xml");
                fichas[contEnemy] = (Inimigo) AcessoXML.data.get( idfichas[i] );
                contEnemy++;
            }
        }
        return Combate.luta(pj, fichas);
    }
    
}
