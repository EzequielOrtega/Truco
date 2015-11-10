package fiuba.algo3.tpfinal.modelo;

import java.util.Vector;

public class CalculadorDeTantos {
	
	public boolean tieneFlor(Vector<Carta> cartas){
		if(cartas.size()<3){
			throw new SoloSePuedeCantarEnPrimeraError();
		}
		Boolean tieneFlor = false;
		for(Integer x = 0; x<2; x++){
			Carta cartaActual = cartas.elementAt(x);
			Carta cartaSiguiente = cartas.elementAt(x+1);
			if(cartaActual.getPalo()==cartaSiguiente.getPalo()){
				tieneFlor = true;
			} else {
				tieneFlor = false;
			}
		}
		return tieneFlor;
	}
	
	
	public Integer obtenerTantosDeFlor(Vector<Carta> cartas){
		if (this.tieneFlor(cartas)){
			Integer tantosDeFlor = 20;
			for (Carta carta: cartas){
				ValorDeCartaParaEnvidoYFlor cartaActual = (ValorDeCartaParaEnvidoYFlor) carta;
				tantosDeFlor = tantosDeFlor + cartaActual.obtenerValorParaEnvidoYFlor();
			}
			return tantosDeFlor;
		}else{
			throw new JugadorNoTieneFlorError();
		}
	}
	
	public Integer obtenerTantosDeEnvido(Vector<Carta> cartas){
		if (cartas.size()<3){
			throw new SoloSePuedeCantarEnPrimeraError();
		}
		Integer tantosDeEnvido;
		if (this.esEnvidoDeTresCartas(cartas)){
			tantosDeEnvido = this.calcularEnvidoDeTresCartas(cartas);
		}else if(this.esEnvidoDeDosCartas(cartas)){
			tantosDeEnvido = this.calcularEnvidoDeDosCartas(cartas);
		}else{
			tantosDeEnvido = this.calcularEnvidoDeUnaCarta(cartas);
		}
		return tantosDeEnvido;
		
	}


	private Integer calcularEnvidoDeTresCartas(Vector<Carta> cartas) {
		Integer tantosDeEnvido = 20;
		ValorDeCartaParaEnvidoYFlor carta1 = (ValorDeCartaParaEnvidoYFlor) cartas.elementAt(0);
		ValorDeCartaParaEnvidoYFlor carta2 = (ValorDeCartaParaEnvidoYFlor) cartas.elementAt(1);
		ValorDeCartaParaEnvidoYFlor carta3 = (ValorDeCartaParaEnvidoYFlor) cartas.elementAt(2);
		Integer valor1 = carta1.obtenerValorParaEnvidoYFlor();
		Integer valor2 = carta2.obtenerValorParaEnvidoYFlor();
		Integer valor3 = carta3.obtenerValorParaEnvidoYFlor();
		tantosDeEnvido += this.sumarLosDosValoresMasAltos(valor1, valor2, valor3);	
		return tantosDeEnvido;
	}


	private Integer sumarLosDosValoresMasAltos(Integer valor1, Integer valor2, Integer valor3) {
		Integer suma = 0;
		int[] valores = new int[3];
		valores[0] = valor1;
		valores[1] = valor2;
		valores[2] = valor3;
		this.ordenarAscendentemente(valores);
		suma += valores[1];
		suma += valores[2];
		return suma;
	}


	private void ordenarAscendentemente(int[] valores) {
		int aux;
		 
	    for (int i = 0; i < valores.length - 1; i++) {
	        for (int x = i + 1; x < valores.length; x++) {
	            if (valores[x] < valores[i]) {
	                aux = valores[i];
	                valores[i] = valores[x];
	                valores[x] = aux;
	            }
	        }
	    }
	}


	private Integer calcularEnvidoDeDosCartas(Vector<Carta> cartas) {
		Integer tantosDeEnvido = 20;
		ValorDeCartaParaEnvidoYFlor carta1 = (ValorDeCartaParaEnvidoYFlor) cartas.elementAt(0);
		ValorDeCartaParaEnvidoYFlor carta2 = (ValorDeCartaParaEnvidoYFlor) cartas.elementAt(1);
		ValorDeCartaParaEnvidoYFlor carta3 = (ValorDeCartaParaEnvidoYFlor) cartas.elementAt(2);
		if(cartas.elementAt(0).getPalo() == cartas.elementAt(1).getPalo()) {
			tantosDeEnvido += carta1.obtenerValorParaEnvidoYFlor();
			tantosDeEnvido += carta2.obtenerValorParaEnvidoYFlor();
		}else if(cartas.elementAt(1).getPalo() == cartas.elementAt(2).getPalo()){
			tantosDeEnvido += carta2.obtenerValorParaEnvidoYFlor();
			tantosDeEnvido += carta3.obtenerValorParaEnvidoYFlor();
		}else{
			tantosDeEnvido += carta1.obtenerValorParaEnvidoYFlor();
			tantosDeEnvido += carta3.obtenerValorParaEnvidoYFlor();
		}
		return tantosDeEnvido;
	}


	private Integer calcularEnvidoDeUnaCarta(Vector<Carta> cartas) {
		ValorDeCartaParaEnvidoYFlor carta1 = (ValorDeCartaParaEnvidoYFlor) cartas.elementAt(0);
		ValorDeCartaParaEnvidoYFlor carta2 = (ValorDeCartaParaEnvidoYFlor) cartas.elementAt(1);
		ValorDeCartaParaEnvidoYFlor carta3 = (ValorDeCartaParaEnvidoYFlor) cartas.elementAt(2);
		Integer valor1 = carta1.obtenerValorParaEnvidoYFlor();
		Integer valor2 = carta2.obtenerValorParaEnvidoYFlor();
		Integer valor3 = carta3.obtenerValorParaEnvidoYFlor();
		if((valor1 > valor2) && (valor1 > valor3)){
			return valor1;
		} else if ((valor2 > valor1) && (valor2 > valor3)){
			return valor2;
		}else{
			return valor3;
		}
	}


	private boolean esEnvidoDeDosCartas(Vector<Carta> cartas) {
		Boolean comparacion1 = cartas.elementAt(0).getPalo() == cartas.elementAt(1).getPalo();
		Boolean comparacion2 = cartas.elementAt(1).getPalo() == cartas.elementAt(2).getPalo();
		Boolean esEnvidoDeDos = comparacion1 || comparacion2;
		return esEnvidoDeDos;
	}


	private boolean esEnvidoDeTresCartas(Vector<Carta> cartas) {
		Boolean comparacion1 = cartas.elementAt(0).getPalo() == cartas.elementAt(1).getPalo();
		Boolean comparacion2 = cartas.elementAt(1).getPalo() == cartas.elementAt(2).getPalo();
		Boolean esEnvidoDeTres = comparacion1 && comparacion2;
		return esEnvidoDeTres;
	}
}
