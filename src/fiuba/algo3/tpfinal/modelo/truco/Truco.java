package fiuba.algo3.tpfinal.modelo.truco;

import java.util.LinkedList;

public class Truco extends EstadoTruco {

    private static LinkedList<Object> estadosAceptados;

    static {
        estadosAceptados = new LinkedList<>();
        estadosAceptados.add(EstadoInicialTruco.class);
    }

    public Truco(EstadoTruco estadoAnterior) {
		super(estadoAnterior, estadosAceptados);
	}

	public int obtenerPuntosQueridos() {
		return 2;
	}

	public int obtenerPuntosNoQueridos() {
		return 1;
	}
}
