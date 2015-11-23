package fiuba.algo3.tpfinal.modelo.flor;

import fiuba.algo3.tpfinal.modelo.error.NoRespetaJerarquiaDeFlorError;

public class ContraFlorAlResto extends EstadoFlor {
    public ContraFlorAlResto(EstadoFlor estadoAnterior) {
        super(estadoAnterior);
        if (! (estadoAnterior instanceof ContraFlor)) {
            throw new NoRespetaJerarquiaDeFlorError();
        }
    }
}
