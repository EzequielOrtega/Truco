package fiuba.algo3.tpfinal.modelo;

public class Truco extends EstadoTruco {
	
	public Truco(EstadoTruco estadoAnterior) {
		if (estadoAnterior.obtenerPuntosQueridos() != 0) {
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
