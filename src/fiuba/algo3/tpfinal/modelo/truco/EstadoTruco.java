package fiuba.algo3.tpfinal.modelo.truco;

import fiuba.algo3.tpfinal.modelo.error.NoRespetaJerarquiaDeTrucoError;

import java.util.LinkedList;

public abstract class EstadoTruco {


	protected LinkedList<Object> estadosAceptados = new LinkedList<>();

	public EstadoTruco(EstadoTruco estadoAnterior) {

	}
	public EstadoTruco(EstadoTruco estadoAnterior, LinkedList<Object> estadosAceptados) {
		if(!estadosAceptados.contains(estadoAnterior.getClass())) {
			throw new NoRespetaJerarquiaDeTrucoError();
		}
	}
	public abstract int obtenerPuntosQueridos();

	public abstract int obtenerPuntosNoQueridos();

}
