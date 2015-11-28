package fiuba.algo3.tpfinal.modelo.envido;

import fiuba.algo3.tpfinal.modelo.flor.EstadoFinalFlor;
import fiuba.algo3.tpfinal.modelo.flor.EstadoFlor;
import fiuba.algo3.tpfinal.modelo.flor.EstadoInicialFlor;

import java.util.LinkedList;

public class FaltaEnvido extends EstadoEnvido {

	private static LinkedList<Object> estadosAceptados;
	private int puntosRestantesContrario;
	private int puntosRestantes;
	
	static {
		estadosAceptados = new LinkedList<>();
		estadosAceptados.add(EstadoInicialFlor.class);
		estadosAceptados.add(EstadoFinalFlor.class);
	}
	
	public FaltaEnvido(EstadoEnvido estadoAnterior, EstadoFlor estadoFlor, int puntosRestantesContrario, int puntosRestantes) {
		super(estadoAnterior, estadoFlor, estadosAceptados);
		this.puntosRestantesContrario = puntosRestantesContrario;
		this.puntosRestantes = puntosRestantes;
	}
	
	public int obtenerPuntosQueridos() {
		int puntosQueridos;
		if (this.puntosRestantes > 15) {
			puntosQueridos = this.puntosRestantes - 15;
		} else {
			puntosQueridos = this.puntosRestantesContrario;
		}
		return puntosQueridos;
	}
}
