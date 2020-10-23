package Personagem;

import RacaProfissao.*;
import Recursos.d6;
/**
 *
 * @author Artur de Oliveira
 */
public class PersonagemJogador extends Personagem {
    Raca raca;
    Profissao kit;
    
    /*PersonagemJogador(String pnome, Raca praca, Profissao pkit){
        super(pnome);
        kit = pkit;
    }*/
    PersonagemJogador(String pnome,int nhabilidade, int nenergia, int nsorte, String pdescricao, Raca praca, Profissao pkit){
        super(pnome,
                6+d6.jogar()+nhabilidade+praca.bonusHabilidade+pkit.bonusHabilidade,// nhabilidade = 1d6
                12+d6.jogar2()+nenergia+praca.bonusEnergia+pkit.bonusEnergia,  // nenergia = 2d6
                6+d6.jogar()+nsorte+praca.bonusSorte+pkit.bonusSorte, // nsorte = 1d6
                pdescricao, 
                praca);
        kit = pkit;
    }
}
