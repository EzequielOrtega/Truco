package fiuba.algo3.tpfinal.modelo.flor;

import java.util.LinkedList;

public class ContraFlor extends EstadoFlor {
	private static LinkedList<Object> estadosAceptados;

	static {
		estadosAceptados = new LinkedList<Object>();
		estadosAceptados.add(Flor.class);
	}

	public ContraFlor(EstadoFlor estadoAnterior) {
		super(estadoAnterior, estadosAceptados);
		this.puntosDeEstado = 3;
	}

	public int obtenerPuntosNoQueridos() {
		return estadoAnterior.obtenerPuntosQueridos() + 1;
	}
}
