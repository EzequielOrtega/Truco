package fiuba.algo3.tpfinal.control.envido;

import fiuba.algo3.tpfinal.modelo.Equipo;
import fiuba.algo3.tpfinal.modelo.JuegoDeTruco;
import fiuba.algo3.tpfinal.vista.Programa;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BotonQuieroEnvidoEventHandler implements EventHandler<ActionEvent> {
	
	private JuegoDeTruco juego;
	private Programa programa;
	
	public BotonQuieroEnvidoEventHandler(JuegoDeTruco juego, Programa programa) {
		this.programa = programa;
		this.juego = juego;
	}

	@Override
	public void handle(ActionEvent event) {
		juego.quieroEnvido();
		//programa.actualizarPuntos();
		//programa.deshabilitarBotonesEnvidoYFlor();
		Alert jerarquiaNoValida = new Alert(AlertType.INFORMATION);
		jerarquiaNoValida.setTitle("Tantos del envido");
		jerarquiaNoValida.setHeaderText("Ganador: " + juego.obtenerNombreGanadorDeEnvido());
		jerarquiaNoValida.setContentText("Puntaje del ganador: " + juego.puntosDeEnvidoGanador() + "\n");
		jerarquiaNoValida.showAndWait();
	}
}