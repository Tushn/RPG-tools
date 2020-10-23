package Eventos;
import java.util.Scanner;
/**
 *
 * @author Casa
 */
public class Inicio extends Eventos{
    
    public Inicio() {
        super(1, 
                "Inicio",
                "Você trabalha na construção de um castelo para um estranho aristocrata.\n"
                + "Sua casa é soturna e possui um vasto jardim que muitos dizem sr um cemitério\n"
                + "\nMas você nunca esperou ver uma multidão enfurecida na entrada do castelo querendo queimar o seu senhor.\n"
                + "Parece que ele foi acusado magia negra!! E sequestrou a filha de um fazendeiro novamente.\n"
                + "Você está com um pressentimento ruim.", 
                "Digite 2: Se for acompanhar os fazendeiros para ver no que isso irá dar\n"
                + "Ou Digite 3: Para sair do caminho e ir para casa.");
    }
    public void nextEvent(){};
    public int listaComandos(){
        Scanner escolha = new Scanner(System.in);
        int num;
        System.out.println(this.listaComandos);
        while(true){
            num = escolha.nextInt();
            if(num==2){
                System.out.println("Seguir a multidão.");
                return 2;
            }
            else if(num==3){
                System.out.println("Ir para casa.");
                return 3;
            }
            else{
                System.out.println(this.listaComandos);
            }
        }
    }
    public static void main(String[] args){
        Inicio i = new Inicio();
        i.listaComandos();
    }
}
