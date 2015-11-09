package fiuba.algo3.tpfinal.modelo;

public class CantidadDeCartasInvalidaError extends RuntimeException {
    public CantidadDeCartasInvalidaError() {
    }
    public CantidadDeCartasInvalidaError(String s) {
        System.err.print(s);
        System.err.println();
    }
}
