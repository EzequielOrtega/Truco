package fiuba.algo3.tpfinal.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.tpfinal.modelo.envido.Envido;
import fiuba.algo3.tpfinal.modelo.envido.EstadoEnvido;
import fiuba.algo3.tpfinal.modelo.envido.EstadoInicialEnvido;
import fiuba.algo3.tpfinal.modelo.envido.FaltaEnvido;
import fiuba.algo3.tpfinal.modelo.envido.RealEnvido;
import fiuba.algo3.tpfinal.modelo.error.NoRespetaJerarquiaDeEnvidoError;

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

		assertEquals(7, estado.obtenerPuntosQueridos());
		assertEquals(4, estado.obtenerPuntosNoQueridos());
	}

	@Test
	public void testEnvidoRealEnvido() {
		estado = new Envido(estado);
		estado = new RealEnvido(estado);

		assertEquals(5, estado.obtenerPuntosQueridos());
		assertEquals(2, estado.obtenerPuntosNoQueridos());
	}

	@Test
	public void testRealEnvido() {
		estado = new RealEnvido(estado);

		assertEquals(3, estado.obtenerPuntosQueridos());
		assertEquals(1, estado.obtenerPuntosNoQueridos());
	}

	@Test
	public void testEnvidoSolo() {
		estado = new Envido(estado);

		assertEquals(2, estado.obtenerPuntosQueridos());
		assertEquals(1, estado.obtenerPuntosNoQueridos());
	}

	@Test(expected = NoRespetaJerarquiaDeEnvidoError.class)
	public void testRealEnvidoRealEnvidoNoVale() {
		estado = new RealEnvido(estado);
		estado = new RealEnvido(estado);
	}

	@Test(expected = NoRespetaJerarquiaDeEnvidoError.class)
	public void testRealEnvidoEnvidoNoVale() {
		estado = new RealEnvido(estado);
		estado = new Envido(estado);
	}

	@Test
	public void testEnvidoEnvido() {
		estado = new Envido(estado);
		estado = new Envido(estado);

		assertEquals(4, estado.obtenerPuntosQueridos());
		assertEquals(2, estado.obtenerPuntosNoQueridos());
	}

	@Test(expected = NoRespetaJerarquiaDeEnvidoError.class)
	public void testEnvidoEnvidoEnvidoNoVale() {
		estado = new Envido(estado);
		estado = new Envido(estado);
		estado = new Envido(estado);
	}

	@Test
	public void testFaltaEnvidoNoQuerido() {
		estado = new FaltaEnvido(estado);

		assertEquals(1, estado.obtenerPuntosNoQueridos());
	}

	@Test
	public void testEnvidoFaltaEnvidoNoQuerido() {
		estado = new Envido(estado);
		estado = new FaltaEnvido(estado);

		assertEquals(2, estado.obtenerPuntosNoQueridos());
	}

	@Test
	public void testRealEnvidoFaltaEnvidoNoQuerido() {
		estado = new RealEnvido(estado);
		estado = new FaltaEnvido(estado);

		assertEquals(3, estado.obtenerPuntosNoQueridos());
	}

	@Test
	public void testEnvidoEnvidoFaltaEnvidoNoQuerido() {
		estado = new Envido(estado);
		estado = new Envido(estado);
		estado = new FaltaEnvido(estado);

		assertEquals(4, estado.obtenerPuntosNoQueridos());
	}

	@Test
	public void testEnvidoRealEnvidoFaltaEnvidoNoQuerido() {
		estado = new Envido(estado);
		estado = new RealEnvido(estado);
		estado = new FaltaEnvido(estado);

		assertEquals(5, estado.obtenerPuntosNoQueridos());
	}

	@Test
	public void testEnvidoEnvidoRealEnvidoFaltaEnvidoNoQuerido() {
		estado = new Envido(estado);
		estado = new Envido(estado);
		estado = new RealEnvido(estado);
		estado = new FaltaEnvido(estado);

		assertEquals(7, estado.obtenerPuntosNoQueridos());
	}
}
