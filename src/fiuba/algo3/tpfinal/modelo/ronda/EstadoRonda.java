package fiuba.algo3.tpfinal.modelo.ronda;

public abstract class EstadoRonda {

	protected EstadoRonda estadoRonda;
	protected Mano mano;

	public EstadoRonda(EstadoRonda estadoAnterior) {
		this.estadoRonda = estadoAnterior;
	}
	
	public Mano obtenerMano() {
		return this.mano;
	}

	
}
