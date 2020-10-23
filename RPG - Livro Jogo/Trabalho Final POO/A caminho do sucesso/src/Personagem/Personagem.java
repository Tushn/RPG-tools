package Personagem;
import RacaProfissao.*;
import Recursos.d6;

/**
 *
 * @author Artur
 */
public class Personagem {
    // Atributos totais
    int id;
    int habilidade;
    int energia;
    int magia;
    // Controle dos atributos variando temporariamente na aventura
    int habilidadeTotal;
    int energiaTotal;
    int magiaTotal;
    // Fixos
    public String nome;
    String descricao;
    Raca raca;
    Kit kit;
       //PJ
    public Personagem(int id, int habilidade, int energia, int magia, String nome, String descricao, Raca raca, Kit kit) {
        this.id = id;
        this.habilidade = habilidade;
        this.energia = energia;
        this.habilidadeTotal = habilidade;
        this.energiaTotal = energia;
        this.magia = magia;
        this.magiaTotal = magia;
        this.nome = nome;
        this.descricao = descricao;
        this.raca = raca;
        this.kit = kit;
    }
    //Inimigo
    public Personagem(int id, int habilidade, int energia, String nome, String descricao, Raca raca, Kit kit) {
        this.id = id;
        this.habilidade = habilidade;
        this.energia = energia;
        this.habilidadeTotal = habilidade;
        this.energiaTotal = energia;
        this.magia = 0;
        this.magiaTotal = 0;
        this.nome = nome;
        this.descricao = descricao;
        this.raca = raca;
        this.kit = kit;
    }

    // NPC - Villager
        public Personagem(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }
        
    public int getHabilidade(){
        return habilidade;
    }    
    public String getNomeKit(){
        return this.kit.nome;
    }
    public String getNomeRaca(){
        return this.raca.nome;
    }
    public int getId(){ return id; }
    
    public int getEnergia(){
        return this.energia;
    }

    public int getMagia() {
        return magia;
    }
    
    public int fa(){
        return this.habilidade+d6.jogar2();
    }
    
    public void dano(){
        this.energia = this.energia - 2;
    }
    
    public void ganhaEnergia(int num){
        this.energia = this.energia + num;
    }
}
