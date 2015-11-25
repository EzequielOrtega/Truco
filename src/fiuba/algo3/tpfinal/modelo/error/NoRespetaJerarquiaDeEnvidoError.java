package fiuba.algo3.tpfinal.modelo.error;

public class NoRespetaJerarquiaDeEnvidoError extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoRespetaJerarquiaDeEnvidoError() {
	}

	public NoRespetaJerarquiaDeEnvidoError(String s) {
		System.err.print(s);
		System.err.println();
	}

}
