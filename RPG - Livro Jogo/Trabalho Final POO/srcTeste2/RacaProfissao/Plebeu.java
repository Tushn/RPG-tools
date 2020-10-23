package RacaProfissao;

import Personagem.PersonagemJogador;

/**
 *
 * @author Equip2
 */
public class Plebeu extends Profissao{

    // Valores da entrada de dados
    public Plebeu(){
        super("Plebeu", "Um homem comum", 0, 0, 0);
    }
    
    public void habilidades() {
        System.out.println("Não possui habilidades.");
    }

    public void bonus() {
        System.out.println("Não possui bonus");
    }
}
