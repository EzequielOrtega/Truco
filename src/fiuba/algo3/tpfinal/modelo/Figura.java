package fiuba.algo3.tpfinal.modelo;

import fiuba.algo3.tpfinal.modelo.error.ValorDeCartaInvalidoError;

public class Figura extends Carta implements CartaParaEnvidoYFlor {

	public Figura(Integer valor, Palo palo) {
		super(valor, palo);
		if (valor < 10) {
			throw new ValorDeCartaInvalidoError();
		}
	}

	@Override
	public int obtenerValorParaEnvidoYFlor() {
		return 0;
	}

}
