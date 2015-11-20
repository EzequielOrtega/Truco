package fiuba.algo3.tpfinal.modelo;

public class Envido extends EstadoEnvido {
	
	public Envido(EstadoEnvido estadoAnterior) {
		super(estadoAnterior);
		this.puntosDeEstado = 2;
		if ((estadoAnterior.getPuntosDeEstado() > this.puntosDeEstado) || (estadoAnterior.obtenerPuntosQueridos() == 4)) {
			throw new NoRespetaJerarquiaDeEnvidoError();
		}
	}
}