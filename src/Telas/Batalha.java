package Telas;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.ArrayList;

import Habilidade.Habilidade;
import Personagens.Arqueiro;
import Personagens.Inimigos;
import Personagens.Personagem;
import Personagens.TipoHabilidade;

public class Batalha {
    private JTextPane textPaneTituloBatalha;
    private JButton buttonComecarBatalha;
    private JFrame tela;

    public Batalha(){

        // Criando a janela de pricipal
        tela = new JFrame();
        tela.setTitle("Meu jogo de turnos"); // Definindo o título da janela

        // Configurações básicas da janela
        tela.setSize(800, 600); // Define o tamanho da janela (largura, altura)
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao clicar X
        tela.setLocationRelativeTo(null);

        // Adicionando o JTextPane
        createUIComponents();

        // Adicionando o JTextPane ao JFrame
        tela.add(textPaneTituloBatalha, BorderLayout.NORTH);

        // Adicionando o JButton ao centro
        JPanel painelBotoes = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        painelBotoes.add(buttonComecarBatalha);
        tela.add(painelBotoes, BorderLayout.CENTER);

        // Verificando componentes adicionais
        Component[] components = tela.getContentPane().getComponents();
        for (Component comp : components){
            System.out.println("Componente encontrado: " + comp.getClass().getSimpleName());
        }
        System.out.println("JButton visível: " + buttonComecarBatalha.isVisible());

        // Torna a janela visível
        tela.setVisible(true);
    }

    private void configurarTituloBatalha(){
        // Inicializando o JTextPane
        textPaneTituloBatalha = new JTextPane();
        textPaneTituloBatalha.setEditable(false); // Impede a edição de texto
        textPaneTituloBatalha.setFont(new Font("Arial", Font.BOLD, 24));
        textPaneTituloBatalha.setText("Batalha entre...");
        StyledDocument doc = textPaneTituloBatalha.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        textPaneTituloBatalha.setForeground(Color.WHITE);
        textPaneTituloBatalha.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        textPaneTituloBatalha.setBackground(Color.RED);
        textPaneTituloBatalha.setMargin(new Insets(10,10,10,10));
    }

    private void configurarBotaoBatalha(){
        buttonComecarBatalha = new JButton();
        buttonComecarBatalha.setFont(new Font("Arial", Font.PLAIN, 24));
        buttonComecarBatalha.setText("Iniciar a batalha");
        buttonComecarBatalha.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonComecarBatalha.setForeground(Color.white);
        buttonComecarBatalha.setBackground(Color.BLACK);
        buttonComecarBatalha.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        buttonComecarBatalha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonComecarBatalha.setBackground(new Color(255, 255, 255));
                buttonComecarBatalha.setForeground(Color.BLACK);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonComecarBatalha.setBackground(new Color(0, 0, 0));
                buttonComecarBatalha.setForeground(Color.white);
            }
        });

        buttonComecarBatalha.setOpaque(true);
        buttonComecarBatalha.setBorderPainted(true);
    }

    public void atualizarTitulo(String personagem1, String personagem2){
        textPaneTituloBatalha.setText("Batalha entre " + personagem1 + " e " + personagem2);
    }

    private void createUIComponents() {
        configurarTituloBatalha();
        configurarBotaoBatalha();
    }

    public static void main(String[] args){
        // Executando na thread de despacho de eventos do Swing
        SwingUtilities.invokeLater(() -> {
            Batalha batalha = new Batalha();

            Arqueiro arqueiro = new Arqueiro();
            Inimigos goblin = new Inimigos();
            batalha.atualizarTitulo(arqueiro.getNome(), goblin.getNome());
        });
    }
}
