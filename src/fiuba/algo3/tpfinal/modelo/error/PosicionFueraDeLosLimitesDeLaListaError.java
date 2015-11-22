package fiuba.algo3.tpfinal.modelo.error;

public class PosicionFueraDeLosLimitesDeLaListaError extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PosicionFueraDeLosLimitesDeLaListaError() {
	}
	public PosicionFueraDeLosLimitesDeLaListaError(String s) {
		System.err.print(s);
		System.err.println();
	}
}
