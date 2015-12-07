package fiuba.algo3.tpfinal.control;

import fiuba.algo3.tpfinal.vista.Programa;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.util.Optional;

public class JuegoFinalizadoControl {

	private Programa programa;
	@FXML
	private Label labelGanador;

	@FXML
	private void initialize() {

	}

	public void setPrograma(Programa programa, String ganador) {
		this.programa = programa;
		this.labelGanador.setText(ganador);
	}

	@FXML
	public void opcionNuevoJuegoHandler() throws IOException {
		this.programa.elegirOpcionesDeJuego();
	}

	@FXML
	public void opcionCerrarHandler() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmacion");
		alert.setHeaderText(null);
		alert.setContentText("Esta seguro que desea cerrar?");

		Optional<ButtonType> resultado = alert.showAndWait();
		if (resultado.get() == ButtonType.OK) {
			System.exit(0);
		} else {
			// No hace nada, sigue en la ventana
		}
	}
}
