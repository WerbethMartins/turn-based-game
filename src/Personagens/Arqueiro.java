package Personagens;

import Habilidade.Habilidade;
import Menu.mensagemSleep;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Arqueiro extends Personagem{
    private int destreza;
    private int furtividade;
    private int chanceCritica;

    Scanner scanner = new Scanner(System.in);

    public Arqueiro() {} // Métod criado para deixar a classe pública

    public Arqueiro(String nome) {
        // Define os atributos herdados com valores fixos do Arqueiro
        super(nome, 80, 15, 5, 25, new ArrayList<>());
        // Define os atributos próprios
        this.destreza = 30;
        this.furtividade = 25;
        this.chanceCritica = 20;

        adicionarHabilidade(new Habilidade("Flecha Feroz", TipoHabilidade.DISTANCIA, 22));
        adicionarHabilidade(new Habilidade("Flecha de fogo Carmezin", TipoHabilidade.DISTANCIA, 25));
        adicionarHabilidade(new Habilidade("Flecha tripla", TipoHabilidade.DISTANCIA, 28));
    }

    public Arqueiro(String nome, int pontosVida, int forca, int defesa, int velocidade, ArrayList<Habilidade> habilidades) {
        super(nome, pontosVida, forca, defesa, velocidade, habilidades);
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
    public void atacar(Personagem alvo, Habilidade habilidade){
        Random random = new Random();

        int danoBase = calcularDano(alvo, habilidade);
        boolean critico = Math.random() * 100 < this.chanceCritica;
        int variacao = random.nextInt(5);
        int destrezaBonus = this.getDestreza() / 2;

        if(critico){
            danoBase *= 1.5;
            System.out.println("⚔\uFE0F Você acertou um dano crítico!");
        }

        int danoFinal = danoBase + destrezaBonus + variacao;

        alvo.receberDano(danoFinal);

        System.out.println(this.getNome() + " atacou " + alvo.getNome() + " com " + habilidade.getNome() +
                ", causando " + danoFinal + " de dano!");

        System.out.println(alvo.getNome() + " agora tem " + alvo.getPontosVida() + " ponto de vida!");
    }

    @Override
    public void defender(Personagem atacante, Habilidade habilidade){
        int danoOriginal = atacante.calcularDano(this, habilidade);
        boolean defesaAbsoluta = Math.random() * 100 < getDefesa();
        int danoFinal = 0;

        System.out.println("\uD83D\uDEE1 " + getNome() + " está se defendendo do ataque: " + habilidade.getNome()
                + "\n\uD83D\uDCA5 Dano total do inimigo: " + danoOriginal);

        if(defesaAbsoluta) {
            danoFinal = 0;
            System.out.println("\uD83D\uDEE1\uFE0F Você defendeu todo o dano do inimigo!");
        } else {
            danoFinal = Math.max(danoOriginal - getDefesa(), 0); // Reduz o dano com base na defesa
            System.out.println("\uD83D\uDEE1\uFE0F " + getNome() + " reduziu o dano em " + getDefesa()
                    + " pontos.");
        }

        super.receberDano(danoFinal);

        System.out.println(this.getNome() + " recebeu " + danoFinal + " de dano de " + atacante.getNome() + ".");
        System.out.println("❤\uFE0F Vida restante " + getPontosVida());

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
