package fiuba.algo3.tpfinal.modelo;

import java.util.LinkedList;

import fiuba.algo3.tpfinal.modelo.error.NoHayGanadorDeRondaInconclusaError;
import fiuba.algo3.tpfinal.modelo.error.YaSeJugaronLasTresManosError;
//import fiuba.algo3.tpfinal.modelo.ronda.EstadoInicialRonda;
//import fiuba.algo3.tpfinal.modelo.ronda.EstadoRonda;
//import fiuba.algo3.tpfinal.modelo.ronda.Primera;

public class Ronda {
	private LinkedList<Resultado> resultados;
//	private EstadoRonda estadoRonda = new EstadoInicialRonda();
	
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
/*		switch (this.tamanio()) {
		case 0: { this.estadoRonda = new Primera(this.estadoRonda); }
		case 1: { this.estadoRonda = new Segunda(this.estadoRonda); }
		case 2: { this.estadoRonda = new Tercera(this.estadoRonda); }
		case 3: { throw new YaSeJugaronLasTresManosError(); }
		}*/
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
		
		return ganador;
	}	
}
