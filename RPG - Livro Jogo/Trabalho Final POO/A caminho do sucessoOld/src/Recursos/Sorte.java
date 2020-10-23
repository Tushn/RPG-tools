package Recursos;

import Personagem.PersonagemJogador;

public class Sorte {
    public static boolean jogar(PersonagemJogador pj){
        int result = d6.jogar2();
        pj.reduzSorte(1); // Reduz a sorte em menos como custo
        if(result<=pj.getSorte()+1) // Compara com +1 na sorte atual pois a reducao deve ser posterior
            return true;
        else
            return false;
    }
}
