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
  
    // Jogar 'n' dados de 6 faces
    public static int jogar(int n){
        int total = 0; // valor total da soma dos resultados dos dados de 6 faces
        for(int i = 0; i < n; i++)
            total = total + jogar();
        return total;
    }
    
    public static void main(String[] args){
        System.out.println("Dado: "+jogar(0));
        System.out.println("Dado: "+jogar(0));
        System.out.println("Dado: "+jogar(0));
    }
}