package fiuba.algo3.tpfinal.modelo.flor;

import fiuba.algo3.tpfinal.modelo.error.*;

public class Flor extends EstadoFlor {
    public Flor(EstadoFlor estadoAnterior) {
        super(estadoAnterior);
        this.puntosDeEstado = 3;
        if ((estadoAnterior instanceof EstadoFinalFlor) || (estadoAnterior instanceof Flor))
            throw new SoloSePuedeCantarFlorUnaVezPorRondaError();
        if (estadoAnterior instanceof ContraFlor)
            throw new NoRespetaJerarquiaDeFlorError();
    }

    public int obtenerPuntosNoQueridos() {
        throw new NoSePuedeRechazarFlorError();
    }
}
