package fiuba.algo3.tpfinal.modelo.envido;

import fiuba.algo3.tpfinal.modelo.flor.EstadoFinalFlor;
import fiuba.algo3.tpfinal.modelo.flor.EstadoFlor;
import fiuba.algo3.tpfinal.modelo.flor.EstadoInicialFlor;

import java.util.LinkedList;

public class RealEnvido extends EstadoEnvido {

	private static LinkedList<Object> estadosAceptados;

	static {
		estadosAceptados = new LinkedList<>();
		estadosAceptados.add(EstadoInicialFlor.class);
		estadosAceptados.add(EstadoInicialEnvido.class);
		estadosAceptados.add(Envido.class);
		estadosAceptados.add(EstadoFinalFlor.class);
	}

	public RealEnvido(EstadoEnvido estadoAnterior, EstadoFlor estadoFlor) {
		super(estadoAnterior, estadoFlor, estadosAceptados);
		this.puntosDeEstado = 3;
	}
}
