package fiuba.algo3.tpfinal.tests;


import fiuba.algo3.tpfinal.modelo.Carta;
import fiuba.algo3.tpfinal.modelo.Equipo;
import fiuba.algo3.tpfinal.modelo.JuezDeTruco;
import fiuba.algo3.tpfinal.modelo.Jugador;
import fiuba.algo3.tpfinal.modelo.Palo;
import fiuba.algo3.tpfinal.modelo.Resultado;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.LinkedList;

public class JuezDeTrucoTest {
	private JuezDeTruco arbitro;
    private LinkedList<Jugador> jugadores = new LinkedList<Jugador>();
	@Before
	public void setup(){
		this.arbitro = new JuezDeTruco();
        Jugador j1 = new Jugador("Ana", Equipo.EQUIPO1);
        Jugador j2 = new Jugador("Pedro", Equipo.EQUIPO2);
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
    @Ignore
    @Test
    public void testGanadorManoDosJugadores() {
        Carta carta1 = new Carta(7, Palo.ORO);
        Carta carta2 = new Carta(1, Palo.ORO);
        LinkedList<Carta> cartas = new LinkedList<Carta>();
        cartas.add(carta1);
        cartas.add(carta2);
        Assert.assertEquals(Resultado.GANADOR1, arbitro.ganadorDeLaMano(cartas));
        cartas.removeFirst();
        cartas.removeFirst();
        cartas.add(carta2);
        cartas.add(carta1);        
        Assert.assertEquals(Resultado.GANADOR2, arbitro.ganadorDeLaMano(cartas));
    }
    
    @Test
    public void testEmpateManoDosJugadores(){
    	Carta carta1 = new Carta(3, Palo.ORO);
        Carta carta2 = new Carta(3, Palo.COPA);
        LinkedList<Carta> cartas = new LinkedList<Carta>();
        cartas.add(carta1);
        cartas.add(carta2);
        Assert.assertEquals(Resultado.EMPATE, arbitro.ganadorDeLaMano(cartas));
    }
    
    @Ignore
    @Test 
    public void testGanadorDeManoCuatroJugadores(){
    	Carta carta1 = new Carta(7, Palo.ORO);
        Carta carta2 = new Carta(1, Palo.ORO);
        Carta carta3 = new Carta(5, Palo.COPA);
        Carta carta4 = new Carta(7, Palo.ESPADA);
        LinkedList<Carta> cartas = new LinkedList<Carta>();
        cartas.add(carta1);
        cartas.add(carta2);
        cartas.add(carta3);
        cartas.add(carta4);
        Assert.assertEquals(Resultado.GANADOR2, arbitro.ganadorDeLaMano(cartas));
    }
    
    @Test 
    public void testEmpateDeManoCuatroJugadores(){
    	Carta carta1 = new Carta(3, Palo.ORO);
        Carta carta2 = new Carta(1, Palo.ORO);
        Carta carta3 = new Carta(5, Palo.COPA);
        Carta carta4 = new Carta(3, Palo.ESPADA);
        LinkedList<Carta> cartas = new LinkedList<Carta>();
        cartas.add(carta1);
        cartas.add(carta2);
        cartas.add(carta3);
        cartas.add(carta4);
        Assert.assertEquals(Resultado.EMPATE, arbitro.ganadorDeLaMano(cartas));
    }

}