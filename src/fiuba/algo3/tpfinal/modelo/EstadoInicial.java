package fiuba.algo3.tpfinal.modelo;

public class EstadoInicial extends EstadoEnvido {
		
	public EstadoInicial() {
		super(null);
		this.puntosDeEstado = 0;
	}

	@Override
	public int obtenerPuntosQueridos() {
		return puntosDeEstado;
	}

	@Override
	public int obtenerPuntosNoQueridos() {
		return 0;
	}
	
	 
}
