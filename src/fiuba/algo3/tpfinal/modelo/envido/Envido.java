package fiuba.algo3.tpfinal.modelo.envido;

import fiuba.algo3.tpfinal.modelo.error.NoRespetaJerarquiaDeEnvidoError;
import fiuba.algo3.tpfinal.modelo.error.SoloSePuedeCantarEnvidoUnaVezPorRonda;

public class Envido extends EstadoEnvido {
	
	public Envido(EstadoEnvido estadoAnterior) {
		super(estadoAnterior);
		if (estadoAnterior.getPuntosDeEstado() == -1) {
			throw new SoloSePuedeCantarEnvidoUnaVezPorRonda();
		}
		this.puntosDeEstado = 2;
		if ((estadoAnterior.getPuntosDeEstado() > this.puntosDeEstado) || (estadoAnterior.obtenerPuntosQueridos() == 4)) {
			throw new NoRespetaJerarquiaDeEnvidoError();
		}
	}
}
