package fiuba.algo3.tpfinal.modelo;

public class NoTieneEnvidoTieneFlorError extends RuntimeException {
    public NoTieneEnvidoTieneFlorError() {
    }
    public NoTieneEnvidoTieneFlorError(String s) {
        System.out.print(s);
        System.out.println();
    }
}
