package fiuba.algo3.tpfinal.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.tpfinal.modelo.Envido;
import fiuba.algo3.tpfinal.modelo.EstadoEnvido;
import fiuba.algo3.tpfinal.modelo.EstadoInicialEnvido;
import fiuba.algo3.tpfinal.modelo.FaltaEnvido;
import fiuba.algo3.tpfinal.modelo.NoRespetaJerarquiaDeEnvidoError;
import fiuba.algo3.tpfinal.modelo.RealEnvido;

public class EstadoEnvidoTest {
	
	private EstadoEnvido estado;

	@Before
	public void setUp() {
		estado = new EstadoInicialEnvido();
	}

	@Test
	public void testEnvidoEnvidoRealEnvido() {
		estado = new Envido(estado);
		estado = new Envido(estado);
		estado = new RealEnvido(estado);
		
		Assert.assertEquals(7, estado.obtenerPuntosQueridos());
		Assert.assertEquals(4, estado.obtenerPuntosNoQueridos());
	}
	
	@Test
	public void testEnvidoRealEnvido() {
		estado = new Envido(estado);
		estado = new RealEnvido(estado);
		
		Assert.assertEquals(5, estado.obtenerPuntosQueridos());
		Assert.assertEquals(2, estado.obtenerPuntosNoQueridos());
	}
	
	@Test
	public void testRealEnvido() {
		estado = new RealEnvido(estado);
		
		Assert.assertEquals(3, estado.obtenerPuntosQueridos());
		Assert.assertEquals(1, estado.obtenerPuntosNoQueridos());
	}
	
	@Test
	public void testEnvidoSolo() {
		estado = new Envido(estado);
		
		Assert.assertEquals(2, estado.obtenerPuntosQueridos());
		Assert.assertEquals(1, estado.obtenerPuntosNoQueridos());
	}
	
	@Test (expected = NoRespetaJerarquiaDeEnvidoError.class)
	public void testRealEnvidoRealEnvidoNoVale() {
		estado = new RealEnvido(estado);
		estado = new RealEnvido(estado);
	}
	
	@Test (expected = NoRespetaJerarquiaDeEnvidoError.class)
	public void testRealEnvidoEnvidoNoVale() {
		estado = new RealEnvido(estado);
		estado = new Envido(estado);
	}
	
	@Test
	public void testEnvidoEnvido() {
		estado = new Envido(estado);
		estado = new Envido(estado);
		
		Assert.assertEquals(4, estado.obtenerPuntosQueridos());
		Assert.assertEquals(2, estado.obtenerPuntosNoQueridos());
	}

	@Test (expected = NoRespetaJerarquiaDeEnvidoError.class)
	public void testEnvidoEnvidoEnvidoNoVale() {
		estado = new Envido(estado);
		estado = new Envido(estado);
		estado = new Envido(estado);
	}
	
	@Test
	public void testFaltaEnvidoNoQuerido() {
		estado = new FaltaEnvido(estado);
		
		Assert.assertEquals(1, estado.obtenerPuntosNoQueridos());
	}
	
	@Test
	public void testEnvidoFaltaEnvidoNoQuerido() {
		estado = new Envido(estado);
		estado = new FaltaEnvido(estado);
		
		Assert.assertEquals(2, estado.obtenerPuntosNoQueridos());
	}
	
	@Test
	public void testRealEnvidoFaltaEnvidoNoQuerido() {
		estado = new RealEnvido(estado);
		estado = new FaltaEnvido(estado);
		
		Assert.assertEquals(3, estado.obtenerPuntosNoQueridos());
	}
	
	@Test
	public void testEnvidoEnvidoFaltaEnvidoNoQuerido() {
		estado = new Envido(estado);
		estado = new Envido(estado);
		estado = new FaltaEnvido(estado);
		
		Assert.assertEquals(4, estado.obtenerPuntosNoQueridos());
	}
	
	@Test
	public void testEnvidoRealEnvidoFaltaEnvidoNoQuerido() {
		estado = new Envido(estado);
		estado = new RealEnvido(estado);
		estado = new FaltaEnvido(estado);
		
		Assert.assertEquals(5, estado.obtenerPuntosNoQueridos());
	}
	
	@Test
	public void testEnvidoEnvidoRealEnvidoFaltaEnvidoNoQuerido() {
		estado = new Envido(estado);
		estado = new Envido(estado);
		estado = new RealEnvido(estado);
		estado = new FaltaEnvido(estado);
		
		Assert.assertEquals(7, estado.obtenerPuntosNoQueridos());
	}
}
