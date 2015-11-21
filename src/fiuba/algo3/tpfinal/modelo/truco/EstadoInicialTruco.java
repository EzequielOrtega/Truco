package fiuba.algo3.tpfinal.modelo.truco;

public class EstadoInicialTruco extends EstadoTruco {

	@Override
	public int obtenerPuntosQueridos() {
		return 1;
	}

	@Override
	public int obtenerPuntosNoQueridos() {
		return 0;
	}
}
