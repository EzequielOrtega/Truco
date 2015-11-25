package fiuba.algo3.tpfinal.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.tpfinal.modelo.CalculadorDeTruco;
import fiuba.algo3.tpfinal.modelo.Carta;
import fiuba.algo3.tpfinal.modelo.Figura;
import fiuba.algo3.tpfinal.modelo.Mazo;
import fiuba.algo3.tpfinal.modelo.NoFigura;
import fiuba.algo3.tpfinal.modelo.Palo;
import fiuba.algo3.tpfinal.modelo.error.LaCartaIngresadaNoEstaEnLaTablaError;

public class CalculadorDeTrucoTest {

	private CalculadorDeTruco calculador;

	@Before
	public void setup() {
		calculador = new CalculadorDeTruco();
	}

	@Test
	public void testEstanTodasLasCartas() {
		Mazo mazo = new Mazo();
		Vector<Carta> cartas = mazo.getCartas();
		for (Carta carta : cartas) {
			assertTrue(calculador.eliminarCarta(carta));
		}
	}

	@Test
	public void testObtenerValorRelativo() {
		Carta carta = new NoFigura(1, Palo.ESPADA);
		assertEquals(13, calculador.obtenerValorCarta(carta));
		carta = new NoFigura(2, Palo.BASTO);
		assertEquals(8, calculador.obtenerValorCarta(carta));
		carta = new Figura(12, Palo.ESPADA);
		assertEquals(6, calculador.obtenerValorCarta(carta));
		carta = new NoFigura(5, Palo.ESPADA);
		assertEquals(1, calculador.obtenerValorCarta(carta));
	}

	@Test(expected = LaCartaIngresadaNoEstaEnLaTablaError.class)
	public void testObtenerValorRelativoError() {
		Carta carta = new Carta(8, Palo.COPA);
		calculador.obtenerValorCarta(carta);

	}
}
