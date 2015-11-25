package fiuba.algo3.tpfinal.modelo.ronda;

import java.util.LinkedList;

import fiuba.algo3.tpfinal.modelo.Jugador;
import fiuba.algo3.tpfinal.modelo.Resultado;
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
		if (!this.concluyoRonda()) {
			throw new NoHayGanadorDeRondaInconclusaError();
		}
		return null;
	}

	@Override
	public Resultado obtenerResultadoDeMano() {

		return null;
	}

	@Override
	protected Resultado obtenerGanadorParcial() {

		return null;
	}

	@Override
	protected Resultado obtenerResultadoDePrimera() {

		return null;
	}

}
