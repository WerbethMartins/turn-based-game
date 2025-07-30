package Personagens;

import Combate.ResultadoAtaque;
import Combate.ResultadoDefesa;
import Habilidade.Habilidade;
import Util.mensagemSleep;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Mago extends Personagem{
    private int mana;
    private int poderMagico;
    private boolean sabedoria;

    Scanner scanner = new Scanner(System.in);

    public Mago(){}

    public Mago(String nome) {
        // Define os valores herdados com valores fixo do mago.
        super(nome, 80, 0,8, 10, 15, new ArrayList<>());
        this.mana = 80;
        this.poderMagico = 30;
        this.sabedoria = false;

        adicionarHabilidade(new Habilidade("Bola de Fogo", TipoHabilidade.MAGIA, 13));
        adicionarHabilidade(new Habilidade("Misseis Mágicos", TipoHabilidade.MAGIA, 15));
        adicionarHabilidade(new Habilidade("Fogo do Inferno", TipoHabilidade.MAGIA, 18));
    }

    public Mago(String nome, int pontosVida, int pontosXp,int forca, int defesa, int velocidade, ArrayList<Habilidade> habilidades) {
        super(nome, pontosVida, pontosXp,forca, defesa, velocidade, habilidades);
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

    public void setSabedoria(boolean sabedoria) {
        this.sabedoria = sabedoria;
    }

    @Override
    public ResultadoAtaque atacar(Personagem alvo, Habilidade habilidade){
        int custoMana = habilidade.getDanoBase() / 2;

        if(this.mana < custoMana){
            System.out.println("❌ Mana insuficiente para lançar habilidade!");
            return new ResultadoAtaque(0,false, alvo.getPontosVida());
        }
        this.mana -= custoMana;

        boolean sabedoriaAtiva = this.sabedoria;
        double multiplicador = sabedoriaAtiva ? 1.2 : 1.0;
        int danoBase = habilidade.getDanoBase() + (this.poderMagico / 2) + (custoMana / 3);

        if(sabedoria){
            this.mana += 30;
            System.out.println("\uD83E\uDDD9\uD83C\uDFFC\u200D♂\uFE0F Sabedoria ativada! Você ganhou pontos " +
                    "e mana e poder" + mana);
        }

        int danoFinal = (int) (danoBase  * multiplicador);
        alvo.receberDano(danoFinal);

        return new ResultadoAtaque(danoFinal, sabedoria, alvo.getPontosVida());
    }

    @Override
    public ResultadoDefesa defender(Personagem atacante, Habilidade habilidade) {
        int danoOriginal = atacante.calcularDano(this, habilidade);
        boolean escudoMana = Math.random() * 100 < getDefesa();
        int danoFinal = 0;

        if(escudoMana){
            danoFinal = 0;
            System.out.println("Você usou o escudo de mana, nenhum dano sofrido!");
        } else {
            danoFinal = Math.max(danoOriginal - getDefesa(), 0);
            System.out.println("\uD83D\uDEE1\uFE0F " + getNome() + " reduziu o dano em " + getDefesa() +
                    " pontos.");
        }

        super.receberDano(danoFinal);

        System.out.println("Você se defendeu do ataque de " + atacante.getNome() + "\n"
                            + "Dano: " + danoOriginal + "\n"
                            + "Dano após defender: " + danoFinal + "\n"
                            + "Vida restante: " + getPontosVida());

        return new ResultadoDefesa(danoFinal, escudoMana, super.getPontosVida());
    }

    @Override
    public boolean fugir(Personagem alvo){
        mensagemSleep mensagemSleep = new mensagemSleep();

        int escolhaFugir = JOptionPane.showConfirmDialog(
                null,
                "Você deseja fugir da batalha?",
                "Fuga",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if(escolhaFugir == JOptionPane.YES_NO_OPTION){
            mensagemSleep.mensagemSleep("Fugindo!");
            System.out.println("Você escolheu fugir da batalha, você perdeu honra!");
            return true;
        } else {
            System.out.println("Você decidiu continuar lutando!");
            return false;
        }
    }
}
