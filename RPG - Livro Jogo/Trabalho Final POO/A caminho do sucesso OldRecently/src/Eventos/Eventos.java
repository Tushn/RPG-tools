package Eventos;

/**
 *
 * @author Artur de Oliveira
 */
public abstract class Eventos {
    int id;
    String nome;
    String descricao;
    
    public int[] idSaida;
    String comandos;
    String[] listaRespostas;
    int tipo;
    int subtipo;
    
    public Eventos(){}
    protected Eventos(int id, String nome, String descricao, String comandos, int[] idSaida) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.comandos = comandos;
        this.idSaida = idSaida;
        
        // Ajustar comandos na lista
       /* for(int i=0;i<comandos.length;i++)
            nomesComandos = nomesComandos.concat(comandos[i].concat("\n"));*/
    }
    protected Eventos(int id, String nome, String descricao, String comandos, int[] idSaida, int tipo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.comandos = comandos;
        this.idSaida = idSaida;
        this.tipo = tipo;
        
         // Ajustar comandos na lista
       /* for(int i=0;i<comandos.length;i++)
            nomesComandos = nomesComandos.concat(comandos[i].concat("\n"));*/
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
    
    //public int escolha(int n){ return this.idSaida[n]; }
}
