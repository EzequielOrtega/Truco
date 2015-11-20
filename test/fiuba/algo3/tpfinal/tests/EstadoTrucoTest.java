package fiuba.algo3.tpfinal.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.tpfinal.modelo.error.NoRespetaJerarquiaDeTrucoError;
import fiuba.algo3.tpfinal.modelo.truco.EstadoInicialTruco;
import fiuba.algo3.tpfinal.modelo.truco.EstadoTruco;
import fiuba.algo3.tpfinal.modelo.truco.ReTruco;
import fiuba.algo3.tpfinal.modelo.truco.Truco;
import fiuba.algo3.tpfinal.modelo.truco.ValeCuatro;

public class EstadoTrucoTest {
	
	private EstadoTruco estadoAnterior;

	@Before
	public void setUp() {
		estadoAnterior = new EstadoInicialTruco();
	}
	@Test
	public void testTruco() {
		estadoAnterior = new Truco(estadoAnterior);
		
		Assert.assertEquals(2, estadoAnterior.obtenerPuntosQueridos());
		Assert.assertEquals(1, estadoAnterior.obtenerPuntosNoQueridos());
	}
	
	@Test
	public void testTrucoReTruco() {
		estadoAnterior = new Truco(estadoAnterior);
		estadoAnterior = new ReTruco(estadoAnterior);
		
		Assert.assertEquals(3, estadoAnterior.obtenerPuntosQueridos());
		Assert.assertEquals(2, estadoAnterior.obtenerPuntosNoQueridos());
	}
	
	@Test
	public void testTrucoReTrucoValeCuatro() {
		estadoAnterior = new Truco(estadoAnterior);
		estadoAnterior = new ReTruco(estadoAnterior);
		estadoAnterior = new ValeCuatro(estadoAnterior);
		
		Assert.assertEquals(4, estadoAnterior.obtenerPuntosQueridos());
		Assert.assertEquals(3, estadoAnterior.obtenerPuntosNoQueridos());
	}

	@Test (expected = NoRespetaJerarquiaDeTrucoError.class)
	public void testReTrucoNoValeAntesDeTruco() {
		estadoAnterior = new ReTruco(estadoAnterior);
	}
	
	@Test (expected = NoRespetaJerarquiaDeTrucoError.class)
	public void testValeCuatroNoValeAntesDeTruco() {
		estadoAnterior = new ValeCuatro(estadoAnterior);
	}
	
	@Test (expected = NoRespetaJerarquiaDeTrucoError.class)
	public void testValeCuatroNoValeAntesDeReTruco() {
		estadoAnterior = new Truco(estadoAnterior);
		estadoAnterior = new ValeCuatro(estadoAnterior);
	}
	
	@Test (expected = NoRespetaJerarquiaDeTrucoError.class)
	public void testTrucoTrucoNoVale() {
		estadoAnterior = new Truco(estadoAnterior);
		estadoAnterior = new Truco(estadoAnterior);
	}
}
