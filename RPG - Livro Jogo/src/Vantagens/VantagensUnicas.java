package Vantagens;
public class VantagensUnicas {
    public String nome;
    public String descricao;
    public int habilidade;
    public int energia;
    public int sorte;
    public int magia;

    public VantagensUnicas(String nome, String descricao, int habilidade, int energia, int sorte, int magia) {
        this.nome = nome;
        this.descricao = descricao;
        this.habilidade = habilidade;
        this.energia = energia;
        this.sorte = sorte;
        this.magia = magia;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
}