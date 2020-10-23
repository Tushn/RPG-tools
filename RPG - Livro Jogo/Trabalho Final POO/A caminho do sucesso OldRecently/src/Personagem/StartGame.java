package Personagem;

import Eventos.*;
import NPC.Inimigo;
import RacaProfissao.*;
import Recursos.d6;
import XML.*;
import java.util.Scanner;

public class StartGame { 
    Encontros combateATUAL;
    Scanner acao = new Scanner(System.in); // entrada das escolhas e botoes
            
    private static void acao(int n){
        new AcessoXML("eventoscontrole.xml","escolhas.xml",n);
    }
            
    // chama novo acesso aos eventos no XML, em cima do nodo requerido
    private static Sequencias acaoXML(int n){
        new AcessoXML("eventoscontrole.xml","escolhas.xml",n);
        return (Sequencias) AcessoXML.data.get( 0 );
    }
    
    // chama um encontro (ou combate)
    private static Encontros combateXML(int n){
        new AcessoXML("encontros.xml", n );
        return (Encontros) AcessoXML.data.get( 0 );
    }
    
    // Iniciar o jogo
    public void jogar(){
        // Iniciando dados basicos
        // Personagem do XML para instancia pj (Personagem do Jogador)
        new AcessoXML("player.xml","raca.xml","kits.xml");
        PersonagemJogador pj = (PersonagemJogador) AcessoXML.data.get( 0 );
	
        // Eventos do XML para instancia cena
        Sequencias cena = acaoXML(0); // Novo acesso XML de Eventos
        // Descreve cena inicial
        cena.descreveCena();
        // Fornece opções iniciais de comandos do evento
        cena.listaComandos();
        
        // Variaveis de controle
        int botao; // variavel que armazena ultima escolha, o 'botao' do jogo
        
        // Loop que mantem o jogo 'rodando'
        while(true){
            // Digite sua escolha
            botao = acao.nextInt();
            
            // seleciona nova cena
            //cena = acaoXML(botao);
            new AcessoXML("eventoscontrole.xml","escolhas.xml",cena.idSaida[botao]);
            cena = (Sequencias) AcessoXML.data.get( 0 );
            cena.descreveCena();
            
            switch(cena.getTipo()){
             // Cena comum, apenas descritivo
                case 0: 
                    System.out.println("Cena comum");
                    cena.listaComandos();
                    break;
             // Cena com combate, defina encontro
                case 1: 
                    //System.out.println("Encontro: "+cena.getSubtipo());
                    this.combateATUAL = combateXML(cena.getSubtipo());
                    this.combateATUAL.iniciarEncontro(pj);
                    cena.descreveCena();
                    cena.listaComandos();
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                default:
                    System.out.println("Outros");
                    cena.descreveCena();
                    cena.listaComandos();
            }
        }
    }
    
    // Teste de combate contra varios oponentes
    /*public static void main( String[] args ) {  
            new AcessoXML("player.xml","raca.xml","kits.xml");
            PersonagemJogador pj = (PersonagemJogador) AcessoXML.data.get( 1 );
            
            Inimigo[] enemy = new Inimigo[3]; 
            new AcessoXML("inimigo.xml","raca.xml","kits.xml");
            enemy[0] = (Inimigo) AcessoXML.data.get( 3 );
            new AcessoXML("inimigo.xml","raca.xml","kits.xml");
            enemy[1] = (Inimigo) AcessoXML.data.get( 3 );
            new AcessoXML("inimigo.xml","raca.xml","kits.xml");
            enemy[2] = (Inimigo) AcessoXML.data.get( 3 );
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

    
    public static void main(String[] args){
        StartGame jovem = new StartGame();
        jovem.jogar();
    }
    
    // Teste de Eventos bem sucedidos a seguir
      //Falta correções
        /*public static void main(String[] args) {
            new AcessoXML("player.xml","raca.xml","kits.xml");
            PersonagemJogador pj = (PersonagemJogador) AcessoXML.data.get( 1 );
  
            acao(0);

            //new AcessoXML("player.xml","raca.xml","kits.xml");
            System.out.println("No máximo: "+(AcessoXML.cont));
            Scanner number = new Scanner(System.in);
            
            // valor que armazena o numero total de saidas
            //int numSaidas = 0;
            // Inicializar o primeiro evento de id=0
            Sequencias ficha = (Sequencias) AcessoXML.data.get( 0 );
            // variavel de controle da acao, recebe valor do Scanner
            int botao;
            
            
            while(true){
                try{
                    ficha = (Sequencias) AcessoXML.data.get( 0 );
                    //numSaidas = ficha.idSaida.length; // passar o numero total de saidas
                 }catch(Exception a){
                    ficha.descreveCena();
                }
                
                ficha.descreveCena();
                ficha.listaComandos();
                switch( ficha.getTipo() ){ 
                        case 0:                             
                        ficha.descreveCena();
                        ficha.listaComandos();
                            break;
                        case 1:                      
                            new AcessoXML("encontros.xml",ficha.getSubtipo());
                            System.out.println("Subtipo: "+ficha.getSubtipo());
                            Encontros encontro = (Encontros) AcessoXML.data.get( 0 );
                            encontro.iniciarEncontro(pj);
                            break;
                        default:                           
                            ficha.descreveCena();
                            break;
                        
                }
                        
                botao = number.nextInt();
                System.out.println("Digitado: "+botao);
                
                // Se digitar um numero alem das escolhas ofertadas feche o jogo
                if(AcessoXML.cont<botao){
                    System.out.println("Encerrar...");
                    break;
                }
                
                acao(ficha.idSaida[botao]);
                //new AcessoXML("eventoscontrole.xml","escolhas.xml",1);
              
            }
        }*/
        
        
}
