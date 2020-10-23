package Vantagens;

import Eventos.Combate;
import Personagem.*;
import Recursos.Sorte;
import Recursos.d6;
import java.util.Scanner;

public class Poder {
    int id;
    String nome;
    String descricao;
    int idtipo;
    String tipo;
    int custo;
    int valor;
    int contRandom;

    public Poder(int id, String nome, String descricao, int idtipo, String tipo, int custo, int valor, int contRandom) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.idtipo = idtipo;
        this.tipo = tipo;
        this.custo = custo;
        this.valor = valor;
        this.contRandom = contRandom;
    }
    
    public void getNomeDescricao(){
        System.out.println("Nome: "+nome+"\nDescricao: "+descricao+"\nCusto: "+custo);
    }
    
    public void getNome(){
        System.out.println("Nome: "+nome);
    }
    
    public void usarPoder(PersonagemJogador pj, Inimigo[] inimigo, int alvo, boolean jogadorLancou){
        if(pj.getMagia()>=this.custo){
            pj.gastarMagia(this.custo);
            int resultado = d6.jogar(this.contRandom)+this.valor;
            if(jogadorLancou)
                switch(idtipo){
                    case 0: // dano direto e simples
                        resultado = inimigo[alvo].getEnergia()-resultado;
                        this.getNomeDescricao();
                        System.out.println("Seu oponente sofre comum um dano iqual a: "+resultado);
                        inimigo[alvo].setEnergia(resultado);
                        Combate.atacado(pj, inimigo, -1);
                        break;

                    case 1: // recuperacao direta e simples
                        this.getNomeDescricao();
                        System.out.println("Você recupera "+resultado);
                        pj.ganhaEnergia(resultado);
                        Combate.atacado(pj, inimigo, -1);
                        break;

                    case 2: // ganhar sorte
                        this.getNomeDescricao();
                        System.out.println("Você ganha: "+resultado+" de sorte.");
                        pj.ganhaSorte(resultado);
                        Combate.atacado(pj, inimigo, -1);
                        break;
                        
                    case 3: // golpe especial
                        this.getNomeDescricao();
                        System.out.println("Você atingiu seu oponente, quer usar a Sorte para aumentar o dano? Gaste um ponto para tal?\n Digite 0: Não\nDigite 1: Sim");
                        Scanner escolha = new Scanner(System.in);
                        int opcao = escolha.nextInt();
                        if(opcao==0)
                            inimigo[alvo].dano(4);
                        else{
                            if(Sorte.jogar(pj)){
                                System.out.println("Você acertou o bendito golpe de uma forma pouco vista, você tem grande poder.");
                                inimigo[alvo].dano(8);
                            }else{
                                System.out.println("Você se descuidou e fez um mal uso do poder, contudo ele ainda causou dano.");
                                inimigo[alvo].dano();
                            }    
                        }
                    default:
                        System.out.println("Este feitiço não existe em seu grimório.");
                }
            }else{
                System.out.println("Você não possui pontos de magia suficientes para lançar esta magia.");
        }
    }
}
