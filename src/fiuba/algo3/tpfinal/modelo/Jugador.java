package fiuba.algo3.tpfinal.modelo;


import java.util.Vector;

public class Jugador {
    // Tal vez deberia llamarse JugadorDeTruco o crear una clase JugadorDeTruco que herede/implemente esta.

    private final String nombre;
    private Vector<Carta> cartas = new Vector<Carta>();
    private int puntaje;

    public Jugador (String nombreJugador) {
        this.nombre = nombreJugador;
        this.puntaje = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void sumarPuntos(int cantidadDePuntos) {
        puntaje =+ cantidadDePuntos;
    }

    public Vector<Carta> mostrarCartas() {
        return cartas;
    }

    public void agarrarCarta(Carta nuevaCarta) {
        if (cartas.size() == 3)
            throw new CantidadDeCartasInvalidaError("Un jugador no debe agarrar ni mas ni menos que 3 cartas.");
        this.cartas.add(nuevaCarta);
    }

    public void entregarCartas() {
        this.cartas.removeAllElements();
    }

    //jugarCarta() acepta los valores 1, 2 o 3, que corresponden a las 3 posibles cartas de la mano del jugador
    // Falta ver como funciona esto para cuando ya saque una carta y me quedan dos, o una en la mano.
    public Carta jugarCarta(int numeroDeCarta) {
        Carta carta = cartas.get(numeroDeCarta-1);
        cartas.remove(carta);
        return carta;
    }


    // ENVIDO Y FLOR:
    public int getValorEnvido () {
        // No se si el chequeo de estar en 1ra ronda va aca o en JuegoDeTruco
        if (cartas.size() != 3)
            throw new CantidadDeCartasInvalidaError("El envido debe calcularse solo si se tiene 3 cartas en mano.");
        if (cartas.get(0).mismoPaloQue(cartas.get(1), cartas.get(2)))
            throw new NoTieneEnvidoTieneFlorError("Tiene 3 cartas del mismo palo, no puede cantar envido.");
        int valorEnvido = 0;
        if (cartas.get(0).mismoPaloQue(cartas.get(1)))
            valorEnvido = 20 + valorEnEnvidoYFlor(cartas.get(0)) + valorEnEnvidoYFlor(cartas.get(1));
        if (cartas.get(1).mismoPaloQue(cartas.get(2)))
            valorEnvido = 20 + valorEnEnvidoYFlor(cartas.get(1)) + valorEnEnvidoYFlor(cartas.get(2));
        if (cartas.get(2).mismoPaloQue(cartas.get(1)))
            valorEnvido = 20 + valorEnEnvidoYFlor(cartas.get(2)) + valorEnEnvidoYFlor(cartas.get(1));
        return valorEnvido;
    }

    public int getValorFlor () {
        // No se si el chequeo de estar en 1ra ronda va aca o en JuegoDeTruco
        if (cartas.size() != 3)
            throw new CantidadDeCartasInvalidaError("El envido debe calcularse solo si se tiene 3 cartas en mano.");
        int valorFlor = 0;
        if (cartas.get(0).mismoPaloQue(cartas.get(1), cartas.get(2)))
            valorFlor = 20 + valorEnEnvidoYFlor(cartas.get(0)) + valorEnEnvidoYFlor(cartas.get(1)) + valorEnEnvidoYFlor(cartas.get(2));
        return valorFlor;
    }

    private int valorEnEnvidoYFlor(Carta unaCarta) {
        if (unaCarta.esFigura())    return 0;
        else                        return unaCarta.getValor();
    }
}
