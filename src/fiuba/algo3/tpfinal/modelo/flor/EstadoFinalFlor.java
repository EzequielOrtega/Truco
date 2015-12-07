package fiuba.algo3.tpfinal.modelo.flor;

import java.util.LinkedList;

public class EstadoFinalFlor extends EstadoFlor {

	private static LinkedList<Object> estadosAceptados;

	static {
		estadosAceptados = new LinkedList<Object>();
		estadosAceptados.add(EstadoInicialFlor.class);
		estadosAceptados.add(Flor.class);
		estadosAceptados.add(ContraFlor.class);
		estadosAceptados.add(ContraFlorAlResto.class);
	}

	public EstadoFinalFlor(EstadoFlor estadoAnterior) {
		super(estadoAnterior, estadosAceptados);
		this.puntosDeEstado = 0;
	}
}
