package RacaProfissao;
/**
 *
 * @author Artur
 */
public abstract class Profissao {
    public String nome;
    public String descricao;
    public int bonusHabilidade;
    public int bonusEnergia;
    public int bonusSorte;

    public Profissao(String nome, String descricao, int bonusHabilidade, int bonusEnergia, int bonusSorte) {
        this.nome = nome;
        this.descricao = descricao;
        this.bonusHabilidade = bonusHabilidade;
        this.bonusEnergia = bonusEnergia;
        this.bonusSorte = bonusSorte;
    }

    public abstract void habilidades(); // Magias e ou extras
    public abstract void bonus(); // Bonus nos atributos
}
