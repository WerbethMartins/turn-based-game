package Personagens;

import Combate.ResultadoAtaque;
import Combate.ResultadoDefesa;
import Habilidade.Habilidade;
import java.util.ArrayList;

public abstract class Personagem {
    private String nome;
    private int pontosVida;
    private int pontosXp;
    private int forca;
    private int defesa;
    private int velocidade;
    private ArrayList<Habilidade> habilidades = new ArrayList<>();

    public Personagem(String nome, int pontosVida, int pontosXp,int forca, int defesa, int velocidade, ArrayList<Habilidade> habilidades) {
        this.nome = nome;
        this.pontosVida = pontosVida;
        this.pontosXp = pontosXp;
        this.forca = forca;
        this.defesa = defesa;
        this.velocidade = velocidade;
        this.habilidades = habilidades;
    }

    public Personagem() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontosVida() {return pontosVida;}

    public String getMensagemVida(){
        if(pontosVida >= 100){
            return "100% de vida!";
        } else if(pontosVida <= 80){
            return "80% de vida";
        } else if(pontosVida <= 50) {
            return "50% de vida";
        } else if(pontosVida == 0){
            return "Morto";
        } else {
            return "Vida critica";
        }
    }

    public void setPontosVida(int pontosVida) {
        this.pontosVida = pontosVida;
    }

    public int getPontosXp() {
        return pontosXp;
    }

    public void setPontosXp(int pontosXp) {
        this.pontosXp = pontosXp;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public ArrayList<Habilidade> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(ArrayList<Habilidade> habilidades) {
        this.habilidades = habilidades;
    }

    // Metodo para adicionar as habilidades fora da classe personagem
    public void adicionarHabilidade(Habilidade h){
        habilidades.add(h);
    }

    public int calcularDano(Personagem alvo, Habilidade habilidade){
        double multiplicador = 1.0;

        switch (habilidade.getTipoHabilidade()){
            case DISTANCIA:
                if(alvo instanceof Arqueiro){
                    multiplicador = 0.8;
                } else if(alvo instanceof Guerreiro){
                    multiplicador = 1.2;
                } else if(alvo instanceof Mago){
                    multiplicador = 0.9;
                } else if(alvo instanceof Inimigos inimigo){
                    if(inimigo.getTipo() == TipoPersonagem.GOBLIN){
                        multiplicador = 0.9;
                    } else if(inimigo.getTipo() == TipoPersonagem.ORC){
                        multiplicador = 0.8;
                    }
                }
                break;

            case FISICO:
                if(alvo instanceof  Arqueiro){
                    multiplicador = 1.5;
                } else if(alvo instanceof Mago){
                    multiplicador = 1.5;
                } else if(alvo instanceof Guerreiro){
                    multiplicador = 1.2;
                } else if(alvo instanceof Inimigos inimigo) {
                    if(inimigo.getTipo() == TipoPersonagem.GOBLIN){
                        multiplicador = 1.2;
                    } else if(inimigo.getTipo() == TipoPersonagem.ORC){
                        multiplicador = 0.9;
                    }
                }
                break;

            case MAGIA:
                if(alvo instanceof Arqueiro){
                    multiplicador = 1.0;
                } else if(alvo instanceof Guerreiro) {
                    multiplicador = 1.3;
                } else if(alvo instanceof Mago) {
                    multiplicador = 0.9;
                } else if(alvo instanceof Inimigos inimigo){
                    if(inimigo.getTipo() == TipoPersonagem.GOBLIN){
                        multiplicador = 1.1;
                    } else if(inimigo.getTipo() == TipoPersonagem.ORC){
                        multiplicador = 1.0;
                    }
                }
             break;
        }

        // Para que o dano não volte negativo
        return Math.max(0, (int) (habilidade.getDanoBase() + getForca() * multiplicador) - alvo.getDefesa());
    }

    public void receberDano(int dano){
        this.pontosVida -= Math.max(dano, 0);
    }

    public boolean estaVivo(){
        return this.pontosVida > 0;
    }

    public Habilidade escolherHabilidadeAleatoria(){
        return null;
    }

    public void ganhoXp(Personagem personagemSelececionado, Inimigos inimigos){
        int xp = personagemSelececionado.getPontosXp();
        if(!inimigos.estaVivo()){
            xp += inimigos.getRecompensaXP();
        }
    }

    // Métodos abstratos

    public abstract ResultadoAtaque atacar(Personagem alvo, Habilidade habilidade);

    public abstract ResultadoDefesa defender(Personagem atacante, Habilidade habilidade);

    public abstract boolean fugir(Personagem alvo);

}
