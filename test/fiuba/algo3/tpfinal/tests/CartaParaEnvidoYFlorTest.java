package fiuba.algo3.tpfinal.tests;

import fiuba.algo3.tpfinal.modelo.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CartaParaEnvidoYFlorTest {

	@Test
	public void testValorParEnvidoYFlorDeFiguraEsNulo() {
		Carta reyDeCopa = new Figura(12, Palo.COPA);
		CartaParaEnvidoYFlor reyDeCopaBis = (CartaParaEnvidoYFlor) reyDeCopa;
		assertEquals(0, reyDeCopaBis.obtenerValorParaEnvidoYFlor());
	}
	
	@Test
	public void testValorParaEnvidoYFlorDeNofiguraEsUnEntero(){
		Carta anchoDeEspadas = new NoFigura(1, Palo.ESPADA);
		CartaParaEnvidoYFlor anchoDeEspadasBis = (CartaParaEnvidoYFlor) anchoDeEspadas;
		assertEquals(1, anchoDeEspadasBis.obtenerValorParaEnvidoYFlor());
	}

}
