package fiuba.algo3.tpfinal.modelo.error;

public class NoSePuedeRechazarFlorError extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSePuedeRechazarFlorError() {

	}

	public NoSePuedeRechazarFlorError(String s) {
		System.out.print(s);
		System.out.println();
	}
}
