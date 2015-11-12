package fiuba.algo3.tpfinal.tests;

import fiuba.algo3.tpfinal.modelo.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class CartaTest {

    private Carta nuevaCarta;
    private Carta otraCarta;

    @Test
    public void testCrearCartaExitoso() {
        nuevaCarta = new Carta(7, Palo.ORO);
        assertEquals("ORO",nuevaCarta.getPalo());
        assertEquals(7,nuevaCarta.getValor());

        // Si introduzo el palo con mayusculas y minusculas
        otraCarta = new Carta(4, Palo.BASTO);
        assertEquals("BASTO", otraCarta.getPalo());
    }

    @Test (expected = ValorDeCartaInvalidoError.class)
    public void testCrearCartaFallaSiValorInvalido() {
        nuevaCarta = new Carta(14, Palo.ORO);
    }

    @Test
    public void testComparacionDeCartas(){
    	nuevaCarta = new Carta(1, Palo.ESPADA);
    	otraCarta = new Carta(1, Palo.ESPADA);
    	Carta carta3 = new Carta(1, Palo.BASTO);
    	assertTrue(nuevaCarta.equals(otraCarta));
    	assertFalse(nuevaCarta.equals(carta3));
    }


}