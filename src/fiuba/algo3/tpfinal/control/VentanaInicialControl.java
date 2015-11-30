package fiuba.algo3.tpfinal.control;

import fiuba.algo3.tpfinal.vista.Programa;
import javafx.fxml.FXML;

import java.io.IOException;

public class VentanaInicialControl {

    private Programa programa;

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }


    @FXML
    public void nuevoJuegoHandler() throws IOException {
        this.programa.elegirOpcionesDeJuego();

    }

    @FXML
    public void cerrarHandler() {
        System.exit(0);
    }
}
