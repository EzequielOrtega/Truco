package fiuba.algo3.tpfinal.tests;

import fiuba.algo3.tpfinal.modelo.Carta;
import fiuba.algo3.tpfinal.modelo.Mazo;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class MazoTest {

    private Mazo nuevoMazo;

    @Test
    public void testCrearMazoExitoso() {
        nuevoMazo = new Mazo();
        assertEquals(40, nuevoMazo.cantidadDeCartas());
    }

    @Test
    public void testMezclarMazoExitoso() {
        nuevoMazo = new Mazo();
        List<Carta> cartasOrdenadas = nuevoMazo.getCartas();
        nuevoMazo.mezclar();
        List<Carta> cartasDesordenadas = nuevoMazo.getCartas();
        //assertFalse(cartasOrdenadas.equals(cartasDesordenadas));
    }
}