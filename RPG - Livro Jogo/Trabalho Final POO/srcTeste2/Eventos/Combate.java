/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Eventos;

import Personagem.Personagem;
import Personagem.PersonagemJogador;

/**
 *
 * @author Casa
 */
public class Combate {
    public void atacar(PersonagemJogador pj, Personagem enemy){
        if(pj.fa() > enemy.fa()){
            System.out.println("O jogador acertou!!");
            enemy.dano();
        }else if(pj.fa() < enemy.fa()){
            System.out.println("O jogador levou dano!!");
            pj.dano();
        }
    }
}
