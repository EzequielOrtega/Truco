package fiuba.algo3.tpfinal.control;

import fiuba.algo3.tpfinal.vista.Programa;
import javafx.fxml.FXML;

public class VentanaInicialControl {

    private Programa programa;

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    @FXML
    public void salirHandler() {
        System.exit(0);
    }
}
