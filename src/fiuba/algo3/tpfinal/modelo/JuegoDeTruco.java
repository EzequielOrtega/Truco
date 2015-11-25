package fiuba.algo3.tpfinal.modelo;

import java.util.LinkedList;

import fiuba.algo3.tpfinal.modelo.envido.Envido;
import fiuba.algo3.tpfinal.modelo.envido.EstadoEnvido;
import fiuba.algo3.tpfinal.modelo.envido.EstadoFinalEnvido;
import fiuba.algo3.tpfinal.modelo.envido.EstadoInicialEnvido;
import fiuba.algo3.tpfinal.modelo.envido.FaltaEnvido;
import fiuba.algo3.tpfinal.modelo.envido.RealEnvido;
import fiuba.algo3.tpfinal.modelo.error.JugadorNoTieneFlorError;
import fiuba.algo3.tpfinal.modelo.error.NoPuedeCantarTrucoSeCantoEnvidoError;
import fiuba.algo3.tpfinal.modelo.error.NoPuedeCantarTrucoSeCantoFlorError;
import fiuba.algo3.tpfinal.modelo.error.NoPuedeJugarSeCantoEnvidoError;
import fiuba.algo3.tpfinal.modelo.error.NoPuedeJugarSeCantoFlorError;
import fiuba.algo3.tpfinal.modelo.error.NoPuedeJugarSeCantoTrucoError;
import fiuba.algo3.tpfinal.modelo.error.NoSePuedeRechazarFlorError;
import fiuba.algo3.tpfinal.modelo.error.SeEstaJugandoSinFlorError;
import fiuba.algo3.tpfinal.modelo.error.SoloSePuedeCantarEnvidoEnPrimeraError;
import fiuba.algo3.tpfinal.modelo.error.SoloSePuedeCantarFlorEnPrimeraError;
import fiuba.algo3.tpfinal.modelo.flor.ContraFlor;
import fiuba.algo3.tpfinal.modelo.flor.ContraFlorAlResto;
import fiuba.algo3.tpfinal.modelo.flor.EstadoFinalFlor;
import fiuba.algo3.tpfinal.modelo.flor.EstadoFlor;
import fiuba.algo3.tpfinal.modelo.flor.EstadoInicialFlor;
import fiuba.algo3.tpfinal.modelo.flor.Flor;
import fiuba.algo3.tpfinal.modelo.ronda.Ronda;
import fiuba.algo3.tpfinal.modelo.truco.EstadoInicialTruco;
import fiuba.algo3.tpfinal.modelo.truco.EstadoTruco;
import fiuba.algo3.tpfinal.modelo.truco.ReTruco;
import fiuba.algo3.tpfinal.modelo.truco.Truco;
import fiuba.algo3.tpfinal.modelo.truco.ValeCuatro;

public class JuegoDeTruco {

	private final ListaCircular<Jugador> jugadores = new ListaCircular<Jugador>();
	private Mazo mazoDeCartas = new Mazo();
	private JuezDeTruco arbitro = new JuezDeTruco();
	private Ronda ronda = new Ronda();
	private LinkedList<Carta> cartasEnLaMesa = new LinkedList<Carta>();
	private Jugador jugadorActual;
	private Jugador jugadorQueCanto = null;
	private Jugador jugadorQueCantoTruco = null;
	private boolean conFlor = false;
	private EstadoFlor estadoActualFlor = new EstadoInicialFlor();
	private boolean florCantada = false;
	private EstadoEnvido estadoActualEnvido = new EstadoInicialEnvido();
	private boolean envidoCantado = false;
	private EstadoTruco estadoActualTruco = new EstadoInicialTruco();
	private boolean trucoCantado = false;

	public JuegoDeTruco(String nombreJ1, String nombreJ2) {
		Jugador jugador = new Jugador(nombreJ1, Equipo.EQUIPO1);
		jugadores.agregar(jugador);
		jugador = new Jugador(nombreJ2, Equipo.EQUIPO2);
		jugadores.agregar(jugador);
		this.repartir();
		this.jugadorActual = jugadores.obtenerElemento(0);
	}

	public JuegoDeTruco(String nombreJ1, String nombreJ2, String nombreJ3, String nombreJ4) {
		Jugador jugador = new Jugador(nombreJ1, Equipo.EQUIPO1);
		jugadores.agregar(jugador);
		jugador = new Jugador(nombreJ2, Equipo.EQUIPO2);
		jugadores.agregar(jugador);
		jugador = new Jugador(nombreJ3, Equipo.EQUIPO1);
		jugadores.agregar(jugador);
		jugador = new Jugador(nombreJ4, Equipo.EQUIPO2);
		jugadores.agregar(jugador);
		this.repartir();
		this.jugadorActual = jugadores.obtenerElemento(0);
	}

	public void comenzarPartida(Boolean conFlor) {
		this.resetearPuntos();
		this.conFlor = conFlor;
	}

	public void avanzarJugadorActual() {
		this.jugadorActual = jugadores.obtenerElementoSiguienteDe(jugadorActual);
	}

	public void repartir() {
		LinkedList<Jugador> jugadoresList = jugadores.obtenerElementos();
		for (Jugador jug : jugadoresList)
			jug.entregarCartas();
		mazoDeCartas.mezclar();
		for (int i = 1; i < 4; i++) {
			for (Jugador jug : jugadoresList)
				jug.agarrarCarta(mazoDeCartas.agarrarCarta());
		}
	}

	private void resetearPuntos() {
		LinkedList<Jugador> jugadoresList = jugadores.obtenerElementos();
		for (Jugador jugadorActual : jugadoresList) {
			jugadorActual.resetearPuntos();
		}
	}

	public int puntosDeEquipo(Equipo equipo) {
		Integer puntos = 0;
		LinkedList<Jugador> jugadoresList = jugadores.obtenerElementos();
		for (Jugador jugadorActual : jugadoresList) {
			if (jugadorActual.coincideElEquipo(equipo)) {
				puntos = +jugadorActual.obtenerPuntaje();
			}
		}
		return puntos;
	}

	public Jugador obtenerJugadorActual() {
		return jugadorActual;
	}

	public void moverAlSiguiente() {
		this.jugadores.moverAlSiguiente();
		this.avanzarJugadorActual();
	}

	public void jugar(Carta carta) {
		if (this.envidoCantado) {
			throw new NoPuedeJugarSeCantoEnvidoError();
		}
		if (this.trucoCantado) {
			throw new NoPuedeJugarSeCantoTrucoError();
		}
		if (this.florCantada) {
			throw new NoPuedeJugarSeCantoFlorError();
		}
		this.cartasEnLaMesa.add(jugadorActual.jugarCarta(carta));
		this.avanzarJugadorActual();
		if ((this.cartasEnLaMesa.size() == this.jugadores.tamanio()) && (!ronda.concluyoLaRonda())) {
			Resultado resultadoRonda = arbitro.ganadorDeLaMano(this.cartasEnLaMesa);
			ronda.insercion(resultadoRonda);
			switch (resultadoRonda) {
			case EMPATE: {
				jugadorActual = jugadores.obtenerElemento(0);
				break;
			}
			default: {
				Carta cartaMasAlta = this.obtenerCartaMasAlta(cartasEnLaMesa);
				jugadorActual = this.jugadorQuePoseeLaCarta(cartaMasAlta);
				break;
			}
			}
			this.cartasEnLaMesa = new LinkedList<Carta>();
		}
		if (this.ronda.concluyoLaRonda()) {
			Jugador jugadorGanador = this.ronda.ganadorDeLaRonda(jugadores.obtenerElementos());
			jugadorGanador.sumarPuntos(this.estadoActualTruco.obtenerPuntosQueridos());
			this.setearValoresParaProximaRonda();
		}
	}

	private Carta obtenerCartaMasAlta(LinkedList<Carta> cartas) {
		return arbitro.obtenerCartaMasAlta(cartas);
	}

	private Jugador jugadorQuePoseeLaCarta(Carta carta) {
		Jugador jugadorBuscado = null;
		for (Jugador jugadorActual : jugadores.obtenerElementos()) {
			if (jugadorActual.poseeCarta(carta)) {
				jugadorBuscado = jugadorActual;
				break;
			}
		}
		return jugadorBuscado;
	}

	private void setearValoresParaProximaRonda() {
		jugadores.moverAlSiguiente();
		jugadorActual = jugadores.obtenerElemento(0);
		this.envidoCantado = false;
		this.estadoActualEnvido = new EstadoInicialEnvido();
		this.florCantada = false;
		this.estadoActualFlor = new EstadoInicialFlor();
		this.trucoCantado = false;
		this.estadoActualTruco = new EstadoInicialTruco();
		this.jugadorQueCanto = null;
		this.jugadorQueCantoTruco = null;
		this.repartir();
	}

	// Flor

	public void flor() {
		if (!this.conFlor) {
			throw new SeEstaJugandoSinFlorError();
		}
		if (jugadorActual.mostrarCartas().size() != 3) {
			throw new SoloSePuedeCantarFlorEnPrimeraError();
		}
		if (!this.jugadorActual.tieneFlor()) {
			throw new JugadorNoTieneFlorError();
		}
		this.florCantada = true;
		this.estadoActualFlor = new Flor(this.estadoActualFlor);
		if (this.envidoCantado) {
			this.envidoCantado = false;
			this.jugadorQueCanto = null;
			this.estadoActualEnvido = new EstadoFinalEnvido(estadoActualEnvido);
		}
		if (this.jugadorQueCanto == null) {
			this.jugadorQueCanto = jugadorActual;
		}
		if (!(this.jugadorActual == jugadorQueCanto)) {
			this.avanzarJugadorActual();
		}
	}

	public void quieroFlor() {
		Jugador ganadorDeFlor = this.arbitro.ganadorFlor(jugadores.obtenerElementos());
		if (this.estadoActualFlor instanceof ContraFlorAlResto) {
			ganadorDeFlor.sumarPuntos(this.puntosRestantes(ganadorDeFlor));
		} else {
			ganadorDeFlor.sumarPuntos(this.estadoActualFlor.obtenerPuntosQueridos());
		}
		this.jugadorActual = jugadorQueCanto;
		this.estadoActualFlor = new EstadoFinalFlor(estadoActualFlor);
		this.florCantada = false;
		this.jugadorQueCanto = null;
	}

	private int puntosRestantes(Jugador ganadorDeFlor) {
		int puntosRestantes = 0;
		for (Equipo actual : Equipo.values()) {
			if (ganadorDeFlor.coincideElEquipo(actual)) {
				int puntosEquipo = this.puntosDeEquipo(actual);
				puntosRestantes = 30 - puntosEquipo;
			}
		}
		return puntosRestantes;
	}

	public void noQuieroFlor() {
		if (this.estadoActualFlor instanceof Flor) {
			throw new NoSePuedeRechazarFlorError();
		}
		this.jugadorQueCanto.sumarPuntos(this.estadoActualFlor.obtenerPuntosNoQueridos());
		this.jugadorActual = jugadorQueCanto;
		this.estadoActualFlor = new EstadoFinalFlor(estadoActualFlor);
		this.florCantada = false;
		this.jugadorQueCanto = null;
	}

	public void contraFlor() {
		this.estadoActualFlor = new ContraFlor(this.estadoActualFlor);
		this.avanzarJugadorActual();

	}

	public void contraFlorAlResto() {
		this.estadoActualFlor = new ContraFlorAlResto(this.estadoActualFlor);
		this.avanzarJugadorActual();
	}

	// ENVIDO
	public void envido() {
		if (jugadorActual.mostrarCartas().size() != 3) {
			throw new SoloSePuedeCantarEnvidoEnPrimeraError();
		}

		this.envidoCantado = true;
		this.estadoActualEnvido = new Envido(this.estadoActualEnvido);
		if (this.jugadorQueCanto == null) {
			this.jugadorQueCanto = jugadorActual;
		}
		this.avanzarJugadorActual();
	}

	public void realEnvido() {
		this.envidoCantado = true;
		estadoActualEnvido = new RealEnvido(estadoActualEnvido);
		if (this.jugadorQueCanto == null) {
			this.jugadorQueCanto = jugadorActual;
		}
		this.avanzarJugadorActual();
	}

	public void quieroEnvido() {

		Jugador ganador = arbitro.ganadorEnvido(jugadores.obtenerElementos());
		if (estadoActualEnvido instanceof FaltaEnvido) {
			ganador.sumarPuntos(this.puntosRestantesContrario(ganador));
		} else {
			ganador.sumarPuntos(this.estadoActualEnvido.obtenerPuntosQueridos());
		}
		this.jugadorActual = jugadorQueCanto;
		this.estadoActualEnvido = new EstadoFinalEnvido(estadoActualEnvido);
		this.envidoCantado = false;
		this.jugadorQueCanto = null;
	}

	private int puntosRestantesContrario(Jugador ganadorFaltaEnvido) {
		int puntosRestantes = 0;
		for (Equipo actual : Equipo.values()) {
			if (!ganadorFaltaEnvido.coincideElEquipo(actual)) {
				int puntosEquipo = this.puntosDeEquipo(actual);
				puntosRestantes = 30 - puntosEquipo;
			}
		}
		return puntosRestantes;
	}

	public void faltaEnvido() {
		this.envidoCantado = true;
		estadoActualEnvido = new FaltaEnvido(estadoActualEnvido);
		if (this.jugadorQueCanto == null) {
			this.jugadorQueCanto = jugadorActual;
		}
		this.avanzarJugadorActual();
	}

	public void noQuieroEnvido() {
		this.avanzarJugadorActual();
		jugadorActual.sumarPuntos(this.estadoActualEnvido.obtenerPuntosNoQueridos());
		this.jugadorActual = jugadorQueCanto;
		this.estadoActualEnvido = new EstadoFinalEnvido(estadoActualEnvido);
		this.envidoCantado = false;
		this.jugadorQueCanto = null;
	}

	// TRUCO

	public void truco() {
		if (this.envidoCantado) {
			throw new NoPuedeCantarTrucoSeCantoEnvidoError();
		}
		if (this.florCantada) {
			throw new NoPuedeCantarTrucoSeCantoFlorError();
		}
		this.trucoCantado = true;
		this.jugadorQueCantoTruco = this.jugadorActual;
		this.estadoActualTruco = new Truco(estadoActualTruco);
		this.avanzarJugadorActual();
	}

	public void reTruco() {
		this.estadoActualTruco = new ReTruco(estadoActualTruco);
		this.avanzarJugadorActual();
	}

	public void valeCuatro() {
		this.estadoActualTruco = new ValeCuatro(estadoActualTruco);
		this.avanzarJugadorActual();
	}

	public void quieroTruco() {
		jugadorActual = jugadorQueCantoTruco;
		this.trucoCantado = false;
	}

	public void noQuieroTruco() {
		jugadorQueCantoTruco.sumarPuntos(estadoActualTruco.obtenerPuntosNoQueridos());
		this.estadoActualTruco = new EstadoInicialTruco();
		this.trucoCantado = false;
		this.setearValoresParaProximaRonda();
	}

	public void irseAlMazo() {
		int cantidadDeCartas = this.jugadorActual.mostrarCartas().size();
		this.avanzarJugadorActual();
		this.jugadorActual.sumarPuntos(this.estadoActualTruco.obtenerPuntosQueridos());
		if (cantidadDeCartas == 3) {
			this.jugadorActual.sumarPuntos(1);
		}
		this.setearValoresParaProximaRonda();
	}
}
