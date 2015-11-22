package fiuba.algo3.tpfinal.modelo.flor;

import fiuba.algo3.tpfinal.modelo.error.NoRespetaJerarquiaDeFlorError;

public class ContraFlor extends EstadoFlor {

    public ContraFlor(EstadoFlor estadoAnterior) {
        super(estadoAnterior);
        if (! (estadoAnterior instanceof Flor)) {
            throw new NoRespetaJerarquiaDeFlorError();
        }
        this.puntosDeEstado = 3;
    }

    // No se si ponerlo asi o directamente si poner return 6 y 4, porque nunca cambian.
    public int obtenerPuntosQueridos() {
        return (this.puntosDeEstado + estadoAnterior.obtenerPuntosQueridos());
    }
    public int obtenerPuntosNoQueridos() {
        return this.puntosDeEstado + 1;
    }
}
