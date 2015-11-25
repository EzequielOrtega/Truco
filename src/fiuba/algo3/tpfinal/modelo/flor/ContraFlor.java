package fiuba.algo3.tpfinal.modelo.flor;

import fiuba.algo3.tpfinal.modelo.error.NoRespetaJerarquiaDeFlorError;

public class ContraFlor extends EstadoFlor {

	public ContraFlor(EstadoFlor estadoAnterior) {
		super(estadoAnterior);
		if (!(estadoAnterior instanceof Flor)) {
			throw new NoRespetaJerarquiaDeFlorError();
		}
		this.puntosDeEstado = 3;
	}

	public int obtenerPuntosNoQueridos() {
		return estadoAnterior.obtenerPuntosQueridos() + 1;
	}
}
