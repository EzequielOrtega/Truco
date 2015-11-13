package fiuba.algo3.tpfinal.modelo;

import java.util.LinkedList;

public class JuezDeTruco {
	
   
    public Jugador ganadorEnvido(LinkedList<Jugador> jugadores) {
    	Jugador ganadorEnvido = null;
        int maximoValor = 0;
        for (Jugador jug: jugadores) {
            if(jug.getValorEnvido() > maximoValor)
                ganadorEnvido = jug;
            if(jug.getValorEnvido() == maximoValor)
                ganadorEnvido = jugadores.get(0);
        }

        return ganadorEnvido;
     }

    public Jugador ganadorFlor(LinkedList<Jugador> jugadores) {
    	Jugador ganadorFlor = null;
        int maximoValor = 0;
        for (Jugador jug: jugadores) {
            if(jug.getValorFlor() > maximoValor)
                ganadorFlor = jug;
            if(jug.getValorFlor() == maximoValor)
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
}
