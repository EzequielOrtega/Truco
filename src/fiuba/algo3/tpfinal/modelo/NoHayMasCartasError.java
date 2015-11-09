package fiuba.algo3.tpfinal.modelo;

public class NoHayMasCartasError extends RuntimeException {
    public NoHayMasCartasError() {
    }

    public NoHayMasCartasError(String s) {
        System.err.print(s);
        System.err.println();
    }
}
