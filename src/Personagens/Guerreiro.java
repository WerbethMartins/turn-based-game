package Personagens;

import Habilidade.Habilidade;

import java.util.ArrayList;

public class Guerreiro extends Personagem{
    private int forcaBruta;
    private int resistencia;
    private boolean modoBerserker;

    public Guerreiro(String nome, int pontosVida, int forca, int defesa, int velocidade, ArrayList<Habilidade> habilidades, int forcaBruta, int resistencia, boolean modoBerserker) {
        super(nome, pontosVida, forca, defesa, velocidade, habilidades);
        this.forcaBruta = forcaBruta;
        this.resistencia = resistencia;
        this.modoBerserker = modoBerserker;
    }

    public Guerreiro(int forcaBruta, int resistencia, boolean modoBerserker) {
        this.forcaBruta = forcaBruta;
        this.resistencia = resistencia;
        this.modoBerserker = modoBerserker;
    }

    public int getForcaBruta() {
        return forcaBruta;
    }

    public void setForcaBruta(int forcaBruta) {
        this.forcaBruta = forcaBruta;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public boolean isModoBerserker() {
        return modoBerserker;
    }

    public void setModoBerserker(boolean modoBerserker) {
        this.modoBerserker = modoBerserker;
    }

    public void atacar(Personagem alvo, Habilidade habilidade){
        int danoBase = calcularDano(alvo, habilidade);
        boolean berserker = this.modoBerserker;

        if(modoBerserker){
            danoBase *= 1.5;
            System.out.println("⚔\uFE0F Você está no modo berserker e causou mais dano!");
        }

        alvo.receberDano(danoBase);

        System.out.println(getNome() + " atacou " + alvo.getNome() + " causando " + danoBase + " de dano!");

        System.out.println(alvo.getNome() + " agora tem " + alvo.getPontosVida() + " ponto de vida!");
    }

    @Override
    public void defender(Personagem atacante, Habilidade habilidade) {

    }

    @Override
    public void fugir() {

    }
}
