package fiuba.algo3.tpfinal.tests;

import fiuba.algo3.tpfinal.modelo.Equipo;
import fiuba.algo3.tpfinal.modelo.Jugador;
import fiuba.algo3.tpfinal.modelo.Resultado;
import fiuba.algo3.tpfinal.modelo.ronda.*;
import fiuba.algo3.tpfinal.modelo.error.NoHayGanadorDeRondaInconclusaError;
import fiuba.algo3.tpfinal.modelo.error.YaSeJugaronLasTresManosError;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class RondaTest {
	
	private Ronda ronda;
	private LinkedList<Jugador> jugadores = new LinkedList<Jugador>();
	
	@Before
	public void setup(){
		ronda = new Ronda();
		Jugador jugador1 = new Jugador("eze", Equipo.EQUIPO1);
		jugadores.add(jugador1);
		Jugador jugador2 = new Jugador("matu", Equipo.EQUIPO2);
		jugadores.add(jugador2);
		Jugador jugador3 = new Jugador("marcos", Equipo.EQUIPO1);
		jugadores.add(jugador3);
		Jugador jugador4 = new Jugador("micaela", Equipo.EQUIPO2);
		jugadores.add(jugador4);
	}
	
	@Test
	public void testRondaSeCreaVacia() {
		assertTrue(this.ronda.estaVacia());
		assertEquals(0, this.ronda.tamanio());
	}
	
	@Test
	public void testInsercion() {
		assertEquals(0, this.ronda.tamanio());
		this.ronda.insercion(Resultado.GANADOR1);
		assertEquals(1, this.ronda.tamanio());
		this.ronda.insercion(Resultado.GANADOR2);
		assertEquals(2, this.ronda.tamanio());
		this.ronda.insercion(Resultado.EMPATE);
		assertEquals(3, this.ronda.tamanio());
	}
	
	@Test (expected = YaSeJugaronLasTresManosError.class)
	public void testInsercionDeMasDeTresManos(){
		this.ronda.insercion(Resultado.GANADOR1);
		this.ronda.insercion(Resultado.GANADOR1);
		this.ronda.insercion(Resultado.GANADOR1);
		this.ronda.insercion(Resultado.GANADOR1);
	}
	
	@Test
	public void testNoConcluyoRondaEnPrimera(){
		this.ronda.insercion(Resultado.GANADOR1);
		assertFalse(ronda.concluyoLaRonda());
	}
	
	@Test
	public void testConcluyoLaRondaEnSegundaConEmpateEnPrimera(){
		this.ronda.insercion(Resultado.EMPATE);
		this.ronda.insercion(Resultado.GANADOR1);
		assertTrue(ronda.concluyoLaRonda());
	}
	
	@Test
	public void testConcluyoLaRondaEnSegundaConEmpateEnSegunda(){
		this.ronda.insercion(Resultado.GANADOR1);
		this.ronda.insercion(Resultado.EMPATE);
		assertTrue(ronda.concluyoLaRonda());
	}
	
	@Test
	public void testConcluyoLaRondaEnTercera(){
		this.ronda.insercion(Resultado.GANADOR1);
		this.ronda.insercion(Resultado.GANADOR2);
		this.ronda.insercion(Resultado.EMPATE);
		assertTrue(ronda.concluyoLaRonda());
	}
	
	@Test (expected = NoHayGanadorDeRondaInconclusaError.class)
	public void testObtenerGanadorDeLaRondaSinConcluir(){
		this.ronda.insercion(Resultado.GANADOR1);
		this.ronda.insercion(Resultado.GANADOR2);
		LinkedList<Jugador> jugadores = new LinkedList<Jugador>();
		ronda.ganadorDeLaRonda(jugadores);
	}
	
	@Test
	public void testGanadorDeLaRondaEnSegundaConEmpateEnPrimera(){
		this.ronda.insercion(Resultado.EMPATE);
		this.ronda.insercion(Resultado.GANADOR1);
		assertEquals(jugadores.get(0), this.ronda.ganadorDeLaRonda(jugadores));
	}
	
	@Test
	public void testGanadorDeLaRondaEnSegundaConEmpateEnSegunda(){
		this.ronda.insercion(Resultado.GANADOR2);
		this.ronda.insercion(Resultado.EMPATE);
		assertEquals(jugadores.get(1), this.ronda.ganadorDeLaRonda(jugadores));
	}
	
	@Test
	public void testGanadorDeLaRondaEnTerceraConEmpateEnTercera(){
		this.ronda.insercion(Resultado.GANADOR2);
		this.ronda.insercion(Resultado.GANADOR1);
		this.ronda.insercion(Resultado.EMPATE);
		assertEquals(jugadores.get(1), this.ronda.ganadorDeLaRonda(jugadores));
	}
	
	@Test
	public void testGanadorDeLaRondaEnTerceraConDosManosGanadas(){
		this.ronda.insercion(Resultado.GANADOR2);
		this.ronda.insercion(Resultado.GANADOR1);
		this.ronda.insercion(Resultado.GANADOR1);
		assertEquals(jugadores.get(0), this.ronda.ganadorDeLaRonda(jugadores));
	}
	
	@Test
	public void testGanadorDeLaRondaPorEmpateEnTodasLasManosEsElMano(){
		this.ronda.insercion(Resultado.EMPATE);
		this.ronda.insercion(Resultado.EMPATE);
		this.ronda.insercion(Resultado.EMPATE);
		assertEquals(jugadores.get(0), this.ronda.ganadorDeLaRonda(jugadores));
	}

}
