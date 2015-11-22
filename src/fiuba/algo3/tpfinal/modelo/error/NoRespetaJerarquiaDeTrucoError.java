package fiuba.algo3.tpfinal.modelo.error;

public class NoRespetaJerarquiaDeTrucoError extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoRespetaJerarquiaDeTrucoError() {

	}

	public NoRespetaJerarquiaDeTrucoError(String s) {
		System.out.print(s);
		System.out.println();
	}

}
