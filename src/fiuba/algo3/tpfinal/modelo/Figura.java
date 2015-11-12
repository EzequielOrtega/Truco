package fiuba.algo3.tpfinal.modelo;

public class Figura extends Carta implements CartaParaEnvidoYFlor {
	
	public Figura(Integer valor, Palo palo){
		super(valor, palo);
	}
	@Override
	public int obtenerValorParaEnvidoYFlor() {
		return 0;
	}

}
