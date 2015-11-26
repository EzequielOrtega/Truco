package fiuba.algo3.tpfinal.modelo.flor;

import fiuba.algo3.tpfinal.modelo.error.NoRespetaJerarquiaDeFlorError;

public class ContraFlorAlResto extends EstadoFlor {
	public ContraFlorAlResto(EstadoFlor estadoAnterior, int puntosASumar) {
		super(estadoAnterior);
		if (estadoAnterior instanceof EstadoInicialFlor) {
			throw new NoRespetaJerarquiaDeFlorError();
		}
		this.puntosDeEstado = puntosASumar;
	}

	// en este nivel, no se puede determinar la cantidad de puntos queridos
	public int obtenerPuntosQueridos() {
		return puntosDeEstado;
	}

	public int obtenerPuntosNoQueridos() {
		return estadoAnterior.obtenerPuntosQueridos();
	}

}
