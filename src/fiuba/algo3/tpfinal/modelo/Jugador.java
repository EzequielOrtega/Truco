package fiuba.algo3.tpfinal.modelo;


import java.util.Vector;

public class Jugador {
    // Tal vez deberia llamarse JugadorDeTruco o crear una clase JugadorDeTruco que herede/implemente esta.

    private final String nombre;
    private Vector<Carta> cartas;
    private int puntaje;
    private CalculadorDeTantos calculadorDeTantos;
    private Vector<Carta> cartasJugadas;
   

    public Jugador (String nombreJugador) {
        this.nombre = nombreJugador;
        this.puntaje = 0;
        this.calculadorDeTantos = new CalculadorDeTantos();
        this.cartas = new Vector<Carta>();
        this.cartasJugadas = new Vector<Carta>();
        
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
        this.cartasJugadas.removeAllElements();
    }
    
    public int obtenerPuntaje(){
    	return this.puntaje;
    }

    //jugarCarta() acepta los valores 1, 2 o 3, que corresponden a las 3 posibles cartas de la mano del jugador
    // Falta ver como funciona esto para cuando ya saque una carta y me quedan dos, o una en la mano.
    public Carta jugarCarta(int numeroDeCarta) {
        Carta carta = cartas.get(numeroDeCarta-1);
        cartas.remove(carta);
        cartasJugadas.add(carta);
        return carta;
    }


    // ENVIDO Y FLOR:
    public int getValorEnvido () {
        if (cartas.size() != 3)
            throw new CantidadDeCartasInvalidaError("El envido debe calcularse solo si se tiene 3 cartas en mano.");
        Vector<Carta> cartasTodas = new Vector<Carta>();
        cartasTodas.addAll(cartas);
        cartasTodas.addAll(cartasJugadas);
        return calculadorDeTantos.obtenerTantosDeEnvido(cartasTodas);
    }

    public int getValorFlor () {
        if (cartas.size() != 3)
            throw new CantidadDeCartasInvalidaError("El envido debe calcularse solo si se tiene 3 cartas en mano.");
        Vector<Carta> cartasTodas = new Vector<Carta>();
        cartasTodas.addAll(cartas);
        cartasTodas.addAll(cartasJugadas);
        return calculadorDeTantos.obtenerTantosDeFlor(cartasTodas);
    }
}
