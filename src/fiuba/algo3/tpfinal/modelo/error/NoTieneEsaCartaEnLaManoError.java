package fiuba.algo3.tpfinal.modelo.error;

public class NoTieneEsaCartaEnLaManoError extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoTieneEsaCartaEnLaManoError() {
	}

	public NoTieneEsaCartaEnLaManoError(String s) {
		System.err.print(s);
		System.err.println();
	}

}
