package Telas;

import Combate.ResultadoAtaque;
import Estilos.Estilos;
import Habilidade.Habilidade;
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
    private Personagem inimigo;

    public MenuHabilidades(Frame owner, Personagem personagem, Personagem inimigo) {
        super(owner, "Menu de Habilidades", true);
        this.personagem = personagem;
        this.inimigo = inimigo;

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
        textPaneTituloHabilidades = new JTextPane();
        Estilos.estilosTextPane(textPaneTituloHabilidades);
        textPaneTituloHabilidades.setText("Escolha sua habilidade:");

        configuracaoTitulo();
        configuracaoBotoes();
    }

    private void configuracaoBotoes() {
        List<Habilidade> habilidades = personagem.getHabilidades();

        // Botão 1
        buttonHabilidade1 = new JButton(habilidades.size() > 0 ? habilidades.get(0).getNome() : "Habilidade 1");
        Estilos.estilizarBotao(buttonHabilidade1);
        buttonHabilidade1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (habilidades.size() > 0) {
                    Habilidade habilidade = habilidades.get(0);
                    ResultadoAtaque resultado = personagem.atacar(inimigo, habilidade);
                    exibirResultadoAtaque(resultado, habilidade);
                    tela.dispose();
                } else {
                    JOptionPane.showMessageDialog(tela, "Habilidade não encontrada!");
                }
            }
        });

        // Botão 2
        buttonHabilidade2 = new JButton(habilidades.size() > 1 ? habilidades.get(1).getNome() : "Habilidade 2");
        Estilos.estilizarBotao(buttonHabilidade2);
        buttonHabilidade2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (habilidades.size() > 1) {
                    personagem.atacar(inimigo, habilidades.get(1));
                    JOptionPane.showMessageDialog(tela, personagem.getNome() + " usou " +
                            habilidades.get(1).getNome());
                    tela.dispose();
                }
            }
        });

        // Botão 3
        buttonHabilidade3 = new JButton(habilidades.size() > 2 ? habilidades.get(2).getNome() : "Habilidade 3");
        Estilos.estilizarBotao(buttonHabilidade3);
        buttonHabilidade3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (habilidades.size() > 2) {
                    personagem.atacar(inimigo, habilidades.get(2));
                    JOptionPane.showMessageDialog(tela, personagem.getNome() + " usou " +
                            habilidades.get(2).getNome());
                    tela.dispose();
                }
            }
        });
    }

    private void exibirResultadoAtaque(ResultadoAtaque resultado, Habilidade habilidade) {
        String mensagem = personagem.getNome() + " usou " + habilidade.getNome() +
                "\nDano causado: " + resultado.getDanoCausado() +
                (resultado.isCritico() ? " (Crítico!)" : "") +
                "\nVida restante do inimigo: " + resultado.getVidaRestante();

        JOptionPane.showMessageDialog(tela, mensagem);
    }
}
