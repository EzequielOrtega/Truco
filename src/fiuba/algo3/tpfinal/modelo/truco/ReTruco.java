package fiuba.algo3.tpfinal.modelo.truco;

import java.util.LinkedList;

public class ReTruco extends EstadoTruco {

	private static LinkedList<Object> estadosAceptados;

	static {
		estadosAceptados = new LinkedList<>();
		estadosAceptados.add(Truco.class);
	}

	public ReTruco(EstadoTruco estadoAnterior) {
		super(estadoAnterior, estadosAceptados);
	}

	public int obtenerPuntosQueridos() {
		return 3;
	}

	public int obtenerPuntosNoQueridos() {
		return 2;
	}
}
