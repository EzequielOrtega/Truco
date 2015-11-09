package fiuba.algo3.tpfinal.modelo;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Mazo {
    private List<Carta> cartas = new LinkedList<Carta>();
    private String[] palo = {"oro", "basto", "copa", "espada"};

    public Mazo(){
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
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public int cantidadDeCartas() {
        return cartas.size();
    }
}
