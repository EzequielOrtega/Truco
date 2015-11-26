package fiuba.algo3.tpfinal.modelo;

import fiuba.algo3.tpfinal.modelo.error.SoloSePuedeCantarEnvidoEnPrimeraError;

import java.util.Vector;

public class CalculadorDeEnvidoYFlor {

	public boolean tieneFlor(Vector<Carta> cartas){
		if(cartas.size()<3){
			throw new SoloSePuedeCantarEnvidoEnPrimeraError();
		}
		return cartas.get(0).mismoPaloQue(cartas.get(1), cartas.get(2));
	}


	public int obtenerTantosDeFlor(Vector<Carta> cartas){

		if (this.tieneFlor(cartas)) {
			int tantosDeFlor = 20;
			for (Carta carta: cartas) {
				CartaParaEnvidoYFlor cartaActual = (CartaParaEnvidoYFlor) carta;
				tantosDeFlor += cartaActual.obtenerValorParaEnvidoYFlor();
			}
			return tantosDeFlor;
		}else{
			//throw new JugadorNoTieneFlorError();
			return -1;
		}
	}

	public int obtenerTantosDeEnvido(Vector<Carta> cartas) {

		if (cartas.size()<3){
			throw new SoloSePuedeCantarEnvidoEnPrimeraError();
		}

		int envidoMaximo;

		envidoMaximo = maximo(this.calcularEnvidoDeDosCartas(cartas.get(0), cartas.get(1)),
				this.calcularEnvidoDeDosCartas(cartas.get(1), cartas.get(2)),
				this.calcularEnvidoDeDosCartas(cartas.get(0), cartas.get(2)));

		return envidoMaximo;

	}

	private int calcularEnvidoDeDosCartas(Carta carta1, Carta carta2) {
		int valorEnvidoDeDosCartas;
		CartaParaEnvidoYFlor interfazCarta1 = (CartaParaEnvidoYFlor) carta1;
		CartaParaEnvidoYFlor interfazCarta2 = (CartaParaEnvidoYFlor) carta2;

		if (carta1.mismoPaloQue(carta2)) {
			valorEnvidoDeDosCartas = (20 + interfazCarta1.obtenerValorParaEnvidoYFlor() + interfazCarta2.obtenerValorParaEnvidoYFlor());
		} else {
			valorEnvidoDeDosCartas = Math.max (interfazCarta1.obtenerValorParaEnvidoYFlor(), interfazCarta2.obtenerValorParaEnvidoYFlor());
		}
		return valorEnvidoDeDosCartas;
	}

	private int maximo(int integer1, int integer2, int integer3) {
		return Math.max (Math.max(integer1, integer2), integer3);
	}

}
