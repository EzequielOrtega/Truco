package fiuba.algo3.tpfinal.modelo;

import fiuba.algo3.tpfinal.modelo.error.LaCartaIngresadaNoEstaEnLaTablaError;

import java.util.LinkedList;

public class TablaDeDiccionario {

	private LinkedList<ElementoDeDiccionario<Carta>> elementos;

	public TablaDeDiccionario() {
		elementos = new LinkedList<ElementoDeDiccionario<Carta>>();
	}

	public Boolean insercion(Carta carta, String valor) {
		ElementoDeDiccionario<Carta> elementoNuevo = new ElementoDeDiccionario<Carta>(carta, valor);
		elementos.add(elementoNuevo);
		return true;
	}

	public String busqueda(Carta carta) {
		String valorADevolver = null;
		for (ElementoDeDiccionario<Carta> elementoActual : elementos) {
			if (elementoActual.getLlave().esIgualA(carta)) {
				valorADevolver = elementoActual.getValor();
			}
		}
		if (valorADevolver == null) {
			throw new LaCartaIngresadaNoEstaEnLaTablaError();
		}
		return valorADevolver;
	}

	public Boolean borrar(Carta carta) {
		int posicionElemento = -1;
		for (int x = 0; x < elementos.size(); x++) {
			if (elementos.get(x).getLlave().esIgualA(carta)) {
				posicionElemento = x;
			}
		}
		if (posicionElemento == -1) {
			throw new LaCartaIngresadaNoEstaEnLaTablaError();
		}
		elementos.remove(posicionElemento);
		return true;
	}
}
