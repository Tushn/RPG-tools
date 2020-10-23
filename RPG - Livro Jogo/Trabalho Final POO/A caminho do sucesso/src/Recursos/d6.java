package Recursos;
import java.util.Random;
/**
 *
 * @author Artur de Oliveira
 */
public class d6 {
    // Jogar um dado somente de 6 faces
    public static int jogar(){
        Random face = new Random(); 
        return face.nextInt( 6 )+1;
    }
    
    // Jogar dois dados de 6 faces
    public static int jogar2(){
        Random face1 = new Random(); 
        Random face2 = new Random(); 
        return face1.nextInt( 6 )+ 2 + face2.nextInt( 6 );
    }
    
    // Jogar 'n' dados de 6 faces
    public static int jogar(int n){
        Random dado = new Random();
        int total = 0; // valor total da soma dos resultados dos dados de 6 faces
        for(int i = 0; i < n; i++)
            total = total + dado.nextInt(6);
        return total;
    }
}