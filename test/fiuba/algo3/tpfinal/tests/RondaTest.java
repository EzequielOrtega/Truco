package fiuba.algo3.tpfinal.tests;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.tpfinal.modelo.Equipo;
import fiuba.algo3.tpfinal.modelo.Jugador;
import fiuba.algo3.tpfinal.modelo.Resultado;
import fiuba.algo3.tpfinal.modelo.Ronda;
import fiuba.algo3.tpfinal.modelo.error.NoHayGanadorDeRondaInconclusaError;
import fiuba.algo3.tpfinal.modelo.error.YaSeJugaronLasTresManosError;

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
		Assert.assertTrue(this.ronda.estaVacia());
		Assert.assertTrue(this.ronda.tamanio() == 0);
	}
	
	@Test
	public void testInsercion(){
		Assert.assertTrue(this.ronda.tamanio() == 0);
		this.ronda.insercion(Resultado.GANADOR1);
		Assert.assertTrue(this.ronda.tamanio() == 1);
		this.ronda.insercion(Resultado.GANADOR2);
		Assert.assertTrue(this.ronda.tamanio() == 2);
		this.ronda.insercion(Resultado.EMPATE);
		Assert.assertTrue(this.ronda.tamanio() == 3);
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
		Assert.assertFalse(ronda.concluyoLaRonda());	
	}
	
	@Test
	public void testConcluyoLaRondaEnSegundaConEmpateEnPrimera(){
		this.ronda.insercion(Resultado.EMPATE);
		this.ronda.insercion(Resultado.GANADOR1);
		Assert.assertTrue(ronda.concluyoLaRonda());		
	}
	
	@Test
	public void testConcluyoLaRondaEnSegundaConEmpateEnSegunda(){
		this.ronda.insercion(Resultado.GANADOR1);
		this.ronda.insercion(Resultado.EMPATE);
		Assert.assertTrue(ronda.concluyoLaRonda());		
	}
	
	@Test
	public void testConcluyoLaRondaEnTercera(){
		this.ronda.insercion(Resultado.GANADOR1);
		this.ronda.insercion(Resultado.GANADOR2);
		this.ronda.insercion(Resultado.EMPATE);
		Assert.assertTrue(ronda.concluyoLaRonda());
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
		Assert.assertTrue(jugadores.get(0) == this.ronda.ganadorDeLaRonda(jugadores));
	}
	
	@Test
	public void testGanadorDeLaRondaEnSegundaConEmpateEnSegunda(){
		this.ronda.insercion(Resultado.GANADOR2);
		this.ronda.insercion(Resultado.EMPATE);
		Assert.assertTrue(jugadores.get(1) == this.ronda.ganadorDeLaRonda(jugadores));
	}
	
	@Test
	public void testGanadorDeLaRondaEnTerceraConEmpateEnTercera(){
		this.ronda.insercion(Resultado.GANADOR2);
		this.ronda.insercion(Resultado.GANADOR1);
		this.ronda.insercion(Resultado.EMPATE);
		Assert.assertTrue(jugadores.get(1) == this.ronda.ganadorDeLaRonda(jugadores));
	}
	
	@Test
	public void testGanadorDeLaRondaEnTerceraConDosManosGanadas(){
		this.ronda.insercion(Resultado.GANADOR2);
		this.ronda.insercion(Resultado.GANADOR1);
		this.ronda.insercion(Resultado.GANADOR1);
		Assert.assertTrue(jugadores.get(0) == this.ronda.ganadorDeLaRonda(jugadores));
	}
	
	@Test
	public void testGanadorDeLaRondaPorEmpateEnTodasLasManosEsElMano(){
		this.ronda.insercion(Resultado.EMPATE);
		this.ronda.insercion(Resultado.EMPATE);
		this.ronda.insercion(Resultado.EMPATE);
		Assert.assertTrue(jugadores.get(0) == this.ronda.ganadorDeLaRonda(jugadores));
	}

}
