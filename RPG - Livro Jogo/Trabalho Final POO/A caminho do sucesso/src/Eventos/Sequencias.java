package Eventos;
import java.util.Scanner;

// Classe responsavel pela transicao de cenas dos eventos
public class Sequencias extends Eventos{

    public Sequencias() {}
    public Sequencias(int id, String descricao, String comandos, int[] idSaida) {
        this.id = id; 
        this.descricao = descricao;
        this.comandos = comandos;
        this.idSaida = idSaida;
    }
    public Sequencias(int id, String descricao, String comandos, int[] idSaida, int tipo, int subtipo) {
        this.id = id; 
        this.descricao = descricao;
        this.comandos = comandos;
        this.idSaida = idSaida;
        this.tipo = tipo;
        this.subtipo = subtipo;
    }

    // Descreve o ambiente
    public void descreveCena(){
        System.out.println( descricao );
    }
    
    // Mostra os comandos por turno
    public void listaComandos() {
        System.out.println("Escolha: " + comandos);
    }
    
    public int getId(){
        return this.id;
    }
    public int nextEvent(int i) {
        return idSaida[i];
    }
    
}
