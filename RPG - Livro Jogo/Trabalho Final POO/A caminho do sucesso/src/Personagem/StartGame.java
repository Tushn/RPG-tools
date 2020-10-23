package Personagem;

import Eventos.*;
import NPC.Inimigo;
import RacaProfissao.*;
import Recursos.d6;
import XML.*;
import java.util.Scanner;

public class StartGame { 
    Encontros combateATUAL;
    boolean continueJogo = true;
    Scanner acao = new Scanner(System.in); // entrada das escolhas e botoes
    int[] registroEventosId = new int[100];
    
    private static void acao(int n){
        new AcessoXML("eventoscontrole.xml","escolhas.xml",n);
    }
    
    private static Sequencias acaoXML(){
        new AcessoXML("eventoscontrole.xml","escolhas.xml",0);
        return (Sequencias) AcessoXML.data.get( 0 );
    }
            
    // chama novo acesso aos eventos no XML, em cima do nodo requerido
    private static Sequencias acaoXML(int n, Sequencias pcena){
        System.out.println("Linha27 pcena.idSaida[n]: "+pcena.idSaida[n]);
        new AcessoXML("eventoscontrole.xml","escolhas.xml",pcena.idSaida[n]);
        return (Sequencias) AcessoXML.data.get( 0 );
    }
    
    private static Sequencias acaoXML(int n){
        new AcessoXML("eventoscontrole.xml","escolhas.xml",n);
        return (Sequencias) AcessoXML.data.get( 0 );
    }
    
    // chama um encontro (ou combate)
    private static Encontros combateXML(int n){
        new AcessoXML("encontros.xml", n );
        return (Encontros) AcessoXML.data.get( 0 );
    }
    
    // Testa de acordo com determinadas condições que são satisfeitas com o desenrolar da história
    private static int testeEventoKey(int n, int[] lista, int max){
        new AcessoXML("testeschave.xml", n);
        EventoChave evento = (EventoChave) AcessoXML.data.get( 0 );
        return evento.testar(lista, max);
    }
     
    // Teste de característica
    private static int teste(int n, PersonagemJogador pj){
        new AcessoXML("testes.xml", n);
        TestarValores evento = (TestarValores) AcessoXML.data.get( 0 );
        return evento.testar(pj);
    }
    
    // Iniciar o jogo
    public void jogar(){
        // Iniciando dados basicos
        // Personagem do XML para instancia pj (Personagem do Jogador)
        new AcessoXML("player.xml","raca.xml","kits.xml");
        PersonagemJogador pj = (PersonagemJogador) AcessoXML.data.get( 1 );
	
        // Eventos do XML para instancia cena
        Sequencias cena = acaoXML(0); // Novo acesso XML de Eventos
        // Descreve cena inicial
        cena.descreveCena();
        // Fornece opções iniciais de comandos do evento
        cena.listaComandos();
        
        // Variaveis de controle
        int botao; // variavel que armazena ultima escolha, o 'botao' do jogo
        int contLoop = 0;
        
        // Loop que mantem o jogo 'rodando'
        while(this.continueJogo){
            // Digite sua escolha
            botao = acao.nextInt();
            
            // seleciona nova cena
            cena =  acaoXML(botao, cena);
            cena.descreveCena();
            
            // registra idsaida
            registroEventosId[contLoop] = cena.getId();
            
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
                    if(this.combateATUAL.iniciarEncontro(pj)==0)
                        this.continueJogo=false;
                    else
                        this.continueJogo=true;
                    cena.descreveCena();
                    cena.listaComandos();
                    break;
             // Teste de eventos chave
                case 2:
                    System.out.println("Evento Chave");
                    cena = acaoXML(testeEventoKey(cena.getSubtipo(), this.registroEventosId, contLoop));
                    cena.descreveCena();
                    cena.listaComandos();
                    botao = acao.nextInt();
                    cena =  acaoXML(botao, cena);
                    registroEventosId[contLoop] = cena.getId();
                    cena.descreveCena();
                    cena.listaComandos();
                    break;
             // Teste de caracteristicas
                case 3:
                    System.out.println("Testes");
                    System.out.println("teste(cena.getSubtipo(),pj): "+teste(cena.getSubtipo(),pj));
                    cena = acaoXML(teste(cena.getSubtipo(),pj));
                    cena.descreveCena();
                    cena.listaComandos();
                    botao = acao.nextInt();
                    cena =  acaoXML(botao, cena);
                    registroEventosId[contLoop] = cena.getId();
                    cena.descreveCena();
                    cena.listaComandos();
                    break;
                case 4:
                case 5:
             // Fim de jogo
                case 6:
                    System.out.println("Fim de jogo!!");
                    break;
                default:
                    System.out.println("Outros");
                    cena.descreveCena();
                    cena.listaComandos();
            }
            
            contLoop++;
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
