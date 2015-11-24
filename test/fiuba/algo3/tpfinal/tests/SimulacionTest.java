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

public class SimulacionTest {
	
	private JuegoDeTruco juego;
	private Carta unoDeEspada;
    private Carta sieteDeEspada;
    private Carta sotaDeEspada;
    private Carta sotaDeBasto;
    private Carta cincoDeEspada;
    private Carta unoDeBasto;
	
	@Before
	public void setup(){
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
		//comienza la partida sin flor
		juego.comenzarPartida(false);
		//le cambio las cartas a los jugadores para poder controlar los resultados
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
		//se canta envido
		juego.envido();
		juego.quieroEnvido();
		Assert.assertEquals(2, juego.puntosDeEquipo(Equipo.EQUIPO1));
		//se juega primera
		juego.jugar(sotaDeEspada);
		juego.jugar(sotaDeBasto);
		//se canta truco
		juego.truco();
		juego.reTruco();
		juego.valeCuatro();
		juego.quieroTruco();
		//parda la mejor
		juego.jugar(unoDeEspada);
		juego.jugar(unoDeBasto);
		//me toma 4 puntos
		//Assert.assertEquals(6, juego.puntosDeEquipo(Equipo.EQUIPO1));
		Assert.assertEquals(0, juego.puntosDeEquipo(Equipo.EQUIPO2));
	}
}
