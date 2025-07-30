package Personagens;

import Combate.ResultadoAtaque;
import Combate.ResultadoDefesa;
import Habilidade.Habilidade;
import Util.mensagemSleep;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Guerreiro extends Personagem{
    private int forcaBruta;
    private int resistencia;
    private boolean modoBerserker;

    Scanner scanner = new Scanner(System.in);

    public void Guerreiro(){};

    public Guerreiro(String nome) {
        super(nome, 100, 0,30, 20, 10, new ArrayList<>());
        this.forcaBruta = forcaBruta;
        this.resistencia = resistencia;
        this.modoBerserker = modoBerserker;

        adicionarHabilidade(new Habilidade("Força Colossal", TipoHabilidade.FISICO, 18));
        adicionarHabilidade(new Habilidade("Agarrada Esmagadora", TipoHabilidade.FISICO, 20));
        adicionarHabilidade(new Habilidade("Sequência de Socos", TipoHabilidade.FISICO, 22));
    }

    public Guerreiro(String nome, int pontosVida, int pontosXp,int forca, int defesa, int velocidade, ArrayList<Habilidade> habilidades) {
        super(nome, pontosVida, pontosXp,forca, defesa, velocidade, habilidades);
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
        double multiplicador = 1.0;
        int danoBase = calcularDano(alvo, habilidade);
        boolean berserkerAtivo = Math.random() < (this.forcaBruta / 100.0);

        if(berserkerAtivo){
            setPontosVida(getPontosVida() - 10);
            multiplicador = 1.3;
            System.out.println("⚔\uFE0F Você está no modo berserker, você causou mais dano, mas deu 10 de vida.");
        }

        if(getPontosVida() < 20 && Math.random() < 0.5){
            berserkerAtivo = true;
        }

        int danoFinal = (int) (berserkerAtivo ? danoBase * multiplicador : danoBase);
        alvo.receberDano(danoFinal);

        return new ResultadoAtaque(danoFinal, berserkerAtivo, alvo.getPontosVida());
    }

    @Override
    public ResultadoDefesa defender(Personagem atacante, Habilidade habilidade) {
        double multiplicador = 1.0;
        int danoBase = atacante.calcularDano(this, habilidade);
        boolean defesaAbsoluta = Math.random() * 100 < getDefesa();
        int danoFinal = 0;

        if(defesaAbsoluta){
            danoFinal = 0;
            System.out.println("Você defendeu todo o dano do inimigo!");
        } else {
            danoFinal = Math.max(danoBase - getDefesa(), 0);
            System.out.println("\uD83D\uDEE1\uFE0F  " + getNome() + " Reduziu o dano do inimigo em "
            + getDefesa() + " pontos");
        }

        System.out.println("você defendeu o ataque de " + atacante.getNome() + "\n"
                + " Dano: " + danoBase + "\n"
                + " Dano Final: " + danoFinal + "\n"
                + "Vida restante: " + getPontosVida());
        return new ResultadoDefesa(danoFinal, defesaAbsoluta, super.getPontosVida());
    }

    @Override
    public boolean fugir(Personagem alvo){
        mensagemSleep mensagem = new mensagemSleep();
        int escolhaFugir = JOptionPane.showConfirmDialog(
                null,
                "Deseja fugir da batalha?",
                "Fugir",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
         if(escolhaFugir == JOptionPane.YES_NO_OPTION){
             mensagem.mensagemSleep("Fugindo...");
             System.out.println("Você fugiu da batalha! Você perdeu honra.");
             return true;
         } else {
             System.out.println("Você decidiu lutar!");
             return false;
         }
    }
}
