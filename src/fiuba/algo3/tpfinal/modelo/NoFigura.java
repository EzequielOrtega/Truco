package fiuba.algo3.tpfinal.modelo;

public class NoFigura extends Carta implements CartaParaEnvidoYFlor {
	
	public NoFigura (Integer valor, Palo palo){
		super(valor, palo);
	}
	@Override
	public int obtenerValorParaEnvidoYFlor() {
		return this.getValor();
	}

}
