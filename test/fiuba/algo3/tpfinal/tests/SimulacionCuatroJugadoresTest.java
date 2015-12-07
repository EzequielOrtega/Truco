package fiuba.algo3.tpfinal.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.tpfinal.modelo.Carta;
import fiuba.algo3.tpfinal.modelo.Equipo;
import fiuba.algo3.tpfinal.modelo.Figura;
import fiuba.algo3.tpfinal.modelo.JuegoDeTruco;
import fiuba.algo3.tpfinal.modelo.Jugador;
import fiuba.algo3.tpfinal.modelo.NoFigura;
import fiuba.algo3.tpfinal.modelo.Palo;

public class SimulacionCuatroJugadoresTest {

	private JuegoDeTruco juego;
	private Jugador j1;
	private Jugador j2;
	private Jugador j3;
	private Jugador j4;

	private Carta anchoBasto = new NoFigura(1, Palo.BASTO);
	private Carta dosBasto = new NoFigura(2, Palo.BASTO);
	private Carta tresBasto = new NoFigura(3, Palo.BASTO);
	private Carta cuatroBasto = new NoFigura(4, Palo.BASTO);
	private Carta cincoBasto = new NoFigura(5, Palo.BASTO);
	private Carta seisBasto = new NoFigura(6, Palo.BASTO);
	private Carta sieteBasto = new NoFigura(7, Palo.BASTO);
	private Carta sotaBasto = new Figura(10, Palo.BASTO);
	private Carta caballoBasto = new Figura(11, Palo.BASTO);
	private Carta reyBasto = new Figura(12, Palo.BASTO);

	private Carta anchoCopa = new NoFigura(1, Palo.COPA);
	private Carta dosCopa = new NoFigura(2, Palo.COPA);
	private Carta tresCopa = new NoFigura(3, Palo.COPA);
	private Carta cuatroCopa = new NoFigura(4, Palo.COPA);
	private Carta cincoCopa = new NoFigura(5, Palo.COPA);
	private Carta seisCopa = new NoFigura(6, Palo.COPA);
	private Carta sieteCopa = new NoFigura(7, Palo.COPA);
	private Carta sotaCopa = new Figura(10, Palo.COPA);
	private Carta caballoCopa = new Figura(11, Palo.COPA);
	private Carta reyCopa = new Figura(12, Palo.COPA);

	private Carta anchoEspada = new NoFigura(1, Palo.ESPADA);
	private Carta dosEspada = new NoFigura(2, Palo.ESPADA);
	private Carta tresEspada = new NoFigura(3, Palo.ESPADA);
	private Carta cuatroEspada = new NoFigura(4, Palo.ESPADA);
	private Carta cincoEspada = new NoFigura(5, Palo.ESPADA);
	private Carta seisEspada = new NoFigura(6, Palo.ESPADA);
	private Carta sieteEspada = new NoFigura(7, Palo.ESPADA);
	private Carta sotaEspada = new Figura(10, Palo.ESPADA);
	private Carta caballoEspada = new Figura(11, Palo.ESPADA);
	private Carta reyEspada = new Figura(12, Palo.ESPADA);

	private Carta anchoOro = new NoFigura(1, Palo.ORO);
	private Carta dosOro = new NoFigura(2, Palo.ORO);
	private Carta tresOro = new NoFigura(3, Palo.ORO);
	private Carta cuatroOro = new NoFigura(4, Palo.ORO);
	private Carta cincoOro = new NoFigura(5, Palo.ORO);
	private Carta seisOro = new NoFigura(6, Palo.ORO);
	private Carta sieteOro = new NoFigura(7, Palo.ORO);
	private Carta sotaOro = new Figura(10, Palo.ORO);
	private Carta caballoOro = new Figura(11, Palo.ORO);
	private Carta reyOro = new Figura(12, Palo.ORO);

	@Before
	public void setup() {
		this.juego = new JuegoDeTruco("j1", "j2", "j3", "j4");
		Boolean conFlor = true;
		j1 = this.juego.obtenerJugadorActual();
		juego.avanzarJugadorActual();
		j2 = this.juego.obtenerJugadorActual();
		juego.avanzarJugadorActual();
		j3 = this.juego.obtenerJugadorActual();
		juego.avanzarJugadorActual();
		j4 = this.juego.obtenerJugadorActual();
		juego.comenzarPartida(conFlor);
	}

	@Test
	public void testPartida() {
		j1.entregarCartas();
		j2.entregarCartas();
		j3.entregarCartas();
		j4.entregarCartas();

		j1.agarrarCarta(anchoBasto);
		j1.agarrarCarta(dosBasto);
		j1.agarrarCarta(tresBasto);

		j2.agarrarCarta(anchoCopa);
		j2.agarrarCarta(dosCopa);
		j2.agarrarCarta(tresCopa);

		j3.agarrarCarta(anchoEspada);
		j3.agarrarCarta(dosEspada);
		j3.agarrarCarta(tresEspada);

		j4.agarrarCarta(anchoOro);
		j4.agarrarCarta(dosOro);
		j4.agarrarCarta(tresOro);

		// equipo1 gana la primera
		juego.jugar(anchoBasto);
		juego.flor();
		juego.contraFlor();
		juego.quieroFlor();
		// empate de flor: gana el que es mano.
		Assert.assertEquals(6, juego.puntosDeEquipo(Equipo.EQUIPO1));
		Assert.assertEquals(0, juego.puntosDeEquipo(Equipo.EQUIPO2));
		juego.jugar(anchoCopa);
		juego.jugar(anchoEspada);
		juego.jugar(anchoOro);

		// equipo2 gana la segunda
		juego.truco();
		juego.quieroTruco();
		juego.jugar(dosEspada);
		juego.reTruco();
		juego.quieroTruco();
		juego.jugar(tresOro);
		juego.jugar(dosBasto);
		juego.jugar(dosCopa);

		// parda la tercera, gana la primera.
		juego.jugar(dosOro);
		juego.valeCuatro();
		juego.quieroTruco();
		juego.jugar(tresBasto);
		juego.jugar(tresCopa);
		juego.jugar(tresEspada);

		Assert.assertEquals(10, juego.puntosDeEquipo(Equipo.EQUIPO1));
		Assert.assertEquals(0, juego.puntosDeEquipo(Equipo.EQUIPO2));
	}

}
