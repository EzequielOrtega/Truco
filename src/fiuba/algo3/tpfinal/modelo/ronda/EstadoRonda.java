package fiuba.algo3.tpfinal.modelo.ronda;

import fiuba.algo3.tpfinal.modelo.Jugador;
import fiuba.algo3.tpfinal.modelo.Resultado;
import fiuba.algo3.tpfinal.modelo.error.NoRespetaJerarquiaDeRondaError;
import java.util.LinkedList;

public abstract class EstadoRonda {

	protected EstadoRonda estadoAnterior;
	protected Mano mano;

	public EstadoRonda(EstadoRonda estadoAnterior, LinkedList<Object> estadosAceptados) {
		if (!estadosAceptados.contains(estadoAnterior.getClass())) {
			throw new NoRespetaJerarquiaDeRondaError();
		}
		this.estadoAnterior = estadoAnterior;
	}
	
	public EstadoRonda() {
		this.estadoAnterior = null;
	}
	
	public Mano obtenerMano() {
		return mano;
	}
	
	protected abstract Resultado obtenerResultadoDeMano();
	
	protected abstract Resultado obtenerResultadoDePrimera();
	
	public abstract Boolean concluyoRonda();
	
	protected abstract Resultado obtenerGanadorParcial();
	
	public abstract Jugador obtenerGanadorDeLaRonda(LinkedList<Jugador> jugadores);

	
}
