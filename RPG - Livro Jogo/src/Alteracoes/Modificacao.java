package Alteracoes;

import Personagem.*;
import XML.*;
import Personagem.StartGame;
import Vantagens.*;

public class Modificacao extends Alteracao{

    public Modificacao(int id, int atributo, int valor, int idoutro) {
        super(id, atributo, valor, idoutro);
    }

    public PersonagemJogador alteracao(PersonagemJogador persona) {
        PersonagemJogador pj = persona;
        
        switch(atributo){
            case 1: // Personagem
                new AcessoXML("player.xml","raca.xml","kits.xml","poderes.xml", this.valor);
                pj = (PersonagemJogador) AcessoXML.data.get( 0 );
                break;
            case 2: // Raca
                try{
                    LerXML xml = new LerXML("raca.xml");
                    Raca raca = xml.lerXMLRaca( this.valor );
                    pj.setRaca(raca);
                }catch(Exception a){
                    a.printStackTrace();
                }
                break;
            case 3: // Kit
                try{
                    LerXML xml = new LerXML("raca.xml");
                    Kit kit = xml.lerXMLKit( this.valor );
                    pj.setKit(kit);
                }catch(Exception a){
                    a.printStackTrace();
                }
                break;
            case 4: // Habilidade
                pj.setHabilidade( this.valor );
                break;
            case 5: // Energia
                pj.setEnergia( this.valor );
                break;
            case 6: // Sorte
                pj.setSorte( this.valor );
                break;
            case 7: // Magia
                pj.setMagia( this.valor );
                break;
            case 8: // Habilidade Total
                pj.setHabilidadeTotal( this.valor );
                break;
            case 9: // Energia Total
                pj.setEnergiaTotal( this.valor );
                break;
            case 10: // Sorte Total
                pj.setSorteTotal( this.valor );
                break;
            case 11: // Magia Total
                pj.setMagiaTotal( this.valor );
                break;
        }
        return pj;
    }
}
