package fiuba.algo3.tpfinal.modelo;

import java.util.Vector;

public class CalculadorDeTantos {
	
	public boolean tieneFlor(Vector<Carta> cartas){
		if(cartas.size()<3){
			throw new NoTieneSuficientesCartasParaCantarError();
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
}
