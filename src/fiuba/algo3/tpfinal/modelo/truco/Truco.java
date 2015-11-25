package fiuba.algo3.tpfinal.modelo.truco;

import fiuba.algo3.tpfinal.modelo.envido.*;
import fiuba.algo3.tpfinal.modelo.flor.*;

import java.util.LinkedList;

public class Truco extends EstadoTruco {

    private static LinkedList<Object> estadosAceptados;

    static {
        estadosAceptados = new LinkedList<>();
        estadosAceptados.add(EstadoInicialTruco.class);
        estadosAceptados.add(EstadoInicialEnvido.class);
        estadosAceptados.add(EstadoFinalEnvido.class);
        estadosAceptados.add(EstadoInicialFlor.class);
        estadosAceptados.add(EstadoFinalFlor.class);
    }

    public Truco(EstadoTruco estadoAnterior, EstadoEnvido envidoAnterior, EstadoFlor florAnterior) {
		super(estadoAnterior, envidoAnterior, florAnterior, estadosAceptados);
	}

	public int obtenerPuntosQueridos() {
		return 2;
	}

	public int obtenerPuntosNoQueridos() {
		return 1;
	}
}
