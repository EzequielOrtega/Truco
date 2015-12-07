package fiuba.algo3.tpfinal.modelo.ronda;

import fiuba.algo3.tpfinal.modelo.Jugador;
import fiuba.algo3.tpfinal.modelo.Resultado;
import fiuba.algo3.tpfinal.modelo.error.NoHayGanadorDeRondaInconclusaError;
import fiuba.algo3.tpfinal.modelo.error.YaSeJugaronLasTresManosError;

import java.util.LinkedList;

public class Ronda {
	private EstadoRonda estadoRonda;

	public Ronda() {
		this.estadoRonda = new EstadoInicialRonda();
	}

	public Boolean estaVacia() {
		return (this.estadoRonda instanceof EstadoInicialRonda);
	}

	public int tamanio() {
		int tamanio = 0;
		for (Mano mano : Mano.values()) {
			if (estadoRonda.obtenerMano().equals(mano))
				break;
			tamanio++;
		}
		return tamanio;
	}

	public void insercion(Resultado resultado) {
		if (this.tamanio() == 3) {
			throw new YaSeJugaronLasTresManosError();
		}
		switch (this.tamanio()) {
		case 0: {
			this.estadoRonda = new Primera(this.estadoRonda, resultado);
			break;
		}
		case 1: {
			this.estadoRonda = new Segunda(this.estadoRonda, resultado);
			break;
		}
		case 2: {
			this.estadoRonda = new Tercera(this.estadoRonda, resultado);
			break;
		}
		case 3: {
			break;
		}
		}
	}

	public Boolean concluyoLaRonda() {
		return this.estadoRonda.concluyoRonda();
	}

	public Jugador ganadorDeLaRonda(LinkedList<Jugador> jugadores) {
		if (!this.concluyoLaRonda()) {
			throw new NoHayGanadorDeRondaInconclusaError();
		}
		return this.estadoRonda.obtenerGanadorDeLaRonda(jugadores);
	}

	public boolean estaEnPrimera() {
		return (this.tamanio() == 1);
	}
}
