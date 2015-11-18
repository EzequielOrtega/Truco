package fiuba.algo3.tpfinal.modelo;

import java.util.LinkedList;

public class JuezDeTruco {
	
   
    public Jugador ganadorEnvido(LinkedList<Jugador> jugadores) {
    	Jugador ganadorEnvido = null;
        int maximoValor = 0;
        for (Jugador jug: jugadores) {
            if((jug.getValorEnvido() > maximoValor)||(maximoValor == 0))
                ganadorEnvido = jug;
            if((jug.getValorEnvido() == maximoValor)&&(!ganadorEnvido.estanEnElMismoEquipo(jug)))
                ganadorEnvido = jugadores.get(0);
        }

        return ganadorEnvido;
     }

    public Jugador ganadorFlor(LinkedList<Jugador> jugadores) {
    	Jugador ganadorFlor = null;
        int maximoValor = 0;
        for (Jugador jug: jugadores) {
            if((jug.getValorFlor() > maximoValor)||(maximoValor == 0))
                ganadorFlor = jug;
            if((jug.getValorFlor() == maximoValor)&&(!ganadorFlor.estanEnElMismoEquipo(jug)))
                ganadorFlor = jugadores.get(0);
        }

        return ganadorFlor;
    }

    public Resultado ganadorDeLaMano(Carta carta1, Carta carta2) {
    	CalculadorDeValorRelativo calculador = new CalculadorDeValorRelativo();
    	if(calculador.obtenerValorCarta(carta1) > calculador.obtenerValorCarta(carta2)){
    		return Resultado.GANADOR1;
        }else if (calculador.obtenerValorCarta(carta1) < calculador.obtenerValorCarta(carta2)){
        	return Resultado.GANADOR2;
        }else{
        	return Resultado.EMPATE;
        }
    }
    //Devuelve: 
    //ganador1 si gana el equipo1
    //ganador2 si gana el equipo2
    //empate en caso de empardar
    public Resultado ganadorDeLaMano(LinkedList<Carta> cartas){
    	CalculadorDeValorRelativo calculador = new CalculadorDeValorRelativo();
    	Integer valorMaximo = -1;
    	for(Integer x = 0; x < cartas.size()-1; x++){
    		Integer valorMaximoActual = Math.max(calculador.obtenerValorCarta(cartas.get(x)), calculador.obtenerValorCarta(cartas.get(x+1)));
    		if(valorMaximoActual > valorMaximo){
    			valorMaximo = valorMaximoActual;
    		}
    	}
    	Resultado resultado = null;
    	for(Integer x = 0; x < cartas.size(); x++){
    		
    		if((calculador.obtenerValorCarta(cartas.get(x)) == valorMaximo)&&(resultado == null)){
				if(x%2 == 0){
					resultado = Resultado.GANADOR1;
				}else{
					resultado = Resultado.GANADOR2;
				}
			}else if((calculador.obtenerValorCarta(cartas.get(x)) == valorMaximo)&&(resultado != null)){
				resultado = Resultado.EMPATE;
			}
    		
		}
    	return resultado;
    }
}
