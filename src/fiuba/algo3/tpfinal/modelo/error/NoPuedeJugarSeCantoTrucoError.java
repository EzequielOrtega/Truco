package fiuba.algo3.tpfinal.modelo.error;

public class NoPuedeJugarSeCantoTrucoError extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoPuedeJugarSeCantoTrucoError() {
	}

	public NoPuedeJugarSeCantoTrucoError(String s) {
		System.err.print(s);
		System.err.println();
	}

}
