package fiuba.algo3.tpfinal.tests;

import fiuba.algo3.tpfinal.modelo.error.*;
import fiuba.algo3.tpfinal.modelo.flor.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EstadoFlorTest {

    private EstadoFlor estado;

    @Before
    public void setUp() {
        estado = new EstadoInicialFlor();
    }

    @Test(expected = NoSePuedeRechazarFlorError.class)
    public void testFlorNoSePuedeRechazar() {
        estado = new Flor(estado);
        estado.obtenerPuntosNoQueridos();
    }

    @Test
    public void testFlorSimpleDaTresPuntos() {
        estado = new Flor(estado);
       assertEquals(3, estado.obtenerPuntosQueridos());
    }

    @Test
    public void testFlorFlorDaSeisPuntos() {
        estado = new Flor(estado);
        estado = new Flor(estado);
        assertEquals(6, estado.obtenerPuntosQueridos());
    }

    @Test
    public void testFlorContraFlorQueridaDaSeisPuntos() {
        estado = new Flor(estado);
        estado = new ContraFlor(estado);
        assertEquals(6, estado.obtenerPuntosQueridos());
    }

    @Test
    public void testFlorContraFlorNoQueridaDaCuatroPuntos() {
        estado = new Flor(estado);
        estado = new ContraFlor(estado);
        assertEquals(4, estado.obtenerPuntosNoQueridos());
    }

    @Test (expected = NoRespetaJerarquiaDeFlorError.class)
    public void testContraFlorDirectoLanzaError() {
        estado = new ContraFlor(estado);
    }

    @Test (expected = NoRespetaJerarquiaDeFlorError.class)
    public void testContraFlorAlRestoDirectoLanzaError() {
        estado = new ContraFlorAlResto(estado);
    }

    @Test
    public void testFlorContraFlorContraFlorAlRestoNoQueridaDaSeisPuntos() {
        estado = new Flor(estado);
        estado = new ContraFlor(estado);
        estado = new ContraFlorAlResto(estado);
        assertEquals(6, estado.obtenerPuntosQueridos());
    }

    @Test (expected = SoloSePuedeCantarFlorUnaVezPorRondaError.class)
    public void testCantarFlorLuegoDelEstadoFinalLanzaError() {
        estado = new EstadoFinalFlor(estado);
        estado = new Flor(estado);
    }

}
