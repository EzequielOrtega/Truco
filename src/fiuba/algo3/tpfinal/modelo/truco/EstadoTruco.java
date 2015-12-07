package fiuba.algo3.tpfinal.modelo.truco;

import fiuba.algo3.tpfinal.modelo.envido.EstadoEnvido;
import fiuba.algo3.tpfinal.modelo.error.NoPuedeCantarTrucoSeCantoEnvidoError;
import fiuba.algo3.tpfinal.modelo.error.NoPuedeCantarTrucoSeCantoFlorError;
import fiuba.algo3.tpfinal.modelo.error.NoRespetaJerarquiaDeTrucoError;
import fiuba.algo3.tpfinal.modelo.flor.EstadoFlor;

import java.util.LinkedList;

public abstract class EstadoTruco {

	protected LinkedList<Object> estadosAceptados = new LinkedList<>();

	public EstadoTruco() {
	}

	public EstadoTruco(EstadoTruco estadoAnterior, LinkedList<Object> estadosAceptados) {
		if (!estadosAceptados.contains(estadoAnterior.getClass())) {
			throw new NoRespetaJerarquiaDeTrucoError();
		}
	}

	public EstadoTruco(EstadoTruco estadoAnterior, EstadoEnvido estadoEnvido, EstadoFlor estadoFlor,
			LinkedList<Object> estadosAceptados) {
		if (!estadosAceptados.contains(estadoAnterior.getClass())) {
			throw new NoRespetaJerarquiaDeTrucoError();
		}
		if (!estadosAceptados.contains(estadoEnvido.getClass())) {
			throw new NoPuedeCantarTrucoSeCantoEnvidoError();
		}
		if (!estadosAceptados.contains(estadoFlor.getClass())) {
			throw new NoPuedeCantarTrucoSeCantoFlorError();
		}
	}

	public abstract int obtenerPuntosQueridos();

	public abstract int obtenerPuntosNoQueridos();

}
