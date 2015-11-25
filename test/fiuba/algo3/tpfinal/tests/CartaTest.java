package fiuba.algo3.tpfinal.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fiuba.algo3.tpfinal.modelo.Carta;
import fiuba.algo3.tpfinal.modelo.Palo;
import fiuba.algo3.tpfinal.modelo.error.ValorDeCartaInvalidoError;

public class CartaTest {

	private Carta nuevaCarta;
	private Carta otraCarta;

	@Test
	public void testCrearCartaExitoso() {
		nuevaCarta = new Carta(7, Palo.ORO);
		assertEquals(Palo.ORO, nuevaCarta.getPalo());
		assertEquals(7, nuevaCarta.getValor());

		// Si introduzo el palo con mayusculas y minusculas
		otraCarta = new Carta(4, Palo.BASTO);
		assertEquals(Palo.BASTO, otraCarta.getPalo());
	}

	@Test(expected = ValorDeCartaInvalidoError.class)
	public void testCrearCartaFallaSiValorMayorA12() {
		nuevaCarta = new Carta(14, Palo.ORO);
	}

	@Test(expected = ValorDeCartaInvalidoError.class)
	public void testCrearCartaFallaSiValorMenorA1() {
		nuevaCarta = new Carta(0, Palo.ORO);
	}

	@Test
	public void testComparacionDeCartas() {
		nuevaCarta = new Carta(1, Palo.ESPADA);
		otraCarta = new Carta(1, Palo.ESPADA);
		Carta carta3 = new Carta(1, Palo.BASTO);
		assertTrue(nuevaCarta.esIgualA(otraCarta));
		assertFalse(nuevaCarta.esIgualA(carta3));
	}

}