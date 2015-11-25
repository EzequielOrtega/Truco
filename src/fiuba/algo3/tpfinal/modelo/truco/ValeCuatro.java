package fiuba.algo3.tpfinal.modelo.truco;

import java.util.LinkedList;

public class ValeCuatro extends EstadoTruco {

	private static LinkedList<Object> estadosAceptados;

	static {
		estadosAceptados = new LinkedList<>();
		estadosAceptados.add(ReTruco.class);
	}
	public ValeCuatro(EstadoTruco estadoAnterior) {
		super(estadoAnterior, estadosAceptados);
	}

	public int obtenerPuntosQueridos() {
		return 4;
	}

	public int obtenerPuntosNoQueridos() {
		return 3;
	}
}
