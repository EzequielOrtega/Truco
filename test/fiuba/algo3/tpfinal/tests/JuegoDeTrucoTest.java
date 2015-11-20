package fiuba.algo3.tpfinal.tests;


import fiuba.algo3.tpfinal.modelo.*;
import fiuba.algo3.tpfinal.modelo.error.NoPuedeCantarTrucoSeCantoEnvidoError;
import fiuba.algo3.tpfinal.modelo.error.NoRespetaJerarquiaDeTrucoError;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class JuegoDeTrucoTest {

    JuegoDeTruco unJuego;
    Jugador j1,j2;
	private Carta unoDeEspada = new NoFigura(1, Palo.ESPADA);
    private Carta sieteDeEspada = new NoFigura(7, Palo.ESPADA);
    private Carta sotaDeEspada = new Figura(10, Palo.ESPADA);
    private Carta sotaDeBasto = new Figura(10, Palo.BASTO);
    private Carta cincoDeEspada = new NoFigura(5, Palo.ESPADA);
    private Carta unoDeBasto = new NoFigura(1, Palo.BASTO);
    
    /*
     * Por algun motivo, el jugador no agarra bien las cartas. porque
     * me tira CantidadDeCartasInvalidaError cuando le agrego tres
     * cartas a cada jugador.
     */
    @Before
    public void setUp() {
    	unJuego = new JuegoDeTruco("Ana", "Juan");
    	unJuego.obtenerJugadorActual().entregarCartas();
    	unJuego.moverAlSiguiente();
    	unJuego.obtenerJugadorActual().entregarCartas();
    	unJuego.obtenerJugadorActual().agarrarCarta(unoDeEspada);
    	unJuego.obtenerJugadorActual().agarrarCarta(sieteDeEspada);
    	unJuego.obtenerJugadorActual().agarrarCarta(sotaDeEspada);
    	unJuego.moverAlSiguiente();
    	unJuego.obtenerJugadorActual().agarrarCarta(sotaDeBasto);
    	unJuego.obtenerJugadorActual().agarrarCarta(cincoDeEspada);
    	unJuego.obtenerJugadorActual().agarrarCarta(unoDeBasto);
    }
    
    //Pedir las cartas asi rompe el encapsulamiento de JuegoDeTruco
    @Test
    public void testSeRepartioLaManoInicial() {
        /*// Chequeo que se le repartieron las 3 cartas a cada jugador.
        assertEquals(3, j1.mostrarCartas().size());
        assertEquals(3, j2.mostrarCartas().size());*/
    }
    
    @Test
    public void testPartidaComienzaCeroACero() {
       	Assert.assertEquals(0, unJuego.obtenerJugadorActual().obtenerPuntaje());
       	unJuego.moverAlSiguiente();
       	Assert.assertEquals(0, unJuego.obtenerJugadorActual().obtenerPuntaje());
    }
    
    @Test
    public void testTrucoReTrucoValeCuatroNoQuerido() {
    	/*
    	 *  es raro el hecho de que es dificil de seguir porque
    	 *  el jugador que hace una accion no es visible en este codigo.
    	 */
    	unJuego.truco();
    	unJuego.reTruco();
    	unJuego.valeCuatro();
    	unJuego.noQuieroTruco();
    	
    	Assert.assertEquals(3, unJuego.obtenerJugadorActual().obtenerPuntaje());
    }
    
    @Test
    public void testTrucoNoQuerido() {
    	unJuego.truco();
    	unJuego.noQuieroTruco();
    	
    	Assert.assertEquals(1, unJuego.obtenerJugadorActual().obtenerPuntaje());
    }

    @Test (expected = NoRespetaJerarquiaDeTrucoError.class)
    public void testReTrucoDirectoMeObligaATenerUnEstadoInicial() {
    	unJuego.reTruco();
    }
    
    @Test
    public void testEnvidoRealEnvidoOtorgaCincoPuntosAlGanador() {
    	unJuego.envido();
    	unJuego.realEnvido();
    	unJuego.quieroEnvido();
    	
    	Assert.assertEquals(5, unJuego.obtenerJugadorActual().obtenerPuntaje());
    }
    
    @Test
    public void testTrucoElEnvidoEstaPrimeroValeEnPrimeraMano() {
    	unJuego.truco();
    	unJuego.envido();
    	unJuego.noQuieroEnvido();
    	unJuego.noQuieroTruco();
    	
    	Assert.assertEquals(1, unJuego.obtenerJugadorActual().obtenerPuntaje());
    	unJuego.moverAlSiguiente();
    	Assert.assertEquals(1, unJuego.obtenerJugadorActual().obtenerPuntaje());
    }
    
    @Test (expected = NoPuedeCantarTrucoSeCantoEnvidoError.class)
    public void testSeCantaTrucoPeroSeCantoEnvidoAntesError() {
    	unJuego.envido();
    	unJuego.truco();
    }
    
    @Ignore
    @Test
    public void testPardaLaPrimeraGanaLaSegunda() {
    	unJuego.jugar(sotaDeBasto);
    	unJuego.jugar(sotaDeEspada);
    	unJuego.jugar(unoDeEspada);
    	
    	unJuego.moverAlSiguiente();
    	Assert.assertEquals(1, unJuego.obtenerJugadorActual().obtenerPuntaje());
    }
}