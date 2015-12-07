package fiuba.algo3.tpfinal.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.tpfinal.modelo.Carta;
import fiuba.algo3.tpfinal.modelo.Diccionario;
import fiuba.algo3.tpfinal.modelo.Figura;
import fiuba.algo3.tpfinal.modelo.NoFigura;
import fiuba.algo3.tpfinal.modelo.Palo;
import fiuba.algo3.tpfinal.modelo.error.LaCartaIngresadaNoEstaEnLaTablaError;

public class DiccionarioTest {

	private Diccionario diccionario;

	@Before
	public void setup() {
		diccionario = new Diccionario();
	}

	@Test
	public void testPruebasVarias() {
		Carta cuatroEspada = new NoFigura(4, Palo.ESPADA);
		Assert.assertEquals("Cuatro de espada", diccionario.Busqueda(cuatroEspada));
		Carta cincoEspada = new NoFigura(5, Palo.ESPADA);
		Assert.assertEquals("Cinco de espada", diccionario.Busqueda(cincoEspada));
		Carta anchoBasto = new NoFigura(1, Palo.BASTO);
		Assert.assertEquals("Uno de basto", diccionario.Busqueda(anchoBasto));
		Carta dosBasto = new NoFigura(2, Palo.BASTO);
		Assert.assertEquals("Dos de basto", diccionario.Busqueda(dosBasto));
		Carta caballoCopa = new Figura(11, Palo.COPA);
		Assert.assertEquals("Once de copa", diccionario.Busqueda(caballoCopa));
		Carta reyCopa = new Figura(12, Palo.COPA);
		Assert.assertEquals("Doce de copa", diccionario.Busqueda(reyCopa));
		Carta dosOro = new NoFigura(2, Palo.ORO);
		Assert.assertEquals("Dos de oro", diccionario.Busqueda(dosOro));
		Carta tresOro = new NoFigura(3, Palo.ORO);
		Assert.assertEquals("Tres de oro", diccionario.Busqueda(tresOro));
	}

	@Test(expected = LaCartaIngresadaNoEstaEnLaTablaError.class)
	public void testLaCartaBuscadaNoEstaEnLaTabla() {
		Carta carta = new Carta(9, Palo.ESPADA);
		diccionario.Busqueda(carta);
	}

}
