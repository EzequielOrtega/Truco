package fiuba.algo3.tpfinal.modelo.error;

public class SoloSePuedeCantarEnvidoUnaVezPorRondaError extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SoloSePuedeCantarEnvidoUnaVezPorRondaError() {

	}

	public SoloSePuedeCantarEnvidoUnaVezPorRondaError(String s) {
		System.err.print(s);
		System.err.println();

	}

}
