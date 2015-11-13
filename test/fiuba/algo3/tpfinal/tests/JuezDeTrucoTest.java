package fiuba.algo3.tpfinal.tests;


import fiuba.algo3.tpfinal.modelo.JuezDeTruco;
import fiuba.algo3.tpfinal.modelo.Jugador;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

public class JuezDeTrucoTest {
	private JuezDeTruco arbitro;
    private LinkedList<Jugador> jugadores = new LinkedList<Jugador>();
	@Before
	public void setup(){
		this.arbitro = new JuezDeTruco();
        Jugador j1 = new Jugador("Ana");
        Jugador j2 = new Jugador("Pedro");
        this.jugadores.add(j1);
        this.jugadores.add(j2);
	}
    @Test
    public void testGanadorEnvido() {
        /*assertEquals(Resultado.GANADOR1, arbitro.ganadorEnvido(29, 23));
        assertEquals(Resultado.GANADOR2, arbitro.ganadorEnvido(30, 33));*/
    }
    
    /*@Test
    public void testEmpateEnEnvido(){
    	assertEquals(Resultado.EMPATE, arbitro.ganadorEnvido(30, 30));
    }

    @Test
    public void testGanadorFlor() {
    	assertEquals(Resultado.GANADOR1, arbitro.ganadorFlor(29, 23));
        assertEquals(Resultado.GANADOR2, arbitro.ganadorFlor(35, 36));
    }
    
    @Test
    public void testEmpateEnFlor(){
    	assertEquals(Resultado.EMPATE, arbitro.ganadorFlor(32, 32));
    }*/

    /*@Test
    public void testGanadorMano() {
        Carta carta1 = new Carta(7, Palo.ORO);
        Carta carta2 = new Carta(1, Palo.ORO);
        assertEquals(Resultado.GANADOR1, arbitro.ganadorDeLaMano(carta1, carta2));
        assertEquals(Resultado.GANADOR2, arbitro.ganadorDeLaMano(carta2, carta1));
    }
    
    @Test
    public void testEmpateMano(){
    	Carta carta1 = new Carta(3, Palo.ORO);
        Carta carta2 = new Carta(3, Palo.COPA);
        assertEquals(Resultado.EMPATE, arbitro.ganadorDeLaMano(carta1, carta2));
    }*/

}