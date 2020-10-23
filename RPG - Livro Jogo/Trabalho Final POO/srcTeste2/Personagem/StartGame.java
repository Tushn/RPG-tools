package Personagem;
import RacaProfissao.*;
import Recursos.d6;
import java.util.Scanner;
/**
 *
 * @author Artur de Oliveira
 */
public class StartGame {
    public static void main(String[] args){    
    Humano humano = new Humano();
    Plebeu plebeu = new Plebeu();
    PersonagemJogador pj = new PersonagemJogador(
    "Artur", 0, 0, 0, "Um predeiro qualquer" ,humano, plebeu
            );
    
        System.out.println("Habilidade: "+pj.habilidade);
    }
}
