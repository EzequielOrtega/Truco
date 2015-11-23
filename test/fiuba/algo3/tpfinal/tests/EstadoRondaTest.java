package fiuba.algo3.tpfinal.tests;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.tpfinal.modelo.Equipo;
import fiuba.algo3.tpfinal.modelo.Jugador;
import fiuba.algo3.tpfinal.modelo.Resultado;
import fiuba.algo3.tpfinal.modelo.error.NoHayGanadorDeRondaInconclusaError;
import fiuba.algo3.tpfinal.modelo.ronda.EstadoInicialRonda;
import fiuba.algo3.tpfinal.modelo.ronda.EstadoRonda;
import fiuba.algo3.tpfinal.modelo.ronda.Primera;
import fiuba.algo3.tpfinal.modelo.ronda.Segunda;

public class EstadoRondaTest {
	
	private EstadoRonda estadoRondaActual = new EstadoInicialRonda();
	LinkedList<Jugador> jugadores = new LinkedList<Jugador>();
	
	@Before
	public void setup(){
		Jugador j1 = new Jugador("eze", Equipo.EQUIPO1);
		Jugador j2 = new Jugador("matu", Equipo.EQUIPO2);
		Jugador j3 = new Jugador("marcos", Equipo.EQUIPO1);
		Jugador j4 = new Jugador("micaela", Equipo.EQUIPO2);
		jugadores.add(j1);
		jugadores.add(j2);
		jugadores.add(j3);
		jugadores.add(j4);
	}
	//tests estado inicial
	@Test
	public void testNoConcluyoLaRondaEnEstadoInicial() {
		Assert.assertFalse(estadoRondaActual.concluyoRonda());
	}
	
	@Test (expected = NoHayGanadorDeRondaInconclusaError.class)
	public void testNoHayGanadorDeRondaEnEstadoInicial() {
		LinkedList<Jugador> jugadores = new LinkedList<Jugador>();
		estadoRondaActual.obtenerGanadorDeLaRonda(jugadores);
	}
	
	//tests estado primera
	@Test
	public void testNoConcluyoLaRondaEnPrimera() {
		estadoRondaActual = new Primera(estadoRondaActual);
		Assert.assertFalse(estadoRondaActual.concluyoRonda());
	}
	
	@Test (expected = NoHayGanadorDeRondaInconclusaError.class)
	public void testNoHayGanadorDeRondaEnPrimera() {
		estadoRondaActual = new Primera(estadoRondaActual);
		estadoRondaActual.obtenerGanadorDeLaRonda(jugadores);
	}
	
	//tests estado segunda
	@Test
	public void testNoConcluyoLaRondaEnSegundaPorGanadoresDiferentes() {
		estadoRondaActual = new Primera(estadoRondaActual, Resultado.GANADOR1);
		estadoRondaActual = new Segunda(estadoRondaActual, Resultado.GANADOR2);
		Assert.assertFalse(estadoRondaActual.concluyoRonda());
	}
	
	@Test
	public void testNoConcluyoLaRondaEnSegundaPorEmpate() {
		estadoRondaActual = new Primera(estadoRondaActual, Resultado.EMPATE);
		estadoRondaActual = new Segunda(estadoRondaActual, Resultado.EMPATE);
		Assert.assertFalse(estadoRondaActual.concluyoRonda());
	}
	
	@Test (expected = NoHayGanadorDeRondaInconclusaError.class)
	public void testNoHayGanadorDeRondaInconclusaEnSegunda() {
		estadoRondaActual = new Primera(estadoRondaActual, Resultado.GANADOR1);
		estadoRondaActual = new Segunda(estadoRondaActual, Resultado.GANADOR2);
		estadoRondaActual.obtenerGanadorDeLaRonda(jugadores);
	}
	
	@Test
	public void testConcluyoLaRondaEnSegundaPorPardaEnPrimera() {
		estadoRondaActual = new Primera(estadoRondaActual, Resultado.EMPATE);
		estadoRondaActual = new Segunda(estadoRondaActual, Resultado.GANADOR2);
		Assert.assertTrue(estadoRondaActual.concluyoRonda());
	}
	
	@Test
	public void testConcluyoLaRondaEnSegundaPorPardaEnSegunda() {
		estadoRondaActual = new Primera(estadoRondaActual, Resultado.GANADOR1);
		estadoRondaActual = new Segunda(estadoRondaActual, Resultado.EMPATE);
		Assert.assertTrue(estadoRondaActual.concluyoRonda());
	}
	
	@Test
	public void testConcluyoLaRondaEnSegundaPorGanador() {
		estadoRondaActual = new Primera(estadoRondaActual, Resultado.GANADOR1);
		estadoRondaActual = new Segunda(estadoRondaActual, Resultado.GANADOR1);
		Assert.assertTrue(estadoRondaActual.concluyoRonda());
	}
	
	@Test
	public void testGanadorDeLaRondaEnSegundaPorPardaEnPrimera() {
		estadoRondaActual = new Primera(estadoRondaActual, Resultado.EMPATE);
		estadoRondaActual = new Segunda(estadoRondaActual, Resultado.GANADOR2);
		Assert.assertTrue(estadoRondaActual.obtenerGanadorDeLaRonda(jugadores) == jugadores.get(1));
	}
	
	@Test
	public void testGanadorDeLaRondaEnSegundaPorPardaEnSegunda() {
		estadoRondaActual = new Primera(estadoRondaActual, Resultado.GANADOR1);
		estadoRondaActual = new Segunda(estadoRondaActual, Resultado.EMPATE);
		Assert.assertTrue(estadoRondaActual.obtenerGanadorDeLaRonda(jugadores) == jugadores.get(0));
	}
	
	@Test
	public void testGanadorDeLaRondaEnSegundaPorGanador() {
		estadoRondaActual = new Primera(estadoRondaActual, Resultado.GANADOR1);
		estadoRondaActual = new Segunda(estadoRondaActual, Resultado.GANADOR1);
		Assert.assertTrue(estadoRondaActual.obtenerGanadorDeLaRonda(jugadores) == jugadores.get(0));
	}

}
