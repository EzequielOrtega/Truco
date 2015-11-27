package fiuba.algo3.tpfinal.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import fiuba.algo3.tpfinal.modelo.Carta;
import fiuba.algo3.tpfinal.modelo.Equipo;
import fiuba.algo3.tpfinal.modelo.Figura;
import fiuba.algo3.tpfinal.modelo.JuegoDeTruco;
import fiuba.algo3.tpfinal.modelo.Jugador;
import fiuba.algo3.tpfinal.modelo.NoFigura;
import fiuba.algo3.tpfinal.modelo.Palo;

public class SimulacionPartidaCompletaSinFlorTest {
	
	private JuegoDeTruco juego;
	private Jugador matu;
	private Jugador eze;
	
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
		this.juego = new JuegoDeTruco("Matu", "Eze");
		Boolean conFlor = false;
		juego.comenzarPartida(conFlor);
		matu = this.juego.obtenerJugadorActual();
		juego.avanzarJugadorActual();
		eze = this.juego.obtenerJugadorActual();
		juego.avanzarJugadorActual();
	}
	
	@Test
	public void testRonda1() {
		matu.entregarCartas();
		eze.entregarCartas();
		matu.agarrarCarta(sotaOro);
		matu.agarrarCarta(sieteBasto);
		matu.agarrarCarta(cincoOro);
		eze.agarrarCarta(caballoOro);
		eze.agarrarCarta(cuatroBasto);
		eze.agarrarCarta(anchoBasto);
		juego.envido();
		juego.quieroEnvido();
		juego.jugar(sotaOro);
		juego.jugar(caballoOro);
		juego.jugar(cuatroBasto);
		juego.jugar(sieteBasto);
		juego.truco();
		juego.reTruco();
		juego.noQuieroTruco();
		Assert.assertEquals(2, juego.puntosDeEquipo(Equipo.EQUIPO1));
		Assert.assertEquals(2, juego.puntosDeEquipo(Equipo.EQUIPO2));
	}
	
	@Ignore
	@Test
	public void testRonda2(){
		juego.moverAlSiguiente();
		matu.sumarPuntos(2);
		eze.sumarPuntos(2);
		matu.entregarCartas();
		eze.entregarCartas();
		matu.agarrarCarta(dosBasto);
		matu.agarrarCarta(sieteBasto);
		matu.agarrarCarta(tresEspada);
		eze.agarrarCarta(anchoCopa);
		eze.agarrarCarta(tresOro);
		eze.agarrarCarta(sieteCopa);
		juego.envido();
		juego.quieroEnvido();
		juego.jugar(anchoCopa);
		juego.truco();
		juego.quieroTruco();
		juego.jugar(dosBasto);
		juego.jugar(sieteBasto);
		juego.jugar(tresOro);
		juego.jugar(sieteCopa);
		juego.jugar(tresEspada);
		Assert.assertTrue(juego.concluyoLaRonda());
		Assert.assertEquals(6, juego.puntosDeEquipo(Equipo.EQUIPO1));
		Assert.assertEquals(2, juego.puntosDeEquipo(Equipo.EQUIPO2));
	}

}
