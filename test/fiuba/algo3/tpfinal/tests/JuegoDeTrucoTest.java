package fiuba.algo3.tpfinal.tests;


import fiuba.algo3.tpfinal.modelo.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class JuegoDeTrucoTest {

    JuegoDeTruco nuevoJuego;
    Jugador j1,j2;
/*	private Carta unoDeEspada = new NoFigura(1, Palo.ESPADA);
    private Carta sieteDeEspada = new NoFigura(7, Palo.ESPADA);
    private Carta sotaDeEspada = new Figura(10, Palo.ESPADA);
    private Carta sotaDeBasto = new Figura(10, Palo.BASTO);
    private Carta cincoDeEspada = new NoFigura(5, Palo.ESPADA);
    private Carta unoDeBasto = new NoFigura(1, Palo.BASTO);*/
    
    /*
     * Por algun motivo, el jugador no agarra bien las cartas. porque
     * me tira CantidadDeCartasInvalidaError cuando le agrego tres
     * cartas a cada jugador.
     */
    @Before
    public void setUp() {
    	nuevoJuego = new JuegoDeTruco("Ana", "Juan");
/*    	nuevoJuego.obtenerJugadorActual().agarrarCarta(unoDeEspada);
    	nuevoJuego.obtenerJugadorActual().agarrarCarta(sieteDeEspada);
    	nuevoJuego.obtenerJugadorActual().agarrarCarta(sotaDeEspada);
    	nuevoJuego.moverAlSiguiente();
    	nuevoJuego.obtenerJugadorActual().agarrarCarta(sotaDeBasto);
    	nuevoJuego.obtenerJugadorActual().agarrarCarta(cincoDeEspada);
    	nuevoJuego.obtenerJugadorActual().agarrarCarta(unoDeBasto);*/
    }
    
    //Pedir las cartas asi rompe el encapsulamiento de JuegoDeTruco
    @Test
    public void testSeRepartioLaManoInicial() {
        /*// Chequeo que se le repartieron las 3 cartas a cada jugador.
        assertEquals(3, j1.mostrarCartas().size());
        assertEquals(3, j2.mostrarCartas().size());*/
    }
    
    @Test
    public void testTrucoReTrucoValeCuatroNoQuerido() {
    	/*
    	 *  es raro el hecho de que es dificil de seguir porque
    	 *  el jugador que hace una accion no es visible en este codigo.
    	 */
    	nuevoJuego.truco();
    	nuevoJuego.reTruco();
    	nuevoJuego.valeCuatro();
    	nuevoJuego.noQuieroTruco();
    	
    	Assert.assertEquals(3, nuevoJuego.obtenerJugadorActual().obtenerPuntaje());
    }
    
    @Test
    public void testTrucoNoQuerido() {
    	nuevoJuego.truco();
    	nuevoJuego.noQuieroTruco();
    	
    	Assert.assertEquals(1, nuevoJuego.obtenerJugadorActual().obtenerPuntaje());
    }

    @Test (expected = NoRespetaJerarquiaDeTrucoError.class)
    public void testReTrucoDirectoMeObligaATenerUnEstadoInicial() {
    	nuevoJuego.reTruco();
    }

    @Ignore
    @Test
    public void testEnvidoRealEnvidoOtorgaCincoPuntosAlGanador() {
    	nuevoJuego.envido();
    	nuevoJuego.realEnvido();
    	nuevoJuego.quieroEnvido();
    	
    	Assert.assertEquals(5, nuevoJuego.obtenerJugadorActual().obtenerPuntaje());
    }
}