package fiuba.algo3.tpfinal.tests;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.tpfinal.modelo.Carta;
import fiuba.algo3.tpfinal.modelo.Figura;
import fiuba.algo3.tpfinal.modelo.NoFigura;
import fiuba.algo3.tpfinal.modelo.ValorDeCartaParaEnvidoYFlor;

public class ValorDeCartaParaEnvidoYFlorTest {

	@Test
	public void testValorParEnvidoYFlorDeFiguraEsNulo() {
		Carta reyDeCopa = new Figura(12, "copa");
		ValorDeCartaParaEnvidoYFlor reyDeCopaBis = (ValorDeCartaParaEnvidoYFlor) reyDeCopa;
		Assert.assertTrue(0 == reyDeCopaBis.obtenerValorParaEnvidoYFlor());
	}
	
	@Test
	public void testValorParaEnvidoYFlorDeNofiguraEsUnEntero(){
		Carta anchoDeEspadas = new NoFigura(1, "espada");
		ValorDeCartaParaEnvidoYFlor anchoDeEspadasBis = (ValorDeCartaParaEnvidoYFlor) anchoDeEspadas;
		Assert.assertTrue(1 == anchoDeEspadasBis.obtenerValorParaEnvidoYFlor());
	}

}
