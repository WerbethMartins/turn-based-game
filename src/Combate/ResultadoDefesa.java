package Combate;

public class ResultadoDefesa {
    private int danoOriginal;
    private boolean defesaAbsoluta;
    private int vidaRestante;

    public ResultadoDefesa(int danoOriginal, boolean defesaAbsoluta, int vidaRestante) {
        this.danoOriginal = danoOriginal;
        this.defesaAbsoluta = defesaAbsoluta;
        this.vidaRestante = vidaRestante;
    }

    public int getDanoOriginal() {
        return danoOriginal;
    }

    public void setDanoOriginal(int danoOriginal) {
        this.danoOriginal = danoOriginal;
    }

    public boolean isDefesaAbsoluta() {
        return defesaAbsoluta;
    }

    public void setDefesaAbsoluta(boolean defesaAbsoluta) {
        this.defesaAbsoluta = defesaAbsoluta;
    }

    public int getVidaRestante() {
        return vidaRestante;
    }

    public void setVidaRestante(int vidaRestante) {
        this.vidaRestante = vidaRestante;
    }
}
