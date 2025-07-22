package Telas;

import Estilos.Estilos;

import javax.swing.*;
import java.awt.*;

public class MenuHabilidades {
    private JTextPane textPaneTituloHabilidades;
    private JButton buttonHabilidade1;
    private JButton buttonHabilidade2;
    private JButton buttonHabilidade3;
    private JFrame tela;

    public MenuHabilidades(){
        tela = new JFrame();
        tela.setTitle("Menu de Habilidades");
        tela.setSize(800, 600);
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha apenas esta janela
        tela.setLocationRelativeTo(null);

        // Adicionando o JTextPane
        createUIComponents();

        tela.setLayout(new BorderLayout());
        tela.add(textPaneTituloHabilidades, BorderLayout.NORTH);

        // Adicionando o titulo ao centro
        JPanel painelBotoes = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelBotoes.add(buttonHabilidade1, gbc);
        gbc.gridy = 1;
        painelBotoes.add(buttonHabilidade2, gbc);
        gbc.gridy = 2;
        painelBotoes.add(buttonHabilidade3, gbc);
        tela.add(painelBotoes, BorderLayout.CENTER);

        // Verificando componentes adicionais
        Component[] components = tela.getContentPane().getComponents();
        for (Component comp : components){
            System.out.println("Componente Encontrado " + comp.getClass().getSimpleName());
        }
        System.out.println("JtextPane visível " + textPaneTituloHabilidades.isVisible());
        System.out.println("Botão 1 visível " + buttonHabilidade1);
        System.out.println("Botão 2 visível " + buttonHabilidade2);
        System.out.println("Botão 3 visível " + buttonHabilidade3);

        tela.setVisible(true);
    }

    public void configuracaoTitulo(){
        textPaneTituloHabilidades = new JTextPane();
        Estilos.estilosTextPane(textPaneTituloHabilidades);
        textPaneTituloHabilidades.setText("Escolha sua habilidade.");
    }

    private void createUIComponents() {
        configuracaoTitulo();

        // Inicializando os botões
        buttonHabilidade1 = new JButton();
        Estilos.estilizarBotao(buttonHabilidade1);
        buttonHabilidade1.setText("Flecha Feroz");


        buttonHabilidade2 = new JButton();
        Estilos.estilizarBotao(buttonHabilidade2);
        buttonHabilidade2.setText("Tiro Rápido");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MenuHabilidades();
        });
    }

}
