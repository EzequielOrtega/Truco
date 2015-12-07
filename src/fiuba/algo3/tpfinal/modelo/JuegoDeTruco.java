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
	private boolean concluyoLaRonda = false;

	public JuegoDeTruco(String nombreJ1, String nombreJ2) {
		if (nombreJ1.equals("") || (nombreJ2.equals(""))) {
			throw new NoPuedeHaberJugadoresSinNombreError();
		}
		Jugador jugador = new Jugador(nombreJ1, Equipo.EQUIPO1);
		jugadores.agregar(jugador);
		jugador = new Jugador(nombreJ2, Equipo.EQUIPO2);
		jugadores.agregar(jugador);
		this.repartir();
		this.jugadorActual = jugadores.obtenerElemento(0);
	}

	public JuegoDeTruco(String nombreJ1, String nombreJ2, String nombreJ3, String nombreJ4) {
		if (nombreJ1.equals("") || (nombreJ2.equals("")) || (nombreJ3.equals("")) || (nombreJ4.equals(""))) {
			throw new NoPuedeHaberJugadoresSinNombreError();
		}
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
		this.jugadorActual = jugadores.obtenerElemento(0);
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
				puntos = puntos + jugadorActual.obtenerPuntaje();
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
			Resultado resultadoRonda = juez.ganadorDeLaMano(this.cartasEnLaMesa, this.jugadores.obtenerElementos());
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
		this.concluyoLaRonda = false;
		if (this.ronda.concluyoLaRonda()) {
			Jugador jugadorGanador = this.ronda.ganadorDeLaRonda(jugadores.obtenerElementos());
			jugadorGanador.sumarPuntos(this.estadoActualTruco.obtenerPuntosQueridos());
			this.setearValoresParaProximaRonda();
			this.concluyoLaRonda = true;
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
		this.florCantada = false;
		this.jugadorQueCantoFlor = null;
		this.estadoActualFlor = new EstadoInicialFlor();
		this.envidoCantado = false;
		this.jugadorQueCantoEnvido = null;
		this.estadoActualEnvido = new EstadoInicialEnvido();
		this.trucoCantado = false;
		this.jugadorQueCantoTruco = null;
		this.estadoActualTruco = new EstadoInicialTruco();
		this.ronda = new Ronda();
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
		} else {
			this.jugadorActual = jugadorQueCantoFlor;
		}
		this.estadoActualFlor = new EstadoFinalFlor(estadoActualFlor);
		this.florCantada = false;
		this.jugadorQueCantoFlor = null;
		this.estadoActualEnvido = new EstadoFinalEnvido(this.estadoActualEnvido);
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
		if (!this.puedeCantarLosTantos()) {
			throw new NoPuedeCantarEnvidoNoEsPieError();
		}
		this.envidoCantado = true;
		this.estadoActualEnvido = new Envido(estadoActualEnvido, estadoActualFlor);
		if (this.jugadorQueCantoEnvido == null) {
			this.jugadorQueCantoEnvido = jugadorActual;
		}
		this.avanzarJugadorActual();
	}

	public void realEnvido() {
		if (this.ronda.estaEnPrimera()) {
			throw new SoloSePuedeCantarEnvidoEnPrimeraError();
		}
		if (!this.puedeCantarLosTantos()) {
			throw new NoPuedeCantarEnvidoNoEsPieError();
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
		ganador.sumarPuntos(this.estadoActualEnvido.obtenerPuntosQueridos());
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
		if (this.ronda.estaEnPrimera()) {
			throw new SoloSePuedeCantarEnvidoEnPrimeraError();
		}
		if (!this.puedeCantarLosTantos()) {
			throw new NoPuedeCantarEnvidoNoEsPieError();
		}
		this.envidoCantado = true;
		Jugador ganador = juez.ganadorEnvido(jugadores.obtenerElementos());
		estadoActualEnvido = new FaltaEnvido(estadoActualEnvido, estadoActualFlor,
				this.puntosRestantesContrario(ganador), this.puntosRestantes(ganador));
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
			if (this.jugadorQueCantoTruco.estanEnElMismoEquipo(this.jugadorActual)) {
				throw new NoPuedeRedoblarTrucoSuEquipoLoCantoError();
			}
			this.trucoCantado = true;
			this.jugadorQueCantoTruco = this.jugadorActual;
		}
		this.avanzarJugadorActual();
	}

	public void valeCuatro() {
		this.estadoActualTruco = new ValeCuatro(estadoActualTruco);
		if (!this.trucoCantado) {
			if (this.jugadorQueCantoTruco.estanEnElMismoEquipo(this.jugadorActual)) {
				throw new NoPuedeRedoblarTrucoSuEquipoLoCantoError();
			}
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
		Jugador jugadorASumar = this.obtenerJugadorNoQuieroTruco();
		jugadorASumar.sumarPuntos(estadoActualTruco.obtenerPuntosNoQueridos());
		this.estadoActualTruco = new EstadoInicialTruco();
		this.trucoCantado = false;
		this.setearValoresParaProximaRonda();
	}

	private Jugador obtenerJugadorNoQuieroTruco() {
		Jugador jugadorPedido;
		if (this.jugadorQueCantoTruco.estanEnElMismoEquipo(this.jugadorActual)) {
			this.avanzarJugadorActual();
			jugadorPedido = this.jugadorActual;
		} else {
			jugadorPedido = this.jugadorQueCantoTruco;
		}
		return jugadorPedido;
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

	public Boolean concluyoLaRonda() {
		return this.concluyoLaRonda;
	}

	public Boolean concluyoLaPartida() {
		Boolean concluyoLaPartida = false;
		if ((this.puntosDeEquipo(Equipo.EQUIPO1) >= 30) || (this.puntosDeEquipo(Equipo.EQUIPO2) >= 30)) {
			concluyoLaPartida = true;
		}
		return concluyoLaPartida;
	}

	public boolean estaEnPrimera() {
		return this.ronda.estaEnPrimera();
	}

	private Boolean puedeCantarLosTantos() {
		Boolean puedeCantarLosTantos = false;
		if ((jugadores.esPie(this.jugadorActual)) || (this.envidoCantado) || (this.trucoCantado)) {
			puedeCantarLosTantos = true;
		}
		return puedeCantarLosTantos;
	}

	public int puntosDeEnvidoGanador() {
		Jugador ganadorEnvido = this.juez.ganadorEnvido(jugadores.obtenerElementos());
		return ganadorEnvido.getValorEnvido();
	}

	public String obtenerNombreGanadorDeEnvido() {
		Jugador ganadorEnvido = this.juez.ganadorEnvido(jugadores.obtenerElementos());
		return ganadorEnvido.getNombre();
	}

	public String obtenerNombreGanadorDeFlor() {
		Jugador ganadorFlor = this.juez.ganadorFlor(jugadores.obtenerElementos());
		return ganadorFlor.getNombre();
	}

	public int puntosDeFlorGanador() {
		Jugador ganadorFlor = this.juez.ganadorFlor(jugadores.obtenerElementos());
		return ganadorFlor.getValorFlor();
	}

	public Object obtenerEstadoDeFlor() {
		return this.estadoActualFlor;
	}

	public String obtenerNombreGanadorDelJuego() {
		if (this.concluyoLaPartida()) {
			if (this.puntosDeEquipo(Equipo.EQUIPO1) > this.puntosDeEquipo(Equipo.EQUIPO2))
				return Equipo.EQUIPO1.toString();
			else
				return Equipo.EQUIPO2.toString();
		} else
			throw new NoConcluyoElJuegoError();
	}

	public LinkedList<String> obtenerNombres() {
		LinkedList<String> nombres = new LinkedList<String>();
		for (int x = 0; x < this.jugadores.tamanio(); x++) {
			nombres.add(jugadores.obtenerElemento(x).getNombre());
		}
		return nombres;
	}

	public Boolean seCantoTruco() {
		return this.trucoCantado;
	}
}
