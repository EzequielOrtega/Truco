package fiuba.algo3.tpfinal.tests;

import fiuba.algo3.tpfinal.modelo.envido.EstadoEnvido;
import fiuba.algo3.tpfinal.modelo.envido.EstadoInicialEnvido;
import fiuba.algo3.tpfinal.modelo.error.NoRespetaJerarquiaDeTrucoError;
import fiuba.algo3.tpfinal.modelo.flor.EstadoFlor;
import fiuba.algo3.tpfinal.modelo.flor.EstadoInicialFlor;
import fiuba.algo3.tpfinal.modelo.truco.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EstadoTrucoTest {

	private EstadoTruco estadoAnterior;
	private EstadoEnvido estadoActualEnvido;
	private EstadoFlor estadoActualFlor;

	@Before
	public void setUp() {
		estadoAnterior = new EstadoInicialTruco();
		estadoActualEnvido = new EstadoInicialEnvido();
		estadoActualFlor = new EstadoInicialFlor();
	}

	@Test
	public void testTruco() {
		estadoAnterior = new Truco(estadoAnterior, estadoActualEnvido, estadoActualFlor);

		assertEquals(2, estadoAnterior.obtenerPuntosQueridos());
		assertEquals(1, estadoAnterior.obtenerPuntosNoQueridos());
	}

	@Test
	public void testTrucoReTruco() {
		estadoAnterior = new Truco(estadoAnterior, estadoActualEnvido, estadoActualFlor);
		estadoAnterior = new ReTruco(estadoAnterior);

		assertEquals(3, estadoAnterior.obtenerPuntosQueridos());
		assertEquals(2, estadoAnterior.obtenerPuntosNoQueridos());
	}

	@Test
	public void testTrucoReTrucoValeCuatro() {
		estadoAnterior = new Truco(estadoAnterior, estadoActualEnvido, estadoActualFlor);
		estadoAnterior = new ReTruco(estadoAnterior);
		estadoAnterior = new ValeCuatro(estadoAnterior);

		assertEquals(4, estadoAnterior.obtenerPuntosQueridos());
		assertEquals(3, estadoAnterior.obtenerPuntosNoQueridos());
	}

	@Test(expected = NoRespetaJerarquiaDeTrucoError.class)
	public void testReTrucoNoValeAntesDeTruco() {
		estadoAnterior = new ReTruco(estadoAnterior);
	}

	@Test(expected = NoRespetaJerarquiaDeTrucoError.class)
	public void testValeCuatroNoValeAntesDeTruco() {
		estadoAnterior = new ValeCuatro(estadoAnterior);
	}

	@Test(expected = NoRespetaJerarquiaDeTrucoError.class)
	public void testValeCuatroNoValeAntesDeReTruco() {
		estadoAnterior = new Truco(estadoAnterior, estadoActualEnvido, estadoActualFlor);
		estadoAnterior = new ValeCuatro(estadoAnterior);
	}

	@Test(expected = NoRespetaJerarquiaDeTrucoError.class)
	public void testTrucoTrucoNoVale() {
		estadoAnterior = new Truco(estadoAnterior, estadoActualEnvido, estadoActualFlor);
		estadoAnterior = new Truco(estadoAnterior, estadoActualEnvido, estadoActualFlor);
	}
}
