package Alteracoes;

import Personagem.*;
import Vantagens.*;
import XML.*;

public class IncrementoDecremento extends Alteracao{

    public IncrementoDecremento(int id, int atributo, int valor, int idoutro) {
        super(id, atributo, valor, idoutro);
    }

    public PersonagemJogador alteracao(PersonagemJogador persona) {
        PersonagemJogador pj = persona;
        
        switch(atributo){
            case 1: // Personagem
                new AcessoXML("player.xml","raca.xml","kits.xml");
                pj = (PersonagemJogador) AcessoXML.data.get( this.valor );
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
                pj.setHabilidade( pj.getHabilidade() + this.valor );
                break;
            case 5: // Energia
                pj.setEnergia( pj.getEnergia() + this.valor );
                break;
            case 6: // Sorte
                pj.setSorte( pj.getSorte() + this.valor );
                break;
            case 7: // Magia
                pj.setMagia( pj.getMagia() + this.valor );
                break;
            case 8: // Habilidade Total
                pj.setHabilidadeTotal( pj.getHabilidadeTotal() + this.valor );
                break;
            case 9: // Energia Total
                pj.setEnergiaTotal( pj.getEnergiaTotal() + this.valor );
                break;
            case 10: // Sorte Total
                pj.setSorteTotal( pj.getSorteTotal() + this.valor );
                break;
            case 11: // Magia Total
                pj.setMagiaTotal( pj.getMagiaTotal() + this.valor );
                break;
        }
        return pj;
    }
}
