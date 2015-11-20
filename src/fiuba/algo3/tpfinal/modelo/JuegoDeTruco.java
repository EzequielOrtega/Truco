package fiuba.algo3.tpfinal.modelo;

import java.util.LinkedList;
import java.util.Vector;

import fiuba.algo3.tpfinal.modelo.envido.Envido;
import fiuba.algo3.tpfinal.modelo.envido.EstadoEnvido;
import fiuba.algo3.tpfinal.modelo.envido.EstadoInicialEnvido;
import fiuba.algo3.tpfinal.modelo.envido.RealEnvido;
import fiuba.algo3.tpfinal.modelo.error.NoPuedeCantarTrucoSeCantoEnvidoError;
import fiuba.algo3.tpfinal.modelo.error.NoPuedeJugarSeCantoEnvidoError;
import fiuba.algo3.tpfinal.modelo.error.NoPuedeJugarSeCantoTrucoError;
import fiuba.algo3.tpfinal.modelo.error.SoloSePuedeCantarEnvidoEnPrimeraError;
import fiuba.algo3.tpfinal.modelo.truco.EstadoInicialTruco;
import fiuba.algo3.tpfinal.modelo.truco.EstadoTruco;
import fiuba.algo3.tpfinal.modelo.truco.ReTruco;
import fiuba.algo3.tpfinal.modelo.truco.Truco;
import fiuba.algo3.tpfinal.modelo.truco.ValeCuatro;

public class JuegoDeTruco {

    
   	private final ListaCircular<Jugador> jugadores = new ListaCircular<Jugador>();
    private Mazo mazoDeCartas;
    private JuezDeTruco arbitro;
    private ListaCircular<Jugador> jugadorActual = new ListaCircular<Jugador>();
	private EstadoEnvido estadoActualEnvido;
	private EstadoTruco estadoActualTruco;
	private Boolean envidoCantado;
	private Boolean conFlor;
	private Boolean trucoCantado;
	private Jugador jugadorQueCanto;
	private LinkedList<Carta> manoActual;

    public JuegoDeTruco(String nombreJ1, String nombreJ2) {
    	Jugador jugador = new Jugador(nombreJ1, Equipo.EQUIPO1);
    	jugadorActual.agregar(jugador);
    	jugadores.agregar(jugador);
    	jugador = new Jugador(nombreJ2, Equipo.EQUIPO2);
    	jugadorActual.agregar(jugador);
    	jugadores.agregar(jugador);
        this.mazoDeCartas = new Mazo();
        this.repartir();
        this.arbitro = new JuezDeTruco();
        this.estadoActualEnvido = new EstadoInicialEnvido();
        this.estadoActualTruco = new EstadoInicialTruco();
        this.envidoCantado = false;
        this.trucoCantado = false;
        this.conFlor = false;
        this.jugadorQueCanto = null;
    }
    
    public JuegoDeTruco(String nombreJ1, String nombreJ2, String nombreJ3, String nombreJ4) {
    	Jugador jugador = new Jugador(nombreJ1, Equipo.EQUIPO1);
    	jugadorActual.agregar(jugador);
    	jugadores.agregar(jugador);
    	jugador = new Jugador(nombreJ2, Equipo.EQUIPO2);
    	jugadorActual.agregar(jugador);
    	jugadores.agregar(jugador);
    	jugador = new Jugador(nombreJ3, Equipo.EQUIPO1);
    	jugadorActual.agregar(jugador);
    	jugadores.agregar(jugador);
    	jugador = new Jugador(nombreJ4, Equipo.EQUIPO2);
    	jugadorActual.agregar(jugador);
    	jugadores.agregar(jugador);
        this.mazoDeCartas = new Mazo();
        this.repartir();
        this.arbitro = new JuezDeTruco();
        this.estadoActualEnvido = new EstadoInicialEnvido();
        this.estadoActualTruco = new EstadoInicialTruco();
        this.envidoCantado = false;
        this.trucoCantado = false;
        this.conFlor = false;
        this.jugadorQueCanto = null;
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

    public void envido() {
    	if (jugadorActual.obtenerElemento(0).mostrarCartas().size() != 3) {
    		throw new SoloSePuedeCantarEnvidoEnPrimeraError();
    	}
    	this.envidoCantado = true;
        this.estadoActualEnvido = new Envido(this.estadoActualEnvido);
        if(this.jugadorQueCanto == null){
        	this.jugadorQueCanto = jugadorActual.obtenerElemento(0);
        	this.jugadorActual.moverAlSiguiente();
        }else if(this.jugadorQueCanto == jugadorActual.obtenerElemento(0)){
        	this.jugadorActual.moverAlSiguiente();
        }else{
        	this.jugadorActual.moverAlAnterior();
        }
    }
    
    public void quieroEnvido() {
    	Jugador ganador = arbitro.ganadorEnvido(jugadores.obtenerElementos());
        ganador.sumarPuntos(this.estadoActualEnvido.obtenerPuntosQueridos());
        this.jugadorActual.moverAlAnterior();
        this.estadoActualEnvido = new EstadoInicialEnvido();
        this.envidoCantado = false;
        this.jugadorQueCanto = null;
    }
    
    public void noQuieroEnvido() {
    	this.jugadorActual.moverAlAnterior();
    	jugadorActual.obtenerElemento(0).sumarPuntos(this.estadoActualEnvido.obtenerPuntosNoQueridos());
    	this.estadoActualEnvido = new EstadoInicialEnvido();
    	this.envidoCantado = false;
    	this.jugadorQueCanto = null;
    }

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
		this.conFlor = conFlor;
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

	public void truco() {
		if(this.envidoCantado){
			throw new NoPuedeCantarTrucoSeCantoEnvidoError();
		}
		this.trucoCantado = true;
		this.estadoActualTruco = new Truco(estadoActualTruco);
		jugadorActual.moverAlSiguiente();
	}

	public void reTruco() {
		this.estadoActualTruco = new ReTruco(estadoActualTruco);
		jugadorActual.moverAlAnterior();
	}

	public void valeCuatro() {
		this.estadoActualTruco = new ValeCuatro(estadoActualTruco);
		jugadorActual.moverAlSiguiente();
	}

	public void noQuieroTruco() {
		jugadorActual.moverAlAnterior();
		jugadorActual.obtenerElemento(0).sumarPuntos(estadoActualTruco.obtenerPuntosNoQueridos());
		this.estadoActualTruco = new EstadoInicialTruco();
		this.trucoCantado = false;
		//this.terminarRonda();
	}
	
	public void quieroTruco(){
		jugadorActual.moverAlAnterior();
		this.trucoCantado = false;
	}

	public Jugador obtenerJugadorActual() {
		return jugadorActual.obtenerElemento(0);
	}

	public void moverAlSiguiente() {
		this.jugadores.moverAlSiguiente();
		this.jugadorActual.moverAlSiguiente();
	}

	public void realEnvido() {
		this.envidoCantado = true;
		estadoActualEnvido = new RealEnvido(estadoActualEnvido);
		if(this.jugadorQueCanto == null){
        	this.jugadorQueCanto = jugadorActual.obtenerElemento(0);
        	this.jugadorActual.moverAlSiguiente();
        }else if(this.jugadorQueCanto == jugadorActual.obtenerElemento(0)){
        	this.jugadorActual.moverAlSiguiente();
        }else{
        	this.jugadorActual.moverAlAnterior();
        }
	}

	// todavia esta incompleto
	public void jugar(Carta carta) {
		if(this.envidoCantado){
			throw new NoPuedeJugarSeCantoEnvidoError();
		}
		if(this.trucoCantado){
			throw new NoPuedeJugarSeCantoTrucoError();
		}
		this.manoActual.add(carta);
		if (this.manoActual.size() == this.jugadores.tamanio()) {
			arbitro.ganadorDeLaMano(this.manoActual);
		}
		this.jugadorActual.moverAlSiguiente();
	}
}
