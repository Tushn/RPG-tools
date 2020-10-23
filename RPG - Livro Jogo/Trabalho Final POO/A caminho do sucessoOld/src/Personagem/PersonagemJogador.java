package Personagem;

import RacaProfissao.*;
/**
 *
 * @author Artur de Oliveira
 */
public class PersonagemJogador extends Personagem {  
    // Optativos
    int sorte;
    int sorteTotal;

    public PersonagemJogador(String nome, String descricao, int habilidade, int energia, int sorte, int magia, int id, Raca raca, Kit kit) {
        super(id, 
                habilidade+raca.habilidade+kit.habilidade, 
                energia+raca.energia+kit.energia,
                magia+raca.magia+kit.magia,
                nome,
                descricao,
                raca,
                kit);
        this.sorte = sorte+raca.sorte+kit.sorte;
        this.sorteTotal = this.sorte;
    }

    public void reduzSorte(int num){ this.sorte = this.sorte-num; }
    public int getSorte(){ return this.sorte; }
}
