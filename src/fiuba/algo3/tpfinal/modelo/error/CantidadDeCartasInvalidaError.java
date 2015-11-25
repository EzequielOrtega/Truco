package fiuba.algo3.tpfinal.modelo.error;

public class CantidadDeCartasInvalidaError extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CantidadDeCartasInvalidaError() {
	}

	public CantidadDeCartasInvalidaError(String s) {
		System.err.print(s);
		System.err.println();
	}
}
