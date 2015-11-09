package fiuba.algo3.tpfinal.modelo;

public class Figura extends Carta implements ValorDeCartaParaEnvidoYFlor {
	
	public Figura(Integer valor, String palo){
		super(valor, palo);		
	}
	@Override
	public int obtenerValorParaEnvidoYFlor() {
		return 0;
	}

}
