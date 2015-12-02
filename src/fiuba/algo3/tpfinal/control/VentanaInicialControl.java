package fiuba.algo3.tpfinal.control;

import fiuba.algo3.tpfinal.vista.Programa;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.Optional;

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
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("Está seguro que desea cerrar?");

        Optional<ButtonType> resultado = alert.showAndWait();
        if (resultado.get() == ButtonType.OK){
            System.exit(0);
        } else {
            // No hace nada, sigue en la ventana
        }
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