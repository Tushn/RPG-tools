package XML;
import java.util.Vector;
public class ControleEventosXML {
    public static int cont = 0;
    public static Vector eventos;
        
    public ControleEventosXML(String fileXML, int id){
        try{
            // informe o caminho correto do seu arquivo xml, mais o id de controle
            LerXML reader = new LerXML( "src\\XML\\"+fileXML );  

            //eventos = reader.lerFichaXMLEventos();
            
            cont = eventos.size(); // contador do número de ids do arquivo
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
