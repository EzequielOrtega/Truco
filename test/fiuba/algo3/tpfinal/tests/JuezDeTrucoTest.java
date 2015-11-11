package fiuba.algo3.tpfinal.tests;


import fiuba.algo3.tpfinal.modelo.*;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JuezDeTrucoTest {

    @Test
    public void testGanadorEnvido() {
        assertTrue(JuezDeTruco.ganadorEnvido(29, 23));
        assertFalse(JuezDeTruco.ganadorEnvido(30, 33));
    }

    @Test
    public void testGanadorFlor() {
        assertTrue(JuezDeTruco.ganadorFlor(29, 23));
        assertFalse(JuezDeTruco.ganadorFlor(35, 36));

    }

    @Test
    public void testGanadorMano() {
        Carta carta1 = new Carta(7, "ORO");
        Carta carta2 = new Carta(1, "ORO");
        assertTrue(JuezDeTruco.ganadorDeLaMano(carta1,carta2));

    }

}