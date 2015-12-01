package fiuba.algo3.tpfinal.control;

import fiuba.algo3.tpfinal.vista.Programa;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
    
    @FXML
    public void acercaDeHandler() {
		Alert jerarquiaNoValida = new Alert(AlertType.INFORMATION);
		jerarquiaNoValida.setTitle("Acerca de FonTruco");
		jerarquiaNoValida.setHeaderText("Grupo 2 Paez");
		jerarquiaNoValida.setContentText("Ezequiel Martin Ortega Mateo" + "\n"
										+ "Marcos Pozzo" + "\n"
										+ "Micaela Lean Cole");
		jerarquiaNoValida.showAndWait();
    }
}