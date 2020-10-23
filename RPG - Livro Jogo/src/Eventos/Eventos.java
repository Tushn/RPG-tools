package Eventos;

/**
 *
 * @author Artur de Oliveira
 */
public abstract class Eventos {
    int id;
    String descricao;
    
    public int[] idSaida;
    String comandos;
    int tipo;
    int subtipo;
    
    public Eventos(){}
    protected Eventos(int id, String descricao, String comandos, int[] idSaida) {
        this.id = id;
        this.descricao = descricao;
        this.comandos = comandos;
        this.idSaida = idSaida;
    }
    
    protected Eventos(int id, String descricao, String comandos, int[] idSaida, int tipo) {
        this.id = id;
        this.descricao = descricao;
        this.comandos = comandos;
        this.idSaida = idSaida;
        this.tipo = tipo;
    }
    
    public void listaComandos(){
        System.out.println( "Descricao: " + this.descricao );
    }
    public abstract int nextEvent(int i);
  
    // obtem tipo
    public int getTipo(){
        return this.tipo;
    }

    // obtem subtipo
    public int getSubtipo(){
        return this.subtipo;
    }
    
}
