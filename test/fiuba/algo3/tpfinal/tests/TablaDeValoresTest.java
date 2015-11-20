package fiuba.algo3.tpfinal.tests;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.tpfinal.modelo.Carta;
import fiuba.algo3.tpfinal.modelo.LaCartaIngresadaNoEstaEnLaTablaError;
import fiuba.algo3.tpfinal.modelo.NoFigura;
import fiuba.algo3.tpfinal.modelo.Palo;
import fiuba.algo3.tpfinal.modelo.TablaDeValores;



public class TablaDeValoresTest {
	
	private TablaDeValores tabla;
	
	@Before
	public void setup(){
		this.tabla = new TablaDeValores();
	}
	
	@Test
	public void testInsercionCorrecta() {
		Carta carta = new NoFigura(1, Palo.ESPADA);
		Assert.assertTrue(tabla.insercion(carta, 13));
	}
	
	@Test
	public void testBusqueda(){
		Carta carta = new NoFigura(1, Palo.ESPADA);
		tabla.insercion(carta, 13);
		Assert.assertTrue(13 == tabla.busqueda(carta));
	}
	
	@Test (expected = LaCartaIngresadaNoEstaEnLaTablaError.class)
	public void testBusquedaError(){
		Carta carta = new NoFigura(1, Palo.ESPADA);
		tabla.busqueda(carta);
	}
	
	@Test
	public void testEliminarElemento() {
		Carta carta = new NoFigura(1, Palo.ESPADA);
		tabla.insercion(carta, 13);
		Assert.assertTrue(tabla.borrar(carta));
	}
	
	@Test (expected = LaCartaIngresadaNoEstaEnLaTablaError.class)
	public void testEliminarElementoError() {
		Carta carta = new NoFigura(1, Palo.ESPADA);
		Assert.assertTrue(tabla.borrar(carta));
	}

}
