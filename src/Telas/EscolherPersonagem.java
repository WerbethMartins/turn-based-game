package Telas;

import Estilos.Estilos;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EscolherPersonagem extends JFrame{
    private JButton iconeArqueiro;
    private JButton iconeMago;
    private JButton iconeGuerreiro;

    public EscolherPersonagem(){
        super("Escolha o personagem");

        setSize(600, 400);
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
        painel.add(criarCampo("Arqueiro", "/Imagem/6081840.png"));
        // Mago
        painel.add(criarCampo("Mago", "/Imagem/6081840.png"));
        // Guerreiro
        painel.add(criarCampo("Guerreiro", "/Imagem/6081840.png"));

        add(painel);
        setVisible(true);
    }

    public JPanel criarCampo(String nomePersonagem, String caminhoImagem){
        JPanel painelCampo = new JPanel(new BorderLayout(5,5));
        painelCampo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        painelCampo.setOpaque(false);

        JLabel label = new JLabel(nomePersonagem, SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 16));
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setPreferredSize(new Dimension(150, 80));

        JButton botao = new JButton(redimensionarImagem(String.valueOf(caminhoImagem), 80, 120));
        Estilos.aplicarEfeitoBotaoImagem(botao);
        botao.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    nomePersonagem + " selecionado!",
                    "Personagem escolhido",
                    JOptionPane.INFORMATION_MESSAGE);

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
