package fiuba.algo3.tpfinal.modelo.flor;

import java.util.LinkedList;

import fiuba.algo3.tpfinal.modelo.error.NoSePuedeRechazarFlorError;

public class Flor extends EstadoFlor {

	private static LinkedList<Object> estadosAceptados;

	static {
		estadosAceptados = new LinkedList<Object>();
		estadosAceptados.add(EstadoInicialFlor.class);
	}

	public Flor(EstadoFlor estadoAnterior) {
		super(estadoAnterior, estadosAceptados);
		this.puntosDeEstado = 3;
	}

	public int obtenerPuntosNoQueridos() {
		throw new NoSePuedeRechazarFlorError();
	}
}
