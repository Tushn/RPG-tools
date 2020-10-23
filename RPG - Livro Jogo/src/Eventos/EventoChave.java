package Eventos;
public class EventoChave implements Teste {
    int idteste;
    int trueid;
    int falseid;
    String descricaotrueid;
    String descricaofalseid;

    public EventoChave(int idteste, int trueid, int falseid, String descricaotrueid, String descricaofalseid) {
        this.idteste = idteste;
        this.trueid = trueid;
        this.falseid = falseid;
        this.descricaotrueid = descricaotrueid;
        this.descricaofalseid = descricaofalseid;
    }
    
    public int testar(int[] lista, int max){
        boolean teste=false;
        
        for(int i=0;i<max;i++)
            // Se encontrarem o evento chave em algum momento pare a procura
            if(lista[i]==this.idteste){
                teste=true;
                break;
            }
        
        // Descreva o resultado do teste e o retorna o eventoid ele ocasionara
        return getId(teste);
    }
    
    public int getId(boolean teste){
        if(teste){
            System.out.println(descricaotrueid);
            return this.trueid;
        }else{
            System.out.println(descricaofalseid);
            return this.falseid;
        }
    }
}
