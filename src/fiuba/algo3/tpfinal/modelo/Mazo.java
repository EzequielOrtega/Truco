package fiuba.algo3.tpfinal.modelo;

import java.util.*;

public class Mazo {
    private Vector<Carta> cartas = new Stack<Carta>();
    private int cartasUsadas = 0;
    private String[] palo = {"oro", "basto", "copa", "espada"};

    // Constructor por defecto: mazo de 40 cartas (sin 8s y 9s) para jugar al truco.

    public Mazo() {
        for (int j=0; j<4; j++) {
            for (int i = 1; i < 8; i++) {
                Carta nuevaCarta = new Carta(i, palo[j]);
                cartas.add(nuevaCarta);
            }
            for (int i = 10; i < 13; i++) {
                Carta nuevaCarta = new Carta(i, palo[j]);
                cartas.add(nuevaCarta);
            }
        }
    }

    public void mezclar() {
        Collections.shuffle(cartas);
        cartasUsadas = 0;
    }

    public Carta agarrarCarta() {
        if (cartasUsadas == cartas.size())
            throw new NoHayMasCartasError("No quedan mas cartas. Vuelva a mezclar.");
        return cartas.get(cartas.size() - ++cartasUsadas);
    }

    public Vector<Carta> getCartas() {
        return cartas;
    }

    public int cantidadDeCartasRestantes() {
        return (cartas.size() - cartasUsadas);
    }
}
