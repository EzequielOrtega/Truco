package fiuba.algo3.tpfinal.control;

import fiuba.algo3.tpfinal.vista.Programa;
import javafx.fxml.FXML;

import java.io.IOException;

public class JuegoFinalizadoControl {

    private Programa programa;

    @FXML
    private void initialize() {

    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    @FXML
    public void opcionNuevoJuegoHandler() throws IOException {
        this.programa.elegirOpcionesDeJuego();
    }

    @FXML
    public void opcionCerrarHandler() {
        System.exit(0);
    }
}
