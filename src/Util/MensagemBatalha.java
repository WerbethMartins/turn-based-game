package Util;

import Habilidade.Habilidade;
import Personagens.Personagem;

import javax.swing.*;

public class MenssagemBatalha {

    public static void MostrarResultadoAtaque(JFrame tela, Personagem atacante, Personagem defensor, Habilidade habilidade, int dano, boolean critico){
        StringBuilder mensagem = new StringBuilder();

        mensagem.append(atacante.getNome()).append(" usou ").append(habilidade.getNome()).append("!\n\n");
        mensagem.append("\uD83D\uDDE1\uFE0F Dano causado: ").append(dano).append("\n");
        mensagem.append("❤\uFE0F Vida restante de ").append(defensor.getNome()).append(": ").append(defensor.getPontosVida())
                .append("\n");

        if(critico){
            mensagem.append("\uD83D\uDCA5 Acerto Crítico!");
        }

        JOptionPane.showMessageDialog(tela, mensagem.toString(), "Resultado do ataque" , JOptionPane.INFORMATION_MESSAGE);

    }

    public static void mostrarDefesa(JFrame tela, Personagem defensor, int danoBloqueado){
        String mensagem = "\uD83D\uDEE1\uFE0F " + defensor.getNome() + " defendeu!\n" +
                " Dano bloqueado: " + danoBloqueado + "\n" + "Vida restante: " + defensor.getPontosVida();
        JOptionPane.showMessageDialog(tela, mensagem, " Defesa", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mostrarFulga(JFrame tela, Personagem personagem, boolean fugiu){
        String mensagem;

        if(fugiu){
            mensagem = "\uD83C\uDFC3 " + personagem.getNome() + " consegiu fugir!";
        }else {
            mensagem = personagem.getNome() + "Não consegiu fugir!";
        }

        JOptionPane.showMessageDialog(tela, mensagem, "Fuga", JOptionPane.WARNING_MESSAGE);
    }

    public  static void mostrarVitoria(JFrame tela, Personagem vencedor, int xpGanho){
        String mensagem = "\uD83C\uDFC6 " + vencedor.getNome() + " venceu a batalha!\n" + "XP ganho " + xpGanho;
        JOptionPane.showMessageDialog(tela, mensagem, "Vitória", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mostrarDerrota(JFrame tela, Personagem derrotado){
        String mensagem = "\uD83D\uDC80 " + derrotado.getNome() + " foi derrotado!\n Fim de jogo";
        JOptionPane.showMessageDialog(tela, mensagem, " Derrota", JOptionPane.ERROR_MESSAGE);
    }


}
