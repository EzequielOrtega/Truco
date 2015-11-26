package fiuba.algo3.tpfinal.modelo.ronda;

import java.util.LinkedList;

import fiuba.algo3.tpfinal.modelo.Jugador;
import fiuba.algo3.tpfinal.modelo.Resultado;
import fiuba.algo3.tpfinal.modelo.error.NoHayGanadorDeRondaInconclusaError;

public class Primera extends EstadoRonda {

	private Resultado resultadoDePrimera;
	private static LinkedList<Object> estadosAceptados;

	static {
		estadosAceptados = new LinkedList<>();
		estadosAceptados.add(EstadoInicialRonda.class);
	}
	
	public Primera(EstadoRonda estadoAnterior) {
		super(estadoAnterior, estadosAceptados);
		this.mano = Mano.PRIMERA;
	}

	public Primera(EstadoRonda estadoAnterior, Resultado resultadoDePrimera) {
		super(estadoAnterior, estadosAceptados);
		this.resultadoDePrimera = resultadoDePrimera;
		this.mano = Mano.PRIMERA;
	}

	@Override
	public Boolean concluyoRonda() {

		return false;
	}

	@Override
	public Jugador obtenerGanadorDeLaRonda(LinkedList<Jugador> jugadores) {
		if (!this.concluyoRonda()) {
			throw new NoHayGanadorDeRondaInconclusaError();
		}
		return null;
	}

	public Resultado obtenerResultadoDeMano() {
		return resultadoDePrimera;
	}

	public void cambiarResultadoDeMano(Resultado resultadoDePrimera) {
		this.resultadoDePrimera = resultadoDePrimera;
	}

	@Override
	protected Resultado obtenerGanadorParcial() {

		return null;
	}

	@Override
	protected Resultado obtenerResultadoDePrimera() {

		return resultadoDePrimera;
	}

}