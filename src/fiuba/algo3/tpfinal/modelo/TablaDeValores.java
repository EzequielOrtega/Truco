package fiuba.algo3.tpfinal.modelo;

import java.util.LinkedList;

import fiuba.algo3.tpfinal.modelo.error.LaCartaIngresadaNoEstaEnLaTablaError;

public class TablaDeValores {

	private LinkedList<ElementoDeTabla<Carta>> elementos;

	public TablaDeValores() {
		elementos = new LinkedList<ElementoDeTabla<Carta>>();
	}

	public Boolean insercion(Carta carta, int valor) {
		ElementoDeTabla<Carta> elementoNuevo = new ElementoDeTabla<Carta>(carta, valor);
		elementos.add(elementoNuevo);
		return true;
	}

	public int busqueda(Carta carta) {
		int valorADevolver = -1;
		for (ElementoDeTabla<Carta> elementoActual : elementos) {
			if (elementoActual.getLlave().esIgualA(carta)) {
				valorADevolver = elementoActual.getValor();
			}
		}
		if (valorADevolver == -1) {
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
