package fiuba.algo3.tpfinal.modelo;

public abstract class EstadoEnvido {
	
	protected EstadoEnvido estadoAnterior;
	protected int puntosDeEstado;
	
	public EstadoEnvido(EstadoEnvido estadoAnterior) {
		this.estadoAnterior = estadoAnterior;
	}

	public int obtenerPuntosQueridos() {
		return this.puntosDeEstado + estadoAnterior.obtenerPuntosQueridos();
	}

	public int obtenerPuntosNoQueridos() {
		if (estadoAnterior.obtenerPuntosNoQueridos() == 0) {
			return 1;
		} else {
			return this.estadoAnterior.obtenerPuntosQueridos();
		}
	}

	public int getPuntosDeEstado() {
		return this.puntosDeEstado;
	}
}
