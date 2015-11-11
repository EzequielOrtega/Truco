package fiuba.algo3.tpfinal.tests;


import fiuba.algo3.tpfinal.modelo.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JuegoDeTrucoTest {

    JuegoDeTruco nuevoJuego;
    Jugador j1,j2;

    @Before
    public void SetUp() {
        j1 = new Jugador("Ana");
        j2 = new Jugador("Juan");
        nuevoJuego = new JuegoDeTruco(j1, j2);
    }

    @Test
    public void testSeRepartioLaManoInicial() {
        // Chequeo que se le repartieron las 3 cartas a cada jugador.
        assertEquals(3, j1.mostrarCartas().size());
        assertEquals(3, j2.mostrarCartas().size());
    }

}