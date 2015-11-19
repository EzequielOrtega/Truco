package fiuba.algo3.tpfinal.modelo;

public class ValeCuatro extends EstadoTruco {

	public ValeCuatro(EstadoTruco estadoAnterior) {
		// TODO: preguntar si no es mejor preguntarle la clase al estadoAnterior,
		// siendo que en este caso la clase representa un estado.
		if (estadoAnterior.obtenerPuntosQueridos() != 3) { 
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
