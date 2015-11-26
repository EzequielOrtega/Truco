package fiuba.algo3.tpfinal.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.tpfinal.modelo.error.NoRespetaJerarquiaDeFlorError;
import fiuba.algo3.tpfinal.modelo.error.NoSePuedeRechazarFlorError;
import fiuba.algo3.tpfinal.modelo.error.SoloSePuedeCantarFlorUnaVezPorRondaError;
import fiuba.algo3.tpfinal.modelo.flor.ContraFlor;
import fiuba.algo3.tpfinal.modelo.flor.ContraFlorAlResto;
import fiuba.algo3.tpfinal.modelo.flor.EstadoFinalFlor;
import fiuba.algo3.tpfinal.modelo.flor.EstadoFlor;
import fiuba.algo3.tpfinal.modelo.flor.EstadoInicialFlor;
import fiuba.algo3.tpfinal.modelo.flor.Flor;

public class EstadoFlorTest {

	private EstadoFlor estado;

	@Before
	public void setUp() {
		estado = new EstadoInicialFlor();
	}

	@Test(expected = NoSePuedeRechazarFlorError.class)
	public void testFlorNoSePuedeRechazar() {
		estado = new Flor(estado);
		estado.obtenerPuntosNoQueridos();
	}

	@Test
	public void testFlorSimpleDaTresPuntos() {
		estado = new Flor(estado);
		assertEquals(3, estado.obtenerPuntosQueridos());
	}

	@Test
	public void testContraFlorDaSeisPuntos() {
		estado = new Flor(estado);
		estado = new ContraFlor(estado);
		assertEquals(6, estado.obtenerPuntosQueridos());
	}

	@Test
	public void testFlorContraFlorQueridaDaSeisPuntos() {
		estado = new Flor(estado);
		estado = new ContraFlor(estado);
		assertEquals(6, estado.obtenerPuntosQueridos());
	}

	@Test
	public void testFlorContraFlorNoQueridaDaCuatroPuntos() {
		estado = new Flor(estado);
		estado = new ContraFlor(estado);
		assertEquals(4, estado.obtenerPuntosNoQueridos());
	}

	@Test(expected = NoRespetaJerarquiaDeFlorError.class)
	public void testContraFlorDirectoLanzaError() {
		estado = new ContraFlor(estado);
	}

	@Test(expected = NoRespetaJerarquiaDeFlorError.class)
	public void testContraFlorAlRestoDirectoLanzaError() {
		estado = new ContraFlorAlResto(estado, 30);
	}

	@Test
	public void testFlorContraFlorContraFlorAlRestoNoQueridaDaSeisPuntos() {
		estado = new Flor(estado);
		estado = new ContraFlor(estado);
		estado = new ContraFlorAlResto(estado, 30);
		assertEquals(6, estado.obtenerPuntosNoQueridos());
	}

	@Test(expected = SoloSePuedeCantarFlorUnaVezPorRondaError.class)
	public void testCantarFlorLuegoDelEstadoFinalLanzaError() {
		estado = new EstadoFinalFlor(estado);
		estado = new Flor(estado);
	}

	@Test
	public void testFlorContraFlorAlResto() {
		estado = new Flor(estado);
		estado = new ContraFlorAlResto(estado, 30);

		assertEquals(3, estado.obtenerPuntosNoQueridos());
	}
}
