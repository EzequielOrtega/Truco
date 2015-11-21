package fiuba.algo3.tpfinal.modelo.truco;

import fiuba.algo3.tpfinal.modelo.error.NoRespetaJerarquiaDeTrucoError;

public class Truco extends EstadoTruco {
	
	public Truco(EstadoTruco estadoAnterior) {
		if (estadoAnterior.obtenerPuntosQueridos() != 1) {
			throw new NoRespetaJerarquiaDeTrucoError();
		}
	}

	public int obtenerPuntosQueridos() {
		return 2;
	}

	public int obtenerPuntosNoQueridos() {
		return 1;
	}
}
