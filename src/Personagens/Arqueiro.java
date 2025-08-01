package Personagens;

import Combate.*;
import Habilidade.Habilidade;
import Util.mensagemSleep;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Arqueiro extends Personagem{
    private int destreza;
    private int furtividade;
    private int chanceCritica;

    Scanner scanner = new Scanner(System.in);

    public Arqueiro() {} // Métod criado para deixar a classe pública

    public Arqueiro(String nome) {
        // Define os atributos herdados com valores fixos do Arqueiro
        super(nome, 80, 0,1,15, 5, 25, new ArrayList<>());
        // Define os atributos próprios
        this.destreza = 14;
        this.furtividade = 16;
        this.chanceCritica = 20;

        adicionarHabilidade(new Habilidade("Flecha Feroz", TipoHabilidade.DISTANCIA, 15));
        adicionarHabilidade(new Habilidade("Flecha de fogo Carmezin", TipoHabilidade.DISTANCIA, 18));
        adicionarHabilidade(new Habilidade("Flecha tripla", TipoHabilidade.DISTANCIA, 17));
    }

    public Arqueiro(String nome, int pontosVida, int pontosXp,int nivel,int forca, int defesa, int velocidade, ArrayList<Habilidade> habilidades) {
        super(nome, pontosVida, pontosXp,nivel,forca, defesa, velocidade, habilidades);
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getFurtividade() {
        return furtividade;
    }

    public void setFurtividade(int furtividade) {
        this.furtividade = furtividade;
    }

    public int getChanceCritica() {
        return chanceCritica;
    }

    public void setChanceCritica(int chanceCritica) {
        this.chanceCritica = chanceCritica;
    }

    @Override
    public ResultadoAtaque atacar(Personagem alvo, Habilidade habilidade){
        double multiplicador = 1.0;
        int danoBase = habilidade.getDanoBase() + this.getDestreza();
        boolean critico = Math.random() * 100 < this.chanceCritica;

        if(critico){
            multiplicador = 1.2;
        }

        int danoFinal = (int) (critico ? danoBase * multiplicador : danoBase);
        alvo.receberDano(danoFinal);

        return new ResultadoAtaque(danoFinal, critico,alvo.getPontosVida());
    }

    @Override
    public ResultadoDefesa defender(Personagem atacante,Habilidade habilidade){
        int danoOriginal = atacante.calcularDano(this, habilidade);
        boolean esquivaAbsoluta = Math.random() * 100 < getDefesa();
        int danoFinal = 0;

        if(esquivaAbsoluta) {
            danoFinal = 0;
        } else {
            danoFinal = Math.max(danoOriginal - getDefesa(), 0); // Reduz o dano com base na defesa
        }

        super.receberDano(danoFinal);

        System.out.println("Você se defendeu do ataque de " + atacante.getNome() + "\n"
                + "Dano: " + danoOriginal + "\n"
                + "Dano após defender: " + danoFinal + "\n"
                + "Vida restante: " + getPontosVida());

        return new ResultadoDefesa(danoFinal, esquivaAbsoluta, super.getPontosVida());
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
            System.out.println("Você escolheu fugir da batalha você perdeu honra!");
            return true;
        } else {
            System.out.println("Você decidiu continuar lutando!");
            return false;
        }
    }

}
