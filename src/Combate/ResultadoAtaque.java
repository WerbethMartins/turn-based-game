package Combate;

public class ResultadoAtaque {
    private int danoCausado;
    private boolean critico;
    private int vidaRestante;

    public ResultadoAtaque(int danoCausado, boolean critico, int vidaRestante) {
        this.danoCausado = danoCausado;
        this.critico = critico;
        this.vidaRestante = vidaRestante;
    }

    public int getDanoCausado() {
        return danoCausado;
    }

    public void setDanoCausado(int danoCausado) {
        this.danoCausado = danoCausado;
    }

    public boolean isCritico() {
        return critico;
    }

    public void setCritico(boolean critico) {
        this.critico = critico;
    }

    public int getVidaRestante() {
        return vidaRestante;
    }

    public void setVidaRestante(int vidaRestante) {
        this.vidaRestante = vidaRestante;
    }

}
