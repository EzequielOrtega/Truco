package fiuba.algo3.tpfinal.modelo;

public class RealEnvido extends EstadoEnvido {

	public RealEnvido(EstadoEnvido estadoAnterior) {
		super(estadoAnterior);
		this.puntosDeEstado = 3;
		if (estadoAnterior.getPuntosDeEstado() >= this.puntosDeEstado) {
			throw new NoRespetaJerarquiaDeEnvidoError();
		}
	}
}
