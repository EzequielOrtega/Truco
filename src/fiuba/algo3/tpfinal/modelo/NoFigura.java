package fiuba.algo3.tpfinal.modelo;

public class NoFigura extends Carta implements ValorDeCartaParaEnvidoYFlor {
	
	public NoFigura (Integer valor, String palo){
		super(valor, palo);
	}
	@Override
	public int obtenerValorParaEnvidoYFlor() {
		return this.getValor();
	}

}
