package XML;
import Personagem.*;
import NPC.*;
import java.util.Vector;  
import java.util.Scanner;      

public class AcessoXML {
    public static int cont = 0;
    public static Vector data;
    public String pathTest="vazio";
    /*
     * Sequencias dos Eventos
     */
    public AcessoXML(String fileXML, String fileXML2, int id)// Um XML dos eventoscontrole e o outro das escolhas
    { 
        try{
            LerXML reader = new LerXML("src\\XML\\"+fileXML, "src\\XML\\"+fileXML2);
            
            data = reader.lerXMLEventos(id);
            cont = data.size();
        }catch(Exception a){
            a.printStackTrace();
        }
    }
    // Encontro e EventosChave
    public AcessoXML(String fileXML, int id)// Um XML dos eventoscontrole e o outro das escolhas
    { 
        try{
            LerXML reader = new LerXML("src\\XML\\"+fileXML);
            if(fileXML=="encontros.xml")
                data = reader.lerXMLEncontros(id);
            else if(fileXML=="testeschave.xml")
                data = reader.lerXMLEventosChave(id);
            else if(fileXML=="testes.xml")
                data = reader.lerXMLTestarValores(id);
            
            cont = data.size();
        }catch(Exception a){
            a.printStackTrace();
        }
    }
    
    /*
     * PJs e NPCs
     */
    public AcessoXML(String fileXML, String fileXML2, String fileXML3) {  
        try {
            // informe o caminho correto do seu arquivo xml  
            LerXML reader = new LerXML( "src\\XML\\"+fileXML, "src\\XML\\"+fileXML2, "src\\XML\\"+fileXML3 );  
          
            if(fileXML=="player.xml")
                data = reader.lerFichaXMLPersonagem();  
            else if(fileXML=="inimigo.xml")
                data = reader.lerFichaXMLNPC();
            
            cont = data.size(); // contador do número de ids do arquivo
        } catch( Exception e ) {  
                e.printStackTrace();
        }
    }
    
    public static void main( String[] args ) {  
            new AcessoXML("player.xml","raca.xml","kits.xml");
            System.out.println("No máximo: "+(AcessoXML.cont-1));
            Scanner number = new Scanner(System.in);
            boolean ok=true;
            int num = 0;
            while(true){
                try{
                    num = number.nextInt();
                }catch(Exception a){
                    String fg = number.next();
                    System.out.println("Digitado: "+fg);                    
                    PersonagemJogador ficha = (PersonagemJogador) AcessoXML.data.get( num );
                    break;
                }
                if(num==10){
                    new AcessoXML("inimigo.xml","raca.xml","kits.xml");
                    ok = false;
                    num = 0;
                }
                if(num==20){
                    new AcessoXML("player.xml","raca.xml","kits.xml");
                    ok = true;
                    num = 0;
                }
                if(num>=AcessoXML.cont){
                    System.out.println("Encerrar...");
                    break;
                }
                if(ok){
                    PersonagemJogador ficha = (PersonagemJogador) AcessoXML.data.get( num );
                    System.out.println( "      nome: " + ficha.nome );
                    System.out.println("Habilidade: " + ficha.getHabilidade());
                    System.out.println("Profissao: " + ficha.getNomeKit());
                    System.out.println("Raca: " + ficha.getNomeRaca());
                }else{
                    Inimigo ficha = (Inimigo) AcessoXML.data.get( num );
                    System.out.println( "      nome: " + ficha.nome );
                    System.out.println("Habilidade: " + ficha.getHabilidade());
                    System.out.println("Profissao: " + ficha.getNomeKit());
                    System.out.println("Raca: " + ficha.getNomeRaca());
                }
            }
        }
    }  