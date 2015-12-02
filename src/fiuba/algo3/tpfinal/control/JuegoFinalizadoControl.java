package fiuba.algo3.tpfinal.control;

import fiuba.algo3.tpfinal.vista.Programa;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.util.Optional;

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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
}
