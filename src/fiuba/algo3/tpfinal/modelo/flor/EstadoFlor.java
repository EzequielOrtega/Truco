package fiuba.algo3.tpfinal.modelo.flor;

import java.util.LinkedList;

import fiuba.algo3.tpfinal.modelo.error.NoRespetaJerarquiaDeFlorError;
import fiuba.algo3.tpfinal.modelo.error.SoloSePuedeCantarFlorUnaVezPorRondaError;

public abstract class EstadoFlor {

	protected EstadoFlor estadoAnterior;
	protected int puntosDeEstado;
	

	public EstadoFlor(EstadoFlor estadoAnterior, LinkedList<Object> estadosAceptados) {
		if (estadoAnterior instanceof EstadoFinalFlor) {
			throw new SoloSePuedeCantarFlorUnaVezPorRondaError();
		}
		if (!estadosAceptados.contains(estadoAnterior.getClass())) {
			throw new NoRespetaJerarquiaDeFlorError();
		}
		this.estadoAnterior = estadoAnterior;
	}
	
	public EstadoFlor() {
		this.estadoAnterior = null;
	}

	public int obtenerPuntosQueridos() {
		return (this.puntosDeEstado + estadoAnterior.obtenerPuntosQueridos());
	}

	public int obtenerPuntosNoQueridos() {
		return 0;
	}

	public int getPuntosDeEstado() {
		return puntosDeEstado;
	}
}
