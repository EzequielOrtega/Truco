package fiuba.algo3.tpfinal.modelo.error;

public class NoHayMasCartasError extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoHayMasCartasError() {

	}

	public NoHayMasCartasError(String s) {
		System.err.print(s);
		System.err.println();
	}
}
