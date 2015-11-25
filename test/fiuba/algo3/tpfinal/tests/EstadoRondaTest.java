package fiuba.algo3.tpfinal.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.tpfinal.modelo.Equipo;
import fiuba.algo3.tpfinal.modelo.Jugador;
import fiuba.algo3.tpfinal.modelo.Resultado;
import fiuba.algo3.tpfinal.modelo.error.NoHayGanadorDeRondaInconclusaError;
import fiuba.algo3.tpfinal.modelo.error.NoRespetaJerarquiaDeRondaError;
import fiuba.algo3.tpfinal.modelo.ronda.EstadoInicialRonda;
import fiuba.algo3.tpfinal.modelo.ronda.EstadoRonda;
import fiuba.algo3.tpfinal.modelo.ronda.Primera;
import fiuba.algo3.tpfinal.modelo.ronda.Segunda;
import fiuba.algo3.tpfinal.modelo.ronda.Tercera;

public class EstadoRondaTest {

	private EstadoRonda estadoRondaActual = new EstadoInicialRonda();
	LinkedList<Jugador> jugadores = new LinkedList<Jugador>();

	@Before
	public void setup() {
		Jugador j1 = new Jugador("eze", Equipo.EQUIPO1);
		Jugador j2 = new Jugador("matu", Equipo.EQUIPO2);
		Jugador j3 = new Jugador("marcos", Equipo.EQUIPO1);
		Jugador j4 = new Jugador("micaela", Equipo.EQUIPO2);
		jugadores.add(j1);
		jugadores.add(j2);
		jugadores.add(j3);
		jugadores.add(j4);
	}

	// Tests RONDA INICIAL

	@Test
	public void testNoConcluyoLaRondaEnEstadoInicial() {
		assertFalse(estadoRondaActual.concluyoRonda());
	}

	@Test(expected = NoHayGanadorDeRondaInconclusaError.class)
	public void testNoHayGanadorDeRondaEnEstadoInicial() {
		LinkedList<Jugador> jugadores = new LinkedList<Jugador>();
		estadoRondaActual.obtenerGanadorDeLaRonda(jugadores);
	}

	// Tests PRIMERA

	@Test
	public void testNoConcluyoLaRondaEnPrimera() {
		estadoRondaActual = new Primera(estadoRondaActual);
		assertFalse(estadoRondaActual.concluyoRonda());
	}

	@Test(expected = NoHayGanadorDeRondaInconclusaError.class)
	public void testNoHayGanadorDeRondaEnPrimera() {
		estadoRondaActual = new Primera(estadoRondaActual);
		estadoRondaActual.obtenerGanadorDeLaRonda(jugadores);
	}

	@Test(expected = NoRespetaJerarquiaDeRondaError.class)
	public void testEstadoInicialPrimeraPrimeraNoVale() {
		estadoRondaActual = new Primera(estadoRondaActual);
		estadoRondaActual = new Primera(estadoRondaActual);
	}

	// Tests SEGUNDA

	@Test
	public void testNoConcluyoLaRondaEnSegundaPorGanadoresDiferentes() {
		estadoRondaActual = new Primera(estadoRondaActual, Resultado.GANADOR1);
		estadoRondaActual = new Segunda(estadoRondaActual, Resultado.GANADOR2);
		assertFalse(estadoRondaActual.concluyoRonda());
	}

	@Test
	public void testNoConcluyoLaRondaEnSegundaPorEmpate() {
		estadoRondaActual = new Primera(estadoRondaActual, Resultado.EMPATE);
		estadoRondaActual = new Segunda(estadoRondaActual, Resultado.EMPATE);
		assertFalse(estadoRondaActual.concluyoRonda());
	}

	@Test(expected = NoHayGanadorDeRondaInconclusaError.class)
	public void testNoHayGanadorDeRondaInconclusaEnSegunda() {
		estadoRondaActual = new Primera(estadoRondaActual, Resultado.GANADOR1);
		estadoRondaActual = new Segunda(estadoRondaActual, Resultado.GANADOR2);
		estadoRondaActual.obtenerGanadorDeLaRonda(jugadores);
	}

	@Test(expected = NoRespetaJerarquiaDeRondaError.class)
	public void testEstadoInicialSegundaNoVale() {
		estadoRondaActual = new Segunda(estadoRondaActual);

	}

	@Test(expected = NoRespetaJerarquiaDeRondaError.class)
	public void testEstadoInicialPrimeraSegundaSegundaNoVale() {
		estadoRondaActual = new Primera(estadoRondaActual);
		estadoRondaActual = new Segunda(estadoRondaActual);
		estadoRondaActual = new Segunda(estadoRondaActual);
	}

	@Test
	public void testConcluyoLaRondaEnSegundaPorPardaEnPrimera() {
		estadoRondaActual = new Primera(estadoRondaActual, Resultado.EMPATE);
		estadoRondaActual = new Segunda(estadoRondaActual, Resultado.GANADOR2);
		assertTrue(estadoRondaActual.concluyoRonda());
	}

	@Test
	public void testConcluyoLaRondaEnSegundaPorPardaEnSegunda() {
		estadoRondaActual = new Primera(estadoRondaActual, Resultado.GANADOR1);
		estadoRondaActual = new Segunda(estadoRondaActual, Resultado.EMPATE);
		assertTrue(estadoRondaActual.concluyoRonda());
	}

	@Test
	public void testConcluyoLaRondaEnSegundaPorGanador() {
		estadoRondaActual = new Primera(estadoRondaActual, Resultado.GANADOR1);
		estadoRondaActual = new Segunda(estadoRondaActual, Resultado.GANADOR1);
		assertTrue(estadoRondaActual.concluyoRonda());
	}

	@Test
	public void testGanadorDeLaRondaEnSegundaPorPardaEnPrimera() {
		estadoRondaActual = new Primera(estadoRondaActual, Resultado.EMPATE);
		estadoRondaActual = new Segunda(estadoRondaActual, Resultado.GANADOR2);
		assertEquals(jugadores.get(1), estadoRondaActual.obtenerGanadorDeLaRonda(jugadores));
	}

	@Test
	public void testGanadorDeLaRondaEnSegundaPorPardaEnSegunda() {
		estadoRondaActual = new Primera(estadoRondaActual, Resultado.GANADOR1);
		estadoRondaActual = new Segunda(estadoRondaActual, Resultado.EMPATE);
		assertEquals(jugadores.get(0), estadoRondaActual.obtenerGanadorDeLaRonda(jugadores));
	}

	@Test
	public void testGanadorDeLaRondaEnSegundaPorGanador() {
		estadoRondaActual = new Primera(estadoRondaActual, Resultado.GANADOR1);
		estadoRondaActual = new Segunda(estadoRondaActual, Resultado.GANADOR1);
		assertEquals(jugadores.get(0), estadoRondaActual.obtenerGanadorDeLaRonda(jugadores));
	}

	// Tests TERCERA

	@Test
	public void testConcluyoLaRondaEnTercera() {
		estadoRondaActual = new Primera(estadoRondaActual);
		estadoRondaActual = new Segunda(estadoRondaActual);
		estadoRondaActual = new Tercera(estadoRondaActual);
		assertTrue(estadoRondaActual.concluyoRonda());
	}

	@Test(expected = NoRespetaJerarquiaDeRondaError.class)
	public void testEstadoInicialPrimeraTerceraNoVale() {
		estadoRondaActual = new Primera(estadoRondaActual);
		estadoRondaActual = new Tercera(estadoRondaActual);
	}

	@Test(expected = NoRespetaJerarquiaDeRondaError.class)
	public void testEstadoInicialTerceraNoVale() {
		estadoRondaActual = new Tercera(estadoRondaActual);
	}

	@Test(expected = NoRespetaJerarquiaDeRondaError.class)
	public void testEstadoInicialPrimeraSegundaTerceraTerceraNoVale() {
		estadoRondaActual = new Primera(estadoRondaActual);
		estadoRondaActual = new Segunda(estadoRondaActual);
		estadoRondaActual = new Tercera(estadoRondaActual);
		estadoRondaActual = new Tercera(estadoRondaActual);
	}

	@Test
	public void testGanadorDeLaRondaEnTerceraPorHaberGanadoDosManos() {
		estadoRondaActual = new Primera(estadoRondaActual, Resultado.GANADOR1);
		estadoRondaActual = new Segunda(estadoRondaActual, Resultado.GANADOR2);
		estadoRondaActual = new Tercera(estadoRondaActual, Resultado.GANADOR2);
		assertEquals(jugadores.get(1), estadoRondaActual.obtenerGanadorDeLaRonda(jugadores));
	}

	@Test
	public void testGanadorDeLaRondaEnTerceraConPardaEnTercera() {
		estadoRondaActual = new Primera(estadoRondaActual, Resultado.GANADOR1);
		estadoRondaActual = new Segunda(estadoRondaActual, Resultado.GANADOR2);
		estadoRondaActual = new Tercera(estadoRondaActual, Resultado.EMPATE);
		assertEquals(jugadores.get(0), estadoRondaActual.obtenerGanadorDeLaRonda(jugadores));
	}

	@Test
	public void testGanadorDeLaRondaEnTerceraConPardaEnTodasLasManos() {
		estadoRondaActual = new Primera(estadoRondaActual, Resultado.EMPATE);
		estadoRondaActual = new Segunda(estadoRondaActual, Resultado.EMPATE);
		estadoRondaActual = new Tercera(estadoRondaActual, Resultado.EMPATE);
		assertEquals(jugadores.get(0), estadoRondaActual.obtenerGanadorDeLaRonda(jugadores));
	}

}
