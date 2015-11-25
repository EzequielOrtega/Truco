package fiuba.algo3.tpfinal.modelo;

import java.util.LinkedList;

public class JuezDeTruco {
	
	private CalculadorDeTruco calculador = new CalculadorDeTruco();
   
    public Jugador ganadorEnvido(LinkedList<Jugador> jugadores) {
    	Jugador ganadorEnvido = jugadores.getFirst();
        int maximoValor = jugadores.getFirst().getValorEnvido();
        for (Integer x = 1; x < jugadores.size(); x++) {
            if(jugadores.get(x).getValorEnvido() > maximoValor){
                ganadorEnvido = jugadores.get(x);
            }
            if((jugadores.get(x).getValorEnvido() == maximoValor)&&(!ganadorEnvido.estanEnElMismoEquipo(jugadores.get(x)))){
                ganadorEnvido = jugadores.get(0);
            }
        }

        return ganadorEnvido;
     }

    public Jugador ganadorFlor(LinkedList<Jugador> jugadores) {
    	Jugador ganadorFlor = jugadores.getFirst();
        int maximoValor = jugadores.getFirst().getValorFlor();
        for (Integer x = 1; x < jugadores.size(); x++) {
            if(jugadores.get(x).getValorFlor() > maximoValor){
                ganadorFlor = jugadores.get(x);
            }
            if((jugadores.get(x).getValorFlor() == maximoValor)&&(!ganadorFlor.estanEnElMismoEquipo(jugadores.get(x)))){
                ganadorFlor = jugadores.get(0);
            }
        }

        return ganadorFlor;
    }

    //Devuelve: 
    //ganador1 si gana el equipo1
    //ganador2 si gana el equipo2
    //empate en caso de empardar
    public Resultado ganadorDeLaMano(LinkedList<Carta> cartas){
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

	public Carta obtenerCartaMasAlta(LinkedList<Carta> cartas) {
		Carta cartaMasAlta = cartas.get(0);
		for (int x = 1; x < cartas.size(); x++) {
			if (calculador.obtenerValorCarta(cartaMasAlta) < calculador.obtenerValorCarta(cartas.get(x))) {
				cartaMasAlta = cartas.get(x);
			}
		}
		return cartaMasAlta;
	}
}
