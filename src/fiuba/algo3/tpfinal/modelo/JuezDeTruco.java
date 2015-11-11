package fiuba.algo3.tpfinal.modelo;

public class JuezDeTruco {

    public static Resultado ganadorEnvido(int envido1, int envido2) {
    	if(envido1 > envido2){
    		return Resultado.GANADOR1;
        }else if (envido1 < envido2){
        	return Resultado.GANADOR2;
        }else{
        	return Resultado.EMPATE;
        }
     }
    public static Resultado ganadorFlor(int flor1, int flor2) {
    	if(flor1 > flor2){
    		return Resultado.GANADOR1;
        }else if (flor1 < flor2){
        	return Resultado.GANADOR2;
        }else{
        	return Resultado.EMPATE;
        }
    }

    public static Resultado ganadorDeLaMano(Carta carta1, Carta carta2) {
    	if(obtenerValorCarta(carta1) > obtenerValorCarta(carta2)){
    		return Resultado.GANADOR1;
        }else if (obtenerValorCarta(carta1) < obtenerValorCarta(carta2)){
        	return Resultado.GANADOR2;
        }else{
        	return Resultado.EMPATE;
        }    	
    }

    private static int obtenerValorCarta(Carta unaCarta) {
        if (unaCarta.getValor() == 4)   return 0;
        if (unaCarta.getValor() == 5)   return 1;
        if (unaCarta.getValor() == 6)   return 2;
        if ((unaCarta.getValor() == 7) && ((unaCarta.getPalo()=="COPA") || (unaCarta.getPalo()=="BASTO")))   return 3;
        if (unaCarta.getValor() == 10)   return 4;
        if (unaCarta.getValor() == 11)   return 5;
        if (unaCarta.getValor() == 12)   return 6;
        if ((unaCarta.getValor() == 1) && ((unaCarta.getPalo()=="COPA") || (unaCarta.getPalo()=="ORO")))   return 7;
        if (unaCarta.getValor() == 2)   return 8;
        if (unaCarta.getValor() == 3)   return 9;
        if ((unaCarta.getValor() == 7) && (unaCarta.getPalo()=="ORO"))   return 10;
        if ((unaCarta.getValor() == 7) && (unaCarta.getPalo()=="ESPADA"))   return 11;
        if ((unaCarta.getValor() == 1) && (unaCarta.getPalo()=="BASTO"))   return 12;
        if ((unaCarta.getValor() == 1) && (unaCarta.getPalo()=="ESPADA"))   return 13;
        return 0;
    }
}
