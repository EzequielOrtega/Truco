package fiuba.algo3.tpfinal.modelo.envido;

public class EstadoInicialEnvido extends EstadoEnvido {

	public EstadoInicialEnvido() {
		super(null);
		this.puntosDeEstado = 0;
	}

	@Override
	public int obtenerPuntosQueridos() {
		return puntosDeEstado;
	}

	@Override
	public int obtenerPuntosNoQueridos() {
		return 1;
	}

}
