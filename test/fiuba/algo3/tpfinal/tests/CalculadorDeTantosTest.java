package fiuba.algo3.tpfinal.tests;

import java.util.Vector;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.tpfinal.modelo.CalculadorDeTantos;
import fiuba.algo3.tpfinal.modelo.Carta;
import fiuba.algo3.tpfinal.modelo.Figura;
import fiuba.algo3.tpfinal.modelo.JugadorNoTieneFlorError;
import fiuba.algo3.tpfinal.modelo.NoFigura;
import fiuba.algo3.tpfinal.modelo.NoTieneSuficientesCartasParaCantarError;

public class CalculadorDeTantosTest {
	private CalculadorDeTantos calculador;
	
	@Before
	public void setup(){
		this.calculador = new CalculadorDeTantos();
	}
	@Test
	public void testTieneFlor() {
		Carta carta1 = new NoFigura(1, "espada");
		Carta carta2 = new NoFigura(7, "espada");
		Carta carta3 = new Figura(10, "espada");
		Vector<Carta> cartas = new Vector<Carta>();
		cartas.addElement(carta1);
		cartas.addElement(carta2);
		cartas.addElement(carta3);
		Assert.assertTrue(calculador.tieneFlor(cartas));
	}
	@Test
	public void testNoTieneFlor() {
		Carta carta1 = new NoFigura(1, "espada");
		Carta carta2 = new NoFigura(7, "copa");
		Carta carta3 = new Figura(10, "basto");
		Vector<Carta> cartas = new Vector<Carta>();
		cartas.addElement(carta1);
		cartas.addElement(carta2);
		cartas.addElement(carta3);
		Assert.assertFalse(calculador.tieneFlor(cartas));
	}
	@Test (expected = NoTieneSuficientesCartasParaCantarError.class)
	public void testTieneFlorFallaSiFaltanCartas(){
		Carta carta1 = new NoFigura(1, "espada");
		Carta carta2 = new NoFigura(7, "copa");
		Vector<Carta> cartas = new Vector<Carta>();
		cartas.addElement(carta1);
		cartas.addElement(carta2);
		calculador.tieneFlor(cartas);
	}
	@Test
	public void testObtenerTantosDeFlor(){
		Carta carta1 = new NoFigura(1, "espada");
		Carta carta2 = new NoFigura(7, "espada");
		Carta carta3 = new Figura(10, "espada");
		Vector<Carta> cartas = new Vector<Carta>();
		cartas.addElement(carta1);
		cartas.addElement(carta2);
		cartas.addElement(carta3);
		Assert.assertTrue(calculador.obtenerTantosDeFlor(cartas) == 28);
	}
	@Test (expected = JugadorNoTieneFlorError.class)
	public void testObtenerTantosDeFlorSinFLorError(){
		Carta carta1 = new NoFigura(1, "espada");
		Carta carta2 = new NoFigura(7, "copa");
		Carta carta3 = new Figura(10, "basto");
		Vector<Carta> cartas = new Vector<Carta>();
		cartas.addElement(carta1);
		cartas.addElement(carta2);
		cartas.addElement(carta3);
		calculador.obtenerTantosDeFlor(cartas);
	}
}
