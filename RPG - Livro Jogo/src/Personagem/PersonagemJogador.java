package Personagem;

import Vantagens.*;
import XML.AcessoXML;
/**
 *
 * @author Artur de Oliveira
 */
public class PersonagemJogador extends Personagem {  
    // Optativos
    private int sorte;
    private int sorteTotal;
    private int idMax;
    private int idMin;
    
    public PersonagemJogador(String nome, String descricao, int habilidade, int energia, int sorte, int magia, int id, Raca raca, Kit kit, int maxPoder, int minPoder) {
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
    public int getSorteTotal() { return sorteTotal; }
    
    public void setSorte(int valor){ this.sorte = valor; }
    public void setSorteTotal(int valor){ this.sorteTotal = valor; }
    
    public void ganhaSorte(int valor){
        this.sorte = this.sorte + valor;
        if(this.sorte>this.sorteTotal)
            sorte = sorteTotal;
    }
    
    public int totalMagias(){
        return this.idMax-this.idMin+1;
    }
    
    public Poder[] listaFeiticos(){
        return feiticos;
    }
}
