package fiuba.algo3.tpfinal.modelo.flor;

import fiuba.algo3.tpfinal.modelo.error.NoRespetaJerarquiaDeFlorError;

public class ContraFlorAlResto extends EstadoFlor {
    public ContraFlorAlResto(EstadoFlor estadoAnterior) {
        super(estadoAnterior);
        if (estadoAnterior instanceof EstadoInicialFlor) {
            throw new NoRespetaJerarquiaDeFlorError();
        }
    }
    
    // en este nivel, no se puede determinar la cantidad de puntos queridos
    public int obtenerPuntosQueridos() {
    	return 0;
    }
    
    public int obtenerPuntosNoQueridos() {
    	return estadoAnterior.obtenerPuntosQueridos();
    }
    
}
