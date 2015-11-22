package fiuba.algo3.tpfinal.modelo.error;

public class SoloSePuedeCantarEnvidoEnPrimeraError extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SoloSePuedeCantarEnvidoEnPrimeraError() {
	}
	public SoloSePuedeCantarEnvidoEnPrimeraError(String s) {
		System.err.print(s);
		System.err.println();
	}

}
