package fiuba.algo3.tpfinal.modelo.envido;

import fiuba.algo3.tpfinal.modelo.error.*;
public abstract class EstadoEnvido {
	
	protected EstadoEnvido estadoAnterior;
	protected int puntosDeEstado;
	
	public EstadoEnvido(EstadoEnvido estadoAnterior) {
		if (estadoAnterior instanceof EstadoFinalEnvido) {
			throw new SoloSePuedeCantarEnvidoUnaVezPorRondaError();
		}
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
