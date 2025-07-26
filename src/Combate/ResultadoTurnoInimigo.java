package Combate;

import Habilidade.Habilidade;
import Personagens.Inimigos;

public class ResultadoTurnoInimigo {
    private ResultadoAtaque resultado;
    private Habilidade habilidadeUsada;

    public ResultadoTurnoInimigo(ResultadoAtaque resultado, Habilidade habilidadeUsada) {
        this.resultado = resultado;
        this.habilidadeUsada = habilidadeUsada;
    }

    public ResultadoAtaque getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoAtaque resultado) {
        this.resultado = resultado;
    }

    public Habilidade getHabilidadeUsada() {
        return habilidadeUsada;
    }

    public void setHabilidadeUsada(Habilidade habilidadeUsada) {
        this.habilidadeUsada = habilidadeUsada;
    }

}
