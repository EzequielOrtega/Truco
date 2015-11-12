package fiuba.algo3.tpfinal.modelo;

import java.util.LinkedList;

public class JuegoDeTruco {

    private static final int PUNTOS_ENVIDO = 2;
    //private static final int PUNTOS_REAL_ENVIDO = 3;
    //private static final int PUNTOS_ENVIDO_ENVIDO = 4;
    private static final int PUNTOS_FLOR = 3;
    //private static final int PUNTOS_CONTRAFLOR = 6;
    //private static final int PUNTOS_TRUCO = 2;
    //private static final int PUNTOS_RETRUCO = 3;
    //private static final int PUNTOS_TRUCO_VALE4 = 4;


    private final LinkedList<Jugador> jugadores = new LinkedList<Jugador>();
   
    private Mazo mazoDeCartas;
    private JuezDeTruco arbitro;
    //private int enfrentamiento;
    //private int[] ronda;

    public JuegoDeTruco(Jugador j1, Jugador j2) {
        this(j1, j2, null, null);
    }
    
    public JuegoDeTruco(Jugador j1, Jugador j2, Jugador j3, Jugador j4) {
        jugadores.add(j1);
        jugadores.add(j2);
        if ((j3 != null) && (j4 != null)) {
            jugadores.add(j3);
            jugadores.add(j4);
        }
        mazoDeCartas = new Mazo();
        this.repartir();
        this.arbitro = new JuezDeTruco();
    }

    public void repartir() {
        for (Jugador jug: jugadores)
            jug.entregarCartas();
        mazoDeCartas.mezclar();
        for (int i=1; i<4; i++) {
            for (Jugador jug : jugadores)
                jug.agarrarCarta(mazoDeCartas.agarrarCarta());
        }
    }

    public void envido() {
        Jugador ganador = arbitro.ganadorEnvido(jugadores);
        ganador.sumarPuntos(PUNTOS_ENVIDO);
    }

	public void flor() {
        Jugador ganador = arbitro.ganadorFlor(jugadores);
        ganador.sumarPuntos(PUNTOS_FLOR);
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

}
