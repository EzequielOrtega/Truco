package fiuba.algo3.tpfinal.tests;

import fiuba.algo3.tpfinal.modelo.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JugadorTest {

    private Jugador nuevoJugador;
    private Carta carta1, carta2, carta3, carta4;

    @Before
    public void setUp() {
        nuevoJugador = new Jugador("Juan", Equipo.EQUIPO1);
        carta1 = new Carta(7, Palo.ESPADA);
        carta2 = new Carta(3, Palo.ESPADA);
        carta3 = new Carta(4, Palo.BASTO);
        carta4 = new Carta(11, Palo.COPA);
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

        assertEquals(Palo.ESPADA, nuevoJugador.mostrarCartas().get(0).getPalo());
        assertEquals(Palo.ESPADA, nuevoJugador.mostrarCartas().get(1).getPalo());
        assertEquals(Palo.BASTO, nuevoJugador.mostrarCartas().get(2).getPalo());
        assertEquals(7, nuevoJugador.mostrarCartas().get(0).getValor());
        assertEquals(3, nuevoJugador.mostrarCartas().get(1).getValor());
        assertEquals(4, nuevoJugador.mostrarCartas().get(2).getValor());
    }

    @Test (expected = CantidadDeCartasInvalidaError.class)
    public void testAgarrarCartasDevuelveErrorSiSeAgreganMasDeTres() {
        nuevoJugador.agarrarCarta(carta1);
        nuevoJugador.agarrarCarta(carta2);
        nuevoJugador.agarrarCarta(carta3);
        nuevoJugador.agarrarCarta(carta4);
    }

}