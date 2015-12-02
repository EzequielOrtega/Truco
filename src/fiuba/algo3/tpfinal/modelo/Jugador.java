package fiuba.algo3.tpfinal.modelo;

import fiuba.algo3.tpfinal.modelo.error.CantidadDeCartasInvalidaError;
import fiuba.algo3.tpfinal.modelo.error.NoTieneEsaCartaEnLaManoError;

import java.util.Vector;

public class Jugador {
	// Tal vez deberia llamarse JugadorDeTruco o crear una clase JugadorDeTruco
	// que herede/implemente esta.

	private final String nombre;
	private Vector<Carta> cartas;
	private int puntaje;
	private CalculadorDeEnvidoYFlor calculadorDeTantos;
	private Vector<Carta> cartasJugadas;
	private Equipo equipo;

	public Jugador(String nombreJugador, Equipo equipo) {
		this.nombre = nombreJugador;
		this.puntaje = 0;
		this.calculadorDeTantos = new CalculadorDeEnvidoYFlor();
		this.cartas = new Vector<Carta>();
		this.cartasJugadas = new Vector<Carta>();
		this.equipo = equipo;
	}

	public String getNombre() {
		return nombre;
	}

	// MANEJO DE CARTAS

	public Vector<Carta> mostrarCartas() {
		return cartas;
	}

	public void agarrarCarta(Carta nuevaCarta) {
		if (cartas.size() == 3) {
			throw new CantidadDeCartasInvalidaError();
		}
		this.cartas.add(nuevaCarta);
	}

	public void entregarCartas() {
		this.cartas.removeAllElements();
		this.cartasJugadas.removeAllElements();
	}

	public Carta jugarCarta(Carta carta) {
		if (!cartas.contains(carta)) {
			throw new NoTieneEsaCartaEnLaManoError();
		}
		cartas.remove(carta);
		cartasJugadas.add(carta);
		return carta;
	}

	public boolean poseeCarta(Carta carta) {
		Vector<Carta> cartasTodas = new Vector<Carta>();
		cartasTodas.addAll(cartas);
		cartasTodas.addAll(cartasJugadas);
		for (Carta cartaActual : cartasTodas) {
			if (cartaActual.esIgualA(carta)) {
				return true;
			}
		}
		return false;
	}

	public int cantidadDeCartasJugadas() {
		return cartasJugadas.size();
	}

	// PUNTAJE

	public void sumarPuntos(int cantidadDePuntos) {
		puntaje += cantidadDePuntos;
	}

	public void resetearPuntos() {
		this.puntaje = 0;
	}

	public int obtenerPuntaje() {
		return this.puntaje;
	}

	// EQUIPO

	public Boolean coincideElEquipo(Equipo equipo) {
		return (this.equipo == equipo);
	}

	public Boolean estanEnElMismoEquipo(Jugador otroJugador) {
		return (otroJugador.coincideElEquipo(this.equipo));
	}

	// ENVIDO Y FLOR

	public int getValorEnvido() {
		Vector<Carta> cartasTodas = new Vector<Carta>();
		cartasTodas.addAll(cartas);
		cartasTodas.addAll(cartasJugadas);
		if (cartasTodas.size() != 3)
			throw new CantidadDeCartasInvalidaError();
		return calculadorDeTantos.obtenerTantosDeEnvido(cartasTodas);
	}

	public int getValorFlor() {
		Vector<Carta> cartasTodas = new Vector<Carta>();
		cartasTodas.addAll(cartas);
		cartasTodas.addAll(cartasJugadas);
		if (cartasTodas.size() != 3)
			throw new CantidadDeCartasInvalidaError();
		return calculadorDeTantos.obtenerTantosDeFlor(cartasTodas);
	}

	public boolean tieneFlor() {
		return this.calculadorDeTantos.tieneFlor(cartas);
	}
}
