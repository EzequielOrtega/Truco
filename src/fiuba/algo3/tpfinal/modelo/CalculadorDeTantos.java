package fiuba.algo3.tpfinal.modelo;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class CalculadorDeTantos {
	
	public boolean tieneFlor(Vector<Carta> cartas){
		if(cartas.size()<3){
			throw new SoloSePuedeCantarEnPrimeraError();
		}
		return cartas.get(0).mismoPaloQue(cartas.get(1), cartas.get(2));
	}
	
	
	public int obtenerTantosDeFlor(Vector<Carta> cartas){
		if (this.tieneFlor(cartas)){
			int tantosDeFlor = 20;
			for (Carta carta: cartas){
				CartaParaEnvidoYFlor cartaActual = (CartaParaEnvidoYFlor) carta;
				tantosDeFlor += cartaActual.obtenerValorParaEnvidoYFlor();
			}
			return tantosDeFlor;
		}else{
			throw new JugadorNoTieneFlorError();
		}
	}
	
	public int obtenerTantosDeEnvido(Vector<Carta> cartas){
		if (cartas.size()<3){
			throw new SoloSePuedeCantarEnPrimeraError();
		}
        List<Integer> valores = new LinkedList<Integer>();

        for (Carta carta: cartas) {
            CartaParaEnvidoYFlor cadaCarta = (CartaParaEnvidoYFlor) carta;
            valores.add(cadaCarta.obtenerValorParaEnvidoYFlor());
        }
		int tantosDeEnvido = 0;
		if (this.esEnvidoDeTresCartas(cartas)){
			tantosDeEnvido = this.calcularEnvidoDeTresCartas(valores);
		}else if(this.esEnvidoDeDosCartas(cartas)){
			tantosDeEnvido = this.calcularEnvidoDeDosCartas(cartas, valores);
		}else{
			tantosDeEnvido = this.calcularEnvidoDeUnaCarta(valores);
		}
		return tantosDeEnvido;
		
	}


	private int calcularEnvidoDeTresCartas(List<Integer> valores) {
        Collections.sort(valores);
		int tantosDeEnvido = 20 + valores.get(1) + valores.get(2) + valores.get(0);
		return tantosDeEnvido;
	}

	private int calcularEnvidoDeDosCartas(Vector<Carta> cartas, List<Integer> valores) {
		int tantosDeEnvido = 20;
		if(cartas.get(0).mismoPaloQue(cartas.get(1))) {
			tantosDeEnvido += valores.get(0) + valores.get(1);
		}else if(cartas.get(1).mismoPaloQue(cartas.get(2))){
            tantosDeEnvido += valores.get(1) + valores.get(2);
		}else{
            tantosDeEnvido += valores.get(0) + valores.get(3);
		}
		return tantosDeEnvido;
	}


	private int calcularEnvidoDeUnaCarta(List<Integer> valores) {
        return Collections.max(valores);
	}


	private boolean esEnvidoDeDosCartas(Vector<Carta> cartas) {
		return (cartas.get(0).mismoPaloQue(cartas.get(1)) ||
				cartas.get(1).mismoPaloQue(cartas.get(2)) ||
				cartas.get(2).mismoPaloQue(cartas.get(0)));
	}


	private boolean esEnvidoDeTresCartas(Vector<Carta> cartas) {
		return(cartas.get(0).mismoPaloQue(cartas.get(1), cartas.get(2)));
	}

    /* public int obtenerTantosDeEnvido(Vector<Carta> cartas){
        if (cartas.size()<3){
            throw new SoloSePuedeCantarEnPrimeraError();
        }
        List<Integer> valores = new LinkedList<Integer>();
        int tantosDeEnvido = 0;
        for (Carta carta: cartas) {
            CartaParaEnvidoYFlor cadaCarta = (CartaParaEnvidoYFlor) carta;
            valores.add(cadaCarta.obtenerValorParaEnvidoYFlor());
        }

        // Caso EnvidoDeTresCartas
        if (cartas.get(0).mismoPaloQue(cartas.get(1), cartas.get(2))){
            Collections.sort(valores);
            tantosDeEnvido = 20 + valores.get(1) + valores.get(2);
        }
        // Caso EnvidoDeDosCartas (si encontramos el iterador circular, esto se acorta a un for con un if)
        if(cartas.get(0).mismoPaloQue(cartas.get(1))) {
            tantosDeEnvido = 20 + valores.get(0) + valores.get(1);
        }
        if(cartas.get(1).mismoPaloQue(cartas.get(2))) {
            tantosDeEnvido = 20 + valores.get(1) + valores.get(2);
        }
        if(cartas.get(2).mismoPaloQue(cartas.get(0))) {
            tantosDeEnvido = 20 + valores.get(2) + valores.get(0);
        }

        // Caso EnvidoDeUnaCarta
        else{ tantosDeEnvido = Collections.max(valores); }
        return tantosDeEnvido;
    } */

}
