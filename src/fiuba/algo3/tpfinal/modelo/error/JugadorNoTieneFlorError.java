package fiuba.algo3.tpfinal.modelo.error;

public class JugadorNoTieneFlorError extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public JugadorNoTieneFlorError() {
	}
	public JugadorNoTieneFlorError(String s) {
		System.out.print(s);
		System.out.println();
	}

}
