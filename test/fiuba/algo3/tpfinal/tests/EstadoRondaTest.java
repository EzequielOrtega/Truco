package fiuba.algo3.tpfinal.tests;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.tpfinal.modelo.Jugador;
import fiuba.algo3.tpfinal.modelo.error.NoHayGanadorDeRondaInconclusaError;
import fiuba.algo3.tpfinal.modelo.ronda.EstadoInicialRonda;
import fiuba.algo3.tpfinal.modelo.ronda.EstadoRonda;
import fiuba.algo3.tpfinal.modelo.ronda.Primera;

public class EstadoRondaTest {
	
	private EstadoRonda estadoRondaActual = new EstadoInicialRonda();
	
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
		LinkedList<Jugador> jugadores = new LinkedList<Jugador>();
		estadoRondaActual = new Primera(estadoRondaActual);
		estadoRondaActual.obtenerGanadorDeLaRonda(jugadores);
	}
	
	

}
