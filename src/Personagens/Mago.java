package Personagens;

import Combate.ResultadoAtaque;
import Combate.ResultadoDefesa;
import Habilidade.Habilidade;
import Util.mensagemSleep;

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
        super(nome, 80, 8, 10, 15, new ArrayList<>());
        this.mana = 80;
        this.poderMagico = 30;
        this.sabedoria = false;
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

    public void setSabedoria(boolean sabedoria) {
        this.sabedoria = sabedoria;
    }

    @Override
    public ResultadoAtaque atacar(Personagem alvo, Habilidade habilidade){
        Random random = new Random();

        int variacao = random.nextInt(5);
        int danoBase = calcularDano(alvo, habilidade);
        int danoMagico = this.poderMagico / 2;
        boolean sabedoria = this.sabedoria;
        int mana = this.mana;

        if(sabedoria){
            mana += 30;
            danoBase *= 1.3;
            System.out.println("\uD83E\uDDD9\uD83C\uDFFC\u200D♂\uFE0F Sabedoria ativada! Você ganhou pontos " +
                    "e mana e poder" + mana);
        }

        int danoFinal = danoBase + danoMagico + variacao;

        alvo.receberDano(danoFinal);

        System.out.println(getNome() + " atacou " + alvo.getNome() + " e causou " + danoFinal + " de dano!");
        System.out.println(alvo.getNome() + " agora tem " + alvo.getPontosVida() + " de vida.");
        return null;
    }

    @Override
    public ResultadoDefesa defender(Personagem atacante, Habilidade habilidade) {
        int danoOriginal = atacante.calcularDano(this, habilidade);
        boolean escudoMana = Math.random() * 100 < getDefesa();
        int danoFinal = 0;

        System.out.println("\uD83D\uDEE1" + getNome() + " está se defendendo do ataque: " + habilidade.getNome() +
                "\n\uD83D\uDCA5 dano total do inimigo: " + danoOriginal);

        if(escudoMana){
            danoFinal = 0;
            System.out.println("Você usou o escudo de mana, nenhum dano sofrido!");
        } else {
            danoFinal = Math.max(danoOriginal - getDefesa(), 0);
            System.out.println("\uD83D\uDEE1\uFE0F " + getNome() + " reduziu o dano em " + getDefesa() +
                    " pontos.");
        }

        super.receberDano(danoFinal);

        System.out.println(this.getNome() + " recebeu " + danoFinal + " de dano de " + atacante.getNome() +
                ".");
        System.out.println("❤\uFE0F Vida restante: " + getPontosVida() + getMensagemVida());
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
