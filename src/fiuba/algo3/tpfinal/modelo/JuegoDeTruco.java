package fiuba.algo3.tpfinal.modelo;

import fiuba.algo3.tpfinal.modelo.envido.*;
import fiuba.algo3.tpfinal.modelo.error.*;
import fiuba.algo3.tpfinal.modelo.flor.*;
import fiuba.algo3.tpfinal.modelo.ronda.Ronda;
import fiuba.algo3.tpfinal.modelo.truco.*;

import java.util.LinkedList;

public class JuegoDeTruco {

	private final ListaCircular<Jugador> jugadores = new ListaCircular<Jugador>();
	private Mazo mazoDeCartas = new Mazo();
	private JuezDeTruco juez = new JuezDeTruco();
	private Ronda ronda = new Ronda();
	private LinkedList<Carta> cartasEnLaMesa = new LinkedList<Carta>();
	private Jugador jugadorActual;
	private Jugador jugadorQueCantoEnvido = null;
    private Jugador jugadorQueCantoFlor = null;
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
			Resultado resultadoRonda = juez.ganadorDeLaMano(this.cartasEnLaMesa);
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
		return juez.obtenerCartaMasAlta(cartas);
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
		this.jugadorQueCantoEnvido = null;
        this.jugadorQueCantoFlor = null;
		this.jugadorQueCantoTruco = null;
		this.repartir();
	}

	// Flor

	public void flor() {
		if (!this.conFlor) {
			throw new SeEstaJugandoSinFlorError();
		}
        if (this.ronda.estaEnPrimera()) {
            throw new SoloSePuedeCantarFlorEnPrimeraError();
        }
		if (this.jugadorActual.getValorFlor() == -1) {
			throw new JugadorNoTieneFlorError();
		}
		this.florCantada = true;
		this.estadoActualFlor = new Flor(this.estadoActualFlor);
		if (this.envidoCantado) {
			this.estadoActualEnvido = new EstadoFinalEnvido(estadoActualEnvido);
		}
		this.jugadorQueCantoFlor = jugadorActual;
		this.avanzarJugadorActual();
	}

	public void quieroFlor() {
		Jugador ganadorDeFlor = this.juez.ganadorFlor(jugadores.obtenerElementos());
		ganadorDeFlor.sumarPuntos(this.estadoActualFlor.obtenerPuntosQueridos());
        if (envidoCantado) {
            this.jugadorActual = jugadorQueCantoEnvido;
            envidoCantado = false;
        }
        else { this.jugadorActual = jugadorQueCantoFlor; }
		this.estadoActualFlor = new EstadoFinalFlor(estadoActualFlor);
		this.florCantada = false;
		this.jugadorQueCantoFlor = null;
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
		this.jugadorQueCantoFlor.sumarPuntos(this.estadoActualFlor.obtenerPuntosNoQueridos());
		this.jugadorActual = jugadorQueCantoFlor;
		this.estadoActualFlor = new EstadoFinalFlor(estadoActualFlor);
		this.florCantada = false;
		this.jugadorQueCantoFlor = null;
	}

	public void contraFlor() {
		if (this.jugadorActual.getValorFlor() == -1) {
			throw new JugadorNoTieneFlorError();
		}
		this.estadoActualFlor = new ContraFlor(this.estadoActualFlor);
		this.avanzarJugadorActual();

	}

	public void contraFlorAlResto() {
		if (this.jugadorActual.getValorFlor() == -1) {
			throw new JugadorNoTieneFlorError();
		}
		Jugador ganadorDeFlor = this.juez.ganadorFlor(jugadores.obtenerElementos());
		this.estadoActualFlor = new ContraFlorAlResto(this.estadoActualFlor, this.puntosRestantes(ganadorDeFlor));
		this.avanzarJugadorActual();
	}

	// ENVIDO
	public void envido() {
		if (this.ronda.estaEnPrimera()) {
			throw new SoloSePuedeCantarEnvidoEnPrimeraError();
		}
		this.envidoCantado = true;
		this.estadoActualEnvido = new Envido(estadoActualEnvido, estadoActualFlor);
		if (this.jugadorQueCantoEnvido == null) {
			this.jugadorQueCantoEnvido = jugadorActual;
		}
		this.avanzarJugadorActual();
	}

	public void realEnvido() {
		if (this.florCantada) {
			throw new NoPuedeCantarEnvidoSeCantoFlorError();
		}
		if (this.ronda.estaEnPrimera()) {
			throw new SoloSePuedeCantarEnvidoEnPrimeraError();
		}
		this.envidoCantado = true;
		estadoActualEnvido = new RealEnvido(estadoActualEnvido, estadoActualFlor);
		if (this.jugadorQueCantoEnvido == null) {
			this.jugadorQueCantoEnvido = jugadorActual;
		}
		this.avanzarJugadorActual();
	}

	public void quieroEnvido() {

		Jugador ganador = juez.ganadorEnvido(jugadores.obtenerElementos());
        // TODO: polimorfismo para sumarPuntos en faltaEnvido
		if (estadoActualEnvido instanceof FaltaEnvido) {
			ganador.sumarPuntos(this.puntosRestantesContrario(ganador));
		} else {
			ganador.sumarPuntos(this.estadoActualEnvido.obtenerPuntosQueridos());
		}
		this.jugadorActual = jugadorQueCantoEnvido;
		this.estadoActualEnvido = new EstadoFinalEnvido(estadoActualEnvido);
		this.envidoCantado = false;
		this.jugadorQueCantoEnvido = null;
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
		estadoActualEnvido = new FaltaEnvido(estadoActualEnvido, estadoActualFlor);
		if (this.jugadorQueCantoEnvido == null) {
			this.jugadorQueCantoEnvido = jugadorActual;
		}
		this.avanzarJugadorActual();
	}

	public void noQuieroEnvido() {
		this.avanzarJugadorActual();
		jugadorActual.sumarPuntos(this.estadoActualEnvido.obtenerPuntosNoQueridos());
		this.jugadorActual = jugadorQueCantoEnvido;
		this.estadoActualEnvido = new EstadoFinalEnvido(estadoActualEnvido);
		this.envidoCantado = false;
		this.jugadorQueCantoEnvido = null;
	}

	// TRUCO

	public void truco() {
		this.trucoCantado = true;
		this.jugadorQueCantoTruco = this.jugadorActual;
		this.estadoActualTruco = new Truco(estadoActualTruco, estadoActualEnvido, estadoActualFlor);
		this.avanzarJugadorActual();
	}

	public void reTruco() {
		this.estadoActualTruco = new ReTruco(estadoActualTruco);
		if (!this.trucoCantado) {
			this.trucoCantado = true;
			this.jugadorQueCantoTruco = this.jugadorActual;
		}
		this.avanzarJugadorActual();
	}

	public void valeCuatro() {
		this.estadoActualTruco = new ValeCuatro(estadoActualTruco);
		if (!this.trucoCantado) {
			this.trucoCantado = true;
			this.jugadorQueCantoTruco = this.jugadorActual;
		}
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
		if (this.trucoCantado) {
			this.jugadorActual.sumarPuntos(this.estadoActualTruco.obtenerPuntosNoQueridos());
		} else {
			this.jugadorActual.sumarPuntos(this.estadoActualTruco.obtenerPuntosQueridos());
		}
		if (cantidadDeCartas == 3) {
			this.jugadorActual.sumarPuntos(1);
		}
		this.setearValoresParaProximaRonda();
	}
}
