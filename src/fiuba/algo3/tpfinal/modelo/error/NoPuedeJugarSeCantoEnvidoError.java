package fiuba.algo3.tpfinal.modelo.error;

public class NoPuedeJugarSeCantoEnvidoError extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoPuedeJugarSeCantoEnvidoError() {
	}
	public NoPuedeJugarSeCantoEnvidoError(String s) {
		System.err.print(s);
		System.err.println();
	}

}
