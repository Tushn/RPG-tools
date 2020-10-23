package XML;

import Personagem.*;
import Eventos.*;
import Alteracoes.*;
import Vantagens.*;
import java.io.IOException;
import java.util.Vector;  
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

// Acessar o XML, por meio da classe LerXML e retornar uma instancia requerida
public class AcessoXML {
    // variavel estatica que representa um vetor
    public static Vector data;
    /*
     * Sequencias dos Eventos
     */
    public AcessoXML(String fileXML, String fileXML2, int id)// Um XML dos eventoscontrole e o outro das escolhas
    { 
        try{
            LerXML reader = new LerXML("src\\XML\\"+fileXML, "src\\XML\\"+fileXML2);
            
            data = reader.lerXMLEventos(id);
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
                
        }catch(Exception a){
            a.printStackTrace();
        }
    }
    
    /* 
     * Acesso as classes derivadas da Alteracao
     */
    public AcessoXML(String fileXML, int tipo, int subtipo){
        try{
            LerXML reader = new LerXML("src\\XML\\"+fileXML);
            if(fileXML=="incrementodecremento.xml")
                data = reader.lerXMLAlteracao(tipo, subtipo);
            else if(fileXML=="modificacao.xml")
                data = reader.lerXMLAlteracao(tipo, subtipo);
            
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
           
                data = reader.lerFichaXMLNPC();
            
        } catch( Exception e ) {  
                e.printStackTrace();
        }
    }
    
    public AcessoXML(String fileXML, String fileXML2, String fileXML3, String fileXML4, int id) {  
        try {
            // informe o caminho correto do seu arquivo xml  
            LerXML reader = new LerXML( "src\\XML\\"+fileXML, "src\\XML\\"+fileXML2, "src\\XML\\"+fileXML3, "src\\XML\\"+fileXML4);
          

                data = reader.lerFichaXMLPersonagem(id);
            
        } catch( Exception e ) {  
                e.printStackTrace();
        }
    }
    
  }  