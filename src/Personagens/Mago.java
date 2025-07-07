package Personagens;

import Habilidade.Habilidade;

import java.util.ArrayList;

public class Mago extends Personagem{
    private int mana;
    private int poderMagico;
    private boolean sabedoria;

    public Mago(){}

    public Mago(String nome, int pontosVida, int forca, int defesa, int velocidade,
                ArrayList<Habilidade> habilidades, int mana, int poderMagico, boolean sabedoria) {
        // Define os valores herdados com valores fixo do mago.
        super(nome, pontosVida, forca, defesa, velocidade, habilidades);
        this.mana = mana;
        this.poderMagico = poderMagico;
        this.sabedoria = sabedoria;
    }

    public Mago(int mana, int poderMagico, boolean sabedoria) {
        this.mana = mana;
        this.poderMagico = poderMagico;
        this.sabedoria = sabedoria;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getPoderMagico() {
        return poderMagico;
    }

    public void setPoderMargico(int poderMagico) {
        this.poderMagico = poderMagico;
    }

    public boolean isSabedoria() {
        return sabedoria;
    }

    public void setSabedoria(boolean sabedoria) {
        this.sabedoria = sabedoria;
    }

    @Override
    public void atacar(Personagem alvo, Habilidade habilidade){
        int danoBase = calcularDano(alvo, habilidade);

        alvo.receberDano(danoBase);

        System.out.println(getNome() + " atacou " + alvo.getNome() + " e causou " + danoBase + " de dano!");
        System.out.println(alvo.getNome() + " agora tem " + alvo.getPontosVida() + " de vida.");
    }

    @Override
    public void defender(Personagem atacante, Habilidade habilidade) {

    }

    @Override
    public void fugir() {

    }
}
