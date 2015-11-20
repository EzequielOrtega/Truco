package fiuba.algo3.tpfinal.modelo;

import java.util.LinkedList;

import fiuba.algo3.tpfinal.modelo.error.NoHayGanadorDeRondaInconclusaError;
import fiuba.algo3.tpfinal.modelo.error.YaSeJugaronLasTresManosError;

public class Ronda {
	private LinkedList<Resultado> resultados;
	
	public Ronda(){
		this.resultados = new LinkedList<Resultado>();
	}
	
	public Boolean estaVacia(){
		return (this.resultados.size() == 0);
	}
	
	public Integer tamanio(){
		return this.resultados.size();
	}

	public void insercion(Resultado resultado) {
		if(this.tamanio() == 3){
			throw new YaSeJugaronLasTresManosError();
		}
		this.resultados.add(resultado);
	}
	
	public Boolean concluyoLaRonda(){
		Boolean concluyoLaRonda = false;
		if(this.tamanio() == 3){
			concluyoLaRonda = true;
		}else if(this.tamanio() == 2){
			Boolean huboEmpate = false;
			for(Resultado resultadoMano : this.resultados){
				if(resultadoMano == Resultado.EMPATE){
					huboEmpate = true;
				}
			}
			concluyoLaRonda = huboEmpate;
		}
		return concluyoLaRonda;
	}
	
	//devuelve null en caso de haber empatado las tres manos
	public Jugador ganadorDeLaRonda(LinkedList<Jugador> jugadores){
		if(!this.concluyoLaRonda()){
			throw new NoHayGanadorDeRondaInconclusaError();
		}
		Jugador ganador = null;
		//falta resolver
		return ganador;
	}
	
	
}
