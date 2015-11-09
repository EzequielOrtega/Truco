package fiuba.algo3.tpfinal.modelo;

public class ValorDeCartaInvalidoError extends RuntimeException {
    public ValorDeCartaInvalidoError() {
    }
    public ValorDeCartaInvalidoError(String s) {
        System.err.print(s);
        System.err.println();
    }

}
