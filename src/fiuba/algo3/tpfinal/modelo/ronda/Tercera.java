package fiuba.algo3.tpfinal.modelo.ronda;

import java.util.LinkedList;

import fiuba.algo3.tpfinal.modelo.Equipo;
import fiuba.algo3.tpfinal.modelo.Jugador;
import fiuba.algo3.tpfinal.modelo.Resultado;

public class Tercera extends EstadoRonda {

	private Resultado resultadoDeTercera;
	private static LinkedList<Object> estadosAceptados;

	static {
		estadosAceptados = new LinkedList<>();
		estadosAceptados.add(Segunda.class);
	}

	public Tercera(EstadoRonda estadoAnterior) {
		super(estadoAnterior, estadosAceptados);
		this.mano = Mano.TERCERA;
	}

	public Tercera(EstadoRonda estadoAnterior, Resultado resultadoDeTercera) {
		super(estadoAnterior, estadosAceptados);
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
			if (jugadores.get(0).coincideElEquipo(Equipo.EQUIPO1)) {
				ganador = jugadores.get(0);
			} else {
				ganador = jugadores.get(1);
			}
			break;
		}
		case GANADOR2: {
			if (jugadores.get(0).coincideElEquipo(Equipo.EQUIPO2)) {
				ganador = jugadores.get(0);
			} else {
				ganador = jugadores.get(1);
			}
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
