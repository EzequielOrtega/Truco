package fiuba.algo3.tpfinal.modelo;

import java.util.Vector;

public class CalculadorDeTantos {
	
	public boolean tieneFlor(Vector<Carta> cartas){
		if(cartas.size()<3){
			throw new SoloSePuedeCantarEnPrimeraError();
		}
		return cartas.get(0).mismoPaloQue(cartas.get(1), cartas.get(2));
	}
	
	
	public int obtenerTantosDeFlor(Vector<Carta> cartas){
		
		if (this.tieneFlor(cartas)) {
			int envidoMaximo;
			CartaParaEnvidoYFlor interfazCarta1 = (CartaParaEnvidoYFlor) cartas.get(0);
			CartaParaEnvidoYFlor interfazCarta2 = (CartaParaEnvidoYFlor) cartas.get(1);
			CartaParaEnvidoYFlor interfazCarta3 = (CartaParaEnvidoYFlor) cartas.get(2);
	        
	        envidoMaximo = 20 + (interfazCarta1.obtenerValorParaEnvidoYFlor() +
	        					 interfazCarta2.obtenerValorParaEnvidoYFlor() +
	        					 interfazCarta3.obtenerValorParaEnvidoYFlor());
	        return envidoMaximo;
	        }  else {
	        		throw new JugadorNoTieneFlorError();
	        		}
	}
		// VERSION ANTERIOR
		/*
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
		*/
	
	public int obtenerTantosDeEnvido(Vector<Carta> cartas) {
		
		if (cartas.size()<3){
			throw new SoloSePuedeCantarEnPrimeraError();
		}
		
		int envidoMaximo;
		
        envidoMaximo = maximo(this.calcularEnvidoDeDosCartas(cartas.get(0), cartas.get(1)),
        				      this.calcularEnvidoDeDosCartas(cartas.get(1), cartas.get(2)),
        				      this.calcularEnvidoDeDosCartas(cartas.get(0), cartas.get(2)));
        
        return envidoMaximo;
        
	}
        // VERSION ANTERIOR
        /*
        List<Integer> valores = new LinkedList<Integer>();
        
        for (Carta carta: cartas) {
            CartaParaEnvidoYFlor cadaCarta = (CartaParaEnvidoYFlor) carta;
            valores.add(cadaCarta.obtenerValorParaEnvidoYFlor());
        }
        */
		/*
		int tantosDeEnvido = 0;
		if (this.esEnvidoDeTresCartas(cartas)){
			tantosDeEnvido = this.calcularEnvidoDeTresCartas(valores);
		}else if(this.esEnvidoDeDosCartas(cartas)){
			tantosDeEnvido = this.calcularEnvidoDeDosCartas(cartas, valores);
		}else{
			tantosDeEnvido = this.calcularEnvidoDeUnaCarta(valores);
		}
		return tantosDeEnvido;
		*/
		
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

	private int maximo(Integer integer1, Integer integer2, Integer integer3) {
		return (Math.max (Math.max (integer1.intValue(), integer2.intValue()), integer3.intValue()));
	}

	// VERSION ANTERIOR
/*
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

	*/
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
