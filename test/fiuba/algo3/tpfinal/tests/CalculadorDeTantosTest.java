package fiuba.algo3.tpfinal.tests;

import fiuba.algo3.tpfinal.modelo.*;
import fiuba.algo3.tpfinal.modelo.error.JugadorNoTieneFlorError;
import fiuba.algo3.tpfinal.modelo.error.SoloSePuedeCantarEnvidoEnPrimeraError;

import org.junit.Before;
import org.junit.Test;

import java.util.Vector;

import static org.junit.Assert.*;

public class CalculadorDeTantosTest {
	
	private CalculadorDeTantos calculador;
	private Carta unoDeEspada, sieteDeEspada, sotaDeEspada, 
			sieteDeCopa, sotaDeBasto, cincoDeEspada, unoDeBasto,
			caballoDeOro, reyDeCopa;
    private Vector<Carta> cartas = new Vector<Carta>();
	
	@Before
	public void setUp(){
        calculador = new CalculadorDeTantos();
        unoDeEspada = new NoFigura(1, Palo.ESPADA);
        sieteDeEspada = new NoFigura(7, Palo.ESPADA);
        sotaDeEspada = new Figura(10, Palo.ESPADA);
        sieteDeCopa = new NoFigura(7, Palo.COPA);
        sotaDeBasto = new Figura(10, Palo.BASTO);
        cincoDeEspada = new NoFigura(5, Palo.ESPADA);
        unoDeBasto = new NoFigura(1, Palo.BASTO);
        caballoDeOro = new Figura(11, Palo.ORO);
        reyDeCopa = new Figura(12, Palo.COPA);
        
	}
	@Test
	public void testTieneFlor() {
		cartas.add(unoDeEspada);
		cartas.add(sieteDeEspada);
		cartas.add(sotaDeEspada);
		assertTrue(calculador.tieneFlor(cartas));
	}
	@Test
	public void testNoTieneFlor() {
		cartas.add(unoDeEspada);
		cartas.add(sieteDeCopa);
		cartas.add(sotaDeBasto);
		assertFalse(calculador.tieneFlor(cartas));
	}
	@Test (expected = SoloSePuedeCantarEnvidoEnPrimeraError.class)
	public void testTieneFlorFallaSiFaltanCartas(){
		cartas.add(unoDeEspada);
		cartas.add(sieteDeCopa);
		calculador.tieneFlor(cartas);
	}
	@Test
	public void testObtenerTantosDeFlor(){
		cartas.add(unoDeEspada);
		cartas.add(sieteDeEspada);
		cartas.add(cincoDeEspada);
		assertEquals(33, calculador.obtenerTantosDeFlor(cartas));
	}
	@Test (expected = JugadorNoTieneFlorError.class)
	public void testObtenerTantosDeFlorSinFLorError(){
		cartas.add(unoDeEspada);
		cartas.add(sieteDeCopa);
		cartas.add(sotaDeBasto);
		calculador.obtenerTantosDeFlor(cartas);
	}
	@Test
	public void testObtenerTantosDeEnvidoDeTresCartas(){
		cartas.addElement(unoDeEspada);
		cartas.addElement(sieteDeEspada);
		cartas.addElement(cincoDeEspada);
		assertEquals(32, calculador.obtenerTantosDeEnvido(cartas));
	}
	@Test
	public void testObtenerTantosDeEnvidoDeDosCartas(){
		cartas.add(unoDeBasto);
		cartas.add(sieteDeEspada);
		cartas.add(cincoDeEspada);
		assertEquals(32, calculador.obtenerTantosDeEnvido(cartas));
	}
	@Test
	public void testObtenerTantosDeEnvidoDeUnaCarta(){
		cartas.add(sieteDeCopa);
		cartas.add(cincoDeEspada);
		cartas.add(unoDeBasto);
		assertEquals(7, calculador.obtenerTantosDeEnvido(cartas));
	}
	@Test (expected = SoloSePuedeCantarEnvidoEnPrimeraError.class)
	public void testObtenerTantosDeEnvidoConMenosDeTresCartas(){
		cartas.add(unoDeEspada);
		cartas.add(sieteDeEspada);
		calculador.obtenerTantosDeEnvido(cartas);
		
	}
	@Test
	public void testUnJugadorTieneCeroDeEnvido() {
		cartas.add(caballoDeOro);
		cartas.add(reyDeCopa);
		cartas.add(sotaDeBasto);
		assertEquals(0, calculador.obtenerTantosDeEnvido(cartas));
	}
}
