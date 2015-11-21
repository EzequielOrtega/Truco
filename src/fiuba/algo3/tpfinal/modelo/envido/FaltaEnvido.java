package fiuba.algo3.tpfinal.modelo.envido;

import fiuba.algo3.tpfinal.modelo.error.SoloSePuedeCantarEnvidoUnaVezPorRonda;

public class FaltaEnvido extends EstadoEnvido {

	public FaltaEnvido(EstadoEnvido estadoAnterior) {
		super(estadoAnterior);
		if (estadoAnterior.getPuntosDeEstado() == -1) {
			throw new SoloSePuedeCantarEnvidoUnaVezPorRonda();
		}
	}
}
