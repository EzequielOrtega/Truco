package fiuba.algo3.tpfinal.tests;

import fiuba.algo3.tpfinal.modelo.*;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;


public class MazoTest {

    private Mazo nuevoMazo = new Mazo();

    @Test
    public void testCrearMazoExitoso() {
        assertEquals(40, nuevoMazo.cantidadDeCartasRestantes());
    }

    @Test
    public void testMezclarMazoExitoso() {
        Vector<Carta> cartasOrdenadas = nuevoMazo.getCartas();
        nuevoMazo.mezclar();
        Vector<Carta> cartasDesordenadas = nuevoMazo.getCartas();
        //assertFalse(cartasOrdenadas.equals(cartasDesordenadas));
    }

    @Test
    public void testAgarrarCartaDevuelveLaUltimaCartaDelMazo() {
        // Mazo ordenado, las ultimas cartas son los valores mas altos de espada.
        Carta carta1 = nuevoMazo.agarrarCarta();
        Carta carta2 = nuevoMazo.agarrarCarta();
        assertEquals(12, carta1.getValor());
        assertEquals(11, carta2.getValor());
        assertEquals("ESPADA", carta1.getPalo());
        assertEquals("ESPADA", carta2.getPalo());
    }

    @Test (expected = NoHayMasCartasError.class)
    public void testAgarrarCartaDevuelveErrorSiNoQuedanCartas() {
        for (int i=1; i<=41; i++)
            nuevoMazo.agarrarCarta();
    }



}