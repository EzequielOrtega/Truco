package fiuba.algo3.tpfinal.tests;

import fiuba.algo3.tpfinal.modelo.Carta;
import fiuba.algo3.tpfinal.modelo.Jugador;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JugadorTest {

    private Jugador nuevoJugador;
    private Carta carta1, carta2, carta3, carta4;

    @Before
    public void SetUp() {
        nuevoJugador = new Jugador("Juan");
        carta1 = new Carta(7, "espada");
        carta2 = new Carta(3, "espada");
        carta3 = new Carta(4, "Basto");
        carta4 = new Carta(11, "COPa");
    }

    @Test
    public void testJugadorSeCreaConElNombreCorrecto() {
        assertEquals("Juan", nuevoJugador.getNombre());
    }

    @Test
    public void testAgarrarCartasGuardaCartasExitosamente() {
        nuevoJugador.agarrarCarta(carta1);
        nuevoJugador.agarrarCarta(carta2);
        nuevoJugador.agarrarCarta(carta3);

        assertEquals("ESPADA", nuevoJugador.mostrarCartas().get(0).getPalo());
        assertEquals("ESPADA", nuevoJugador.mostrarCartas().get(1).getPalo());
        assertEquals("BASTO", nuevoJugador.mostrarCartas().get(2).getPalo());
        assertEquals(7, nuevoJugador.mostrarCartas().get(0).getValor());
        assertEquals(3, nuevoJugador.mostrarCartas().get(1).getValor());
        assertEquals(4, nuevoJugador.mostrarCartas().get(2).getValor());
    }

    @Test (expected = fiuba.algo3.tpfinal.modelo.CantidadDeCartasInvalidaError.class)
    public void testAgarrarCartasDevuelveErrorSiSeAgreganMasDeTres() {
        nuevoJugador.agarrarCarta(carta1);
        nuevoJugador.agarrarCarta(carta2);
        nuevoJugador.agarrarCarta(carta3);
        nuevoJugador.agarrarCarta(carta4);
    }

}