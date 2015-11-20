package fiuba.algo3.tpfinal.tests;

import java.util.Vector;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.tpfinal.modelo.CalculadorDeValorRelativo;
import fiuba.algo3.tpfinal.modelo.Carta;
import fiuba.algo3.tpfinal.modelo.Figura;
import fiuba.algo3.tpfinal.modelo.LaCartaIngresadaNoEstaEnLaTablaError;
import fiuba.algo3.tpfinal.modelo.Mazo;
import fiuba.algo3.tpfinal.modelo.NoFigura;
import fiuba.algo3.tpfinal.modelo.Palo;

public class CalculadorDeValorRelativoTest {
	
	private CalculadorDeValorRelativo calculador;
	
	@Before
	public void setup(){
		calculador = new CalculadorDeValorRelativo();
	}
	
	@Test
	public void testEstanTodasLasCartas() {
		Mazo mazo = new Mazo();
		Vector<Carta> cartas = mazo.getCartas();
		for(Carta carta : cartas){
			Assert.assertTrue(calculador.eliminarCarta(carta));
		}
	}
	
	@Test
	public void testObtenerValorRelativo(){
		Carta carta = new NoFigura(1, Palo.ESPADA);
		Assert.assertTrue(13 == calculador.obtenerValorCarta(carta));
		carta = new NoFigura(2, Palo.BASTO);
		Assert.assertTrue(8 == calculador.obtenerValorCarta(carta));
		carta = new Figura(12, Palo.ESPADA);
		Assert.assertTrue(6 == calculador.obtenerValorCarta(carta));
		carta = new NoFigura(5, Palo.ESPADA);
		Assert.assertTrue(1 == calculador.obtenerValorCarta(carta));
	}
	
	@Test (expected = LaCartaIngresadaNoEstaEnLaTablaError.class)
	public void testObtenerValorRelativoError(){
		Carta carta = new Carta(8, Palo.COPA);
		calculador.obtenerValorCarta(carta);
		
	}
}
