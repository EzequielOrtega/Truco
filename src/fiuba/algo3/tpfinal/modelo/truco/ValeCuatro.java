package fiuba.algo3.tpfinal.modelo.truco;

import fiuba.algo3.tpfinal.modelo.error.NoRespetaJerarquiaDeTrucoError;

public class ValeCuatro extends EstadoTruco {

	public ValeCuatro(EstadoTruco estadoAnterior) {
		if (!(estadoAnterior instanceof ReTruco)) {
			throw new NoRespetaJerarquiaDeTrucoError();
		}
	}

	public int obtenerPuntosQueridos() {
		return 4;
	}

	public int obtenerPuntosNoQueridos() {
		return 3;
	}
}
