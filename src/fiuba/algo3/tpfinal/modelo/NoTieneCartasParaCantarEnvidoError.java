package fiuba.algo3.tpfinal.modelo;

public class NoTieneCartasParaCantarEnvidoError extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoTieneCartasParaCantarEnvidoError() {
	}
	public NoTieneCartasParaCantarEnvidoError(String s) {
		System.out.print(s);
		System.out.println();
	}

}
