package Telas;

import CampoBatalha.CampoBatalha;
import Combate.ResultadoAtaque;
import Combate.ResultadoTurnoInimigo;
import Estilos.Estilos;
import Habilidade.Habilidade;
import Personagens.Personagem;
import Util.MensagemBatalha;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuHabilidades extends  JDialog{
    private JTextPane textPaneTituloHabilidades;
    private JButton buttonHabilidade1;
    private JButton buttonHabilidade2;
    private JButton buttonHabilidade3;
    private JFrame tela;
    private JFrame telaPrincipal;
    private Personagem personagem;
    private Personagem inimigo;
    private CampoBatalha campoBatalha;

    public MenuHabilidades(Frame owner, Personagem personagem, Personagem inimigo, CampoBatalha campoBatalha,
                           JFrame telaPrincipal) {
        super(owner, "Menu de Habilidades", true);
        this.personagem = personagem;
        this.inimigo = inimigo;
        this.campoBatalha = campoBatalha;
        this.telaPrincipal = telaPrincipal;

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

                    if(inimigo.getPontosVida() <= 0){
                        JOptionPane.showMessageDialog(tela, inimigo.getNome() + " Foi derrotado!");
                        tela.dispose();
                        telaPrincipal.dispose();
                        return;
                    }

                    // turno inimigo
                    ResultadoTurnoInimigo turnoInimigo  = campoBatalha.turnoDoInimigo();
                    exibirAtaqueInimigo(turnoInimigo.getResultado(), turnoInimigo.getHabilidadeUsada());

                    if(personagem.getPontosVida() <= 0){
                        JOptionPane.showMessageDialog(tela, personagem.getNome() + " Foi derrotado!");
                        tela.dispose();
                        telaPrincipal.dispose();
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(tela, "Habilidade não encontrada!");
                }
                tela.dispose();
            }
        });

        // Botão 2
        buttonHabilidade2 = new JButton(habilidades.size() > 1 ? habilidades.get(1).getNome() : "Habilidade 2");
        Estilos.estilizarBotao(buttonHabilidade2);
        buttonHabilidade2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(habilidades.size() > 1) {
                    Habilidade habilidade = habilidades.get(1);
                    ResultadoAtaque resultado = personagem.atacar(inimigo, habilidade);
                    exibirResultadoAtaque(resultado, habilidade);

                    if (inimigo.getPontosVida() <= 0) {
                        MensagemBatalha.mostrarDerrota(tela, inimigo);
                        tela.dispose();
                        telaPrincipal.dispose();
                        return;
                    }

                    // Turno do inimigo
                    ResultadoTurnoInimigo turnoInimigo = campoBatalha.turnoDoInimigo();
                    exibirAtaqueInimigo(turnoInimigo.getResultado(), turnoInimigo.getHabilidadeUsada());

                    if (personagem.getPontosVida() <= 0) {
                        MensagemBatalha.mostrarDerrota(tela, personagem);
                        tela.dispose();
                        telaPrincipal.dispose();
                        return;
                    }
                }else {
                    JOptionPane.showMessageDialog(tela, "Habilidade não encontrada!");
                }
                tela.dispose();
            }
        });

        // Botão 3
        buttonHabilidade3 = new JButton(habilidades.size() > 2 ? habilidades.get(2).getNome() : "Habilidade 3");
        Estilos.estilizarBotao(buttonHabilidade3);
        buttonHabilidade3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(habilidades.size() > 2){
                   Habilidade habilidade = habilidades.get(2);
                   ResultadoAtaque resultado = personagem.atacar(inimigo, habilidade);
                   exibirResultadoAtaque(resultado, habilidade);

                   if(inimigo.getPontosVida() <= 0){
                       campoBatalha.getHeroi().estaVivo();
                       MensagemBatalha.mostrarDerrota(tela, inimigo);
                       tela.dispose();
                       telaPrincipal.dispose();
                       return;
                   }

                   // Turno do inimigo
                   ResultadoTurnoInimigo turnoInimigo = campoBatalha.turnoDoInimigo();
                   exibirAtaqueInimigo(turnoInimigo.getResultado(), turnoInimigo.getHabilidadeUsada());

                   if(personagem.getPontosVida() <= 0){
                       campoBatalha.getHeroi().estaVivo();
                       JOptionPane.showMessageDialog(tela, "Você foi derrotado!");
                       tela.dispose();
                       telaPrincipal.dispose();
                       return;
                   }
                }else {
                    JOptionPane.showMessageDialog(tela, "Nenhuma habilidade encontrada!");
                }
                tela.dispose();
            }
        });
    }

    private void exibirAtaqueInimigo(ResultadoAtaque resultado, Habilidade habilidadeUsada){
        if (resultado == null || habilidadeUsada == null) {
            JOptionPane.showMessageDialog(this, "Erro: o resultado ou a habilidade do inimigo veio nulo!");
            return;
        }

        String mensagem = inimigo.getNome() + " usou " + habilidadeUsada.getNome() + "\n"
                + "Dano causado: " + resultado.getDanoCausado() + "\n"
                + (resultado.isCritico() ? "Acerto crítico!\n" : "")

                + "Vida restante de " + personagem.getNome() + ": " + resultado.getVidaRestante();
        JOptionPane.showMessageDialog(this, mensagem);
    }

    private void exibirResultadoAtaque(ResultadoAtaque resultado, Habilidade habilidade) {
        String mensagem = personagem.getNome() + " usou " + habilidade.getNome() +
                "\nDano causado: " + resultado.getDanoCausado() +
                (resultado.isCritico() ? " (Acerto crítico!)" : "") +
                "\nVida restante do inimigo: " + resultado.getVidaRestante();

        JOptionPane.showMessageDialog(tela, mensagem);
    }
}
