package fiuba.algo3.tpfinal.modelo.ronda;

import java.util.LinkedList;

import fiuba.algo3.tpfinal.modelo.Jugador;

public abstract class EstadoRonda {

	protected EstadoRonda estadoRonda;
	protected Mano mano;

	public EstadoRonda(EstadoRonda estadoAnterior) {
		this.estadoRonda = estadoAnterior;
	}
	
	public Mano obtenerMano() {
		return this.mano;
	}
	
	public abstract Boolean concluyoRonda();
	
	public abstract Jugador obtenerGanadorDeLaRonda(LinkedList<Jugador> jugadores);

	
}
