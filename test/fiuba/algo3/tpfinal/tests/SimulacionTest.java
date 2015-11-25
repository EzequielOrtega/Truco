package fiuba.algo3.tpfinal.tests;

import fiuba.algo3.tpfinal.modelo.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimulacionTest {

	private JuegoDeTruco juego;
	private Carta unoDeEspada;
	private Carta sieteDeEspada;
	private Carta sotaDeEspada;
	private Carta sotaDeBasto;
	private Carta cincoDeEspada;
	private Carta unoDeBasto;

	@Before
	public void setup() {
		juego = new JuegoDeTruco("eze", "marcos");
		unoDeEspada = new NoFigura(1, Palo.ESPADA);
		sieteDeEspada = new NoFigura(7, Palo.ESPADA);
		sotaDeEspada = new Figura(10, Palo.ESPADA);
		sotaDeBasto = new Figura(10, Palo.BASTO);
		cincoDeEspada = new NoFigura(5, Palo.ESPADA);
		unoDeBasto = new NoFigura(1, Palo.BASTO);
	}

	@Test
	public void testSimulacionDePartida1() {
		// Comienza la partida sin flor
		juego.comenzarPartida(false);
		// Le cambio las cartas a los jugadores para poder controlar los
		// Resultados
		Jugador j1 = juego.obtenerJugadorActual();
		juego.moverAlSiguiente();
		Jugador j2 = juego.obtenerJugadorActual();
		juego.moverAlSiguiente();
		j1.entregarCartas();
		j1.agarrarCarta(unoDeEspada);
		j1.agarrarCarta(sieteDeEspada);
		j1.agarrarCarta(sotaDeEspada);
		j2.entregarCartas();
		j2.agarrarCarta(unoDeBasto);
		j2.agarrarCarta(cincoDeEspada);
		j2.agarrarCarta(sotaDeBasto);
		// Se canta envido
		juego.envido();
		juego.quieroEnvido();
		assertEquals(2, juego.puntosDeEquipo(Equipo.EQUIPO1));
		// Se juega primera
		juego.jugar(sotaDeEspada);
		juego.jugar(sotaDeBasto);
		// Se canta truco
		juego.truco();
		juego.reTruco();
		juego.valeCuatro();
		juego.quieroTruco();
		// Parda la mejor
		juego.jugar(unoDeEspada);
		juego.jugar(unoDeBasto);
		assertEquals(6, juego.puntosDeEquipo(Equipo.EQUIPO1));
		assertEquals(0, juego.puntosDeEquipo(Equipo.EQUIPO2));
	}

	@Test
	public void testSimulacionDePartida2() {
		// Comienza la partida sin flor
		juego.comenzarPartida(true);
		// Le cambio las cartas a los jugadores para poder controlar los
		// Resultados
		Jugador j1 = juego.obtenerJugadorActual();
		juego.moverAlSiguiente();
		Jugador j2 = juego.obtenerJugadorActual();
		juego.moverAlSiguiente();
		j1.entregarCartas();
        j1.agarrarCarta(unoDeBasto);
        j1.agarrarCarta(cincoDeEspada);
        j1.agarrarCarta(sotaDeBasto);
		j2.entregarCartas();
        j2.agarrarCarta(unoDeEspada);
        j2.agarrarCarta(sieteDeEspada);
        j2.agarrarCarta(sotaDeEspada);
		// Se canta envido, pero flor esta primera
		juego.envido();
		juego.flor();
        juego.quieroFlor();
        assertEquals(3, juego.puntosDeEquipo(Equipo.EQUIPO2));
        // Se juega primera
        juego.jugar(unoDeBasto);
        // Se canta truco
        juego.truco();
        juego.quieroTruco();
        juego.jugar(unoDeEspada);
        // Segunda
        juego.jugar(sotaDeEspada);
        juego.jugar(sotaDeBasto);
        // Parda la segunda, gana el que gano la primera
        assertEquals(0, juego.puntosDeEquipo(Equipo.EQUIPO1));
        assertEquals(5, juego.puntosDeEquipo(Equipo.EQUIPO2));
	}
}
