package fiuba.algo3.tpfinal.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.tpfinal.modelo.Equipo;
import fiuba.algo3.tpfinal.modelo.Jugador;
import fiuba.algo3.tpfinal.modelo.ListaCircular;
import fiuba.algo3.tpfinal.modelo.error.ElementoNoEstaEnLaListaError;
import fiuba.algo3.tpfinal.modelo.error.PosicionFueraDeLosLimitesDeLaListaError;

public class ListaCircularTest {

	private ListaCircular<Jugador> jugadores;
	private Jugador eze, matu, micaela, marcos;

	@Before
	public void setup() {
		jugadores = new ListaCircular<Jugador>();
		eze = new Jugador("eze", Equipo.EQUIPO1);
		matu = new Jugador("matu", Equipo.EQUIPO1);
		micaela = new Jugador("micaela", Equipo.EQUIPO1);
		marcos = new Jugador("marcos", Equipo.EQUIPO1);
	}

	@Test
	public void testLaListaSeCreaVacia() {
		Assert.assertTrue(jugadores.estaVacia());
	}

	@Test
	public void testAumentaElTamanioAlAgregar() {
		assertEquals(0, jugadores.tamanio());
		jugadores.agregar(eze);
		assertEquals(1, jugadores.tamanio());
	}

	@Test
	public void testLosElementosSeAgreganCorrectamente() {
		jugadores.agregar(eze);
		jugadores.agregar(matu);
		assertEquals(eze, jugadores.obtenerElemento(0));
		assertEquals(matu, jugadores.obtenerElemento(1));
	}

	@Test
	public void testMoverAlSiguiente() {
		jugadores.agregar(eze);
		jugadores.agregar(matu);
		assertEquals(eze, jugadores.obtenerElemento(0));
		jugadores.moverAlSiguiente();
		assertEquals(matu, jugadores.obtenerElemento(0));
	}

	@Test
	public void testVaciarLista() {
		jugadores.agregar(eze);
		jugadores.agregar(matu);
		assertFalse(jugadores.estaVacia());
		jugadores.vaciar();
		assertTrue(jugadores.estaVacia());
	}

	@Test
	public void testObtenerElementos() {
		jugadores.agregar(eze);
		jugadores.agregar(matu);
		LinkedList<Jugador> jugadoresList = jugadores.obtenerElementos();
		assertEquals(eze, jugadoresList.getFirst());
		assertEquals(matu, jugadoresList.getLast());
	}

	@Test
	public void testObtenerSiguienteDeUnJugadorParticular() {
		jugadores.agregar(eze);
		jugadores.agregar(matu);
		jugadores.agregar(micaela);
		jugadores.agregar(marcos);
		assertEquals(marcos, jugadores.obtenerElementoSiguienteDe(micaela));
		assertEquals(eze, jugadores.obtenerElementoSiguienteDe(marcos));
	}

	@Test(expected = ElementoNoEstaEnLaListaError.class)
	public void testObtenerSiguienteDeUnJugadorParticularQueNoEstaEnLaLista() {
		jugadores.agregar(eze);
		jugadores.agregar(matu);
		jugadores.obtenerElementoSiguienteDe(micaela);
	}

	@Test
	public void testObtenerElementoEnPosicionX() {
		jugadores.agregar(eze);
		jugadores.agregar(matu);
		jugadores.agregar(micaela);
		jugadores.agregar(marcos);
		assertEquals(micaela, jugadores.obtenerElemento(2));
		assertEquals(eze, jugadores.obtenerElemento(0));
	}

	@Test(expected = PosicionFueraDeLosLimitesDeLaListaError.class)
	public void testObtenerElementoEnPosicionXMenorACero() {
		jugadores.agregar(eze);
		jugadores.agregar(matu);
		jugadores.obtenerElemento(-1);
	}

	@Test(expected = PosicionFueraDeLosLimitesDeLaListaError.class)
	public void testObtenerElementoEnPosicionXMayorAlTamanioDeLaLista() {
		jugadores.agregar(eze);
		jugadores.agregar(matu);
		jugadores.obtenerElemento(4);
	}

	@Test
	public void testEsPieDosJugadores() {
		jugadores.agregar(eze);
		jugadores.agregar(matu);
		Assert.assertTrue(jugadores.esPie(eze));
		Assert.assertTrue(jugadores.esPie(matu));
	}

	@Test
	public void testEsPieCuatroJugadores() {
		jugadores.agregar(eze);
		jugadores.agregar(matu);
		jugadores.agregar(marcos);
		jugadores.agregar(micaela);
		Assert.assertFalse(jugadores.esPie(eze));
		Assert.assertFalse(jugadores.esPie(matu));
		Assert.assertTrue(jugadores.esPie(marcos));
		Assert.assertTrue(jugadores.esPie(micaela));
	}

}
