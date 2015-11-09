package fiuba.algo3.tpfinal.modelo;

public class Carta {
    private final int valor;
    private Palo palo;

    public enum Palo {ORO, BASTO, COPA, ESPADA}


    public Carta(Integer valor, String palo){
        if ( valor<1 || valor > 12 )
            throw new ValorDeCartaInvalidoError("Valor invalido. La carta debe tener un valor de 1 a 12.");
        this.valor = valor;
        for (Palo p: Palo.values()) {
            if (p.toString().equalsIgnoreCase(palo)) {
                this.palo = p;
                break;
            }
        }
        if (this.palo == null)
            throw new PaloDeCartaInvalidoError("Palo invalido. La carta debe ser de oro, basto, copa o espada.");
    }
    public int getValor(){
        return this.valor;
    }
    public String getPalo(){
        return this.palo.toString();
    }
    public boolean esFigura() {
        return (this.getValor() >= 10);
    }

    public boolean mismoPaloQue(Carta otraCarta) {
        return (this.getPalo().equals(otraCarta.getPalo()));
    }

    public boolean mismoPaloQue(Carta otraCarta, Carta terceraCarta) {
        return (this.getPalo().equals(otraCarta.getPalo()) && this.getPalo().equals(terceraCarta.getPalo()));
    }

}
