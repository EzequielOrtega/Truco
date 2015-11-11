package fiuba.algo3.tpfinal.tests;


import fiuba.algo3.tpfinal.modelo.*;
import org.junit.Test;

import org.junit.Assert;

public class JuezDeTrucoTest {

    @Test
    public void testGanadorEnvido() {
        Assert.assertEquals(Resultado.GANADOR1, JuezDeTruco.ganadorEnvido(29, 23));
        Assert.assertEquals(Resultado.GANADOR2, JuezDeTruco.ganadorEnvido(30, 33));
    }
    
    @Test
    public void testEmpateEnEnvido(){
    	Assert.assertEquals(Resultado.EMPATE, JuezDeTruco.ganadorEnvido(30, 30));
    }

    @Test
    public void testGanadorFlor() {
    	Assert.assertEquals(Resultado.GANADOR1, JuezDeTruco.ganadorFlor(29, 23));
        Assert.assertEquals(Resultado.GANADOR2, JuezDeTruco.ganadorFlor(35, 36));
    }
    
    @Test
    public void testEmpateEnFlor(){
    	Assert.assertEquals(Resultado.EMPATE, JuezDeTruco.ganadorFlor(32, 32));
    }

    @Test
    public void testGanadorMano() {
        Carta carta1 = new Carta(7, "ORO");
        Carta carta2 = new Carta(1, "ORO");
        Assert.assertEquals(Resultado.GANADOR1, JuezDeTruco.ganadorDeLaMano(carta1, carta2));
        Assert.assertEquals(Resultado.GANADOR2, JuezDeTruco.ganadorDeLaMano(carta2, carta1));
    }
    
    @Test
    public void testEmpateMano(){
    	Carta carta1 = new Carta(3, "ORO");
        Carta carta2 = new Carta(3, "copa");
        Assert.assertEquals(Resultado.EMPATE, JuezDeTruco.ganadorDeLaMano(carta1, carta2));
    }

}