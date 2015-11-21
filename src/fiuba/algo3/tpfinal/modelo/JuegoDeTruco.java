package fiuba.algo3.tpfinal.modelo;

import java.util.LinkedList;

import fiuba.algo3.tpfinal.modelo.envido.Envido;
import fiuba.algo3.tpfinal.modelo.envido.EstadoEnvido;
import fiuba.algo3.tpfinal.modelo.envido.EstadoFinalEnvido;
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
    private Jugador jugadorActual;
	private EstadoEnvido estadoActualEnvido;
	private EstadoTruco estadoActualTruco;
	private Boolean envidoCantado;
	private Boolean conFlor;
	private Boolean trucoCantado;
	private Jugador jugadorQueCanto;
	private LinkedList<Carta> cartasEnLaMesa;
	private Ronda ronda = new Ronda();

    public JuegoDeTruco(String nombreJ1, String nombreJ2) {
    	Jugador jugador = new Jugador(nombreJ1, Equipo.EQUIPO1);
    	jugadores.agregar(jugador);
    	jugador = new Jugador(nombreJ2, Equipo.EQUIPO2);
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
        this.mazoDeCartas = new Mazo();
        this.repartir();
        this.arbitro = new JuezDeTruco();
        this.estadoActualEnvido = new EstadoInicialEnvido();
        this.estadoActualTruco = new EstadoInicialTruco();
        this.envidoCantado = false;
        this.trucoCantado = false;
        this.conFlor = false;
        this.jugadorQueCanto = null;
        
        this.jugadorActual = jugadores.obtenerElemento(0);
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

	public void noQuieroTruco() {
		this.avanzarJugadorActual();
		jugadorActual.sumarPuntos(estadoActualTruco.obtenerPuntosNoQueridos());
		this.estadoActualTruco = new EstadoInicialTruco();
		this.trucoCantado = false;
		//this.terminarRonda();
	}
	
	public void quieroTruco(){
		this.avanzarJugadorActual();
		this.trucoCantado = false;
	}

	public Jugador obtenerJugadorActual() {
		return jugadorActual;
	}

	public void moverAlSiguiente() {
		this.jugadores.moverAlSiguiente();
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
}
