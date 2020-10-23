package XML;
import java.io.*;  
    import java.net.*;  
    import java.util.*;  
    import javax.xml.parsers.*;  
    import org.w3c.dom.*;  
      
    public class LerXML {  
      
    // caminho (path) do arquivo XML  
    private String xmlPathname;  
      
    // construtor que seta o caminho do XML  
    public LerXML( String path ) {  
        xmlPathname = path;  
    }
      
    // le o XML carregando os dados dos usuários em um Vector.  
    // retorna o vector contendo os usuários cadastrados no XML.  
    public Vector lerUsuariosGuj() throws Exception {  
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
        DocumentBuilder db = dbf.newDocumentBuilder();  
        Document doc = db.parse( xmlPathname ); 
        Element elem = doc.getDocumentElement();  
        // pega todos os elementos usuario do XML  
        NodeList nl = elem.getElementsByTagName( "usuario" );  
        // prepara o vetor      
        Vector usuarios = new Vector();     

        // percorre cada elemento usuario encontrado  
        for( int i=0; i<nl.getLength(); i++ ) {  
            Element tagUsuario = (Element) nl.item( i );  
            // pega os dados cadastrado para o usuario atual  
            int id = Integer.parseInt( tagUsuario.getAttribute( "id" ) );  
            String nome = getChildTagValue( tagUsuario, "nome" );  
            Integer idade = new Integer( getChildTagValue( tagUsuario, "idade" ) );  
            String email = getChildTagValue( tagUsuario, "email" );   

            // cria uma nova instancia do UsuarioGuj com os dados do xml  
            UsuarioGUJ usuarioGuj = new UsuarioGUJ( id, nome, idade, email );  

            // adiciona o usuario na coleção (vector) de usuários do guj  
            usuarios.addElement( usuarioGuj );  
        }  
     
        return usuarios;  
    } 


    // este método lê e retorna o conteúdo (texto) de uma tag (elemento)  
    // filho da tag informada como parâmetro. A tag filho a ser pesquisada  
    // é a tag informada pelo nome (string)  
    private String getChildTagValue( Element elem, String tagName ) throws Exception {  
        NodeList children = elem.getElementsByTagName( tagName );  
        if( children == null ) return null;  
            Element child = (Element) children.item(0);  
        if( child == null ) return null;  
            return child.getFirstChild().getNodeValue();  
        }  
    }  