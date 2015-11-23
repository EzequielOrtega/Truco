package fiuba.algo3.tpfinal.modelo.ronda;

import java.util.LinkedList;

import fiuba.algo3.tpfinal.modelo.Jugador;
import fiuba.algo3.tpfinal.modelo.error.NoHayGanadorDeRondaInconclusaError;

public class EstadoInicialRonda extends EstadoRonda {
	
	public EstadoInicialRonda() {
		super(null);
		this.mano = Mano.INICIAL;
	}

	@Override
	public Boolean concluyoRonda() {
		
		return false;
	}

	@Override
	public Jugador obtenerGanadorDeLaRonda(LinkedList<Jugador> jugadores) {
		if(!this.concluyoRonda()){
			throw new NoHayGanadorDeRondaInconclusaError();
		}
		return null;
	}

	
}
