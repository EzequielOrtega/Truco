package fiuba.algo3.tpfinal.modelo.ronda;

import java.util.LinkedList;

import fiuba.algo3.tpfinal.modelo.Jugador;

public abstract class EstadoRonda {

	protected EstadoRonda estadoRonda;

	public EstadoRonda(EstadoRonda estadoAnterior) {
		this.estadoRonda = estadoAnterior;
	}
		
	public abstract Boolean concluyoRonda();
	
	public abstract Jugador obtenerGanadorDeLaRonda(LinkedList<Jugador> jugadores);

	
}
