package fiuba.algo3.tpfinal.tests;

import fiuba.algo3.tpfinal.modelo.Carta;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CartaTest {

    private Carta nuevaCarta;
    private Carta otraCarta;
    @Before
    public void SetUp() {

    }

    @Test
    public void testCrearCartaExitoso() {
        nuevaCarta = new Carta(7,"ORO");
        assertEquals("ORO",nuevaCarta.getPalo());
        assertEquals(7,nuevaCarta.getValor());

        // Si introduzo el palo con mayusculas y minusculas
        otraCarta = new Carta(4, "Basto");
        assertEquals("BASTO", otraCarta.getPalo());
    }

    @Test (expected = fiuba.algo3.tpfinal.modelo.ValorDeCartaInvalidoError.class)
    public void testCrearCartaFallaSiValorInvalido() {
        nuevaCarta = new Carta(14, "oro");
    }

    @Test (expected = fiuba.algo3.tpfinal.modelo.PaloDeCartaInvalidoError.class)
    public void testCrearCartaFallaSiPaloInvalido() {
        nuevaCarta = new Carta(7, "corazones");
    }




}