package fiuba.algo3.tpfinal.modelo.flor;

import fiuba.algo3.tpfinal.modelo.error.SoloSePuedeCantarFlorUnaVezPorRondaError;

public abstract class EstadoFlor {

    protected EstadoFlor estadoAnterior;
    protected int puntosDeEstado;

    public EstadoFlor(EstadoFlor estadoAnterior) {
        if (estadoAnterior instanceof EstadoFinalFlor) {
            throw new SoloSePuedeCantarFlorUnaVezPorRondaError();
        }
        this.estadoAnterior = estadoAnterior;
    }

    public int obtenerPuntosQueridos() {
        return (this.puntosDeEstado + estadoAnterior.obtenerPuntosQueridos());
    }
    
    public int obtenerPuntosNoQueridos() {
        return 0;
    }

    public int getPuntosDeEstado() {
        return puntosDeEstado;
    }
}
