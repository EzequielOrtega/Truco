package fiuba.algo3.tpfinal.modelo;

public class PaloDeCartaInvalidoError extends RuntimeException {
    public PaloDeCartaInvalidoError() {
    }
    public PaloDeCartaInvalidoError(String s) {
        System.err.print(s);
        System.err.println();
    }
}