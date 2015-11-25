package fiuba.algo3.tpfinal.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.tpfinal.modelo.Carta;
import fiuba.algo3.tpfinal.modelo.Equipo;
import fiuba.algo3.tpfinal.modelo.Jugador;
import fiuba.algo3.tpfinal.modelo.Palo;
import fiuba.algo3.tpfinal.modelo.error.CantidadDeCartasInvalidaError;
import fiuba.algo3.tpfinal.modelo.error.NoTieneEsaCartaEnLaManoError;

public class JugadorTest {

	private Jugador nuevoJugador;
	private Carta carta1, carta2, carta3, carta4;

	@Before
	public void setUp() {
		nuevoJugador = new Jugador("Juan", Equipo.EQUIPO1);
		carta1 = new Carta(7, Palo.ESPADA);
		carta2 = new Carta(3, Palo.ESPADA);
		carta3 = new Carta(4, Palo.BASTO);
		carta4 = new Carta(11, Palo.COPA);
	}

	@Test
	public void testJugadorSeCreaConElNombreCorrecto() {
		assertEquals("Juan", nuevoJugador.getNombre());
	}

	// TESTS MANEJO DE CARTAS

	@Test
	public void testAgarrarCartasGuardaCartasExitosamente() {
		nuevoJugador.agarrarCarta(carta1);
		nuevoJugador.agarrarCarta(carta2);
		nuevoJugador.agarrarCarta(carta3);
		assertEquals(carta1, nuevoJugador.mostrarCartas().get(0));
		assertEquals(carta2, nuevoJugador.mostrarCartas().get(1));
		assertEquals(carta3, nuevoJugador.mostrarCartas().get(2));
	}

	@Test(expected = CantidadDeCartasInvalidaError.class)
	public void testAgarrarCartasDevuelveErrorSiSeAgreganMasDeTres() {
		nuevoJugador.agarrarCarta(carta1);
		nuevoJugador.agarrarCarta(carta2);
		nuevoJugador.agarrarCarta(carta3);
		nuevoJugador.agarrarCarta(carta4);
	}

	@Test
	public void testMostrarCartas() {
		assertEquals(0, nuevoJugador.mostrarCartas().size());
		nuevoJugador.agarrarCarta(carta1);
		assertEquals(1, nuevoJugador.mostrarCartas().size());
		nuevoJugador.agarrarCarta(carta2);
		assertEquals(2, nuevoJugador.mostrarCartas().size());
		nuevoJugador.agarrarCarta(carta3);
		assertEquals(3, nuevoJugador.mostrarCartas().size());
	}

	@Test
	public void testCartasEnManoDisminuyenAlJugarCarta() {
		nuevoJugador.agarrarCarta(carta1);
		nuevoJugador.agarrarCarta(carta2);
		nuevoJugador.agarrarCarta(carta3);
		assertEquals(3, nuevoJugador.mostrarCartas().size());
		nuevoJugador.jugarCarta(carta2);
		assertEquals(2, nuevoJugador.mostrarCartas().size());
	}

	@Test(expected = NoTieneEsaCartaEnLaManoError.class)
	public void testNoSePuedeJugarUnaCartaQueNoTengaEnLaMano() {
		nuevoJugador.jugarCarta(carta1);
	}

	@Test
	public void testEntregarCartas() {
		nuevoJugador.agarrarCarta(carta1);
		nuevoJugador.agarrarCarta(carta2);
		nuevoJugador.agarrarCarta(carta3);
		nuevoJugador.entregarCartas();
		assertEquals(0, nuevoJugador.mostrarCartas().size());
	}

	@Test
	public void testPoseeCarta() {
		nuevoJugador.agarrarCarta(carta1);
		assertTrue(nuevoJugador.poseeCarta(carta1));
		assertFalse(nuevoJugador.poseeCarta(carta2));
	}

	// TESTS PUNTAJE

	@Test
	public void testJugadorSeCreaConCeroPuntos() {
		assertEquals(0, nuevoJugador.obtenerPuntaje());
	}

	@Test
	public void testAcumularPuntos() {
		assertEquals(0, nuevoJugador.obtenerPuntaje());
		nuevoJugador.sumarPuntos(2);
		assertEquals(2, nuevoJugador.obtenerPuntaje());
	}

	@Test
	public void testResetearPuntos() {
		nuevoJugador.sumarPuntos(2);
		assertEquals(2, nuevoJugador.obtenerPuntaje());
		nuevoJugador.resetearPuntos();
		assertEquals(0, nuevoJugador.obtenerPuntaje());
	}

	// TESTS EQUIPO

	@Test
	public void testCompararEquipoConOtroJugador() {
		Jugador otroJugador = new Jugador("Mica", Equipo.EQUIPO1);
		assertTrue(nuevoJugador.estanEnElMismoEquipo(otroJugador));
		otroJugador = new Jugador("Mica", Equipo.EQUIPO2);
		assertFalse(nuevoJugador.estanEnElMismoEquipo(otroJugador));
	}

	// TESTS ENVIDO Y FLOR

	@Test(expected = CantidadDeCartasInvalidaError.class)
	public void testNoSePuedeObtenerEnvidoSinCartas() {
		nuevoJugador.getValorEnvido();
	}

	@Test(expected = CantidadDeCartasInvalidaError.class)
	public void testNoSePuedeObtenerFlorSinCartas() {
		nuevoJugador.getValorFlor();
	}
}