package Personagem;

import RacaProfissao.Raca;
import Recursos.d6;

/**
 *
 * @author Artur
 */
public class Personagem {
    // Atributos totais
     int habilidade;
     int energia;
     int sorte;
    // Controle dos atributos variando temporariamente na aventura
     int habilidadeTotal;
     int energiaTotal;
     int sorteTotal;
    // Fixos
     String nome;
     String descricao;
    Raca raca;
    
    // Gera aleatório
/*    Personagem(String pnome){
        nome = pnome;
        habilidadeTotal = 6+d6.jogar2();
        energiaTotal = 12+d6.jogar2();
        sorteTotal = 6+d6.jogar2();
        descricao = "";
        habilidade = habilidadeTotal;
        energia = sorteTotal;
        sorte = energiaTotal;
    }*/
    public Personagem(String pnome,int nhabilidade, int nenergia, int nsorte, String pdescricao){
        nome = pnome;
        habilidadeTotal = nhabilidade;
        energiaTotal = nenergia;
        sorteTotal = nsorte;
        descricao = pdescricao;        
        habilidade = habilidadeTotal;
        energia = sorteTotal;
        sorte = energiaTotal;
    }
    public Personagem(String pnome,int nhabilidade, int nenergia, int nsorte, String pdescricao, Raca praca){
        nome = pnome;
        habilidadeTotal = nhabilidade;
        energiaTotal = nenergia;
        sorteTotal = nsorte;
        descricao = pdescricao;        
        habilidade = habilidadeTotal;
        energia = sorteTotal;
        sorte = energiaTotal;
        raca = praca;
    }
    
    public int fa(){
        return this.habilidade+d6.jogar2();
    }
    public void dano(){
        this.energia = this.energia - 2;
    }
}
