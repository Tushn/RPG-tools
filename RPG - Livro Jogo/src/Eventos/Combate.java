package Eventos;

import Personagem.*;
import Recursos.Sorte;
import Recursos.d6;
import Vantagens.Poder;
import java.util.Scanner;

public class Combate {
    static int totalOponentesDerrotados = 0;
            
    // metodo que insere um combate entre o personagem e os inimigos
    public static int luta(PersonagemJogador pj, Inimigo[] inimigo){
        int cont = inimigo.length;// conta o numero total de inimigos
        int contVitorias = 0;
        
        Scanner escolhaMagia = new Scanner(System.in); // entrada de dados para jogadores com magia
        int opcaoMagia; // variavel da opcao escolhida envolvendo magia
        
        Scanner escolha = new Scanner(System.in); // entrada de dados para acoes comuns, sem magia
        int opcao = 0; // variavel escolhida sem envolver magia
        estado(pj,inimigo); // mostra estado do jogador e dos inimigos
        while(true){
            // listar oponentes disponiveis para lutar
            System.out.println("Escolha oponente na lista: ");
            for(int i=0;i<inimigo.length;i++)
                if(inimigo[i].getEnergia()>0)
                System.out.println("Para atacar o, "+inimigo[i].nome+". Digite: "+i);
            
            // Atacar um oponente
            opcao = escolha.nextInt();
            while(opcao >= cont || inimigo[opcao].getEnergia()<=0 || opcao < 0){
                System.out.println("Voce escolheu um oponente invalido ou inexistente, escolha novamente");
                opcao = escolha.nextInt();
            }
            if(pj.getMagia()>0){ // o jogador for usuario de magia e tiver pontos para gastar, as opcoes a seguir estarao disponiveis
                System.out.println("Você possui de magia "+pj.getMagia()+". Se quiser lançar uma magia, digite: 1, \nSe não, digite: 0.");
                opcaoMagia = escolhaMagia.nextInt();
                Poder[] conjuracoes = pj.listaFeiticos();
                if(opcaoMagia==1){ // se o jogador escolheu usar magia
                    // listar opcoes
                    for(int i=0; i <= pj.totalMagias(); i++){
                        conjuracoes[i].getNomeDescricao();
                        System.out.println("Digite: "+i);
                    }
                    opcaoMagia = escolhaMagia.nextInt();
                    while(true){
                        if(opcaoMagia<conjuracoes.length){
                            conjuracoes[opcaoMagia].usarPoder(pj, inimigo, opcao, true);
                            if(inimigo[opcao].getEnergia()<0)
                                contVitorias++;
                            break;
                        }else if(opcaoMagia<0){ // se for digitado valores menores que zero interronpa o processo
                            System.out.println("Você quebrou o efeito da magia, realize um ataque normal.");
                            break;
                        }else{ // quaisquer valores digitados acima da lista de poderes
                            System.out.println("Não possui esta magia em seu grimório.");
                            opcaoMagia = escolhaMagia.nextInt();
                        }
                    }
                }else{
                    System.out.println("Atacar de forma normal: ");
                    contVitorias = contVitorias + atacar(pj, inimigo,opcao);
                    // Oponentes atacados
                    atacado(pj, inimigo, opcao);
                }
            }else{
                System.out.println("Atacar de forma normal: ");
                contVitorias = contVitorias + atacar(pj, inimigo,opcao);
                // Oponentes atacados
                atacado(pj, inimigo, opcao);
            }
            
            // Mostrar estado do jogador e dos monstros
            estado(pj,inimigo);
            
            /*
             * CONDICOES DE FIM DO COMBATE
             */
            if(pj.getEnergia()<=0){
                System.out.println("Voce perece diante de um golpe mortal!!");
                break;
            }
            if(contVitorias==cont){
                System.out.println("Fim do combate, voce venceu");
                break;
            }
        }
        return contVitorias;
    }
    
    // mostrar estado da batalha do jogador e dos oponentes
    public static void estado(PersonagemJogador pj, Inimigo[] inimigo){
        System.out.println("\nPj, nome: "+pj.nome+". Habilidade: "+pj.getHabilidade()+". Energia: "+pj.getEnergia()+". Sorte: "+pj.getSorte()+". Magia:"+pj.getMagia()+"\nInimigos: \n");
        for(int i=0;i<inimigo.length;i++)
            System.out.print(inimigo[i].nome+" \nEnergia: "+inimigo[i].getEnergia()+". Habilidade: "+inimigo[i].getHabilidade()+"\n");
    }
    
    // define os acontecimentos durante um combate contra os inimigos, nao tira-se dano dos oponentes apenas recebe-se ou nao
    public static void atacado(PersonagemJogador pj, Personagem[] inimigo, int n){
        int escolhaS; // Verifica a sorte
        Scanner opcao = new Scanner(System.in); // escolha da opcao
        
        boolean npcAcertouPJ = true; // verifica se acertou o inimigo ou se foi acertado
        int atkPJ;
        int atkNPC;
        
        for(int i=0; i<inimigo.length; i++){
        if(n!=i && inimigo[i].getEnergia()>0){
            atkPJ = pj.fa(); // ataque do personagem jogador
            atkNPC = inimigo[i].fa(); // ataque do inimigo

            System.out.println("Voce confronta com "+inimigo[i].nome+", sua forca de ataque foi: "+atkNPC+"\nE a sua "+atkPJ);
            if(atkPJ > atkNPC){
                System.out.println("Voce se esquivou!! Nao saira ferido.");
                npcAcertouPJ = false;
            }else if(atkPJ < atkNPC){
                pj.dano();
                npcAcertouPJ = true;
                System.out.println("Voce errou e se machucou!! Mas pode tentar usar a sorte para reduzi-lo, se falhar o dano sera ainda maior..");
                System.out.println("Usar sorte? \nDigite: 0, se nao\nDigite: 1, se sim");
                escolhaS = opcao.nextInt();
                if(escolhaS==1 && atkPJ != atkNPC){
                    boolean teste = Sorte.jogar(pj);
                    if(npcAcertouPJ){ // Se PJ acertou o NPC
                        if(teste){
                            System.out.println("Voce e sortudo, e voce escapa dos golpes ao pouco");
                            pj.ganhaEnergia(1);
                        }else{
                            System.out.println("Voce e azarado, os golpes do oponente o atingem com precisao e forca");
                            pj.dano();
                            }
                        }
                }
            }else{
                System.out.println("Empate, voce nao se machucou, aguarde o proximo round");
             }
            }
        }
    }
     
    // ataque do jogador contra os oponentes escolhidos, se falharem no ataque recebera dano
    public static int atacar(PersonagemJogador pj, Personagem[] inimigo, int n){
        int escolhaS; // Verifica a sorte
        Scanner opcao = new Scanner(System.in); // escolha da opcao
        
        boolean pjAcertouNPC = true; // verifica se acertou o inimigo ou se foi acertado
        int atkPJ = pj.fa();
        int atkNPC = inimigo[n].fa();
        
        System.out.println("Voce confronta com "+inimigo[n].nome);
        if(atkPJ > atkNPC){
            System.out.println("Voce acertou!! \nMas, pode tentar usar a sorte para aumentar o dano, contudo se errar seu dano será menor..");
            inimigo[n].dano();
            pjAcertouNPC = true;
        }else if(atkPJ < atkNPC){
            System.out.println("Voce errou e se machucou!! Mas pode tentar usar a sorte para reduzi-lo, se falhar o dano sera ainda maior..");
            pj.dano();
            pjAcertouNPC = false;
        }else{
            System.out.println("Empate, aguarde o proximo round");
         }
        // Verifica se ha necessidade de usar a sorte
        if(atkPJ != atkNPC){
            System.out.println("Usar sorte? \nDigite: 0, se nao\nDigite: 1, se sim");
            escolhaS = opcao.nextInt();
        }else{
            escolhaS = 0;
        }
        
        if(escolhaS==1){
            boolean teste = Sorte.jogar(pj);
            if(pjAcertouNPC){ // Se PJ acertou o NPC
                if(teste){
                    System.out.println("Voce e sortudo, e seu golpe atinge o oponente em cheio");
                    inimigo[n].dano();
                }else{
                    System.out.println("Voce e azarado, seu golpe mal atinge o oponente");
                    inimigo[n].ganhaEnergia(1);
                }
            }else{ // se o NPC tiver atingido o oponente
                if(teste){
                    System.out.println("Voce e sortudo, o golpe do oponente foi de raspao");
                    pj.ganhaEnergia(1);
                }else{
                    System.out.println("Voce e azarado, o golpe do oponente o atinge com muita forca");
                    pj.dano();
                }
            }
        }
        
        // Verifica se o oponente morreu nesse ataque
        if(inimigo[n].getEnergia()<=0){
            System.out.println("Voce acaba de derrubar um "+inimigo[n].nome+"\nUm inimigo a menos.\n");
            totalOponentesDerrotados++;
            return 1;
        }else
            return 0;
    }
}
