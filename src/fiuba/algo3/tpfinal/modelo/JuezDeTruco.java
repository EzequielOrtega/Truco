package fiuba.algo3.tpfinal.modelo;

import java.util.LinkedList;

public class JuezDeTruco {

	private CalculadorDeTruco calculador = new CalculadorDeTruco();

	public Jugador ganadorEnvido(LinkedList<Jugador> jugadores) {
		Jugador ganadorEnvido = jugadores.getFirst();
		int maximoValor = jugadores.getFirst().getValorEnvido();
		for (Jugador jug : jugadores) {
			if (jug.getValorEnvido() > maximoValor) {
				ganadorEnvido = jug;
			}
			if ((jug.getValorEnvido() == maximoValor) && (!ganadorEnvido.estanEnElMismoEquipo(jug))) {
				ganadorEnvido = jugadores.get(0);
			}
		}

		return ganadorEnvido;
	}

	public Jugador ganadorFlor(LinkedList<Jugador> jugadores) {
		Jugador ganadorFlor = jugadores.getFirst();
		int maximoValor = jugadores.getFirst().getValorFlor();
		for (Jugador jug : jugadores) {
			if (jug.getValorFlor() > maximoValor) {
				ganadorFlor = jug;
			}
			if ((jug.getValorFlor() == maximoValor) && (!ganadorFlor.estanEnElMismoEquipo(jug))) {
				ganadorFlor = jugadores.get(0);
			}
		}

		return ganadorFlor;
	}

	// Devuelve:
	// ganador1 si gana el equipo1
	// ganador2 si gana el equipo2
	// empate en caso de empardar
	public Resultado ganadorDeLaMano(LinkedList<Carta> cartas) {
		int valorMaximo = -1;
		for (int x = 0; x < cartas.size() - 1; x++) {
			int valorMaximoActual = Math.max(calculador.obtenerValorCarta(cartas.get(x)),
					calculador.obtenerValorCarta(cartas.get(x + 1)));
			if (valorMaximoActual > valorMaximo) {
				valorMaximo = valorMaximoActual;
			}
		}
		Resultado resultado = null;
		for (Integer x = 0; x < cartas.size(); x++) {

			if ((calculador.obtenerValorCarta(cartas.get(x)) == valorMaximo) && (resultado == null)) {
				if (x % 2 == 0) {
					resultado = Resultado.GANADOR1;
				} else {
					resultado = Resultado.GANADOR2;
				}
			} else if ((calculador.obtenerValorCarta(cartas.get(x)) == valorMaximo) && (resultado != null)) {
				resultado = Resultado.EMPATE;
			}

		}
		return resultado;
	}

	public Carta obtenerCartaMasAlta(LinkedList<Carta> cartas) {
		Carta cartaMasAlta = cartas.get(0);
		for (Carta carta : cartas) {
			if (calculador.obtenerValorCarta(cartaMasAlta) < calculador.obtenerValorCarta(carta)) {
				cartaMasAlta = carta;
			}
		}
		return cartaMasAlta;
	}

	public Resultado ganadorDeLaMano(LinkedList<Carta> cartas, LinkedList<Jugador> jugadores) {
		Carta cartaMasAlta = cartas.get(0);
		Boolean huboEmpate = false;
		for (int x = 1; x < jugadores.size(); x++) {
			if (calculador.obtenerValorCarta(cartaMasAlta) < calculador.obtenerValorCarta(cartas.get(x))) {
				cartaMasAlta = cartas.get(x);
				huboEmpate = false;
			} else if (calculador.obtenerValorCarta(cartaMasAlta) == calculador.obtenerValorCarta(cartas.get(x))) {
				huboEmpate = true;
			}
		}
		if (!huboEmpate) {
			Jugador ganadorDeLaMano = null;
			for (Jugador jugadorActual : jugadores) {
				if (jugadorActual.poseeCarta(cartaMasAlta)) {
					ganadorDeLaMano = jugadorActual;
					break;
				}
			}
			if (ganadorDeLaMano.coincideElEquipo(Equipo.EQUIPO1)) {
				return Resultado.GANADOR1;
			} else {
				return Resultado.GANADOR2;
			}
		} else {
			return Resultado.EMPATE;
		}

	}
}
