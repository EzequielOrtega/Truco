package fiuba.algo3.tpfinal.tests;

import fiuba.algo3.tpfinal.modelo.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Vector;

import static org.junit.Assert.*;

public class CalculadorDeTantosTest {
	private CalculadorDeTantos calculador;
	private Carta carta1, carta2, carta3, carta4, carta5, carta6, carta7;
    private Vector<Carta> cartas = new Vector<Carta>();
	
	@Before
	public void setup(){
        calculador = new CalculadorDeTantos();
        carta1 = new NoFigura(1, Palo.ESPADA);
        carta2 = new NoFigura(7, Palo.ESPADA);
        carta3 = new Figura(10, Palo.ESPADA);
        carta4 = new NoFigura(7, Palo.COPA);
        carta5 = new Figura(10, Palo.BASTO);
        carta6 = new NoFigura(5, Palo.ESPADA);
        carta7 = new NoFigura(1, Palo.BASTO);
	}
	@Test
	public void testTieneFlor() {
		cartas.add(carta1);
		cartas.add(carta2);
		cartas.add(carta3);
		assertTrue(calculador.tieneFlor(cartas));
	}
	@Test
	public void testNoTieneFlor() {
		cartas.add(carta1);
		cartas.add(carta4);
		cartas.add(carta5);
		assertFalse(calculador.tieneFlor(cartas));
	}
	@Test (expected = SoloSePuedeCantarEnPrimeraError.class)
	public void testTieneFlorFallaSiFaltanCartas(){
		cartas.add(carta1);
		cartas.add(carta4);
		calculador.tieneFlor(cartas);
	}
	@Test
	public void testObtenerTantosDeFlor(){
		cartas.add(carta1);
		cartas.add(carta2);
		cartas.add(carta3);
		assertEquals(28, calculador.obtenerTantosDeFlor(cartas));
	}
	@Test (expected = JugadorNoTieneFlorError.class)
	public void testObtenerTantosDeFlorSinFLorError(){
		cartas.add(carta1);
		cartas.add(carta4);
		cartas.add(carta5);
		calculador.obtenerTantosDeFlor(cartas);
	}
	@Test
	public void testObtenerTantosDeEnvidoDeTresCartas(){
		cartas.addElement(carta1);
		cartas.addElement(carta2);
		cartas.addElement(carta6);
		assertEquals(32, calculador.obtenerTantosDeEnvido(cartas));
	}
	@Test
	public void testObtenerTantosDeEnvidoDeDosCartas(){
		cartas.add(carta7);
		cartas.add(carta2);
		cartas.add(carta6);
		assertEquals(32, calculador.obtenerTantosDeEnvido(cartas));
	}
	@Test
	public void testObtenerTantosDeEnvidoDeUnaCarta(){
		cartas.add(carta4);
		cartas.add(carta6);
		cartas.add(carta7);
		assertEquals(7, calculador.obtenerTantosDeEnvido(cartas));
	}
	@Test (expected = SoloSePuedeCantarEnPrimeraError.class)
	public void testObtenerTantosDeEnvidoConMenosDeTresCartas(){
		cartas.add(carta1);
		cartas.add(carta2);
		calculador.obtenerTantosDeEnvido(cartas);
		
	}

}
