package fiuba.algo3.tpfinal.tests;

import fiuba.algo3.tpfinal.modelo.envido.*;
import fiuba.algo3.tpfinal.modelo.error.NoRespetaJerarquiaDeEnvidoError;
import fiuba.algo3.tpfinal.modelo.flor.EstadoFlor;
import fiuba.algo3.tpfinal.modelo.flor.EstadoInicialFlor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EstadoEnvidoTest {

	private EstadoEnvido estado;
	private EstadoFlor estadoFlor;

	@Before
	public void setUp() {
		estado = new EstadoInicialEnvido();
		estadoFlor = new EstadoInicialFlor();
	}

	@Test
	public void testEnvidoEnvidoRealEnvido() {
		estado = new Envido(estado, estadoFlor);
		estado = new Envido(estado, estadoFlor);
		estado = new RealEnvido(estado, estadoFlor);

		assertEquals(7, estado.obtenerPuntosQueridos());
		assertEquals(4, estado.obtenerPuntosNoQueridos());
	}

	@Test
	public void testEnvidoRealEnvido() {
		estado = new Envido(estado, estadoFlor);
		estado = new RealEnvido(estado, estadoFlor);

		assertEquals(5, estado.obtenerPuntosQueridos());
		assertEquals(2, estado.obtenerPuntosNoQueridos());
	}

	@Test
	public void testRealEnvido() {
		estado = new RealEnvido(estado, estadoFlor);

		assertEquals(3, estado.obtenerPuntosQueridos());
		assertEquals(1, estado.obtenerPuntosNoQueridos());
	}

	@Test
	public void testEnvidoSolo() {
		estado = new Envido(estado, estadoFlor);

		assertEquals(2, estado.obtenerPuntosQueridos());
		assertEquals(1, estado.obtenerPuntosNoQueridos());
	}

	@Test(expected = NoRespetaJerarquiaDeEnvidoError.class)
	public void testRealEnvidoRealEnvidoNoVale() {
		estado = new RealEnvido(estado, estadoFlor);
		estado = new RealEnvido(estado, estadoFlor);
	}

	@Test(expected = NoRespetaJerarquiaDeEnvidoError.class)
	public void testRealEnvidoEnvidoNoVale() {
		estado = new RealEnvido(estado, estadoFlor);
		estado = new Envido(estado, estadoFlor);
	}

	@Test
	public void testEnvidoEnvido() {
		estado = new Envido(estado, estadoFlor);
		estado = new Envido(estado, estadoFlor);

		assertEquals(4, estado.obtenerPuntosQueridos());
		assertEquals(2, estado.obtenerPuntosNoQueridos());
	}

	@Test(expected = NoRespetaJerarquiaDeEnvidoError.class)
	public void testEnvidoEnvidoEnvidoNoVale() {
		estado = new Envido(estado, estadoFlor);
		estado = new Envido(estado, estadoFlor);
		estado = new Envido(estado, estadoFlor);
	}

	@Test
	public void testFaltaEnvidoNoQuerido() {
		estado = new FaltaEnvido(estado, estadoFlor, 30, 30);

		assertEquals(1, estado.obtenerPuntosNoQueridos());
	}

	@Test
	public void testEnvidoFaltaEnvidoNoQuerido() {
		estado = new Envido(estado, estadoFlor);
		estado = new FaltaEnvido(estado, estadoFlor, 30, 30);

		assertEquals(2, estado.obtenerPuntosNoQueridos());
	}

	@Test
	public void testRealEnvidoFaltaEnvidoNoQuerido() {
		estado = new RealEnvido(estado, estadoFlor);
		estado = new FaltaEnvido(estado, estadoFlor, 30, 30);

		assertEquals(3, estado.obtenerPuntosNoQueridos());
	}

	@Test
	public void testEnvidoEnvidoFaltaEnvidoNoQuerido() {
		estado = new Envido(estado, estadoFlor);
		estado = new Envido(estado, estadoFlor);
		estado = new FaltaEnvido(estado, estadoFlor, 30, 30);

		assertEquals(4, estado.obtenerPuntosNoQueridos());
	}

	@Test
	public void testEnvidoRealEnvidoFaltaEnvidoNoQuerido() {
		estado = new Envido(estado, estadoFlor);
		estado = new RealEnvido(estado, estadoFlor);
		estado = new FaltaEnvido(estado, estadoFlor, 30, 30);

		assertEquals(5, estado.obtenerPuntosNoQueridos());
	}

	@Test
	public void testEnvidoEnvidoRealEnvidoFaltaEnvidoNoQuerido() {
		estado = new Envido(estado, estadoFlor);
		estado = new Envido(estado, estadoFlor);
		estado = new RealEnvido(estado, estadoFlor);
		estado = new FaltaEnvido(estado, estadoFlor, 30, 30);

		assertEquals(7, estado.obtenerPuntosNoQueridos());
	}

	@Test(expected = NoRespetaJerarquiaDeEnvidoError.class)
	public void testFaltaEnvidoFaltaEnvidoNoVale() {
		estado = new FaltaEnvido(estado, estadoFlor, 30, 30);
		estado = new FaltaEnvido(estado, estadoFlor, 30, 30);
	}
}
