package fiuba.algo3.tpfinal.modelo;

import fiuba.algo3.tpfinal.modelo.envido.*;
import fiuba.algo3.tpfinal.modelo.error.*;
import fiuba.algo3.tpfinal.modelo.truco.*;
import fiuba.algo3.tpfinal.modelo.flor.*;
import fiuba.algo3.tpfinal.modelo.ronda.*;
import java.util.LinkedList;

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

	// todavia esta incompleto
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
			ronda.insercion(arbitro.ganadorDeLaMano(this.cartasEnLaMesa));
			this.cartasEnLaMesa = new LinkedList<Carta>();
		}
		if (this.ronda.concluyoLaRonda()) {
			Jugador jugadorGanador = this.ronda.ganadorDeLaRonda(jugadores.obtenerElementos());
			jugadorGanador.sumarPuntos(this.estadoActualTruco.obtenerPuntosQueridos());
			// Setear valores iniciales para la proxima ronda y avanzar el mano
		}
	}

	// FLOR

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
		try {
			Jugador ganadorDeFlor = this.arbitro.ganadorFlor(jugadores.obtenerElementos());
			ganadorDeFlor.sumarPuntos(this.estadoActualFlor.obtenerPuntosQueridos());

		} catch (JugadorNoTieneFlorError x) {
			jugadorQueCanto.sumarPuntos(this.estadoActualFlor.obtenerPuntosQueridos());
		}
		this.jugadorActual = jugadorQueCanto;
		this.estadoActualFlor = new EstadoFinalFlor(estadoActualFlor);
		this.florCantada = false;
		this.jugadorQueCanto = null;
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
		ganador.sumarPuntos(this.estadoActualEnvido.obtenerPuntosQueridos());

		this.jugadorActual = jugadorQueCanto;
		this.estadoActualEnvido = new EstadoFinalEnvido(estadoActualEnvido);
		this.envidoCantado = false;
		this.jugadorQueCanto = null;
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
		// Setear valores iniciales para la proxima ronda y avanzar el mano
	}

}
