package fiuba.algo3.tpfinal.modelo.truco;

import java.util.LinkedList;

public class EstadoInicialTruco extends EstadoTruco {
	private static LinkedList<Object> estadosAceptados;

	static {
		estadosAceptados = new LinkedList<>();
	}

	public EstadoInicialTruco() {
		super(null);
	}

	@Override
	public int obtenerPuntosQueridos() {
		return 1;
	}

	@Override
	public int obtenerPuntosNoQueridos() {
		return 0;
	}
}
