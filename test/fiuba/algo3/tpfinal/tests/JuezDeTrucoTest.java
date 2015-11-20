package fiuba.algo3.tpfinal.tests;


import fiuba.algo3.tpfinal.modelo.Carta;
import fiuba.algo3.tpfinal.modelo.Equipo;
import fiuba.algo3.tpfinal.modelo.Figura;
import fiuba.algo3.tpfinal.modelo.JuezDeTruco;
import fiuba.algo3.tpfinal.modelo.Jugador;
import fiuba.algo3.tpfinal.modelo.NoFigura;
import fiuba.algo3.tpfinal.modelo.Palo;
import fiuba.algo3.tpfinal.modelo.Resultado;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.LinkedList;

public class JuezDeTrucoTest {
	
	private JuezDeTruco arbitro;
    private LinkedList<Jugador> dosJugadores;
    private LinkedList<Jugador> cuatroJugadores;
	
    @Before
	public void setUp(){
		this.arbitro = new JuezDeTruco();
		this.dosJugadores = new LinkedList<Jugador>();
		this.cuatroJugadores = new LinkedList<Jugador>();
        Jugador j1 = new Jugador("Ana", Equipo.EQUIPO1);
        Jugador j2 = new Jugador("Pedro", Equipo.EQUIPO2);
        Jugador j3 = new Jugador("Pedro", Equipo.EQUIPO2);
        Jugador j4 = new Jugador("Pedro", Equipo.EQUIPO2);
        this.dosJugadores.add(j1);
        this.dosJugadores.add(j2);
        this.cuatroJugadores.add(j1);
        this.cuatroJugadores.add(j2);
        this.cuatroJugadores.add(j3);
        this.cuatroJugadores.add(j4);
        
	}
    @Test
    public void testGanadorEnvidoDosJugadores() {
    	Carta carta1 = new NoFigura(7 , Palo.ESPADA);
    	Carta carta2 = new Figura(10 , Palo.ESPADA);
    	Carta carta3 = new NoFigura(2 , Palo.COPA);
    	dosJugadores.get(0).agarrarCarta(carta1);
    	dosJugadores.get(0).agarrarCarta(carta2);
    	dosJugadores.get(0).agarrarCarta(carta3);
    	Carta carta4 = new NoFigura(6 , Palo.COPA);
    	Carta carta5 = new NoFigura(1 , Palo.ESPADA);
    	Carta carta6 = new Figura(12 , Palo.COPA);
    	dosJugadores.get(1).agarrarCarta(carta4);
    	dosJugadores.get(1).agarrarCarta(carta5);
    	dosJugadores.get(1).agarrarCarta(carta6);
    	Assert.assertTrue(dosJugadores.get(0).getValorEnvido() > dosJugadores.get(1).getValorEnvido());
    	Assert.assertTrue(dosJugadores.get(0) == arbitro.ganadorEnvido(dosJugadores));
    }
    
    @Test
    public void testEmpateEnvidoDosJugadores() {
    	Carta carta1 = new NoFigura(7 , Palo.ESPADA);
    	Carta carta2 = new Figura(10 , Palo.ESPADA);
    	Carta carta3 = new NoFigura(2 , Palo.COPA);
    	dosJugadores.get(0).agarrarCarta(carta1);
    	dosJugadores.get(0).agarrarCarta(carta2);
    	dosJugadores.get(0).agarrarCarta(carta3);
    	Carta carta4 = new NoFigura(6 , Palo.COPA);
    	Carta carta5 = new NoFigura(1 , Palo.COPA);
    	Carta carta6 = new Figura(12 , Palo.ESPADA);
    	dosJugadores.get(1).agarrarCarta(carta4);
    	dosJugadores.get(1).agarrarCarta(carta5);
    	dosJugadores.get(1).agarrarCarta(carta6);
    	Assert.assertTrue(dosJugadores.get(0).getValorEnvido() == dosJugadores.get(1).getValorEnvido());
    	Assert.assertTrue(dosJugadores.get(0) == arbitro.ganadorEnvido(dosJugadores));
    }
    
    @Test
    public void testGanadorEnvidoCuatroJugadores() {
    	Carta carta1 = new NoFigura(7 , Palo.ESPADA);
    	Carta carta2 = new Figura(10 , Palo.ESPADA);
    	Carta carta3 = new NoFigura(2 , Palo.COPA);
    	cuatroJugadores.get(0).agarrarCarta(carta1);
    	cuatroJugadores.get(0).agarrarCarta(carta2);
    	cuatroJugadores.get(0).agarrarCarta(carta3);
    	
    	Carta carta4 = new NoFigura(6 , Palo.COPA);
    	Carta carta5 = new NoFigura(1 , Palo.ESPADA);
    	Carta carta6 = new Figura(12 , Palo.COPA);
    	cuatroJugadores.get(1).agarrarCarta(carta4);
    	cuatroJugadores.get(1).agarrarCarta(carta5);
    	cuatroJugadores.get(1).agarrarCarta(carta6);
    	
    	Carta carta7 = new NoFigura(4 , Palo.ESPADA);
    	Carta carta8 = new Figura(11 , Palo.ESPADA);
    	Carta carta9 = new NoFigura(3 , Palo.COPA);
    	cuatroJugadores.get(2).agarrarCarta(carta7);
    	cuatroJugadores.get(2).agarrarCarta(carta8);
    	cuatroJugadores.get(2).agarrarCarta(carta9);
    	
    	Carta carta10 = new NoFigura(6 , Palo.ESPADA);
    	Carta carta11 = new NoFigura(5 , Palo.ESPADA);
    	Carta carta12 = new NoFigura(4 , Palo.COPA);
    	cuatroJugadores.get(3).agarrarCarta(carta10);
    	cuatroJugadores.get(3).agarrarCarta(carta11);
    	cuatroJugadores.get(3).agarrarCarta(carta12);
    	
    	Assert.assertTrue(cuatroJugadores.get(3).getValorEnvido() > cuatroJugadores.get(0).getValorEnvido());
    	Assert.assertTrue(cuatroJugadores.get(3).getValorEnvido() > cuatroJugadores.get(2).getValorEnvido());
    	Assert.assertTrue(cuatroJugadores.get(3) == arbitro.ganadorEnvido(cuatroJugadores));
    }
    
    @Test
    public void testEmpateEnvidoCuatroJugadores() {
    	Carta carta1 = new NoFigura(7 , Palo.BASTO);
    	Carta carta2 = new Figura(10 , Palo.ESPADA);
    	Carta carta3 = new NoFigura(4 , Palo.BASTO);
    	cuatroJugadores.get(0).agarrarCarta(carta1);
    	cuatroJugadores.get(0).agarrarCarta(carta2);
    	cuatroJugadores.get(0).agarrarCarta(carta3);
    	
    	Carta carta4 = new NoFigura(6 , Palo.COPA);
    	Carta carta5 = new NoFigura(1 , Palo.ESPADA);
    	Carta carta6 = new Figura(12 , Palo.COPA);
    	cuatroJugadores.get(1).agarrarCarta(carta4);
    	cuatroJugadores.get(1).agarrarCarta(carta5);
    	cuatroJugadores.get(1).agarrarCarta(carta6);
    	
    	Carta carta7 = new NoFigura(4 , Palo.ESPADA);
    	Carta carta8 = new Figura(11 , Palo.ESPADA);
    	Carta carta9 = new NoFigura(3 , Palo.COPA);
    	cuatroJugadores.get(2).agarrarCarta(carta7);
    	cuatroJugadores.get(2).agarrarCarta(carta8);
    	cuatroJugadores.get(2).agarrarCarta(carta9);
    	
    	Carta carta10 = new NoFigura(6 , Palo.ESPADA);
    	Carta carta11 = new NoFigura(5 , Palo.ESPADA);
    	Carta carta12 = new NoFigura(4 , Palo.COPA);
    	cuatroJugadores.get(3).agarrarCarta(carta10);
    	cuatroJugadores.get(3).agarrarCarta(carta11);
    	cuatroJugadores.get(3).agarrarCarta(carta12);
    	
    	Assert.assertTrue(cuatroJugadores.get(3).getValorEnvido() == cuatroJugadores.get(0).getValorEnvido());
    	Assert.assertTrue(cuatroJugadores.get(3).getValorEnvido() > cuatroJugadores.get(2).getValorEnvido());
    	Assert.assertTrue(cuatroJugadores.get(0) == arbitro.ganadorEnvido(cuatroJugadores));
    }

    @Test
    public void testGanadorFlor() {
    	Carta carta1 = new NoFigura(7 , Palo.ESPADA);
    	Carta carta2 = new Figura(10 , Palo.ESPADA);
    	Carta carta3 = new NoFigura(4 , Palo.ESPADA);
    	dosJugadores.get(0).agarrarCarta(carta1);
    	dosJugadores.get(0).agarrarCarta(carta2);
    	dosJugadores.get(0).agarrarCarta(carta3);
    	
    	Carta carta4 = new NoFigura(6 , Palo.BASTO);
    	Carta carta5 = new NoFigura(1 , Palo.BASTO);
    	Carta carta6 = new Figura(12 , Palo.BASTO);
    	dosJugadores.get(1).agarrarCarta(carta4);
    	dosJugadores.get(1).agarrarCarta(carta5);
    	dosJugadores.get(1).agarrarCarta(carta6);
    	
    	Assert.assertTrue(dosJugadores.get(0).getValorFlor() > dosJugadores.get(1).getValorFlor());
    	Assert.assertTrue(dosJugadores.get(0) == arbitro.ganadorFlor(dosJugadores));
    }
    
    @Test
    public void testEmpateEnFlor(){
    	Carta carta1 = new NoFigura(3 , Palo.ESPADA);
    	Carta carta2 = new Figura(10 , Palo.ESPADA);
    	Carta carta3 = new NoFigura(4 , Palo.ESPADA);
    	dosJugadores.get(0).agarrarCarta(carta1);
    	dosJugadores.get(0).agarrarCarta(carta2);
    	dosJugadores.get(0).agarrarCarta(carta3);
    	
    	Carta carta4 = new NoFigura(6 , Palo.BASTO);
    	Carta carta5 = new NoFigura(1 , Palo.BASTO);
    	Carta carta6 = new Figura(12 , Palo.BASTO);
    	dosJugadores.get(1).agarrarCarta(carta4);
    	dosJugadores.get(1).agarrarCarta(carta5);
    	dosJugadores.get(1).agarrarCarta(carta6);
    	
    	Assert.assertTrue(dosJugadores.get(0).getValorFlor() == dosJugadores.get(1).getValorFlor());
    	Assert.assertTrue(dosJugadores.get(0) == arbitro.ganadorFlor(dosJugadores));
    }
    
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