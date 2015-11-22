package fiuba.algo3.tpfinal.modelo.flor;


public class EstadoFinalFlor extends EstadoFlor {

    public EstadoFinalFlor(EstadoFlor estadoAnterior) {
        super(estadoAnterior);
        this.puntosDeEstado = 0;
    }
}
