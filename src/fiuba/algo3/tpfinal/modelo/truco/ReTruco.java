package fiuba.algo3.tpfinal.modelo.truco;

import fiuba.algo3.tpfinal.modelo.error.NoRespetaJerarquiaDeTrucoError;

public class ReTruco extends EstadoTruco {

	public ReTruco(EstadoTruco estadoAnterior) {
		// TODO: preguntar si no es mejor preguntarle la clase al estadoAnterior,
		// siendo que en este caso la clase representa un estado.
		if (estadoAnterior.obtenerPuntosQueridos() != 2) { 
			throw new NoRespetaJerarquiaDeTrucoError();
		}
	}
	/*
	 *  le deje "hardcodeados" los valores en vez de que llame al estadoAnterior
	 *  porque no es necesario, como si lo es para el envido.
	 */
	public int obtenerPuntosQueridos() {
		return 3;
	}

	public int obtenerPuntosNoQueridos() {
		return 2;
	}
}
