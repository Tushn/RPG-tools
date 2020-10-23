package Personagem;
import Vantagens.*;
import Recursos.d6;

/**
 *
 * @author Artur
 */
public class Personagem {
    // Atributos totais
    private int id;
    private int habilidade;
    private int energia;
    private int magia;
    // Controle dos atributos variando temporariamente na aventura
    private int habilidadeTotal;
    private int energiaTotal;
    private int magiaTotal;
    // Fixos
    public String nome;
    private String descricao;
    private Raca raca;
    private Kit kit;
    
    protected Poder[] feiticos = new Poder[10];
    protected static int contPoder = 0; 
    
    //PJ
    public Personagem(int id, int habilidade, int energia, int magia, String nome, String descricao, Raca raca, Kit kit) {
        this.id = id;
        this.habilidade = habilidade;
        this.energia = energia;
        this.habilidadeTotal = habilidade;
        this.energiaTotal = energia;
        this.magia = magia;
        this.magiaTotal = magia;
        this.nome = nome;
        this.descricao = descricao;
        this.raca = raca;
        this.kit = kit;
        
    }
    
    //Inimigo
    public Personagem(int id, int habilidade, int energia, String nome, String descricao, Raca raca, Kit kit) {
        this.id = id;
        this.habilidade = habilidade;
        this.energia = energia;
        this.habilidadeTotal = habilidade;
        this.energiaTotal = energia;
        this.magia = 0;
        this.magiaTotal = 0;
        this.nome = nome;
        this.descricao = descricao;
        this.raca = raca;
        this.kit = kit;
    }
    /*
     * Getters
     */
    public int getHabilidade(){
        return habilidade;
    }    
    public String getNomeKit(){
        return this.kit.nome;
    }
    public String getNomeRaca(){
        return this.raca.nome;
    }
    public int getId(){ return id; }
    
    public int getEnergia(){
        return this.energia;
    }

    public int getMagia() {
        return magia;
    }

    public int getEnergiaTotal() {
        return energiaTotal;
    }

    public int getHabilidadeTotal() {
        return habilidadeTotal;
    }

    public int getMagiaTotal() {
        return magiaTotal;
    }
    
    /*
     * Setters
     */

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public void setEnergiaTotal(int energiaTotal) {
        this.energiaTotal = energiaTotal;
    }

    public void setHabilidade(int habilidade) {
        this.habilidade = habilidade;
    }

    public void setHabilidadeTotal(int habilidadeTotal) {
        this.habilidadeTotal = habilidadeTotal;
    }

    public void setKit(Kit kit) {
        this.kit = kit;
    }

    public void setMagia(int magia) {
        this.magia = magia;
    }

    public void setMagiaTotal(int magiaTotal) {
        this.magiaTotal = magiaTotal;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }
    
    // Poderes
    public void setPoder(Poder f){
        this.feiticos[this.contPoder] = f;
        this.feiticos[this.contPoder].getNomeDescricao();
        contPoder++;
    }
    
    // Outros ligados ao combate
    public int fa(){
        return this.habilidade+d6.jogar(2);
    }
    
    public void dano(){
        this.energia = this.energia - 2;
    }
    
    public void dano(int i){
        this.energia = this.energia - i;
    }
    
    public void ganhaEnergia(int num){
        this.energia = this.energia + num;
        if(this.energia>this.energiaTotal)
            this.energia = this.energiaTotal;
    }
    
    public void ganhaHabilidade(int num){
        this.habilidade = this.habilidade + num;
        if(this.habilidade>this.habilidadeTotal)
            this.habilidade = this.habilidadeTotal;
    }
    
    public void gastarMagia(int magia){
        this.magia = this.magia - magia;
    }
    
    public boolean verificarVida(){
        if(this.energia<=0)
            return false;
        else
            return true;
    }
}
