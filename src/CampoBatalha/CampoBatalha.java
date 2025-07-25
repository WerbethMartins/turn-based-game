package CampoBatalha;

import Combate.ResultadoAtaque;
import Combate.ResultadoTurnoInimigo;
import Habilidade.Habilidade;
import Personagens.*;

import java.util.ArrayList;

public class CampoBatalha {
    private Arqueiro arqueiro;
    private Inimigos goblin;
    private int turno;
    private boolean batalhaAtiva;

    public Personagem getHeroi() {
        return arqueiro;
    }

    public void setHeroi(Arqueiro arqueiro) {
        this.arqueiro = arqueiro;
    }

    public Inimigos getInimigo() {
        return goblin;
    }

    public void setInimigo(Inimigos goblin) {
        this.goblin = goblin;
    }

    public boolean isBatalhaAtiva() {
        return batalhaAtiva;
    }

    public void setBatalhaAtiva(boolean batalhaAtiva) {
        this.batalhaAtiva = batalhaAtiva;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public void iniciarPersonagem(){
        this.arqueiro = new Arqueiro("Arc");

        ArrayList<Habilidade> habilidadesGoblin = new ArrayList<>();
        habilidadesGoblin.add(new Habilidade("Estocada Precisa", TipoHabilidade.FISICO, 13));
        habilidadesGoblin.add(new Habilidade("Ataque pesado", TipoHabilidade.FISICO, 15));
        habilidadesGoblin.add(new Habilidade("Triplo ataque na cabeça", TipoHabilidade.FISICO, 18));
        goblin = new Inimigos(
                "Gorlag",
                100,
                10,
                10,
                15,
                habilidadesGoblin,
                TipoPersonagem.GOBLIN,
                20,
                Math.random() < 0.5,
                20);

        System.out.println("Habilidades do goblin:");
        for (Habilidade h : goblin.getHabilidades()) {
            System.out.println("- " + h.getNome());
        }
    }


    public CampoBatalha() {
        this.turno = 1;
        this.batalhaAtiva = true;
        iniciarPersonagem();
    }

    public String atacarComHabilidade(int indice){
        if(indice < 0 || indice >= arqueiro.getHabilidades().size()){
            return "Escolha inválida.";
        }

        Habilidade habilidade = arqueiro.getHabilidades().get(indice);
        arqueiro.atacar(goblin, habilidade);

        if(!goblin.estaVivo()){
            batalhaAtiva = false;
            return "\uD83C\uDFF9 " + goblin.getNome() + " foi derrotado!";
        }

        if(!arqueiro.estaVivo()){
            batalhaAtiva = false;
            return "☠\uFE0F Você foi derrotado.";
        }

        return "Você atacou com " + habilidade.getNome();
    }

    public String defender(){
        Habilidade habilidadeGoblin = goblin.getHabilidades().get(0);
        arqueiro.defender(goblin, habilidadeGoblin);
        return "Você se defendeu.";
    }

    public String fugir(){
        boolean fugiu = arqueiro.fugir(goblin);
        batalhaAtiva = !fugiu;
        return fugiu ? " ⚠\uFE0F Você fugiu da batalha com sucesso." : "Falha ao fugir da batalha.";
    }

    public ResultadoTurnoInimigo turnoDoInimigo(){
        if (goblin == null || arqueiro == null) {
            System.out.println("[ERRO] Goblin ou Arqueiro está null");
            return new ResultadoTurnoInimigo(null, null);
        }

        Habilidade habilidade = goblin.escolherHabilidadeAleatoria();

        if (habilidade == null) {
            System.out.println("[ERRO] Habilidade do goblin veio null");
            return new ResultadoTurnoInimigo(null, null);
        }

        ResultadoAtaque resultado = goblin.atacar(arqueiro, habilidade);

        if (resultado == null) {
            System.out.println("[ERRO] Resultado do ataque veio null");
        }

        return new ResultadoTurnoInimigo(resultado, habilidade);
    }

    public ArrayList<Habilidade> getHabilidadesArqueiro(){
        return arqueiro.getHabilidades();
    }

    public boolean estaAtiva(){
        return batalhaAtiva;
    }

    public String status(){
        return "Arqueiro: " + arqueiro.getPontosVida() + " HP | Goblin:" + goblin.getPontosVida() + " HP"
                + "\n" + arqueiro.getMensagemVida();
    }

}
