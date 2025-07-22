package Estilos;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class Estilos {
    public static void estilosTextPane(JTextPane textPane){
        textPane.setEditable(false);
        textPane.setFont(new Font("Arial", Font.BOLD,24));
        textPane.setForeground(Color.DARK_GRAY);
        textPane.setBackground(new Color(240,240,240));
        textPane.setBorder(BorderFactory.createEmptyBorder(20,10,10,10));
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
    }

    public static void estilizarBotao(JButton botao){
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botao.setFont(new Font("Arial", Font.PLAIN, 24));
        botao.setForeground(Color.WHITE);
        botao.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        botao.setBackground(Color.BLACK);
        botao.setOpaque(true);
        botao.setBorderPainted(true);
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(Color.WHITE);
                botao.setForeground(Color.BLACK);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(Color.BLACK);
                botao.setForeground(Color.WHITE);
            }
        });
    }
}
