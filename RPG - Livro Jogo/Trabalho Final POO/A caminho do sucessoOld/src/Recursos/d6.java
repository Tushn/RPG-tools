package Recursos;
import java.util.Random;
/**
 *
 * @author Artur de Oliveira
 */
public class d6 {
    public static int jogar(){
        Random face = new Random(); 
        return face.nextInt( 6 )+1;
    }
    
    public static int jogar2(){
        Random face1 = new Random(); 
        Random face2 = new Random(); 
        return face1.nextInt( 6 )+ 2 + face2.nextInt( 6 );
    }
}
