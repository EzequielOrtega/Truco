package fiuba.algo3.tpfinal.modelo.ronda;

import java.util.LinkedList;

import fiuba.algo3.tpfinal.modelo.Jugador;
import fiuba.algo3.tpfinal.modelo.Resultado;

public abstract class EstadoRonda {

	protected EstadoRonda estadoAnterior;

	public EstadoRonda(EstadoRonda estadoAnterior) {
		this.estadoAnterior = estadoAnterior;
	}
	
	protected abstract Resultado obtenerResultadoDeMano();
	
	public abstract Boolean concluyoRonda();
	
	protected abstract Resultado obtenerGanadorParcial();
	
	public abstract Jugador obtenerGanadorDeLaRonda(LinkedList<Jugador> jugadores);

	
}
