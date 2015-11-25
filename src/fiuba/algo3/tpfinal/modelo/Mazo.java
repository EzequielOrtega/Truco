package fiuba.algo3.tpfinal.modelo;

import java.util.Collections;
import java.util.Stack;
import java.util.Vector;

import fiuba.algo3.tpfinal.modelo.error.NoHayMasCartasError;

public class Mazo {
	private Vector<Carta> cartas = new Stack<Carta>();
	private int cartasUsadas = 0;

	// Constructor por defecto: mazo de 40 cartas (sin 8s y 9s) para jugar al
	// truco.

	public Mazo() {

		for (Palo palo : Palo.values()) {
			for (int i = 1; i < 8; i++) {
				Carta nuevaCarta = new NoFigura(i, palo);
				cartas.add(nuevaCarta);
			}
			for (int i = 10; i < 13; i++) {
				Carta nuevaCarta = new Figura(i, palo);
				cartas.add(nuevaCarta);
			}
		}
	}

	public void mezclar() {
		Collections.shuffle(cartas);
		cartasUsadas = 0;
	}

	public Carta agarrarCarta() {
		if (cantidadDeCartasRestantes() == 0)
			throw new NoHayMasCartasError();
		return cartas.get(cartas.size() - ++cartasUsadas);
	}

	public Vector<Carta> getCartas() {
		return cartas;
	}

	public int cantidadDeCartasRestantes() {
		return (cartas.size() - cartasUsadas);
	}
}
