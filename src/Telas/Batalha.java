package Telas;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import CampoBatalha.CampoBatalha;
import Estilos.Estilos;
import Personagens.*;

public class Batalha {
    private JTextPane textPaneTituloBatalha;
    private JButton buttonComecarBatalha;
    private JButton botaoAtacar;
    private JFrame tela;
    private Personagem personagem;
    private CampoBatalha campoBatalha;

    public Batalha(){

        this.campoBatalha = new CampoBatalha();

        // Criando a janela de pricipal
        tela = new JFrame();
        tela.setTitle("Meu jogo de turnos"); // Definindo o título da janela

        // Configurações básicas da janela
        tela.setSize(600, 400); // Define o tamanho da janela (largura, altura)
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

    private void createUIComponents() {
        //textPaneTituloBatalha.setText("Batalha entre " + personagem.getNome() + " e " + personagem.getNome());

        textPaneTituloBatalha = new JTextPane();
        Estilos.estilosTextPane(textPaneTituloBatalha);
        textPaneTituloBatalha.setText("Batalha entre...");

        buttonComecarBatalha = new JButton();
        buttonComecarBatalha.setText("Iniciar a batalha");
        Estilos.estilizarBotao(buttonComecarBatalha);
        buttonComecarBatalha.addActionListener(e -> {
            campoBatalha.batalhaTurnos();
            textPaneTituloBatalha.setText("Batalha iniciada!");
        });
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new Batalha());
    }
}
