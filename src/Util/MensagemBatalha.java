package Util;

import Combate.ResultadoAtaque;
import Combate.ResultadoDefesa;
import Habilidade.Habilidade;
import Personagens.Inimigos;
import Personagens.Personagem;

import javax.swing.*;
import java.awt.*;

public class MensagemBatalha extends Component {

    // Metodo sem uso...
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

    public static void mostrarFulga(JFrame tela, Personagem personagem, boolean fugiu){
        String mensagem;

        if(fugiu){
            mensagem = "\uD83C\uDFC3 " + personagem.getNome() + " consegiu fugir!";
        }else {
            mensagem = personagem.getNome() + "Não conseguiu fugir!";
        }

        JOptionPane.showMessageDialog(tela, mensagem, "Fuga", JOptionPane.WARNING_MESSAGE);
    }

    public static void defender(JFrame tela,ResultadoDefesa resultado, Personagem defensor,Habilidade habilidadeUsada){
        String mensagem = defensor.getNome() + " se defendeu do " + habilidadeUsada.getNome() + "\n"
                + "\uD83D\uDEE1 Dano recebido: " + resultado.getDanoOriginal() + "\n"
                + (resultado.isDefesaAbsoluta() ? "✨ Defesa absoluta " : "Defesa parcial\n")
                + "❤\uFE0F Vida restante: " + defensor.getPontosVida() + "\n";
        JOptionPane.showMessageDialog(tela, mensagem);
    }

    public static void mostrarVitoria(JFrame tela, Personagem vencedor, Inimigos inimigo, int xpGanho, int nivel){

        String mensagem = "\uD83C\uDFC6 " + vencedor.getNome() + " venceu a batalha!\n"
                + inimigo.getNome() + " foi derrotado!\n"
                + "XP ganho " + xpGanho + "\n"
                + "Nivel: " + nivel;
        JOptionPane.showMessageDialog(tela, mensagem, "Vitória", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mostrarDerrota(JFrame tela, Personagem derrotado){
        String mensagem = "\uD83D\uDC80 " + derrotado.getNome() + " foi derrotado!\n Fim de jogo";
        JOptionPane.showMessageDialog(tela, mensagem, " Derrota", JOptionPane.ERROR_MESSAGE);
    }

}
