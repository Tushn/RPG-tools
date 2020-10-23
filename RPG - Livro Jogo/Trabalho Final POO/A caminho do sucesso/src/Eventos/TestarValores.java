package Eventos;

import Personagem.PersonagemJogador;
import Recursos.*;

public class TestarValores implements Teste{
    int id;
    int caracteristica;
    int dificuldade;
    int trueid;
    int falseid;
    int numeroD6;

    public TestarValores(int id, int caracteristica, int dificuldade, int trueid, int falseid) {
        this.id = id;
        this.caracteristica = caracteristica;
        this.dificuldade = dificuldade;
        this.trueid = trueid;
        this.falseid = falseid;
    }
    
    // compara o valro mais o dado mais o nivel de dificuldade
    public boolean testarAtributo(int n){
        if(n+d6.jogar2()>=dificuldade)
            return true;
        else
            return false;
    }
    
    // Método que captura
    public int testar(PersonagemJogador pj){
        int valorAttrPJ; // valor da caracteristica do personagemJogador
        
        // define atributo ou caracteristica a ser testada
        switch(this.caracteristica){
            case 0: // Habilidade
                valorAttrPJ = pj.getHabilidade();
                break;
            case 1: // Energia
                valorAttrPJ = pj.getEnergia();
                break;
            case 2: // Sorte
                valorAttrPJ = pj.getSorte();
                break;
            case 3: // Magia
                valorAttrPJ = pj.getMagia();
                break;
            default:
                valorAttrPJ = pj.getHabilidade();
        }
        
        return getId(testarAtributo(valorAttrPJ));
    }
    
    public int getId(boolean teste){
        if(teste)
            return trueid;
        else
            return falseid;
    }

}
