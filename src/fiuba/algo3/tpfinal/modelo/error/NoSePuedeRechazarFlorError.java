package fiuba.algo3.tpfinal.modelo.error;

public class NoSePuedeRechazarFlorError extends RuntimeException {


    public NoSePuedeRechazarFlorError() {

    }

    public NoSePuedeRechazarFlorError(String s) {
        System.out.print(s);
        System.out.println();
    }
}
