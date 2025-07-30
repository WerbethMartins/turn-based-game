package Telas;

import CampoBatalha.CampoBatalha;
import Estilos.Estilos;
import Personagens.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EscolherPersonagem extends JFrame{


    public EscolherPersonagem(){
        super("Escolha o personagem");

        Arqueiro arqueiro = new Arqueiro("Legolas");
        CampoBatalha campoBatalha = new CampoBatalha();
        campoBatalha.iniciarPersonagem(arqueiro);

        Mago mago = new Mago("Albert");
        Guerreiro guerreiro = new Guerreiro("Thorin");

        setSize(800, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Painel Principal
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(new EmptyBorder(10,10,10,10));
        painel.setBackground(new Color(245,245, 245));

        // Titulo
        JLabel titulo = new JLabel("Escolha seu personagem");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        titulo.setBorder(new EmptyBorder(10,10,10,10));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(titulo);

        // Arqueiro
        painel.add(criarCampo("Arqueiro", "/Imagem/arqueiro.png", arqueiro));
        // Mago
        painel.add(criarCampo("Mago", "/Imagem/mago.png", mago));
        // Guerreiro
        painel.add(criarCampo("Guerreiro", "/Imagem/guerreiro.png", guerreiro));

        add(painel);
        setVisible(true);
    }

    public JPanel criarCampo(String nomePersonagem, String caminhoImagem, Personagem personagemSelecionado){
        JPanel painelCampo = new JPanel(new BorderLayout(5,5));
        painelCampo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        painelCampo.setOpaque(false);

        JLabel label = new JLabel(nomePersonagem, SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 16));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(150, 80));

        JButton botao = new JButton(redimensionarImagem(String.valueOf(caminhoImagem), 80, 130));
        Estilos.aplicarEfeitoBotaoImagem(botao);
        botao.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    nomePersonagem + " selecionado!",
                    "Personagem escolhido",
                    JOptionPane.INFORMATION_MESSAGE);

            CampoBatalha campoBatalha = new CampoBatalha();
            campoBatalha.iniciarPersonagem(personagemSelecionado);
            new Batalha(personagemSelecionado);

            dispose();
        });

        painelCampo.add(label, BorderLayout.NORTH);
        painelCampo.add(botao, BorderLayout.CENTER);

        return painelCampo;
    }

    public ImageIcon redimensionarImagem(String caminhoImagem, int largura, int altura) {
        ImageIcon iconeOriginal = new ImageIcon(getClass().getResource(caminhoImagem));
        Image imagemRedimensionada = iconeOriginal.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        return new ImageIcon(imagemRedimensionada);
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(EscolherPersonagem::new);
    }

}
