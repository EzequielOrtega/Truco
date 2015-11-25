package fiuba.algo3.tpfinal.modelo.envido;

import fiuba.algo3.tpfinal.modelo.flor.EstadoFinalFlor;
import fiuba.algo3.tpfinal.modelo.flor.EstadoFlor;
import fiuba.algo3.tpfinal.modelo.flor.EstadoInicialFlor;

import java.util.LinkedList;

public class FaltaEnvido extends EstadoEnvido {

	private static LinkedList<Object> estadosAceptados;

	static {
		estadosAceptados = new LinkedList<>();
		estadosAceptados.add(EstadoInicialFlor.class);
		estadosAceptados.add(EstadoFinalFlor.class);
	}
	public FaltaEnvido(EstadoEnvido estadoAnterior, EstadoFlor estadoFlor) {
		super(estadoAnterior, estadoFlor, estadosAceptados);
	}
}
