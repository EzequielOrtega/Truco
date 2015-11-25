package fiuba.algo3.tpfinal.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fiuba.algo3.tpfinal.modelo.Carta;
import fiuba.algo3.tpfinal.modelo.CartaParaEnvidoYFlor;
import fiuba.algo3.tpfinal.modelo.Figura;
import fiuba.algo3.tpfinal.modelo.NoFigura;
import fiuba.algo3.tpfinal.modelo.Palo;

public class CartaParaEnvidoYFlorTest {

	@Test
	public void testValorParEnvidoYFlorDeFiguraEsNulo() {
		Carta reyDeCopa = new Figura(12, Palo.COPA);
		CartaParaEnvidoYFlor reyDeCopaBis = (CartaParaEnvidoYFlor) reyDeCopa;
		assertEquals(0, reyDeCopaBis.obtenerValorParaEnvidoYFlor());
	}

	@Test
	public void testValorParaEnvidoYFlorDeNofiguraEsUnEntero() {
		Carta anchoDeEspadas = new NoFigura(1, Palo.ESPADA);
		CartaParaEnvidoYFlor anchoDeEspadasBis = (CartaParaEnvidoYFlor) anchoDeEspadas;
		assertEquals(1, anchoDeEspadasBis.obtenerValorParaEnvidoYFlor());
	}

}
