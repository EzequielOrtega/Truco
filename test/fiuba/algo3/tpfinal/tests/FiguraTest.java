package fiuba.algo3.tpfinal.tests;

import org.junit.Test;

import fiuba.algo3.tpfinal.modelo.Carta;
import fiuba.algo3.tpfinal.modelo.Figura;
import fiuba.algo3.tpfinal.modelo.Palo;
import fiuba.algo3.tpfinal.modelo.ValorDeCartaInvalidoError;

public class FiguraTest {

	@Test	(expected = ValorDeCartaInvalidoError.class)
	public void testValorMayorADoce() {
		@SuppressWarnings("unused")
		Carta carta = new Figura(13, Palo.ESPADA);
	}
	
	@Test (expected = ValorDeCartaInvalidoError.class)
	public void testValorMenorADiez(){
		@SuppressWarnings("unused")
		Carta carta = new Figura(9, Palo.ESPADA);
	}
	
}
