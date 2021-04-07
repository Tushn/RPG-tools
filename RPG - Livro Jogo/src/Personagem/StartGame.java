package Personagem;

import Alteracoes.*;
import Eventos.*;
import Vantagens.*;
import Recursos.d6;
import XML.*;
import java.util.Scanner;

public class StartGame { 
    Encontros combateATUAL;
    boolean continueJogo = true;
    Scanner acao = new Scanner(System.in); // entrada das escolhas e botoes
    int[] registroEventosId = new int[100];
    
    // chama novo acesso aos eventos no XML, em cima do nodo requerido
    private static Sequencias acaoXML(int n, Sequencias pcena){
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
    
    // Alteracao nos personagens
    private static PersonagemJogador alterarPj(String fileXML, int tipo, int subtipo, PersonagemJogador pj){
        new AcessoXML(fileXML, tipo, subtipo);
        Modificacao alterar = (Modificacao) AcessoXML.data.get( 0 );
        return alterar.alteracao(pj);
    }
    
    // Iniciar o jogo
    public void jogar(){
        // Iniciando dados basicos
        // Personagem do XML para instancia pj (Personagem do Jogador)
        new AcessoXML("player.xml","raca.xml","kits.xml","poderes.xml", 0);
        PersonagemJogador pj = (PersonagemJogador) AcessoXML.data.get( 0 );
	
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
            cena = acaoXML(botao, cena);
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
                    else{
                        this.continueJogo=true;
                        cena.listaComandos();
                    }
                    break;
                    
             // Teste de eventos chave
                case 2:
                    System.out.println("Evento Chave");
                    cena = acaoXML(testeEventoKey(cena.getSubtipo(), this.registroEventosId, contLoop));
                    cena.mostrar();
                    botao = acao.nextInt();
                    cena =  acaoXML(botao, cena);
                    registroEventosId[contLoop] = cena.getId();
                    cena.listaComandos();
                    break;
                    
             // Teste de caracteristicas
                case 3:
                    System.out.println("Testes");
                    System.out.println("teste(cena.getSubtipo(),pj): "+teste(cena.getSubtipo(),pj));
                    cena = acaoXML(teste(cena.getSubtipo(),pj));
                    registroEventosId[contLoop] = cena.getId();
                    cena.mostrar();
                    break;
                    
             // Modificacao de caracteristicas, como personagens, raca, etc
                case 4:
                    System.out.println("Modificação dos atributos");
                    pj = alterarPj("modificacao.xml",cena.getTipo(),cena.getSubtipo(),pj);
                    cena.listaComandos();
                    break;
                    
             // Incremento/Decremento dos valores    
                case 5:
                    System.out.println("Incremento/Decremento de atributos");
                    pj = alterarPj("incrementodecremento.xml",cena.getTipo(),cena.getSubtipo(),pj);
                    cena.listaComandos();
                    break;
                    
             // Fim de jogo
                case 6:
                    System.out.println("Fim de jogo!!");
                    this.continueJogo=false;
                    break;
                default:
                    System.out.println("Outros");
                    cena.mostrar();
            }
            contLoop++;
        }
    }
    
    public static void main(String[] args){
        StartGame jovem = new StartGame();
        jovem.jogar();
    }        
}
