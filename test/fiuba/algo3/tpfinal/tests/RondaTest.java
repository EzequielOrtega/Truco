package fiuba.algo3.tpfinal.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.tpfinal.modelo.Resultado;
import fiuba.algo3.tpfinal.modelo.Ronda;
import fiuba.algo3.tpfinal.modelo.error.YaSeJugaronLasTresManosError;

public class RondaTest {
	
	private Ronda ronda;
	
	@Before
	public void setup(){
		ronda = new Ronda();
	}
	
	@Test
	public void testRondaSeCreaVacia() {
		Assert.assertTrue(this.ronda.estaVacia());
		Assert.assertTrue(this.ronda.tamanio() == 0);
	}
	
	@Test
	public void testInsercion(){
		Assert.assertTrue(this.ronda.tamanio() == 0);
		this.ronda.insercion(Resultado.GANADOR1);
		Assert.assertTrue(this.ronda.tamanio() == 1);
		this.ronda.insercion(Resultado.GANADOR2);
		Assert.assertTrue(this.ronda.tamanio() == 2);
		this.ronda.insercion(Resultado.EMPATE);
		Assert.assertTrue(this.ronda.tamanio() == 3);
	}
	
	@Test (expected = YaSeJugaronLasTresManosError.class)
	public void testInsercionDeMasDeTresManos(){
		this.ronda.insercion(Resultado.GANADOR1);
		this.ronda.insercion(Resultado.GANADOR1);
		this.ronda.insercion(Resultado.GANADOR1);
		this.ronda.insercion(Resultado.GANADOR1);
	}
	
	@Test
	public void testNoConcluyoRondaEnPrimera(){
		this.ronda.insercion(Resultado.GANADOR1);
		Assert.assertFalse(ronda.concluyoLaRonda());	
	}
	
	@Test
	public void testConcluyoLaRondaEnSegundaConEmpateEnPrimera(){
		this.ronda.insercion(Resultado.EMPATE);
		this.ronda.insercion(Resultado.GANADOR1);
		Assert.assertTrue(ronda.concluyoLaRonda());		
	}
	
	@Test
	public void testConcluyoLaRondaEnSegundaConEmpateEnSegunda(){
		this.ronda.insercion(Resultado.GANADOR1);
		this.ronda.insercion(Resultado.EMPATE);
		Assert.assertTrue(ronda.concluyoLaRonda());		
	}
	
	@Test
	public void testConcluyoLaRondaEnTercera(){
		this.ronda.insercion(Resultado.GANADOR1);
		this.ronda.insercion(Resultado.GANADOR2);
		this.ronda.insercion(Resultado.EMPATE);
		Assert.assertTrue(ronda.concluyoLaRonda());
	}

}
