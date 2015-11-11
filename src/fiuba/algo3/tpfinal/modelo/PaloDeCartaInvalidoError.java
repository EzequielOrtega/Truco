package fiuba.algo3.tpfinal.modelo;

public class PaloDeCartaInvalidoError extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public PaloDeCartaInvalidoError() {
    }
    public PaloDeCartaInvalidoError(String s) {
        System.err.print(s);
        System.err.println();
    }
}