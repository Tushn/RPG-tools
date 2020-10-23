package NPC;
import Personagem.*;
import Personagem.Personagem;
import RacaProfissao.*;
/**
 *
 * @author Artur de Oliveria
 */
// Classe responsavel pelo controle de dados dos inimigos no jogo
public class Inimigo extends Personagem {
    public Inimigo(String nome, String descricao, int habilidade, int energia, int magia, int id, Raca raca, Kit kit) {
        super(id, 
                habilidade+raca.habilidade+kit.habilidade, 
                energia+raca.energia+kit.energia,
                nome,
                descricao,
                raca,
                kit);
    }
}
