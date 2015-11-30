package fiuba.algo3.tpfinal.control.flor;

import java.util.LinkedList;

import fiuba.algo3.tpfinal.modelo.JuegoDeTruco;
import fiuba.algo3.tpfinal.modelo.error.NoSePuedeRechazarFlorError;
import fiuba.algo3.tpfinal.modelo.flor.ContraFlor;
import fiuba.algo3.tpfinal.modelo.flor.ContraFlorAlResto;
import fiuba.algo3.tpfinal.vista.Programa;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BotonNoQuieroFlorEventHandler implements EventHandler<ActionEvent> {
	
	private JuegoDeTruco juego;
	private Programa programa;
	
	public BotonNoQuieroFlorEventHandler(JuegoDeTruco juego, Programa programa) {
		this.programa = programa;
		this.juego = juego;
	}

	@Override
	public void handle(ActionEvent event) {
		try {
			juego.noQuieroFlor();
			//programa.actualizarPuntos();
			//programa.deshabilitarBotonesEnvidoYFlor();
		} catch (NoSePuedeRechazarFlorError x1) {
			Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
			jerarquiaNoValida.setTitle("Error");
			jerarquiaNoValida.setHeaderText(null);
			jerarquiaNoValida.setContentText("No se puede rechazar la flor");
			jerarquiaNoValida.showAndWait();
		}
	}
}