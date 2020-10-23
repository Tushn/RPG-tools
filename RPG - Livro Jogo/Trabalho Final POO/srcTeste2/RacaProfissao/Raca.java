package RacaProfissao;

/**
 *
 * @author Artur
 */
public abstract class Raca {
    public String nome;
    public String descricao;
    public int bonusHabilidade;
    public int bonusEnergia;
    public int bonusSorte;

    /*public Raca(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.bonusHabilidade = 0;
        this.bonusEnergia = 0;
        this.bonusSorte = 0;
    }*/
    
    /* Coloque nome, descricao e bonus */
    public Raca(String nome, String descricao, int bonusHabilidade, int bonusEnergia, int bonusSorte){
        this.nome = nome;
        this.descricao = descricao;
        this.bonusHabilidade = bonusHabilidade;
        this.bonusEnergia = bonusEnergia;
        this.bonusSorte = bonusSorte;
    }
    
    abstract void poder();
}
