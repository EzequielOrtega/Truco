package fiuba.algo3.tpfinal.modelo.flor;

import java.util.LinkedList;

public class ContraFlorAlResto extends EstadoFlor {

	private static LinkedList<Object> estadosAceptados;

	static {
		estadosAceptados = new LinkedList<Object>();
		estadosAceptados.add(Flor.class);
		estadosAceptados.add(ContraFlor.class);
	}

	public ContraFlorAlResto(EstadoFlor estadoAnterior, int puntosASumar) {
		super(estadoAnterior, estadosAceptados);
		this.puntosDeEstado = puntosASumar;
	}

	public int obtenerPuntosQueridos() {
		return puntosDeEstado;
	}

	public int obtenerPuntosNoQueridos() {
		return estadoAnterior.obtenerPuntosQueridos();
	}

}
