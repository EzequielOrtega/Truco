package fiuba.algo3.tpfinal.tests;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import fiuba.algo3.tpfinal.modelo.Envido;
import fiuba.algo3.tpfinal.modelo.EstadoEnvido;
import fiuba.algo3.tpfinal.modelo.EstadoInicial;
import fiuba.algo3.tpfinal.modelo.NoRespetaJerarquiaDeEnvidoError;
import fiuba.algo3.tpfinal.modelo.RealEnvido;

public class EstadoEnvidoTest {

	@Test
	public void testEnvidoEnvidoRealEnvido() {
		EstadoEnvido estado = new EstadoInicial();
		estado = new Envido(estado);
		estado = new Envido(estado);
		estado = new RealEnvido(estado);
		
		Assert.assertEquals(7, estado.obtenerPuntosQueridos());
		Assert.assertEquals(4, estado.obtenerPuntosNoQueridos());
	}
	
	@Test
	public void testEnvidoRealEnvido() {
		EstadoEnvido estado = new EstadoInicial();
		estado = new Envido(estado);
		estado = new RealEnvido(estado);
		
		Assert.assertEquals(5, estado.obtenerPuntosQueridos());
		Assert.assertEquals(2, estado.obtenerPuntosNoQueridos());
	}
	
	@Test
	public void testRealEnvido() {
		EstadoEnvido estado = new EstadoInicial();
		estado = new RealEnvido(estado);
		
		Assert.assertEquals(3, estado.obtenerPuntosQueridos());
		Assert.assertEquals(1, estado.obtenerPuntosNoQueridos());
	}
	
	@Test
	public void testEnvidoSolo() {
		EstadoEnvido estado = new EstadoInicial();
		estado = new Envido(estado);
		
		Assert.assertEquals(2, estado.obtenerPuntosQueridos());
		Assert.assertEquals(1, estado.obtenerPuntosNoQueridos());
	}
	
	@Test (expected = NoRespetaJerarquiaDeEnvidoError.class)
	public void testRealEnvidoRealEnvidoNoVale() {
		EstadoEnvido estado = new EstadoInicial();
		estado = new RealEnvido(estado);
		estado = new RealEnvido(estado);
	}
	
	@Test (expected = NoRespetaJerarquiaDeEnvidoError.class)
	public void testRealEnvidoEnvidoNoVale() {
		EstadoEnvido estado = new EstadoInicial();
		estado = new RealEnvido(estado);
		estado = new Envido(estado);
	}
	
	@Ignore
	@Test (expected = NoRespetaJerarquiaDeEnvidoError.class)
	public void testEnvidoEnvidoEnvidoNoVale() {
		EstadoEnvido estado = new EstadoInicial();
		estado = new Envido(estado);
		estado = new Envido(estado);
		estado = new Envido(estado);
	}
}
