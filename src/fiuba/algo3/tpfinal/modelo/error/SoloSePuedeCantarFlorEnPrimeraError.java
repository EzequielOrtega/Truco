package fiuba.algo3.tpfinal.modelo.error;

public class SoloSePuedeCantarFlorEnPrimeraError extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SoloSePuedeCantarFlorEnPrimeraError() {
	}

	public SoloSePuedeCantarFlorEnPrimeraError(String s) {
		System.err.print(s);
		System.err.println();
	}
}
