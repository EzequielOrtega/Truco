package fiuba.algo3.tpfinal.modelo.ronda;

import java.util.LinkedList;

import fiuba.algo3.tpfinal.modelo.Jugador;
import fiuba.algo3.tpfinal.modelo.Resultado;
import fiuba.algo3.tpfinal.modelo.error.NoRespetaJerarquiaDeRondaError;

public class Tercera extends EstadoRonda {

	private Resultado resultadoDeTercera;

	public Tercera(EstadoRonda estadoAnterior) {
		super(estadoAnterior);
		if (!(estadoAnterior instanceof Segunda)) {
			throw new NoRespetaJerarquiaDeRondaError();
		}
		this.mano = Mano.TERCERA;
	}

	public Tercera(EstadoRonda estadoAnterior, Resultado resultadoDeTercera) {
		super(estadoAnterior);
		if (!(estadoAnterior instanceof Segunda)) {
			throw new NoRespetaJerarquiaDeRondaError();
		}
		this.resultadoDeTercera = resultadoDeTercera;
		this.mano = Mano.TERCERA;
	}

	@Override
	protected Resultado obtenerResultadoDeMano() {

		return resultadoDeTercera;
	}

	@Override
	public Boolean concluyoRonda() {

		return true;
	}

	@Override
	protected Resultado obtenerGanadorParcial() {
		Resultado resultado = null;
		if (resultadoDeTercera != Resultado.EMPATE) {
			resultado = resultadoDeTercera;
		} else {
			resultado = this.obtenerResultadoDePrimera();
		}
		return resultado;
	}

	@Override
	public Jugador obtenerGanadorDeLaRonda(LinkedList<Jugador> jugadores) {
		Jugador ganador = null;
		switch (this.obtenerGanadorParcial()) {
		case GANADOR1: {
			ganador = jugadores.get(0);
			break;
		}
		case GANADOR2: {
			ganador = jugadores.get(1);
			break;
		}
		case EMPATE: {
			ganador = jugadores.get(0);
			break;
		}
		}
		return ganador;
	}

	@Override
	protected Resultado obtenerResultadoDePrimera() {

		return estadoAnterior.obtenerResultadoDePrimera();
	}

}
