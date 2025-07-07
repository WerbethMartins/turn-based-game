package Habilidade;

import Personagens.TipoHabilidade;

public class Habilidade {
    private String nome;
    private TipoHabilidade tipoHabilidade;
    private int danoBase;

    public Habilidade(){

    }

    public Habilidade(String nome, TipoHabilidade tipoHabilidade, int danoBase) {
        this.nome = nome;
        this.tipoHabilidade = tipoHabilidade;
        this.danoBase = danoBase;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoHabilidade getTipoHabilidade() {
        return tipoHabilidade;
    }

    public void setTipoHabilidade(TipoHabilidade tipoHabilidade) {
        this.tipoHabilidade = tipoHabilidade;
    }

    public int getDanoBase() {
        return danoBase;
    }

    public void setDanoBase(int danoBase) {
        this.danoBase = danoBase;
    }

}
