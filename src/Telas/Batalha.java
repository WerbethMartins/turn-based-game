package Telas;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import CampoBatalha.CampoBatalha;
import Estilos.Estilos;
import Habilidade.Habilidade;
import Personagens.*;

public class Batalha {
    private JTextPane textPaneTituloBatalha;
    private JButton buttonComecarBatalha;
    private JButton botaoAtacar;
    private JFrame tela;
    private Personagem personagem;
    private Personagem inimigo;
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
        System.out.println("JButton visível: " + botaoAtacar);

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
            textPaneTituloBatalha.setText("Faça sua escolha...");
            botoeAcoes();
        });
    }

    public void botoeAcoes(){
        personagem = campoBatalha.getHeroi();
        inimigo = campoBatalha.getInimigo();

        // Botão atacar
        botaoAtacar = new JButton("Atacar");
        Estilos.estilizarBotao(botaoAtacar);

        botaoAtacar.addActionListener(e -> {
           MenuHabilidades menu = new MenuHabilidades(tela, personagem, inimigo);
        });

        JPanel painelBotoeA = new JPanel(new GridBagLayout());
        GridBagConstraints botoesAcoes = new GridBagConstraints();
        botoesAcoes.insets = new Insets(10,10,10,10);
        painelBotoeA.add(botaoAtacar);
        tela.add(painelBotoeA, BorderLayout.CENTER);

        // Removendo o botão anterior e adicionando novo no painel
        tela.getContentPane().removeAll(); // Limpa a tela
        tela.add(textPaneTituloBatalha, BorderLayout.NORTH);
        tela.add(painelBotoeA, BorderLayout.CENTER);
        tela.revalidate();
        tela.repaint();
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new Batalha());
    }
}
