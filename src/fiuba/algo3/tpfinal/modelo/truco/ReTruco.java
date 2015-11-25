package fiuba.algo3.tpfinal.modelo.truco;

import fiuba.algo3.tpfinal.modelo.error.NoRespetaJerarquiaDeTrucoError;

public class ReTruco extends EstadoTruco {

	public ReTruco(EstadoTruco estadoAnterior) {
		if (!(estadoAnterior instanceof Truco)) {
			throw new NoRespetaJerarquiaDeTrucoError();
		}
	}

	public int obtenerPuntosQueridos() {
		return 3;
	}

	public int obtenerPuntosNoQueridos() {
		return 2;
	}
}
