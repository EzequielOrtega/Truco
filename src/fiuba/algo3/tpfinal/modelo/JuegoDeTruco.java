package fiuba.algo3.tpfinal.modelo;

import fiuba.algo3.tpfinal.modelo.envido.*;
import fiuba.algo3.tpfinal.modelo.error.*;
import fiuba.algo3.tpfinal.modelo.truco.*;
import fiuba.algo3.tpfinal.modelo.flor.*;
import fiuba.algo3.tpfinal.modelo.ronda.*;
import java.util.LinkedList;

public class JuegoDeTruco {

    
   	private final ListaCircular<Jugador> jugadores = new ListaCircular<Jugador>();
    private Mazo mazoDeCartas = new Mazo();
    private JuezDeTruco arbitro = new JuezDeTruco();
    private Ronda ronda = new Ronda();
    private LinkedList<Carta> cartasEnLaMesa;
    private Jugador jugadorActual;
    private Jugador jugadorQueCanto = null;

    private Boolean conFlor = false;
    private EstadoFlor estadoActualFlor = new EstadoInicialFlor();
    private boolean florCantada = false;
    private EstadoEnvido estadoActualEnvido = new EstadoInicialEnvido();
    private Boolean envidoCantado = false;
    private EstadoTruco estadoActualTruco = new EstadoInicialTruco();
    private Boolean trucoCantado = false;

    public JuegoDeTruco(String nombreJ1, String nombreJ2) {
    	Jugador jugador = new Jugador(nombreJ1, Equipo.EQUIPO1);
    	jugadores.agregar(jugador);
    	jugador = new Jugador(nombreJ2, Equipo.EQUIPO2);
    	jugadores.agregar(jugador);
        this.repartir();
        this.jugadorActual = jugadores.obtenerElemento(0);
    }
    
    public JuegoDeTruco(String nombreJ1, String nombreJ2, String nombreJ3, String nombreJ4) {
    	Jugador jugador = new Jugador(nombreJ1, Equipo.EQUIPO1);
    	jugadores.agregar(jugador);
    	jugador = new Jugador(nombreJ2, Equipo.EQUIPO2);
    	jugadores.agregar(jugador);
    	jugador = new Jugador(nombreJ3, Equipo.EQUIPO1);
    	jugadores.agregar(jugador);
    	jugador = new Jugador(nombreJ4, Equipo.EQUIPO2);
    	jugadores.agregar(jugador);
        this.repartir();
        this.jugadorActual = jugadores.obtenerElemento(0);
    }

    public void comenzarPartida(Boolean conFlor){
        this.resetearPuntos();
        this.conFlor = conFlor;
    }

    public void avanzarJugadorActual() {
    	this.jugadorActual = jugadores.obtenerElementoSiguienteDe(jugadorActual);
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

    private void resetearPuntos() {
        LinkedList<Jugador> jugadoresList = jugadores.obtenerElementos();
        for(Jugador jugadorActual: jugadoresList){
            jugadorActual.resetearPuntos();
        }
    }

    public int puntosDeEquipo(Equipo equipo) {
        Integer puntos = 0;
        LinkedList<Jugador> jugadoresList = jugadores.obtenerElementos();
        for(Jugador jugadorActual: jugadoresList){
            if(jugadorActual.coincideElEquipo(equipo)){
                puntos =+ jugadorActual.obtenerPuntaje();
            }
        }
        return puntos;
    }

    public Jugador obtenerJugadorActual() {
        return jugadorActual;
    }

    public void moverAlSiguiente() {
        this.jugadores.moverAlSiguiente();
        this.avanzarJugadorActual();
    }

    // todavia esta incompleto
    public void jugar(Carta carta) {
        if(this.envidoCantado){
            throw new NoPuedeJugarSeCantoEnvidoError();
        }
        if(this.trucoCantado){
            throw new NoPuedeJugarSeCantoTrucoError();
        }
        this.cartasEnLaMesa.add(carta);
        if ((this.cartasEnLaMesa.size() == this.jugadores.tamanio())&&(!ronda.concluyoLaRonda())) {
            ronda.insercion(arbitro.ganadorDeLaMano(this.cartasEnLaMesa));
        }
        this.avanzarJugadorActual();
    }

    // FLOR

    public void flor() {
        if (jugadorActual.mostrarCartas().size() != 3) {
            throw new SoloSePuedeCantarFlorEnPrimeraError();
        }

        this.florCantada = true;
        this.estadoActualFlor = new Flor(this.estadoActualFlor);
        if (this.jugadorQueCanto == null) {
            this.jugadorQueCanto = jugadorActual;
        }
        this.avanzarJugadorActual();
    }

    public void contraFlor() {
        this.estadoActualFlor = new ContraFlor(this.estadoActualFlor);
        this.avanzarJugadorActual();

    }

    public void contraFlorAlResto() {
        this.estadoActualFlor = new ContraFlorAlResto(this.estadoActualFlor);
        this.avanzarJugadorActual();
    }

    // ENVIDO
    public void envido() {
    	if (jugadorActual.mostrarCartas().size() != 3) {
    		throw new SoloSePuedeCantarEnvidoEnPrimeraError();
    	}
    	
    	this.envidoCantado = true;
        this.estadoActualEnvido = new Envido(this.estadoActualEnvido);
        if (this.jugadorQueCanto == null) {
        	this.jugadorQueCanto = jugadorActual;        	
        }
        this.avanzarJugadorActual();
    }

    public void realEnvido() {
        this.envidoCantado = true;
        estadoActualEnvido = new RealEnvido(estadoActualEnvido);
        if (this.jugadorQueCanto == null) {
            this.jugadorQueCanto = jugadorActual;
        }
        this.avanzarJugadorActual();
    }
    
    public void quieroEnvido() {
    	
    	Jugador ganador = arbitro.ganadorEnvido(jugadores.obtenerElementos());
        ganador.sumarPuntos(this.estadoActualEnvido.obtenerPuntosQueridos());

        this.jugadorActual = jugadorQueCanto;
        this.estadoActualEnvido = new EstadoFinalEnvido(estadoActualEnvido);
        this.envidoCantado = false;
        this.jugadorQueCanto = null;
    }
    
    public void noQuieroEnvido() {
    	this.avanzarJugadorActual();
    	jugadorActual.sumarPuntos(this.estadoActualEnvido.obtenerPuntosNoQueridos());
        this.jugadorActual = jugadorQueCanto;
    	this.estadoActualEnvido = new EstadoFinalEnvido(estadoActualEnvido);
    	this.envidoCantado = false;
    	this.jugadorQueCanto = null;
    }

	// TRUCO

	public void truco() {
		if(this.envidoCantado){
			throw new NoPuedeCantarTrucoSeCantoEnvidoError();
		}
		this.trucoCantado = true;
		this.estadoActualTruco = new Truco(estadoActualTruco);
		this.avanzarJugadorActual();
	}

	public void reTruco() {
		this.estadoActualTruco = new ReTruco(estadoActualTruco);
		this.avanzarJugadorActual();
	}

	public void valeCuatro() {
		this.estadoActualTruco = new ValeCuatro(estadoActualTruco);
		this.avanzarJugadorActual();
	}

    public void quieroTruco(){
        this.avanzarJugadorActual();
        this.trucoCantado = false;
    }

	public void noQuieroTruco() {
		this.avanzarJugadorActual();
		jugadorActual.sumarPuntos(estadoActualTruco.obtenerPuntosNoQueridos());
		this.estadoActualTruco = new EstadoInicialTruco();
		this.trucoCantado = false;
		//this.terminarRonda();
	}

}
