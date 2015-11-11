package fiuba.algo3.tpfinal.tests;


import fiuba.algo3.tpfinal.modelo.*;
import org.junit.Test;

import org.junit.Assert;
import org.junit.Before;

public class JuezDeTrucoTest {
	private JuezDeTruco arbitro;
	@Before
	public void setup(){
		this.arbitro = new JuezDeTruco();
	}
    @Test
    public void testGanadorEnvido() {
        Assert.assertEquals(Resultado.GANADOR1, arbitro.ganadorEnvido(29, 23));
        Assert.assertEquals(Resultado.GANADOR2, arbitro.ganadorEnvido(30, 33));
    }
    
    @Test
    public void testEmpateEnEnvido(){
    	Assert.assertEquals(Resultado.EMPATE, arbitro.ganadorEnvido(30, 30));
    }

    @Test
    public void testGanadorFlor() {
    	Assert.assertEquals(Resultado.GANADOR1, arbitro.ganadorFlor(29, 23));
        Assert.assertEquals(Resultado.GANADOR2, arbitro.ganadorFlor(35, 36));
    }
    
    @Test
    public void testEmpateEnFlor(){
    	Assert.assertEquals(Resultado.EMPATE, arbitro.ganadorFlor(32, 32));
    }

    @Test
    public void testGanadorMano() {
        Carta carta1 = new Carta(7, "ORO");
        Carta carta2 = new Carta(1, "ORO");
        Assert.assertEquals(Resultado.GANADOR1, arbitro.ganadorDeLaMano(carta1, carta2));
        Assert.assertEquals(Resultado.GANADOR2, arbitro.ganadorDeLaMano(carta2, carta1));
    }
    
    @Test
    public void testEmpateMano(){
    	Carta carta1 = new Carta(3, "ORO");
        Carta carta2 = new Carta(3, "copa");
        Assert.assertEquals(Resultado.EMPATE, arbitro.ganadorDeLaMano(carta1, carta2));
    }

}