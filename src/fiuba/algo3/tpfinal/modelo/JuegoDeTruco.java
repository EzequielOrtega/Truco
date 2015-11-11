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
    private Jugador esMano;
    private Jugador turnoDe;
    private Mazo mazoDeCartas;
    private JuezDeTruco arbitro;
    //private int enfrentamiento;
    //private int[] ronda;

    public JuegoDeTruco(Jugador j1, Jugador j2) {
        jugadores.add(j1);
        jugadores.add(j2);
        esMano = j2;
        turnoDe = j2;
        mazoDeCartas = new Mazo();
        this.repartir();
        this.arbitro = new JuezDeTruco();
    }

    /* Para cuando haya 4 jugadores: se cambia el primer constructor por this.JuegoDeTruco(j1,j2,null,null);
    public JuegoDeTruco(Jugador j1, Jugador j2, Jugador j3, Jugador j4) {
        jugadores.add(j1);
        jugadores.add(j2);
        if (j3 != null && j4 != null) {
            jugadores.add(j3);
            jugadores.add(j4);
        }
        esMano = j2;
        turnoDe = j2;
        mazoDeCartas = new Mazo();
        this.repartir();
    } */

    public void repartir() {
        // Estoy repartiendo siempre arrancando por el j1, sin contemplar que reparte el que esta a la izq de esMano.
        for (Jugador jug: jugadores)
            jug.entregarCartas();
        mazoDeCartas.mezclar();
        for (int i=1; i<4; i++) {
            for (Jugador jug : jugadores)
                jug.agarrarCarta(mazoDeCartas.agarrarCarta());
        }
    }
    /*public String ganadorEnvido() {
        if (JuezDeTruco.ganadorEnvido(jugadores.get(0).getValorEnvido(), jugadores.get(1).getValorEnvido())) {
            jugadores.get(0).sumarPuntos(PUNTOS_ENVIDO);
            return jugadores.get(0).getNombre();
        }
        jugadores.get(1).sumarPuntos(PUNTOS_ENVIDO);
        return jugadores.get(1).getNombre();
    }

    public String ganadorFlor() {
        if (JuezDeTruco.ganadorFlor(jugadores.get(0).getValorFlor(), jugadores.get(1).getValorFlor())) {
            jugadores.get(0).sumarPuntos(PUNTOS_FLOR);
            return jugadores.get(0).getNombre();
        }
        jugadores.get(1).sumarPuntos(PUNTOS_FLOR);
        return jugadores.get(1).getNombre();
    }

    public String ganadorDeLaMano(Carta cartaJ1, Carta cartaJ2) {
        if (JuezDeTruco.ganadorDeLaMano(cartaJ1, cartaJ2))
            return jugadores.get(0).getNombre();
        return jugadores.get(1).getNombre();
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
