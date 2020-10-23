package NPC;
import Personagem.Personagem;
import RacaProfissao.*;
/**
 *
 * @author Casa
 */
public class BOSS extends Personagem {
    BOSS(){
        super("Fulgorar, o Verme",
                22, 30, 7,
                "Mestre maligno dos segredos antigos, hoje, "
                + "enlouquecido esse homem tenta trazer "
                + "horrores novamente da escuridao");
        
    }
    public void comportamento(){}
}
