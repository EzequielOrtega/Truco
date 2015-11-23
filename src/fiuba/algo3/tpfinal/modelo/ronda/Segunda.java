package fiuba.algo3.tpfinal.modelo.ronda;

import java.util.LinkedList;

import fiuba.algo3.tpfinal.modelo.Jugador;
import fiuba.algo3.tpfinal.modelo.Resultado;
import fiuba.algo3.tpfinal.modelo.error.NoHayGanadorDeRondaInconclusaError;

public class Segunda extends EstadoRonda {
	
	private Resultado resultadoDeSegunda;
	
	public Segunda(EstadoRonda estadoAnterior) {
		super(estadoAnterior);		
	}
	
	public Segunda(EstadoRonda estadoAnterior, Resultado resultadoDeSegunda) {
		super(estadoAnterior);
		this.resultadoDeSegunda = resultadoDeSegunda;
	}

	@Override
	public Boolean concluyoRonda() {
		Boolean concluyoLaRonda = false;
		if((this.estadoAnterior.obtenerResultadoDeMano() == Resultado.EMPATE) && (resultadoDeSegunda != Resultado.EMPATE)){
			concluyoLaRonda = true;
		}
		if((this.estadoAnterior.obtenerResultadoDeMano() != Resultado.EMPATE) && (resultadoDeSegunda == Resultado.EMPATE)){
			concluyoLaRonda = true;
		}
		if((this.estadoAnterior.obtenerResultadoDeMano() == resultadoDeSegunda) && (resultadoDeSegunda != Resultado.EMPATE)){
			concluyoLaRonda = true;
		}
		return concluyoLaRonda;
	}

	@Override
	public Jugador obtenerGanadorDeLaRonda(LinkedList<Jugador> jugadores) {
		if(!this.concluyoRonda()){
			throw new NoHayGanadorDeRondaInconclusaError();
		}
		Jugador ganador = null;
		switch(this.obtenerGanadorParcial()){
			case GANADOR1:{
				ganador = jugadores.get(0);
				break;
			}
			case GANADOR2:{
				ganador = jugadores.get(1);
				break;
			}
			case EMPATE: {break;}
		}
		return ganador;
	}

	public Resultado obtenerResultadoDeMano() {
		return resultadoDeSegunda;
	}

	public void cambiarResultadoDeSegunda(Resultado resultadoDeSegunda) {
		this.resultadoDeSegunda = resultadoDeSegunda;
	}

	@Override
	protected Resultado obtenerGanadorParcial() {
		Resultado resultadoParcial = null;
		if((estadoAnterior.obtenerResultadoDeMano() == resultadoDeSegunda) &&(resultadoDeSegunda != Resultado.EMPATE)){
			resultadoParcial = resultadoDeSegunda;
		}else if((estadoAnterior.obtenerResultadoDeMano() != Resultado.EMPATE) && (resultadoDeSegunda == Resultado.EMPATE)){
			resultadoParcial = estadoAnterior.obtenerResultadoDeMano();
		}else if((estadoAnterior.obtenerResultadoDeMano() == Resultado.EMPATE) && (resultadoDeSegunda != Resultado.EMPATE)){
			resultadoParcial = resultadoDeSegunda;
		}else{
			resultadoParcial = Resultado.EMPATE;
		}
		return resultadoParcial;
	}

}
