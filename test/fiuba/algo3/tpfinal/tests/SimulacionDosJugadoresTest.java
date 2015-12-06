package fiuba.algo3.tpfinal.tests;

import fiuba.algo3.tpfinal.modelo.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SimulacionDosJugadoresTest {

	private JuegoDeTruco juego;
	private Carta unoDeEspada, sieteDeEspada, sotaDeEspada, sotaDeBasto, cincoDeEspada, unoDeBasto, sieteDeBasto;
    private Carta diezDeCopa, doceDeOro, doceDeEspada, tresDeEspada, unoDeOro, cuatroDeBasto, sieteDeOro, doceDeBasto;
    private Carta dosDeBasto, cuatroDeCopa;

    @Before
	public void setup() {
		juego = new JuegoDeTruco("eze", "marcos");
		unoDeEspada = new NoFigura(1, Palo.ESPADA);
		sieteDeEspada = new NoFigura(7, Palo.ESPADA);
		sotaDeEspada = new Figura(10, Palo.ESPADA);
		sotaDeBasto = new Figura(10, Palo.BASTO);
		cincoDeEspada = new NoFigura(5, Palo.ESPADA);
		unoDeBasto = new NoFigura(1, Palo.BASTO);
        sieteDeBasto = new NoFigura(7, Palo.BASTO);
        diezDeCopa = new Figura(10, Palo.COPA);
        doceDeOro = new Figura(12, Palo.ORO);
        doceDeEspada = new Figura(12, Palo.ESPADA);
        tresDeEspada = new NoFigura(3, Palo.ESPADA);
        unoDeOro = new NoFigura(1, Palo.ORO);
        cuatroDeBasto = new NoFigura(4, Palo.BASTO);
        sieteDeOro = new NoFigura(7, Palo.ORO);
        doceDeBasto = new Figura(12, Palo.BASTO);
        dosDeBasto = new NoFigura(2, Palo.BASTO);
        cuatroDeCopa = new NoFigura(4, Palo.COPA);

    }

	@Test
	public void testSimulacionDePartida1() {
		// Comienza la partida sin flor
		juego.comenzarPartida(false);
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
		// Comienza la partida con flor
		juego.comenzarPartida(true);
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

    @Test
    public void testSimulacionDePartida3() {
        // Comienza la partida con flor
        juego.comenzarPartida(true);
        Jugador j1 = juego.obtenerJugadorActual();
        juego.moverAlSiguiente();
        Jugador j2 = juego.obtenerJugadorActual();
        juego.moverAlSiguiente();
        j1.entregarCartas();
        j1.agarrarCarta(unoDeBasto);
        j1.agarrarCarta(sieteDeBasto);
        j1.agarrarCarta(sotaDeBasto);
        j2.entregarCartas();
        j2.agarrarCarta(unoDeEspada);
        j2.agarrarCarta(sieteDeEspada);
        j2.agarrarCarta(sotaDeEspada);
        // Se canta envido, pero flor esta primera
        juego.envido();
        juego.flor();
        juego.contraFlor();
        juego.quieroFlor();
        // Gana el que es mano
        assertEquals(6, juego.puntosDeEquipo(Equipo.EQUIPO1));
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
        assertEquals(6, juego.puntosDeEquipo(Equipo.EQUIPO1));
        assertEquals(2, juego.puntosDeEquipo(Equipo.EQUIPO2));
    }

    @Test
    public void testSimulacionDePartida4() {
        // Comienza la partida sin flor
        juego.comenzarPartida(false);
        Jugador j1 = juego.obtenerJugadorActual();
        juego.moverAlSiguiente();
        Jugador j2 = juego.obtenerJugadorActual();
        juego.moverAlSiguiente();
        j1.entregarCartas();
        j1.agarrarCarta(diezDeCopa);
        j1.agarrarCarta(doceDeOro);
        j1.agarrarCarta(doceDeEspada);
        j2.entregarCartas();
        j2.agarrarCarta(tresDeEspada);
        j2.agarrarCarta(unoDeOro);
        j2.agarrarCarta(cincoDeEspada);

        juego.jugar(diezDeCopa);
        juego.realEnvido();
        juego.noQuieroEnvido();
        assertEquals(1, juego.puntosDeEquipo(Equipo.EQUIPO2));
        juego.jugar(unoDeOro);

        juego.truco();
        juego.quieroTruco();
        juego.jugar(tresDeEspada);
        juego.jugar(doceDeOro);
        assertEquals(0, juego.puntosDeEquipo(Equipo.EQUIPO1));
        assertEquals(3, juego.puntosDeEquipo(Equipo.EQUIPO2));


        // Segunda ronda
        j1.entregarCartas();
        j1.agarrarCarta(diezDeCopa);
        j1.agarrarCarta(dosDeBasto);
        j1.agarrarCarta(cuatroDeCopa);
        j2.entregarCartas();
        j2.agarrarCarta(cuatroDeBasto);
        j2.agarrarCarta(sieteDeOro);
        j2.agarrarCarta(doceDeBasto);

        juego.envido();
        juego.quieroEnvido();
        assertEquals(5, juego.puntosDeEquipo(Equipo.EQUIPO2));

        // TODO: REVISAR LA IMPLEMENTACION DE LOS ESTADOS RONDA!! CONCLUYO LA RONDA SIGUE DANDO TRUE POR ESO SE RESETEA
        assertTrue(juego.concluyoLaRonda());
        juego.jugar(cuatroDeBasto);
        assertEquals(6, juego.puntosDeEquipo(Equipo.EQUIPO2));
        assertTrue(juego.concluyoLaRonda());


    }

}
