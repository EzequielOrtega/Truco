package fiuba.algo3.tpfinal.modelo;

public class SoloSePuedeCantarEnPrimeraError extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public SoloSePuedeCantarEnPrimeraError() {
	}
	public SoloSePuedeCantarEnPrimeraError(String s) {
		System.err.print(s);
		System.err.println();
	}

}
