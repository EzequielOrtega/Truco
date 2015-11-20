package fiuba.algo3.tpfinal.modelo;

import fiuba.algo3.tpfinal.modelo.error.ValorDeCartaInvalidoError;

public class Carta {
    private final int valor;
    private final Palo palo;

    public Carta(Integer valor, Palo p){
        if ( valor < 1 || valor > 12 )
            throw new ValorDeCartaInvalidoError("Valor invalido. La carta debe tener un valor de 1 a 12.");
        this.valor = valor;
        this.palo = p;
    }
    public int getValor(){
        return this.valor;
    }
    public Palo getPalo(){
        return this.palo;
    }

    public boolean mismoPaloQue(Carta otraCarta) {
        return (this.getPalo().equals(otraCarta.getPalo()));
    }

    public boolean mismoPaloQue(Carta otraCarta, Carta terceraCarta) {
        return (this.getPalo().equals(otraCarta.getPalo()) && this.getPalo().equals(terceraCarta.getPalo()));
    }

    public boolean equals(Carta cartaAComparar){
    	return ((this.valor == cartaAComparar.getValor()) && (this.getPalo() == cartaAComparar.getPalo()));
    }

}
