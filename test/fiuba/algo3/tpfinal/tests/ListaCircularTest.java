package fiuba.algo3.tpfinal.tests;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.tpfinal.modelo.Equipo;
import fiuba.algo3.tpfinal.modelo.Jugador;
import fiuba.algo3.tpfinal.modelo.ListaCircular;

public class ListaCircularTest {
	
	private ListaCircular<Jugador> jugadores;
	
	@Before
	public void setup(){
		jugadores = new ListaCircular<Jugador>();
	}
	
	@Test
	public void testLaListaSeCreaVacia() {
		Assert.assertTrue(jugadores.estaVacia());
	}
	
	@Test
	public void testAumentaElTamanioAlAgregar(){
		Jugador jugador = new Jugador("eze", Equipo.EQUIPO1);
		Assert.assertTrue(0 == jugadores.tamanio());
		jugadores.agregar(jugador);
		Assert.assertTrue(1 == jugadores.tamanio());
	}
	
	@Test
	public void testLosElementosSeAgreganCorrectamente(){
		Jugador jugador1 = new Jugador("eze", Equipo.EQUIPO1);
		Jugador jugador2 = new Jugador("matu", Equipo.EQUIPO2);
		jugadores.agregar(jugador1);
		jugadores.agregar(jugador2);
		Assert.assertTrue(jugador1 == jugadores.obtenerElemento(0));
		Assert.assertTrue(jugador2 == jugadores.obtenerElemento(1));
	}
	
	@Test
	public void testMoverAlSiguiente(){
		Jugador jugador1 = new Jugador("eze", Equipo.EQUIPO1);
		Jugador jugador2 = new Jugador("matu", Equipo.EQUIPO2);
		jugadores.agregar(jugador1);
		jugadores.agregar(jugador2);
		Assert.assertTrue(jugador1 == jugadores.obtenerElemento(0));
		jugadores.moverAlSiguiente();
		Assert.assertTrue(jugador2 == jugadores.obtenerElemento(0));
	}
	
	@Test
	public void testVaciarLista(){
		Jugador jugador1 = new Jugador("eze", Equipo.EQUIPO1);
		Jugador jugador2 = new Jugador("matu", Equipo.EQUIPO2);
		jugadores.agregar(jugador1);
		jugadores.agregar(jugador2);
		Assert.assertFalse(jugadores.estaVacia());
		jugadores.vaciar();
		Assert.assertTrue(jugadores.estaVacia());
	}
	
	@Test
	public void testObtenerElementos(){
		Jugador jugador1 = new Jugador("eze", Equipo.EQUIPO1);
		Jugador jugador2 = new Jugador("matu", Equipo.EQUIPO2);
		jugadores.agregar(jugador1);
		jugadores.agregar(jugador2);
		LinkedList<Jugador> jugadoresList = jugadores.obtenerElementos();
		Assert.assertTrue(jugador1 == jugadoresList.getFirst());
		Assert.assertTrue(jugador2 == jugadoresList.getLast());
	}

}
