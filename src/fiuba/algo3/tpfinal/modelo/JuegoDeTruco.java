package fiuba.algo3.tpfinal.modelo;

import java.util.LinkedList;

public class JuegoDeTruco {

    //private static final int PUNTOS_ENVIDO = 2;
    //private static final int PUNTOS_REAL_ENVIDO = 3;
    //private static final int PUNTOS_ENVIDO_ENVIDO = 4;
    //private static final int PUNTOS_FLOR = 3;
    //private static final int PUNTOS_CONTRAFLOR = 6;
    //private static final int PUNTOS_TRUCO = 2;
    //private static final int PUNTOS_RETRUCO = 3;
    //private static final int PUNTOS_TRUCO_VALE4 = 4;


    private final ListaCircular<Jugador> jugadores = new ListaCircular<Jugador>();
   
    private Mazo mazoDeCartas;
    private JuezDeTruco arbitro;
    //private int enfrentamiento;
    //private int[] ronda;

	private Jugador jugadorActual;

	private EstadoEnvido estadoActualEnvido;

    public JuegoDeTruco(String nombreJ1, String nombreJ2) {
    	Jugador j1 = new Jugador(nombreJ1, Equipo.EQUIPO1);
    	jugadores.agregar(j1);
    	Jugador j2 = new Jugador(nombreJ2, Equipo.EQUIPO2);
        jugadores.agregar(j2);        
        mazoDeCartas = new Mazo();
        this.repartir();
        this.arbitro = new JuezDeTruco();
        this.estadoActualEnvido = new EstadoInicialEnvido();
    }
    
    public JuegoDeTruco(String nombreJ1, String nombreJ2, String nombreJ3, String nombreJ4) {
    	Jugador j1 = new Jugador(nombreJ1, Equipo.EQUIPO1);
    	jugadores.agregar(j1);
    	Jugador j2 = new Jugador(nombreJ2, Equipo.EQUIPO2);
        jugadores.agregar(j2);
        Jugador j3 = new Jugador(nombreJ3, Equipo.EQUIPO1);
        jugadores.agregar(j3);
        Jugador j4 = new Jugador(nombreJ4, Equipo.EQUIPO2);
        jugadores.agregar(j4);
        mazoDeCartas = new Mazo();
        this.repartir();
        this.arbitro = new JuezDeTruco();
        this.estadoActualEnvido = new EstadoInicialEnvido();
    }

    public void repartir() {
        LinkedList<Jugador> jugadoresList = jugadores.obtenerElementos();
    	for (Jugador jug: jugadoresList)
            jug.entregarCartas();
        mazoDeCartas.mezclar();
        for (int i=1; i<4; i++) {
            for (Jugador jug : jugadoresList)
                jug.agarrarCarta(mazoDeCartas.agarrarCarta());
        }
    }

    public void envido(Integer puntos) {
    	if (jugadorActual.mostrarCartas().size() != 3) {
    		throw new SoloSePuedeCantarEnPrimeraError();
    	}
        this.estadoActualEnvido = new Envido(this.estadoActualEnvido);
    }
    
    public void quieroEnvido() {
    	Jugador ganador = arbitro.ganadorEnvido(jugadores.obtenerElementos());
        ganador.sumarPuntos(this.estadoActualEnvido.obtenerPuntosQueridos());
    }
    
    public void noQuieroEnvido() {
    	jugadorActual.sumarPuntos(this.estadoActualEnvido.obtenerPuntosNoQueridos());
    }

	public void flor(Integer puntos) {
        Jugador ganador = arbitro.ganadorFlor(jugadores.obtenerElementos());
        ganador.sumarPuntos(puntos);
    }

    /*
    public void ganadorDeLaMano(Carta cartaJ1, Carta cartaJ2) {
        Jugador ganador = arbitro.ganadorDeLaMano(jugadores);
    }*/

  

	public void ganadorDeLaRonda() {
        // https://es.wikipedia.org/wiki/Truco_argentino
        // G1       G2      G3      Ganador
        // 1        2       E           1
        // 1        E       -           1
        // E        1       -           1
        // E        E       1           1
        // E        E       E           Mano

        return;
    }
	
	public void comenzarPartida(Boolean conFlor){
    	this.resetearPuntos();
		while ((this.puntosDeEquipo(Equipo.EQUIPO1)>=30) || (this.puntosDeEquipo(Equipo.EQUIPO1)>=30)){
    		this.ronda(conFlor);
    	}
    }

	private void resetearPuntos() {
		LinkedList<Jugador> jugadoresList = jugadores.obtenerElementos();
		for(Jugador jugadorActual: jugadoresList){
			jugadorActual.resetearPuntos();
		}
	}

	private void ronda(Boolean conFlor) {
		mazoDeCartas.mezclar();
		this.repartir();
		boolean noTerminoRonda = true;
		while (noTerminoRonda){
			//Acciones de jugador (cantar y/ jugar)
			//Verificar quien gano la mano
			//Verificar si se termino la ronda (cambiar el estado de noTerminoRonda)
		}
		//Sumar los puntos al ganador
		jugadores.moverAlSiguiente();
	}

	private int puntosDeEquipo(Equipo equipo) {
		Integer puntos = 0;
		LinkedList<Jugador> jugadoresList = jugadores.obtenerElementos();
		for(Jugador jugadorActual: jugadoresList){
			if(jugadorActual.coincideElEquipo(equipo)){
				puntos =+ jugadorActual.obtenerPuntaje();
			}
		}
		return puntos;
	}

}
