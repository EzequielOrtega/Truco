package fiuba.algo3.tpfinal.modelo.truco;

public class EstadoInicialTruco extends EstadoTruco {
	public EstadoInicialTruco() {
		super();
	}

	@Override
	public int obtenerPuntosQueridos() {
		return 1;
	}

	@Override
	public int obtenerPuntosNoQueridos() {
		return 0;
	}
}
