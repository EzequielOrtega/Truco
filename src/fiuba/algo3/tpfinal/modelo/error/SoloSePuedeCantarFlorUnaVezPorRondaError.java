package fiuba.algo3.tpfinal.modelo.error;

public class SoloSePuedeCantarFlorUnaVezPorRondaError extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SoloSePuedeCantarFlorUnaVezPorRondaError() {

	}

	public SoloSePuedeCantarFlorUnaVezPorRondaError(String s) {
		System.err.print(s);
		System.err.println();

	}

}
