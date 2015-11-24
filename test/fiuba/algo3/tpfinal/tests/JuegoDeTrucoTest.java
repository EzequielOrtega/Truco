package fiuba.algo3.tpfinal.tests;


import fiuba.algo3.tpfinal.modelo.*;
import fiuba.algo3.tpfinal.modelo.error.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

public class JuegoDeTrucoTest {

    JuegoDeTruco unJuego;
    Jugador j1,j2;
	private Carta unoDeEspada = new NoFigura(1, Palo.ESPADA);
    private Carta sieteDeEspada = new NoFigura(7, Palo.ESPADA);
    private Carta sotaDeEspada = new Figura(10, Palo.ESPADA);
    private Carta sotaDeBasto = new Figura(10, Palo.BASTO);
    private Carta cincoDeEspada = new NoFigura(5, Palo.ESPADA);
    private Carta unoDeBasto = new NoFigura(1, Palo.BASTO);
    
    @Before
    public void setUp() {
    	unJuego = new JuegoDeTruco("Ana", "Juan");
    	unJuego.obtenerJugadorActual().entregarCartas();
    	unJuego.moverAlSiguiente();
    	unJuego.obtenerJugadorActual().entregarCartas();
    	// Cartas de Juan
    	unJuego.obtenerJugadorActual().agarrarCarta(unoDeEspada);
    	unJuego.obtenerJugadorActual().agarrarCarta(sieteDeEspada);
    	unJuego.obtenerJugadorActual().agarrarCarta(sotaDeEspada);
    	unJuego.moverAlSiguiente();
    	// Cartas de Ana
    	unJuego.obtenerJugadorActual().agarrarCarta(sotaDeBasto);
    	unJuego.obtenerJugadorActual().agarrarCarta(cincoDeEspada);
    	unJuego.obtenerJugadorActual().agarrarCarta(unoDeBasto);
    }
    
    @Test
    public void testSeRepartioLaManoInicial() {
        unJuego.comenzarPartida(true);
        unJuego.repartir();
        Jugador jugador1 = unJuego.obtenerJugadorActual();
        assertEquals(3, jugador1.mostrarCartas().size());
        unJuego.moverAlSiguiente();
        Jugador jugador2 = unJuego.obtenerJugadorActual();
        assertEquals(3, jugador2.mostrarCartas().size());
    }
    
    @Test
    public void testPartidaComienzaCeroACero() {
       	assertEquals(0, unJuego.obtenerJugadorActual().obtenerPuntaje());
       	unJuego.moverAlSiguiente();
       	assertEquals(0, unJuego.obtenerJugadorActual().obtenerPuntaje());
    }

    // Tests ENVIDO

    @Test
    public void testEnvidoEnvidoOtorgaCuatroPuntosAlGanador() {
        unJuego.envido();
        unJuego.envido();
        unJuego.quieroEnvido();

        assertEquals(4, unJuego.puntosDeEquipo(Equipo.EQUIPO2));
        assertEquals(0, unJuego.puntosDeEquipo(Equipo.EQUIPO1));
    }

    @Test
    public void testEnvidoRealEnvidoOtorgaCincoPuntosAlGanador() {
        unJuego.envido();
        unJuego.realEnvido();
        unJuego.quieroEnvido();

        assertEquals(5, unJuego.puntosDeEquipo(Equipo.EQUIPO2));
        assertEquals(0, unJuego.puntosDeEquipo(Equipo.EQUIPO1));
    }

    @Test (expected = NoPuedeJugarSeCantoEnvidoError.class)
    public void testJugarSinResponderElEnvido(){
        unJuego.comenzarPartida(true);
        unJuego.repartir();
        unJuego.envido();
        Jugador jugador2 = unJuego.obtenerJugadorActual();
        unJuego.jugar(jugador2.mostrarCartas().get(0));
    }

    @Test (expected = SoloSePuedeCantarEnvidoUnaVezPorRondaError.class)
    public void testSoloSePuedeCantarEnvidoUnaVezPorRonda() {
        unJuego.envido();
        unJuego.noQuieroEnvido();
        unJuego.envido();
    }

    // Tests TRUCO

    @Test
    public void testTrucoReTrucoValeCuatroNoQuerido() {
    	unJuego.truco();
    	unJuego.reTruco();
    	unJuego.valeCuatro();
    	unJuego.noQuieroTruco();
    	
    	assertEquals(3, unJuego.obtenerJugadorActual().obtenerPuntaje());
    }
    
    @Test
    public void testTrucoNoQuerido() {
    	unJuego.truco();
    	unJuego.noQuieroTruco();
    	
    	assertEquals(1, unJuego.obtenerJugadorActual().obtenerPuntaje());
    }

    @Test (expected = NoRespetaJerarquiaDeTrucoError.class)
    public void testReTrucoDirectoNoVale() {
    	unJuego.reTruco();
    }
    
    @Test (expected = NoRespetaJerarquiaDeTrucoError.class)
    public void testValeCuatroDirectoNoVale() {
    	unJuego.valeCuatro();
    }
    
    @Test (expected = NoRespetaJerarquiaDeTrucoError.class)
    public void testValeCuatroSinReTrucoNoVale() {
    	unJuego.truco();
    	unJuego.valeCuatro();
    }
    
    @Ignore
    @Test
    public void testTrucoElEnvidoEstaPrimeroValeEnPrimeraMano() {
        unJuego.truco();
        unJuego.envido();
        unJuego.noQuieroEnvido();
        unJuego.noQuieroTruco();

        assertEquals(1, unJuego.obtenerJugadorActual().obtenerPuntaje());
        unJuego.moverAlSiguiente();
        assertEquals(1, unJuego.obtenerJugadorActual().obtenerPuntaje());
    }

    @Test (expected = NoPuedeCantarTrucoSeCantoEnvidoError.class)
    public void testSeCantaTrucoPeroSeCantoEnvidoAntesError() {
        unJuego.envido();
        unJuego.truco();
    }

    @Test (expected = NoPuedeJugarSeCantoTrucoError.class)
    public void testJugarSinResponderElTruco(){
        unJuego.comenzarPartida(true);
        unJuego.repartir();
        unJuego.truco();
        Jugador jugador2 = unJuego.obtenerJugadorActual();
        unJuego.jugar(jugador2.mostrarCartas().get(0));
    }

    @Test
    public void testPardaLaPrimeraGanaLaSegunda() {
    	unJuego.jugar(sotaDeEspada);
    	unJuego.jugar(sotaDeBasto);
    	unJuego.jugar(unoDeBasto);
    	unJuego.jugar(unoDeEspada);
    	
    	assertEquals(1, unJuego.puntosDeEquipo(Equipo.EQUIPO2));
    }
    
    //este test esta mal planteado
    @Ignore
    @Test
    public void testFlor() {
    	unJuego.avanzarJugadorActual();
    	unJuego.flor();
    	
    	assertEquals(3, unJuego.obtenerJugadorActual().obtenerPuntaje());
    }
}