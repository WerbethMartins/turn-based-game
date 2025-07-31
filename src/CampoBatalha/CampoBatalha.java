package CampoBatalha;

import Combate.ResultadoAtaque;
import Combate.ResultadoTurnoInimigo;
import Habilidade.Habilidade;
import Personagens.*;

import java.util.ArrayList;

public class CampoBatalha {
    private Personagem personagemSelecionado;
    private Arqueiro arqueiro;
    private Mago mago;
    private Inimigos goblin;
    private int turno;
    private boolean batalhaAtiva;

    public Personagem getHeroi() {
        return personagemSelecionado;
    }

    public void setHeroi(Personagem personagemSelecionado) {
        this.personagemSelecionado = personagemSelecionado;
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

    public void iniciarPersonagem(Personagem personagemSelecionado){
        this.personagemSelecionado = personagemSelecionado;

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
                80,
                Math.random() < 0.5,
                12);

        System.out.println("Habilidades do goblin:");
        for (Habilidade h : goblin.getHabilidades()) {
            System.out.println("- " + h.getNome());
        }
    }

    public CampoBatalha() {
        this.turno = 1;
        this.batalhaAtiva = true;
        iniciarPersonagem(personagemSelecionado);
    }

    public String atacarComHabilidade(int indice){
        if(indice < 0 || indice >= personagemSelecionado.getHabilidades().size()){
            return "Escolha inválida.";
        }

        Habilidade habilidade = personagemSelecionado.getHabilidades().get(indice);
        personagemSelecionado.atacar(goblin, habilidade);

        if(!goblin.estaVivo()){
            batalhaAtiva = false;
            return "\uD83C\uDFF9 " + goblin.getNome() + " foi derrotado! Você ganho " + personagemSelecionado.getPontosXp();
        }

        if(!personagemSelecionado.estaVivo()){
            batalhaAtiva = false;
            return "☠\uFE0F Você foi derrotado.";
        }

        return "Você atacou com " + habilidade.getNome();
    }

    public String defender(Personagem inimigo, Habilidade habilidade){
        Habilidade habilidadeGoblin = goblin.getHabilidades().get(0);
        personagemSelecionado.defender(goblin, habilidadeGoblin);
        return "Você se defendeu.";
    }

    public String fugir(){
        boolean fugiu = personagemSelecionado.fugir(goblin);
        batalhaAtiva = !fugiu;
        return fugiu ? " ⚠\uFE0F Você fugiu da batalha com sucesso." : "Falha ao fugir da batalha.";
    }

    public ResultadoTurnoInimigo turnoDoInimigo(){
        if (goblin == null || personagemSelecionado == null) {
            System.out.println("[ERRO] Goblin ou Arqueiro está null");
            return new ResultadoTurnoInimigo(null, null);
        }

        Habilidade habilidade = goblin.escolherHabilidadeAleatoria();

        if (habilidade == null) {
            System.out.println("[ERRO] Habilidade do goblin veio null");
            return new ResultadoTurnoInimigo(null, null);
        }

        ResultadoAtaque resultado = goblin.atacar(personagemSelecionado, habilidade);

        if (resultado == null) {
            System.out.println("[ERRO] Resultado do ataque veio null");
        }

        return new ResultadoTurnoInimigo(resultado,habilidade);
    }

    public ArrayList<Habilidade> getHabilidadesPersonagem(){
        return personagemSelecionado.getHabilidades();
    }

    public boolean estaAtiva(){
        System.out.println("Batalha entre " + getHeroi().getNome() + " e " + getInimigo().getNome()
                + " está ativa.");
        return batalhaAtiva;
    }

    public String status(){
        return "Arqueiro: " + personagemSelecionado.getPontosVida() + " HP | Goblin:" + goblin.getPontosVida() + " HP"
                + "\n" + personagemSelecionado.getMensagemVida();
    }

}
