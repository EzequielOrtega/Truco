package fiuba.algo3.tpfinal.modelo.envido;

import fiuba.algo3.tpfinal.modelo.error.NoPuedeCantarEnvidoSeCantoFlorError;
import fiuba.algo3.tpfinal.modelo.error.SoloSePuedeCantarEnvidoUnaVezPorRondaError;
import fiuba.algo3.tpfinal.modelo.flor.EstadoFlor;

import java.util.LinkedList;

public abstract class EstadoEnvido {

	protected EstadoEnvido estadoAnterior;
	protected int puntosDeEstado;
	private static LinkedList<Object> estadoFinal;

	static {
		estadoFinal = new LinkedList<Object>();
		estadoFinal.add(EstadoFinalEnvido.class);
	}

	public EstadoEnvido(EstadoEnvido estadoAnterior, EstadoFlor estadoFlor, LinkedList<Object> estadosAceptados) {
		if (estadoFinal.contains(estadoAnterior.getClass())) {
			throw new SoloSePuedeCantarEnvidoUnaVezPorRondaError();
		}
		if (!estadosAceptados.contains(estadoFlor.getClass())) {
			throw new NoPuedeCantarEnvidoSeCantoFlorError();
		}
		this.estadoAnterior = estadoAnterior;
	}

	public EstadoEnvido(EstadoEnvido estadoAnterior) {
		this.estadoAnterior = estadoAnterior;
	}

	public int obtenerPuntosQueridos() {
		return (this.puntosDeEstado + estadoAnterior.obtenerPuntosQueridos());
	}

	public int obtenerPuntosNoQueridos() {
		if (estadoAnterior instanceof EstadoInicialEnvido) {
			return (estadoAnterior.obtenerPuntosNoQueridos());
		} else {
			return (estadoAnterior.obtenerPuntosQueridos());
		}
	}

	public int getPuntosDeEstado() {
		return this.puntosDeEstado;
	}
}
