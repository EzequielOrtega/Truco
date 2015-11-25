package fiuba.algo3.tpfinal.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.tpfinal.modelo.Carta;
import fiuba.algo3.tpfinal.modelo.Equipo;
import fiuba.algo3.tpfinal.modelo.Figura;
import fiuba.algo3.tpfinal.modelo.JuezDeTruco;
import fiuba.algo3.tpfinal.modelo.Jugador;
import fiuba.algo3.tpfinal.modelo.NoFigura;
import fiuba.algo3.tpfinal.modelo.Palo;
import fiuba.algo3.tpfinal.modelo.Resultado;

public class JuezDeTrucoTest {

	private JuezDeTruco arbitro;
	private LinkedList<Jugador> dosJugadores;
	private LinkedList<Jugador> cuatroJugadores;

	private Carta sieteEspada = new NoFigura(7, Palo.ESPADA);
	private Carta diezEspada = new Figura(10, Palo.ESPADA);
	private Carta dosCopa = new NoFigura(2, Palo.COPA);
	private Carta seisCopa = new NoFigura(6, Palo.COPA);
	private Carta unoEspada = new NoFigura(1, Palo.ESPADA);
	private Carta doceCopa = new Figura(12, Palo.COPA);
	private Carta unoCopa = new NoFigura(1, Palo.COPA);
	private Carta doceEspada = new Figura(12, Palo.ESPADA);
	private Carta cuatroEspada = new NoFigura(4, Palo.ESPADA);
	private Carta onceEspada = new Figura(11, Palo.ESPADA);
	private Carta tresCopa = new NoFigura(3, Palo.COPA);
	private Carta seisEspada = new NoFigura(6, Palo.ESPADA);
	private Carta cincoEspada = new NoFigura(5, Palo.ESPADA);
	private Carta cuatroCopa = new NoFigura(4, Palo.COPA);
	private Carta sieteBasto = new NoFigura(7, Palo.BASTO);
	private Carta cuatroBasto = new NoFigura(4, Palo.BASTO);
	private Carta tresOro = new NoFigura(3, Palo.ORO);
	private Carta unoOro = new NoFigura(1, Palo.ORO);
	private Carta cincoCopa = new NoFigura(5, Palo.COPA);
	private Carta tresEspada = new NoFigura(3, Palo.ESPADA);
	private Carta seisBasto = new NoFigura(6, Palo.BASTO);
	private Carta unoBasto = new NoFigura(1, Palo.BASTO);
	private Carta doceBasto = new Figura(12, Palo.BASTO);
	private Carta sieteOro = new NoFigura(7, Palo.ORO);

	@Before
	public void setUp() {
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
		dosJugadores.get(0).agarrarCarta(sieteEspada);
		dosJugadores.get(0).agarrarCarta(diezEspada);
		dosJugadores.get(0).agarrarCarta(dosCopa);
		dosJugadores.get(1).agarrarCarta(seisCopa);
		dosJugadores.get(1).agarrarCarta(unoEspada);
		dosJugadores.get(1).agarrarCarta(doceCopa);

		assertTrue(dosJugadores.get(0).getValorEnvido() > dosJugadores.get(1).getValorEnvido());
		assertEquals(dosJugadores.get(0), arbitro.ganadorEnvido(dosJugadores));
	}

	@Test
	public void testEmpateEnvidoDosJugadores() {
		dosJugadores.get(0).agarrarCarta(sieteEspada);
		dosJugadores.get(0).agarrarCarta(diezEspada);
		dosJugadores.get(0).agarrarCarta(dosCopa);
		dosJugadores.get(1).agarrarCarta(seisCopa);
		dosJugadores.get(1).agarrarCarta(unoCopa);
		dosJugadores.get(1).agarrarCarta(doceEspada);

		assertEquals(dosJugadores.get(0).getValorEnvido(), dosJugadores.get(1).getValorEnvido());
		assertEquals(dosJugadores.get(0), arbitro.ganadorEnvido(dosJugadores));
	}

	@Test
	public void testGanadorEnvidoCuatroJugadores() {
		cuatroJugadores.get(0).agarrarCarta(sieteEspada);
		cuatroJugadores.get(0).agarrarCarta(diezEspada);
		cuatroJugadores.get(0).agarrarCarta(dosCopa);

		cuatroJugadores.get(1).agarrarCarta(seisCopa);
		cuatroJugadores.get(1).agarrarCarta(unoEspada);
		cuatroJugadores.get(1).agarrarCarta(doceCopa);

		cuatroJugadores.get(2).agarrarCarta(cuatroEspada);
		cuatroJugadores.get(2).agarrarCarta(onceEspada);
		cuatroJugadores.get(2).agarrarCarta(tresCopa);

		cuatroJugadores.get(3).agarrarCarta(seisEspada);
		cuatroJugadores.get(3).agarrarCarta(cincoEspada);
		cuatroJugadores.get(3).agarrarCarta(cuatroCopa);

		assertTrue(cuatroJugadores.get(3).getValorEnvido() > cuatroJugadores.get(0).getValorEnvido());
		assertTrue(cuatroJugadores.get(3).getValorEnvido() > cuatroJugadores.get(2).getValorEnvido());
		assertEquals(cuatroJugadores.get(3), arbitro.ganadorEnvido(cuatroJugadores));
	}

	@Test
	public void testEmpateEnvidoCuatroJugadores() {
		cuatroJugadores.get(0).agarrarCarta(sieteBasto);
		cuatroJugadores.get(0).agarrarCarta(diezEspada);
		cuatroJugadores.get(0).agarrarCarta(cuatroBasto);

		cuatroJugadores.get(1).agarrarCarta(seisCopa);
		cuatroJugadores.get(1).agarrarCarta(unoEspada);
		cuatroJugadores.get(1).agarrarCarta(doceCopa);

		cuatroJugadores.get(2).agarrarCarta(cuatroEspada);
		cuatroJugadores.get(2).agarrarCarta(onceEspada);
		cuatroJugadores.get(2).agarrarCarta(tresCopa);

		cuatroJugadores.get(3).agarrarCarta(seisEspada);
		cuatroJugadores.get(3).agarrarCarta(cincoEspada);
		cuatroJugadores.get(3).agarrarCarta(cuatroCopa);

		assertEquals(cuatroJugadores.get(3).getValorEnvido(), cuatroJugadores.get(0).getValorEnvido());
		assertTrue(cuatroJugadores.get(3).getValorEnvido() > cuatroJugadores.get(2).getValorEnvido());
		assertEquals(cuatroJugadores.get(0), arbitro.ganadorEnvido(cuatroJugadores));
	}

	@Test
	public void testGanadorFlor() {
		dosJugadores.get(0).agarrarCarta(sieteEspada);
		dosJugadores.get(0).agarrarCarta(diezEspada);
		dosJugadores.get(0).agarrarCarta(cuatroEspada);

		dosJugadores.get(1).agarrarCarta(seisBasto);
		dosJugadores.get(1).agarrarCarta(unoBasto);
		dosJugadores.get(1).agarrarCarta(doceBasto);

		assertTrue(dosJugadores.get(0).getValorFlor() > dosJugadores.get(1).getValorFlor());
		assertEquals(dosJugadores.get(0), arbitro.ganadorFlor(dosJugadores));
	}

	@Test
	public void testEmpateEnFlor() {
		dosJugadores.get(0).agarrarCarta(tresEspada);
		dosJugadores.get(0).agarrarCarta(diezEspada);
		dosJugadores.get(0).agarrarCarta(cuatroEspada);

		dosJugadores.get(1).agarrarCarta(seisBasto);
		dosJugadores.get(1).agarrarCarta(unoBasto);
		dosJugadores.get(1).agarrarCarta(doceBasto);

		assertEquals(dosJugadores.get(0).getValorFlor(), dosJugadores.get(1).getValorFlor());
		assertEquals(dosJugadores.get(0), arbitro.ganadorFlor(dosJugadores));
	}

	@Test
	public void testGanadorManoDosJugadores() {
		LinkedList<Carta> cartas = new LinkedList<Carta>();
		cartas.add(sieteOro);
		cartas.add(unoOro);
		assertEquals(Resultado.GANADOR1, arbitro.ganadorDeLaMano(cartas));
		cartas.removeFirst();
		cartas.removeFirst();
		cartas.add(unoOro);
		cartas.add(sieteOro);
		assertEquals(Resultado.GANADOR2, arbitro.ganadorDeLaMano(cartas));
	}

	@Test
	public void testEmpateManoDosJugadores() {
		LinkedList<Carta> cartas = new LinkedList<Carta>();
		cartas.add(tresOro);
		cartas.add(tresCopa);
		assertEquals(Resultado.EMPATE, arbitro.ganadorDeLaMano(cartas));
	}

	@Test
	public void testGanadorDeManoCuatroJugadores() {
		LinkedList<Carta> cartas = new LinkedList<Carta>();
		cartas.add(sieteOro);
		cartas.add(unoOro);
		cartas.add(cincoCopa);
		cartas.add(sieteEspada);
		assertEquals(Resultado.GANADOR2, arbitro.ganadorDeLaMano(cartas));
	}

	@Test
	public void testEmpateDeManoCuatroJugadores() {
		LinkedList<Carta> cartas = new LinkedList<Carta>();
		cartas.add(tresOro);
		cartas.add(unoOro);
		cartas.add(cincoCopa);
		cartas.add(tresEspada);
		assertEquals(Resultado.EMPATE, arbitro.ganadorDeLaMano(cartas));
	}
}