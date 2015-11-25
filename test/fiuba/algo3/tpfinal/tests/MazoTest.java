package fiuba.algo3.tpfinal.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.tpfinal.modelo.Carta;
import fiuba.algo3.tpfinal.modelo.Mazo;
import fiuba.algo3.tpfinal.modelo.Palo;
import fiuba.algo3.tpfinal.modelo.error.NoHayMasCartasError;

public class MazoTest {

	private Mazo nuevoMazo;

	@Before
	public void setup() {
		this.nuevoMazo = new Mazo();
	}

	@Test
	public void testCrearMazoExitoso() {
		assertEquals(40, nuevoMazo.cantidadDeCartasRestantes());
	}

	@Test
	public void testMezclarMazoExitoso() {
		Mazo otroMazo = new Mazo();
		otroMazo.mezclar();
		nuevoMazo.mezclar();
		Boolean cartasMezcladas = false;
		for (int x = 0; x < 40; x++) {
			if (!(nuevoMazo.getCartas().get(x).esIgualA(otroMazo.getCartas().get(x)))) {
				cartasMezcladas = true;
			}
		}
		assertTrue(cartasMezcladas);
	}

	@Test
	public void testAgarrarCartaDevuelveLaUltimaCartaDelMazo() {
		// Mazo ordenado, las ultimas cartas son los valores mas altos de
		// espada.
		Carta carta1 = nuevoMazo.agarrarCarta();
		Carta carta2 = nuevoMazo.agarrarCarta();
		assertEquals(12, carta1.getValor());
		assertEquals(11, carta2.getValor());
		assertEquals(Palo.ESPADA, carta1.getPalo());
		assertEquals(Palo.ESPADA, carta2.getPalo());
	}

	@Test(expected = NoHayMasCartasError.class)
	public void testAgarrarCartaDevuelveErrorSiNoQuedanCartas() {
		for (int i = 1; i <= 41; i++)
			nuevoMazo.agarrarCarta();
	}

}