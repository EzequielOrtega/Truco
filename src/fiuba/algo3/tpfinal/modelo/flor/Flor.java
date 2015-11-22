package fiuba.algo3.tpfinal.modelo.flor;

import fiuba.algo3.tpfinal.modelo.error.*;

public class Flor extends EstadoFlor {
    public Flor(EstadoFlor estadoAnterior) {
        super(estadoAnterior);
        this.puntosDeEstado = 3;
        if (estadoAnterior instanceof EstadoFinalFlor)
            throw new SoloSePuedeCantarFlorUnaVezPorRondaError();
        if ((estadoAnterior instanceof ContraFlor) || (estadoAnterior.obtenerPuntosQueridos() == 6))
            throw new NoRespetaJerarquiaDeFlorError();
    }

    public int obtenerPuntosQueridos() {
        return this.puntosDeEstado + this.estadoAnterior.obtenerPuntosQueridos();
    }

    public int obtenerPuntosNoQueridos() {
        throw new NoSePuedeRechazarFlorError();
    }
}
