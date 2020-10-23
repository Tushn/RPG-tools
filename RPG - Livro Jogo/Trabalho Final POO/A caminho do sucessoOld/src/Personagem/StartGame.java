package Personagem;

import Eventos.*;
/*import NPC.Inimigo;
import RacaProfissao.*;
import Recursos.d6;*/
import XML.*;
import java.util.Scanner;

public class StartGame { 
    static Encontros combateATUAL;
    
    
    
    // chama novo acesso aos eventos no XML, em cima do nodo requerido
    private static void acao(int n){
        new AcessoXML("eventoscontrole.xml","escolhas.xml",n);
    }
    
    private static void combate(int n){
        new AcessoXML("eventoscontrole.xml","escolhas.xml",n);
    }
    // Teste de combate contra varios oponentes
    /*public static void main( String[] args ) {  
            new AcessoXML("player.xml","raca.xml","kits.xml");
            PersonagemJogador pj = (PersonagemJogador) AcessoXML.data.get( 1 );
            
            new AcessoXML("inimigo.xml","raca.xml","kits.xml");
            Inimigo[] enemy = new Inimigo[3]; 
            enemy[0] = (Inimigo) AcessoXML.data.get( 1 );
            enemy[1] = (Inimigo) AcessoXML.data.get( 0 );
            enemy[2] = (Inimigo) AcessoXML.data.get( 2 );
            System.out.println(enemy.length);
                Combate.luta(pj, enemy);
    }*/
    
    // Teste de combate contra um inimigo
        /*public static void main( String[] args ) {  
            new AcessoXML("player.xml","raca.xml","kits.xml");
            PersonagemJogador pj = (PersonagemJogador) AcessoXML.data.get( 1 );
            
            new AcessoXML("inimigo.xml","raca.xml","kits.xml");
            Inimigo enemy = null; 
            enemy = (Inimigo) AcessoXML.data.get( 1 );
            
                Combate.luta(pj, enemy);
    }*/


    // Teste de Eventos bem sucedidos a seguir
        public static void main( String[] args ) {
            System.out.println("OK");
            new AcessoXML("eventoscontrole.xml","escolhas.xml",0);

            //new AcessoXML("player.xml","raca.xml","kits.xml");
            System.out.println("No máximo: "+(AcessoXML.cont));
            Scanner number = new Scanner(System.in);
            
            // valor que armazena o numero total de saidas
            int numSaidas = 0;
            // Inicializar o primeiro evento de id=0
            Sequencias ficha = (Sequencias) AcessoXML.data.get( 0 );
            // variavel de controle da acao, recebe valor do Scanner
            int acaoEscolhida;
            
            
            while(true){
                try{
                    ficha = (Sequencias) AcessoXML.data.get( 0 );
                    numSaidas = ficha.idSaida.length; // passar o numero total de saidas
                 }catch(Exception a){
                    ficha.descreveCena();
                }
                
                //if (contTurnos == 0)
                ficha.descreveCena();
                ficha.listaComandos();
                acaoEscolhida = number.nextInt();
                System.out.println("Digitado: "+acaoEscolhida);
                
                // Se digitar um numero alem das escolhas ofertadas feche o jogo
                if(AcessoXML.cont<acaoEscolhida){
                    System.out.println("Encerrar...");
                    break;
                }
                
                acao(ficha.idSaida[acaoEscolhida]);
                //new AcessoXML("eventoscontrole.xml","escolhas.xml",1);
              
            }
        }
        
        
}
