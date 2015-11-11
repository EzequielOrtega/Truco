package fiuba.algo3.tpfinal.modelo;

public class NoTieneSuficientesCartasParaCantarError extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoTieneSuficientesCartasParaCantarError() {
	}
	public NoTieneSuficientesCartasParaCantarError(String s) {
		System.out.print(s);
		System.out.println();
	}
}
