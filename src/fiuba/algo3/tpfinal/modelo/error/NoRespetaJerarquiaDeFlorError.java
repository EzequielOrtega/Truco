package fiuba.algo3.tpfinal.modelo.error;

public class NoRespetaJerarquiaDeFlorError extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoRespetaJerarquiaDeFlorError() {
	}

	public NoRespetaJerarquiaDeFlorError(String s) {
		System.err.print(s);
		System.err.println();
	}
}
