package fiuba.algo3.tpfinal.modelo.ronda;

import java.util.LinkedList;

import fiuba.algo3.tpfinal.modelo.Jugador;

public class Primera extends EstadoRonda {
	
	public Primera(EstadoRonda estadoAnterior) {
		super(estadoAnterior);
		this.mano = Mano.PRIMERA;
	}

	@Override
	public Boolean concluyoRonda() {
		
		return null;
	}

	@Override
	public Jugador obtenerGanadorDeLaRonda(LinkedList<Jugador> jugadores) {
		
		return null;
	}

	
}