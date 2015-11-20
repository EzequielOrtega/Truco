package fiuba.algo3.tpfinal.tests;

import org.junit.Test;

import fiuba.algo3.tpfinal.modelo.Carta;
import fiuba.algo3.tpfinal.modelo.NoFigura;
import fiuba.algo3.tpfinal.modelo.Palo;
import fiuba.algo3.tpfinal.modelo.error.ValorDeCartaInvalidoError;

public class NoFiguraTest {

	@Test	(expected = ValorDeCartaInvalidoError.class)
	public void testValorMayorASiete() {
		@SuppressWarnings("unused")
		Carta carta = new NoFigura(8, Palo.ESPADA);
	}
	
	@Test (expected = ValorDeCartaInvalidoError.class)
	public void testValorMenorAUno(){
		@SuppressWarnings("unused")
		Carta carta = new NoFigura(0, Palo.ESPADA);
	}

}
