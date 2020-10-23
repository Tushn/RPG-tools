package Eventos;

import Personagem.Personagem;
import Personagem.PersonagemJogador;
import NPC.*;
import Recursos.Sorte;
import Recursos.d6;
import java.util.Scanner;

public class Combate {
    
    // luta contra um oponente
    public static int luta(PersonagemJogador pj, Inimigo enemy) {
        int contVitorias = 0;

        estado(pj,enemy);
        while(true){
         
            // Atacar um oponente
            contVitorias = contVitorias + atacar(pj, enemy);
            
            // Oponentes atacados
            atacado(pj, enemy);

            // Mostrar estado
            estado(pj,enemy);
            /*
             * CONDICOES DE FIM DE COMBATE
             */
            if(pj.getEnergiaAtual()<=0){
                System.out.println("Voce perece diante de um golpe mortal!!");
                break;
            }
            if(pj.getEnergiaAtual()>0 && enemy.getEnergiaAtual()<=0){
                System.out.println("Voce acaba de derrubar um "+enemy.nome);
                contVitorias++;
            }
            if(contVitorias==1){
                System.out.println("Fim do combate, voce venceu");
                break;
            }               
        }
        if(contVitorias==1)
            return 1;
        else
            return 0;
    }
    // metodo que insere um combate entre o personagem e os inimigos
    public static int luta(PersonagemJogador pj, Inimigo[] enemy){
        int cont = enemy.length;
        int contVitorias = 0;
        
        Scanner escolha = new Scanner(System.in);
        int opcao = 0;
        estado(pj,enemy);
        while(true){
            
            System.out.println("Escolha oponente na lista: ");
            for(int i=0;i<enemy.length;i++)
                if(enemy[i].getEnergiaAtual()>0)
                System.out.println("Para atacar o, "+enemy[i].nome+". Digite: "+i);
            
            // Atacar um oponente
            do{
                opcao = escolha.nextInt();
                if( opcao < cont)
                    if(enemy[opcao].getEnergiaAtual()>0){
                        contVitorias = contVitorias + atacar(pj, enemy,opcao);
                        break;
                    }else
                        System.out.println("Voce escolheu um oponente invalido ou inexistente, escolha novamente");
                else
                    System.out.println("Voce escolheu um oponente inexistente, escolha novamente");
            }while(opcao > cont || enemy[opcao].getEnergiaAtual()<0);
            // Oponentes atacados
            atacado(pj, enemy, opcao);
            
            // Mostrar estado do jogador e dos monstros
            estado(pj,enemy);

            /*
             * CONDICOES DE FIM DE COMBATE
             */
            if(pj.getEnergiaAtual()<=0){
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
      
    // metodo de iniciativa
    private void iniciativa(PersonagemJogador pj, Inimigo[] enemy){
        // Iniciativa do jogador
        int valorIniciativaPJ = pj.getHabilidade() + d6.jogar2();
        // Iniciativas jogador enemy
        int[] listaIniciativa = new int[enemy.length];
        listaIniciativa[enemy.length] = valorIniciativaPJ;
        for(int i = 0;i<enemy.length;i++){
            listaIniciativa[i] = enemy[i].getHabilidade()+d6.jogar2();
        }
        
        
        // Organizando a ordem de ataque
        Personagem pseudop;int temp;
        int atual=-1;int posicao;
        for(int i = 0;i<enemy.length;i++){
            //atual = listaIniciativa[i];
            posicao = i;
            for(int j = i;j<enemy.length;j++)
                if(listaIniciativa[i] > listaIniciativa[j]){
                    posicao = j;
                    atual = listaIniciativa[j];
                }
            // Substituicao dos valores, mas antes verifique-os
            if(i!=posicao){
                temp = listaIniciativa[i];
                listaIniciativa[i]=atual;
                listaIniciativa[posicao]=temp;
                
                pseudop = enemy[i];
                enemy[i] = enemy[posicao];
                enemy[posicao] = enemy[i];
            }
        }
    }  
    
    // mostrar estado da batalha do jogador e de um oponente
    public static void estado(PersonagemJogador pj, Inimigo enemy){
        System.out.println("\nPj, nome: "+pj.nome+". Habilidade: "+pj.getHabilidade()+". Energia: "+pj.getEnergiaAtual()+"\nInimigos: \n");
        System.out.print(enemy.nome+" \nEnergia: "+enemy.getEnergiaAtual()+". Habilidade: "+enemy.getHabilidade());
    }
    
    // mostrar estado da batalha do jogador e dos oponentes
    public static void estado(PersonagemJogador pj, Inimigo[] enemy){
        System.out.println("\nPj, nome: "+pj.nome+". Habilidade: "+pj.getHabilidade()+". Energia: "+pj.getEnergiaAtual()+". Sorte: "+pj.getSorte()+"\nInimigos: \n");
        for(int i=0;i<enemy.length;i++)
            System.out.print(enemy[i].nome+" \nEnergia: "+enemy[i].getEnergiaAtual()+". Habilidade: "+enemy[i].getHabilidade()+"\n");
    }
    
    // define os acontecimentos durante um combate contra os inimigos, nao tira-se dano dos oponentes apenas recebe-se ou nao
    public static void atacado(PersonagemJogador pj, Personagem[] enemy, int n){
        int escolhaS; // Verifica a sorte
        Scanner opcao = new Scanner(System.in); // escolha da opcao
        
        boolean npcAcertouPJ = true; // verifica se acertou o inimigo ou se foi acertado
        int atkPJ;
        int atkNPC;
        
        for(int i=0; i<enemy.length; i++){
        if(n!=i && enemy[i].getEnergiaAtual()>0){
            atkPJ = pj.fa();
            atkNPC = enemy[i].fa();

            System.out.println("Voce confronta com "+enemy[i].nome);
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
    
    // define os acontecimentos durante um combate contra um inimigo, nao tira-se dano do oponente apenas recebe-se ou nao
    private static void atacado(PersonagemJogador pj, Inimigo enemy) {
        int escolhaS; // Verifica a sorte
        Scanner opcao = new Scanner(System.in); // escolha da opcao
        
        boolean npcAcertouPJ = true; // verifica se acertou o inimigo ou se foi acertado
        int atkPJ;
        int atkNPC;
        
            atkPJ = pj.fa();
            atkNPC = enemy.fa();

            System.out.println("Voce confronta com "+enemy.nome);
            if(atkPJ > atkNPC){
                System.out.println("Voce acertou!! Nao saira ferido.");
                npcAcertouPJ = false;
            }else if(atkPJ < atkNPC){
                System.out.println("Voce errou e se machucou!! Mas pode tentar usar a sorte para reduzi-lo, se falhar o dano sera ainda maior..");
               pj.dano();
               npcAcertouPJ = true;
            System.out.println("Usar sorte? \nDigite: 0, se nao\nDigite: 1, se sim");
            escolhaS = opcao.nextInt();
            if(escolhaS==1 && atkPJ != atkNPC){
                boolean teste = Sorte.jogar(pj);
                if(npcAcertouPJ){ // Se PJ acertou o NPC
                    if(teste){
                        System.out.println("Voce e sortudo, e voce escapa dos golpes ao pouco");
                        pj.dano();
                    }else{
                        System.out.println("Voce e azarado, os golpes do oponente o atingem com precisao e forca");
                        pj.ganhaEnergia(1);
                        }
                    }
                }
            }else{
                System.out.println("Empate, aguarde o proximo round");
             }
        }
    
    // ataque do jogador contra um oponente escolhido, se falhar no ataque recebera dano
    public static int atacar(PersonagemJogador pj, Personagem enemy){
        int escolhaS; // Verifica a sorte
        Scanner opcao = new Scanner(System.in); // escolha da opcao
        
        boolean pjAcertouNPC = true; // verifica se acertou o inimigo ou se foi acertado
        int atkPJ = pj.fa();
        int atkNPC = enemy.fa();
        
        System.out.println("Voce confronta com "+enemy.nome);
        if(atkPJ > atkNPC){
            System.out.println("Voce acertou!! \nMas, pode tentar usar a sorte para aumentar o dano, contudo se errar seu dano será menor..");
            enemy.dano();
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
                    enemy.dano();
                }else{
                    System.out.println("Voce e azarado, seu golpe mal atinge o oponente");
                    enemy.ganhaEnergia(1);
                }
            }else{
                if(teste){
                    System.out.println("Voce e sortudo, o golpe do oponente foi de raspao");
                    pj.dano();
                }else{
                    System.out.println("Voce e azarado, o golpe do oponente o atinge com muita forca");
                    pj.ganhaEnergia(1);
                }
            }
        }
        
        // Verifica se o oponente morreu nesse ataque
        if(enemy.getEnergiaAtual()<=0){
            System.out.println("Voce acaba de derrubar um "+enemy.nome+"\nUm inimigo a menos.\n");
            return 1;
        }else
            return 0;
    }
    
    // ataque do jogador contra os oponentes escolhidos, se falharem no ataque recebera dano
    public static int atacar(PersonagemJogador pj, Personagem[] enemy, int n){
        int escolhaS; // Verifica a sorte
        Scanner opcao = new Scanner(System.in); // escolha da opcao
        
        boolean pjAcertouNPC = true; // verifica se acertou o inimigo ou se foi acertado
        int atkPJ = pj.fa();
        int atkNPC = enemy[n].fa();
        
        System.out.println("Voce confronta com "+enemy[n].nome);
        if(atkPJ > atkNPC){
            System.out.println("Voce acertou!! \nMas, pode tentar usar a sorte para aumentar o dano, contudo se errar seu dano será menor..");
            enemy[n].dano();
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
                    enemy[n].dano();
                }else{
                    System.out.println("Voce e azarado, seu golpe mal atinge o oponente");
                    enemy[n].ganhaEnergia(1);
                }
            }else{
                if(teste){
                    System.out.println("Voce e sortudo, o golpe do oponente foi de raspao");
                    pj.dano();
                }else{
                    System.out.println("Voce e azarado, o golpe do oponente o atinge com muita forca");
                    pj.ganhaEnergia(1);
                }
            }
        }
        
        // Verifica se o oponente morreu nesse ataque
        if(enemy[n].getEnergiaAtual()<=0){
            System.out.println("Voce acaba de derrubar um "+enemy[n].nome+"\nUm inimigo a menos.\n");
            return 1;
        }else
            return 0;
    }
}
