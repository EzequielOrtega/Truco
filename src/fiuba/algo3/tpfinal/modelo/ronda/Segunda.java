package fiuba.algo3.tpfinal.modelo.ronda;

import java.util.LinkedList;

import fiuba.algo3.tpfinal.modelo.Equipo;
import fiuba.algo3.tpfinal.modelo.Jugador;
import fiuba.algo3.tpfinal.modelo.Resultado;
import fiuba.algo3.tpfinal.modelo.error.NoHayGanadorDeRondaInconclusaError;

public class Segunda extends EstadoRonda {

	private Resultado resultadoDeSegunda;
	private static LinkedList<Object> estadosAceptados;

	static {
		estadosAceptados = new LinkedList<>();
		estadosAceptados.add(Primera.class);
	}

	public Segunda(EstadoRonda estadoAnterior) {
		super(estadoAnterior, estadosAceptados);
		this.mano = Mano.SEGUNDA;
	}

	public Segunda(EstadoRonda estadoAnterior, Resultado resultadoDeSegunda) {
		super(estadoAnterior, estadosAceptados);
		this.resultadoDeSegunda = resultadoDeSegunda;
		this.mano = Mano.SEGUNDA;
	}

	@Override
	public Boolean concluyoRonda() {
		Boolean concluyoLaRonda = false;
		if ((this.estadoAnterior.obtenerResultadoDeMano() == Resultado.EMPATE)
				&& (resultadoDeSegunda != Resultado.EMPATE)) {
			concluyoLaRonda = true;
		}
		if ((this.estadoAnterior.obtenerResultadoDeMano() != Resultado.EMPATE)
				&& (resultadoDeSegunda == Resultado.EMPATE)) {
			concluyoLaRonda = true;
		}
		if ((this.estadoAnterior.obtenerResultadoDeMano() == resultadoDeSegunda)
				&& (resultadoDeSegunda != Resultado.EMPATE)) {
			concluyoLaRonda = true;
		}
		return concluyoLaRonda;
	}

	@Override
	public Jugador obtenerGanadorDeLaRonda(LinkedList<Jugador> jugadores) {
		if (!this.concluyoRonda()) {
			throw new NoHayGanadorDeRondaInconclusaError();
		}
		Jugador ganador = null;
		switch (this.obtenerGanadorParcial()) {
		case GANADOR1: {
			if (jugadores.get(0).coincideElEquipo(Equipo.EQUIPO1)){
				ganador = jugadores.get(0);
			} else {
				ganador = jugadores.get(1);
			}
			break;
		}
		case GANADOR2: {
			if (jugadores.get(0).coincideElEquipo(Equipo.EQUIPO2)){
				ganador = jugadores.get(0);
			} else {
				ganador = jugadores.get(1);
			}
			break;
		}
		case EMPATE: {
			break;
		}
		}
		return ganador;
	}

	public Resultado obtenerResultadoDeMano() {
		return resultadoDeSegunda;
	}

	public void cambiarResultadoDeSegunda(Resultado resultadoDeSegunda) {
		this.resultadoDeSegunda = resultadoDeSegunda;
	}

	@Override
	protected Resultado obtenerGanadorParcial() {
		Resultado resultadoParcial = null;
		if ((estadoAnterior.obtenerResultadoDeMano() == resultadoDeSegunda)
				&& (resultadoDeSegunda != Resultado.EMPATE)) {
			resultadoParcial = resultadoDeSegunda;
		} else if ((estadoAnterior.obtenerResultadoDeMano() != Resultado.EMPATE)
				&& (resultadoDeSegunda == Resultado.EMPATE)) {
			resultadoParcial = estadoAnterior.obtenerResultadoDeMano();
		} else if ((estadoAnterior.obtenerResultadoDeMano() == Resultado.EMPATE)
				&& (resultadoDeSegunda != Resultado.EMPATE)) {
			resultadoParcial = resultadoDeSegunda;
		} else {
			resultadoParcial = Resultado.EMPATE;
		}
		return resultadoParcial;
	}

	@Override
	protected Resultado obtenerResultadoDePrimera() {

		return estadoAnterior.obtenerResultadoDePrimera();
	}

}
