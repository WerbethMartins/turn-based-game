package CampoBatalha;

import Habilidade.Habilidade;
import Menu.mensagemSleep;
import Personagens.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Batalha {
    private int turno;

    mensagemSleep mensagem = new mensagemSleep();

    public void batalha(){}

    public Batalha() {
        this.turno = turno;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public void batalhaTurnos(){
        Scanner scanner = new Scanner(System.in);
        Arqueiro arqueiro = new Arqueiro("Arc");
        Habilidade flecha = new Habilidade("Flecha Feroz", TipoHabilidade.DISTANCIA, 20);
        arqueiro.adicionarHabilidade(flecha);

        // Criando o orc
        ArrayList<Habilidade> habilidadesGoblin = new ArrayList<>();
        habilidadesGoblin.add(new Habilidade("Estocada Precisa", TipoHabilidade.FISICO, 9));
        Inimigos goblin = new Inimigos(
                "Gorlag",           // nome
                100,                     // pontosVida
                10,                      // forca
                10,                      // defesa
                15,                      // velocidade
                habilidadesGoblin,       // habilidades
                TipoPersonagem.GOBLIN,      // tipo
                20,                      // recompensaXP
                Math.random() < 0.5,                   // isChefe
                20                       // habilidadeEspecial (chance em %)
        );

        System.out.println("\uD83C\uDFF9 CampoBatalha.Batalha entre " + arqueiro.getNome() + " e "
                + goblin.getNome() + " começa!");
        this.turno = 1;

        while(arqueiro.estaVivo() && goblin.estaVivo()){
            // Turno do jogador
            System.out.println("\n Seu turno! Faça sua escolha.");
            System.out.println("(1) - Atacar" + "\n(2) - Defender" + "\n(3) - Fugir");
            System.out.println();
            int op = scanner.nextInt();

            switch (op){
                case 1:
                    mensagem.mensagemSleep("Seguindo para a escolha de habilidade...");
                    System.out.println("Escolha sua habilidade: ");

                    for (int i = 0; i < arqueiro.getHabilidades().size(); i++) {
                        Habilidade h = arqueiro.getHabilidades().get(i);
                        System.out.println((i+1) + " - " + h.getNome() + "(dano base: " + h.getDanoBase() + ")");
                    }

                    System.out.print("Digite o número da habilidade: ");
                    int escolha = scanner.nextInt();

                    int indice = escolha - 1;
                    if(indice >= 0 && indice < arqueiro.getHabilidades().size()){
                        Habilidade habilidadeEscolhida = arqueiro.getHabilidades().get(indice);
                        arqueiro.atacar(goblin, habilidadeEscolhida);

                    } else {
                        System.out.println("Escolha inválida!");
                        break;
                    }

                    if (!goblin.estaVivo()) {
                        System.out.println("\n🏹 " + goblin.getNome() + " foi derrotado! você ganhou " +
                                goblin.getRecompensaXP() + " de xp.");
                        break;
                    }

                    // Turno do inimigo
                    System.out.println("\n \uD83D\uDC80 Turno do inimigo!");
                    Habilidade habilidadeGoblin = goblin.getHabilidades().get(0);
                    goblin.atacar(arqueiro, habilidadeGoblin);

                    if(goblin.fugir(arqueiro)){
                        System.out.println("\uD83C\uDFCE\uFE0F\uD83D\uDCA8" + " O inimigo fugiu, você ganhou " +
                                goblin.getRecompensaXP() + " de xp.");
                    }

                    if(!arqueiro.estaVivo()){
                        System.out.println("\\n\uD83D\uDC80 " + " arqueiro está morto! Fim de jogo.");
                        break;
                    }

                    break;
                case 2:
                    // Turno do inimigo
                    System.out.println("Você escolheu se defender.");
                    System.out.println("\n \uD83D\uDC80 Turno do inimigo!");
                    Habilidade habilidadeGoblin2 = goblin.getHabilidades().get(0);
                    arqueiro.defender(goblin, habilidadeGoblin2);
                    break;
                case 3:
                    boolean fugiu = arqueiro.fugir(goblin);
                    if(fugiu){
                        System.out.println("⚠\uFE0F CampoBatalha.Batalha encerrada. Você fugiu!");
                        break;
                    }
                    break;
            }

            turno++;

            if(!arqueiro.estaVivo() || !goblin.estaVivo()){
                System.out.println("\\n\uD83C\uDFC1 Fim da batalha!");
            }
        }

    }

}
