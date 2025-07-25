package Personagens;

import Combate.ResultadoAtaque;
import Combate.ResultadoDefesa;
import Habilidade.Habilidade;
import Util.mensagemSleep;

import java.util.ArrayList;
import java.util.Scanner;

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

    Scanner scanner = new Scanner(System.in);

    public void Guerreiro(){};

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

    public ResultadoAtaque atacar(Personagem alvo, Habilidade habilidade){
        int danoBase = calcularDano(alvo, habilidade);
        boolean berserker = this.modoBerserker;

        if(modoBerserker){
            danoBase *= 1.5;
            System.out.println("⚔\uFE0F Você está no modo berserker e causou mais dano!");
        }

        alvo.receberDano(danoBase);

        System.out.println(getNome() + " atacou " + alvo.getNome() + " causando " + danoBase + " de dano!");

        System.out.println(alvo.getNome() + " agora tem " + alvo.getPontosVida() + " ponto de vida!");
        return null;
    }

    @Override
    public ResultadoDefesa defender(Personagem atacante, Habilidade habilidade) {

        return null;
    }

    @Override
    public boolean fugir(Personagem alvo){
        mensagemSleep mensagem = new mensagemSleep();
        System.out.println("Você deseja fugir da batalha?");
        System.out.println("(1) - Sim " + "\n(2) - Não");
        int escolhaFugir = scanner.nextInt();

        if(escolhaFugir == 1 ){
            System.out.println("Você escolheu fugir da batalha, você perdeu honra!");
            mensagem.mensagemSleep("Fugindo...");
            return true;
        }else if(escolhaFugir == 2){
            System.out.println("Vamos continuar a batalha.");
            return false;
        }
        return false;
    }
}
