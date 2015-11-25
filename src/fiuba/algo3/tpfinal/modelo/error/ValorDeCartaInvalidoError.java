package fiuba.algo3.tpfinal.modelo.error;

public class ValorDeCartaInvalidoError extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValorDeCartaInvalidoError() {

	}

	public ValorDeCartaInvalidoError(String s) {
		System.err.print(s);
		System.err.println();
	}

}
