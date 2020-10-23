package XML;
import Eventos.*;
import NPC.Inimigo;
import NPC.*;
import Personagem.*;
import RacaProfissao.*;
import java.lang.String.*;
import java.io.*;  
    import java.net.*;  
    import java.util.*;  
    import javax.xml.parsers.*;  
    import org.w3c.dom.*;  
import org.xml.sax.SAXException;
      
    public class LerXML {  
      
    private static String xmlPathname;
    private static String xmlPathname2;
    private static String xmlPathname3;
    private static DocumentBuilderFactory dbf;
    private static DocumentBuilder db;
    private static Document doc;
    private static Element elem;
    
    public LerXML( String path ) throws ParserConfigurationException, SAXException, IOException {  
        xmlPathname = path;
    }
    public LerXML( String path, String path2 ) {  
        xmlPathname = path;
        xmlPathname2 = path2;
    }
    public LerXML( String path, String path2, String path3 ) {  
        xmlPathname = path;
        xmlPathname2 = path2;
        xmlPathname3 = path3;
    }   
    
    /*
     * Classe de suporte, para refatoracao de codigo
     */
    public static void iniciarXML() throws ParserConfigurationException, SAXException, IOException{
        dbf = DocumentBuilderFactory.newInstance();  
        db = dbf.newDocumentBuilder();
        doc = db.parse( xmlPathname );
        elem = doc.getDocumentElement();
    }

    public Vector lerXMLEncontros(int subtipo) throws ParserConfigurationException, SAXException, IOException, Exception{
        iniciarXML();
        int[] idfichas = new int[20]; // vetor com o id das fichas
        int[] numOponentes = new int[20]; // vetor com o numero de oponentes por ficha
        int[] idoutros = new int[20]; // vetor com os ids de outros encontros
        
        // Retorno do objeto para instancia da classe Encontro
        Vector encontros = new Vector();
        
        NodeList nodeEncontro = elem.getElementsByTagName( "encontro" );
        Element tagPlayer = (Element) nodeEncontro.item(subtipo);
        int id = Integer.parseInt( tagPlayer.getAttribute( "id" ) );
        Integer idficha = new Integer( getChildTagValue( tagPlayer, "idficha" ) );
        Integer num = new Integer( getChildTagValue( tagPlayer, "num" ) );
        Integer idoutro = new Integer( getChildTagValue( tagPlayer, "idoutro" ) );
        
        idfichas[0] = idficha;
        numOponentes[0] = num;
        idoutros[0] = idoutro;
        int cont = 0;
        while(idoutro>0 && cont < 20){
            cont++; // contador do id atual
            tagPlayer = (Element) nodeEncontro.item(idoutro); // atualiza novo nodo com os combatentes
            idfichas[cont] = new Integer( getChildTagValue( tagPlayer, "idficha" ) );
            numOponentes[cont] = new Integer( getChildTagValue( tagPlayer, "num" ) );
            idoutro = new Integer( getChildTagValue( tagPlayer, "idoutro" ) );
            idoutros[cont] = idoutro;
        }
        System.out.println(cont);
        // define o tamanho do numero de inimigos
        int[] idInimigos = new int[cont];
        int[] numInimigos = new int[cont];
        // passagem dos valores para estes vetores
        for(int i=0; i < cont; i++){
            idInimigos[i] = idfichas[i];
            numInimigos[i] = numOponentes[i];
        }
        
        Encontros encontro = new Encontros(idInimigos, numInimigos);
        encontros.addElement( encontro );
        
        // Retorna a lista dos encontros
        return encontros;
    }
    
    public Vector lerXMLEventos(int idEscolhido) throws ParserConfigurationException, SAXException, IOException, Exception{ 
        iniciarXML();
        
        // Carregar pagina de escolhas e seus elementos
        doc = db.parse( xmlPathname2 );
        Element elem2 = doc.getDocumentElement();
        
        // Vetor dos eventos e escolhas
        Vector cenas = new Vector();
        
        // Carregar as informações por nodos com id
        NodeList nodeCenas = elem.getElementsByTagName( "evento" );
        NodeList nodeEscolhas = elem2.getElementsByTagName( "escolhas" );
        
        // Escolha da cena a ser descrita
        Element tagPlayer = (Element) nodeCenas.item(idEscolhido);
        int id = Integer.parseInt( tagPlayer.getAttribute( "id" ) );
        String descricao = getChildTagValue( tagPlayer, "descricao" );
        Integer comandoinicial = new Integer( getChildTagValue( tagPlayer, "comandoinicial" ) );
        Integer comandofinal = new Integer( getChildTagValue( tagPlayer, "comandofinal" ) );
        Integer tipo = new Integer ( getChildTagValue( tagPlayer, "tipo" ) );
        Integer subtipo = new Integer ( getChildTagValue ( tagPlayer, "subtipo" ));
        
        // Pesquisar por opcoes de comandos no XML, escolhas, e uma Sequencias cena;
        Sequencias cena = new Sequencias();
        int[] idsaida = new int[comandofinal-comandoinicial+1];
        String comandos = "";
        for(int i = comandoinicial; i <= comandofinal; i++){
            Element tagPlayer2 = (Element) nodeEscolhas.item(i);
            int id2 = Integer.parseInt( tagPlayer2.getAttribute( "id" ));
            String resposta = getChildTagValue( tagPlayer2, "resposta" );
            idsaida[i-comandoinicial] = new Integer ( getChildTagValue( tagPlayer2, "idsaida") );
            comandos = comandos.concat(resposta).concat("Digite: "+(i-comandoinicial)).concat("\n");
        }
        
        /*
         * Old Version:
         *   Carregar classe da cena atual, e uma Sequencias cena;
         * cena = new Sequencias(id,descricao,comandos,idsaida);
         */
        cena = new Sequencias(id,descricao,comandos,idsaida,tipo,subtipo);
        // Transferir para o vetor as características da cena atual
        cenas.addElement( cena );
        
        return cenas;
    }
    
    public Vector lerFichaXMLPersonagem() throws Exception { 
        iniciarXML();
        
        doc = db.parse( xmlPathname2 );
        Element elem2 = doc.getDocumentElement();
        
        doc = db.parse( xmlPathname3 );
        Element elem3 = doc.getDocumentElement();
        
        // pega todos os elementos usuario do XML  
        NodeList nl = elem.getElementsByTagName( "player" );  
        Vector fichas = new Vector();     
        
            // pega todos os elementos usuario do XML  
        NodeList n2 = elem2.getElementsByTagName( "raca" );
        Vector fichas2 = new Vector();
        
            // pega todos os elementos usuario do XML  
        NodeList n3 = elem3.getElementsByTagName( "kit" );
        Vector fichas3 = new Vector();
        for( int i=0; i<nl.getLength(); i++ ) {  
            // Ajustes dos dados do personagem oriundos do XML
            Element tagPlayer = (Element) nl.item( i );
            int id = Integer.parseInt( tagPlayer.getAttribute( "id" ) );  
            String nome = getChildTagValue( tagPlayer, "nome" );
            Integer habilidade = new Integer( getChildTagValue( tagPlayer, "habilidade" ) );  
            Integer energia = new Integer( getChildTagValue( tagPlayer, "energia" ) );
            Integer sorte = new Integer( getChildTagValue( tagPlayer, "sorte" ) );
            Integer magia = new Integer( getChildTagValue( tagPlayer, "magia" ) );
            String descricao = getChildTagValue( tagPlayer, "descricao" );
            Integer intRaca = new Integer( getChildTagValue( tagPlayer, "raca" ) );
            Integer intKit = new Integer( getChildTagValue( tagPlayer, "kit" ) );
            
            // Ajustes dos dados da raca oriundos do XML
            Element tagPlayer2 = (Element) n2.item( intRaca );
            String nomeRaca = getChildTagValue( tagPlayer2, "nome" );
            Integer habilidadeRaca = new Integer( getChildTagValue( tagPlayer2, "habilidade" ) );  
            Integer energiaRaca = new Integer( getChildTagValue( tagPlayer2, "energia" ) );
            Integer sorteRaca = new Integer( getChildTagValue( tagPlayer2, "sorte" ) );
            Integer magiaRaca = new Integer( getChildTagValue( tagPlayer2, "magia" ) );
            String descricaoRaca = getChildTagValue( tagPlayer2, "descricao" );
            Raca raca = new Raca(nomeRaca, descricaoRaca, habilidadeRaca, energiaRaca, sorteRaca, magiaRaca );
            fichas2.addElement( raca );
            fichas2.get(0);
            
            // Ajustes dos dados da profissao, kit, oriundos do XML
            Element tagPlayer3 = (Element) n3.item( intKit );       
            String nomeKit = getChildTagValue( tagPlayer3, "nome" );
            Integer habilidadeKit = new Integer( getChildTagValue( tagPlayer3, "habilidade" ) );  
            Integer energiaKit = new Integer( getChildTagValue( tagPlayer3, "energia" ) );
            Integer sorteKit = new Integer( getChildTagValue( tagPlayer3, "sorte" ) );
            Integer magiaKit = new Integer( getChildTagValue( tagPlayer3, "magia" ) );
            String descricaoKit = getChildTagValue( tagPlayer3, "descricao" );
            Kit kit = new Kit(nomeKit, descricaoKit, habilidadeKit, energiaKit, sorteKit, magiaKit );
            fichas3.addElement(kit);
            fichas3.get(0);
            
            // cria uma nova instancia do player com os dados do xml, usar construtores publicos
            PersonagemJogador player = new PersonagemJogador(nome, descricao, habilidade, energia, sorte, magia, i, raca, kit );  

            fichas.addElement( player );  
        }  
     
        return fichas;  
    } 
        
    public Vector lerFichaXMLNPC() throws Exception { 
        iniciarXML();
        
        doc = db.parse( xmlPathname2 );
        Element elem2 = doc.getDocumentElement();
        
        doc = db.parse( xmlPathname3 );
        Element elem3 = doc.getDocumentElement();
        
        // pega todos os elementos usuario do XML  
        NodeList nl = elem.getElementsByTagName( "inimigo" );  
        Vector fichas = new Vector();     
        
        // pega todos os elementos usuario do XML  
        NodeList n2 = elem2.getElementsByTagName( "raca" );
        Vector fichas2 = new Vector();
        
        // pega todos os elementos usuario do XML  
        NodeList n3 = elem3.getElementsByTagName( "kit" );
        Vector fichas3 = new Vector();
        for( int i=0; i<nl.getLength(); i++ ) {  
            // Ajustes dos dados do personagem oriundos do XML
            Element tagPlayer = (Element) nl.item( i );
            int id = Integer.parseInt( tagPlayer.getAttribute( "id" ) );  
            String nome = getChildTagValue( tagPlayer, "nome" );
            Integer habilidade = new Integer( getChildTagValue( tagPlayer, "habilidade" ) );  
            Integer energia = new Integer( getChildTagValue( tagPlayer, "energia" ) );
            Integer magia = new Integer( getChildTagValue( tagPlayer, "magia" ) );
            String descricao = getChildTagValue( tagPlayer, "descricao" );
            Integer intRaca = new Integer( getChildTagValue( tagPlayer, "raca" ) );
            Integer intKit = new Integer( getChildTagValue( tagPlayer, "kit" ) );
            
            // Ajustes dos dados da raca oriundos do XML
            Element tagPlayer2 = (Element) n2.item( intRaca );
            String nomeRaca = getChildTagValue( tagPlayer2, "nome" );
            Integer habilidadeRaca = new Integer( getChildTagValue( tagPlayer2, "habilidade" ) );  
            Integer energiaRaca = new Integer( getChildTagValue( tagPlayer2, "energia" ) );
            Integer magiaRaca = new Integer( getChildTagValue( tagPlayer2, "magia" ) );
            String descricaoRaca = getChildTagValue( tagPlayer2, "descricao" );
            Raca raca = new Raca(nomeRaca, descricaoRaca, habilidadeRaca, energiaRaca, 0, magiaRaca );
            fichas2.addElement( raca );
            fichas2.get(0);
            
            // Ajustes dos dados da profissao, kit, oriundos do XML
            Element tagPlayer3 = (Element) n3.item( intKit );       
            String nomeKit = getChildTagValue( tagPlayer3, "nome" );
            Integer habilidadeKit = new Integer( getChildTagValue( tagPlayer3, "habilidade" ) );  
            Integer energiaKit = new Integer( getChildTagValue( tagPlayer3, "energia" ) );
            Integer magiaKit = new Integer( getChildTagValue( tagPlayer3, "magia" ) );
            String descricaoKit = getChildTagValue( tagPlayer3, "descricao" );
            Kit kit = new Kit(nomeKit, descricaoKit, habilidadeKit, energiaKit, 0, magiaKit );
            fichas3.addElement(kit);
            fichas3.get(0);
            
            // cria uma nova instancia do player com os dados do xml, usar construtores publicos
            Inimigo player = new Inimigo(nome, descricao, habilidade, energia, magia, i, raca, kit );  

            fichas.addElement( player );  
        }  
     
        return fichas;  
    } 

    private String getChildTagValue( Element elem, String tagName ) throws Exception {  
        NodeList children = elem.getElementsByTagName( tagName );  
        if( children == null ) return null;  
            Element child = (Element) children.item(0);  
        if( child == null ) return null;  
            return child.getFirstChild().getNodeValue();  
        }  

}  