package XML;
import java.util.Vector;  
import java.util.Scanner;      

    public class AcessoXML {  
         static String[] nomes;
        static int cont = 0;
        static Vector usuarios;
        
        public AcessoXML(String fileXML) {  
        try {  
            // informe o caminho correto do seu arquivo xml  
            LerXML reader = new LerXML( "src\\XML\\"+fileXML );  
          
            usuarios = reader.lerUsuariosGuj();  
          
            // imprime na tela os usuarios cadastrados      
            for( int i=0; i<usuarios.size(); i++ ) {  
                UsuarioGUJ usuario = (UsuarioGUJ) usuarios.get( i );  
            
                System.out.println( "Usuario id: " + usuario.id );  
                System.out.println( "      nome: " + usuario.nome );  
                System.out.println( "     idade: " + usuario.idade );  
                System.out.println( "     email: " + usuario.email );
                cont++;
            }  
        } catch( Exception e ) {  
            e.printStackTrace();
        }
    }
        
        public static void main( String[] args ) {  
            new AcessoXML("cadastro.xml");
            System.out.println("No máximo: "+(AcessoXML.cont-1));
            Scanner number = new Scanner(System.in);
            int num = 0;
            while(true){
                num = number.nextInt();
                if(num>=AcessoXML.cont){
                    System.out.println("Encerrar...");
                    break;
                }
                UsuarioGUJ usuario = (UsuarioGUJ) AcessoXML.usuarios.get( num );
                System.out.println( "      nome: " + usuario.nome );
                System.out.println( "      idade: " + usuario.idade );
            }
        }
    }  