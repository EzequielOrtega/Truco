package fiuba.algo3.tpfinal.modelo.envido;

public abstract class EstadoEnvido {
	
	protected EstadoEnvido estadoAnterior;
	protected int puntosDeEstado;
	
	public EstadoEnvido(EstadoEnvido estadoAnterior) {
//		if (estadoAnterior.getClass() == EstadoFinalEnvido.class) {
//			throw new SoloSePuedeCantarEnvidoUnaVezPorRonda();
//		}
		this.estadoAnterior = estadoAnterior;
	}

	public int obtenerPuntosQueridos() {
		return (this.puntosDeEstado + estadoAnterior.obtenerPuntosQueridos());
	}

	public int obtenerPuntosNoQueridos() {
		if (estadoAnterior.obtenerPuntosQueridos() == 0) {
			return (estadoAnterior.obtenerPuntosNoQueridos());
		} else {
			return (estadoAnterior.obtenerPuntosQueridos());
		}
	}

	public int getPuntosDeEstado() {
		return this.puntosDeEstado;
	}
}
