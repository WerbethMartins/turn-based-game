package Telas;

import Estilos.Estilos;
import Habilidade.Habilidade;
import Personagens.Arqueiro;
import Personagens.Personagem;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuHabilidades extends  JDialog{
    private JTextPane textPaneTituloHabilidades;
    private JButton buttonHabilidade1;
    private JButton buttonHabilidade2;
    private JButton buttonHabilidade3;
    private JFrame tela;

    private Personagem personagem;

    public MenuHabilidades(Frame owner, Personagem personagem) {
        this.personagem = new Arqueiro("Legolas");

        tela = new JFrame();
        tela.setTitle("Menu de Habilidades");
        tela.setSize(600, 500);
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha só essa janela
        tela.setLocationRelativeTo(null);

        createUIComponents();

        tela.setLayout(new BorderLayout());
        tela.add(textPaneTituloHabilidades, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,25,5);
        gbc.gridx = 0;
        gbc.gridy = 0;

        painelBotoes.add(buttonHabilidade1, gbc);
        gbc.gridy++;
        painelBotoes.add(buttonHabilidade2, gbc);
        gbc.gridy++;
        painelBotoes.add(buttonHabilidade3, gbc);

        tela.add(painelBotoes, BorderLayout.CENTER);
        tela.setVisible(true);
    }

    private void configuracaoTitulo() {
        textPaneTituloHabilidades = new JTextPane();
        Estilos.estilosTextPane(textPaneTituloHabilidades);
        textPaneTituloHabilidades.setText("Escolha sua habilidade:");
    }

    private void createUIComponents() {
        configuracaoTitulo();
        configuracaoBotoes();
    }

    private void configuracaoBotoes() {
        List<Habilidade> habilidades = this.personagem.getHabilidades();

        buttonHabilidade1 = new JButton(habilidades.size() > 0 ? habilidades.get(0).getNome() : "Habilidade 1");
        Estilos.estilizarBotao(buttonHabilidade1);
        buttonHabilidade1.addActionListener(e -> {
            if (!habilidades.isEmpty()) {
                Habilidade habilidade = habilidades.get(0);
                JOptionPane.showMessageDialog(tela, personagem.getNome() + " usou " +
                        habilidade.getNome());
                dispose();
            } else {
                JOptionPane.showMessageDialog(tela, "Nenhuma habilidade disponível!");
            }
        });

        buttonHabilidade2 = new JButton(habilidades.size() > 1 ? habilidades.get(1).getNome() : "Habilidade 2");
        Estilos.estilizarBotao(buttonHabilidade2);
        buttonHabilidade2.addActionListener(e -> {
            if (habilidades.size() > 1) {
                Habilidade habilidade = habilidades.get(1);
                JOptionPane.showMessageDialog(tela, personagem.getNome() + " usou " +
                        habilidade.getNome());
                dispose();
            } else {
                JOptionPane.showMessageDialog(tela, "Habilidade indisponível.");
            }
        });

        buttonHabilidade3 = new JButton(habilidades.size() > 2 ? habilidades.get(2).getNome() : "Habilidade 3");
        Estilos.estilizarBotao(buttonHabilidade3);
        buttonHabilidade3.addActionListener(e -> {
            if (habilidades.size() > 2) {
                Habilidade habilidade = habilidades.get(2);
                JOptionPane.showMessageDialog(tela, personagem.getNome() + " usou " +
                        habilidade.getNome());
            } else {
                JOptionPane.showMessageDialog(tela, "Habilidade indisponível.");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            new MenuHabilidades(frame, new Arqueiro());
        });
    }
}
