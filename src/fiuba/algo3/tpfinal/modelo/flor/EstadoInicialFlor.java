package fiuba.algo3.tpfinal.modelo.flor;

public class EstadoInicialFlor extends EstadoFlor {

	public EstadoInicialFlor() {
		super();
		this.puntosDeEstado = 0;
	}

	@Override
	public int obtenerPuntosQueridos() {
		return puntosDeEstado;
	}
}
