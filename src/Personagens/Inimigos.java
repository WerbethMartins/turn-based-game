package Personagens;

import Combate.ResultadoAtaque;
import Combate.ResultadoDefesa;
import Habilidade.Habilidade;
import Util.mensagemSleep;

import java.util.ArrayList;
import java.util.List;

public class Inimigos extends Personagem{
    private TipoPersonagem tipo;
    private int recompensaXP;
    private boolean isChefe = false;
    private int habilidadeEspecial;

    mensagemSleep mensagem = new mensagemSleep();

    public Inimigos(){

    }

    public Inimigos(String nome, int pontosVida, int forca, int defesa, int velocidade,
                    ArrayList<Habilidade> habilidades, TipoPersonagem tipo, int recompensaXP, boolean isChefe, int habilidadeEspecial) {
        super(nome, pontosVida, forca, defesa, velocidade, habilidades);
        this.tipo = tipo;
        this.recompensaXP = recompensaXP;
        this.isChefe = isChefe;
        this.habilidadeEspecial = habilidadeEspecial;
    }

    public Inimigos(TipoPersonagem tipo, int recompensaXP, boolean isChefe, int habilidadeEspecial) {
        this.tipo = tipo;
        this.recompensaXP = recompensaXP;
        this.isChefe = isChefe;
        this.habilidadeEspecial = habilidadeEspecial;
    }

    public TipoPersonagem getTipo() {
        return tipo;
    }

    public void setTipo(TipoPersonagem tipo) {
        this.tipo = tipo;
    }

    public int getRecompensaXP() {
        return recompensaXP;
    }

    public void setRecompensaXP(int recompensaXP) {
        this.recompensaXP = recompensaXP;
    }

    public boolean isChefe() {
        return isChefe;
    }

    public void setChefe(boolean chefe) {
        isChefe = chefe;
    }

    public int getHabilidadeEspecial() {
        return habilidadeEspecial;
    }

    public void setHabilidadeEspecial(int habilidadeEspecial) {
        this.habilidadeEspecial = habilidadeEspecial;
    }

    @Override
    public Habilidade escolherHabilidadeAleatoria(){
        List<Habilidade> habilidades = this.getHabilidades();
        if(habilidades == null || habilidades.isEmpty()){
            return null;
        }

        int index = (int)(Math.random() * habilidades.size());
        return habilidades.get(index);
    }

    @Override
    public ResultadoAtaque atacar(Personagem alvo, Habilidade habilidade){
        int danoBase = calcularDano(alvo, habilidade);
        boolean habEspecial = Math.random() * 100 < getHabilidadeEspecial();
        this.isChefe = Math.random() < 0.5;
        double multiplicador = 1.0;

        if (habEspecial){
            multiplicador += 0.1; // Mais 10%
            System.out.println("⚡ Habilidade especial ativada!");
        }

        if(this.isChefe){
            multiplicador += 0.2;
            System.out.println("👑 O chefe ganha buffs!");
        }

        alvo.receberDano(danoBase);

        // Limitando o dano final do inimigo para não mais que 40 de dano
        int danoFinal = Math.min((int) (danoBase * multiplicador), 40);
        System.out.println(getNome() + " atacou " + alvo.getNome() + " causando " + danoBase + " de dano! \n"
        + alvo.getNome() + " agora tem " + alvo.getPontosVida() + " de vida!");
        System.out.println("==== Logs ====");
        System.out.println("Dano base: " + danoBase + "\nDefesa: " + alvo.getDefesa() + "\nMultiplicador final: "
                + multiplicador + "\nDano final: " + danoFinal);

        return new ResultadoAtaque(danoFinal,habEspecial, alvo.getPontosVida());
    }

    @Override
    public ResultadoDefesa defender(Personagem atacante, Habilidade habilidade) {

        return null;
    }

    @Override
    public boolean fugir(Personagem alvo) {
        boolean escolheuFugir = Math.random() < 0.5;

        if(getPontosVida() < 25 && getForca() < alvo.getDefesa()){
            escolheuFugir = true;
            System.out.println(getNome() + ", decidiu fugir da batalha.");
            System.out.println(alvo.getNome() + " recebeu: " + getRecompensaXP());
            mensagem.mensagemSleep("Saindo da batalha...");
        }else {
            escolheuFugir = false;
        }
        return false;
    }
}
