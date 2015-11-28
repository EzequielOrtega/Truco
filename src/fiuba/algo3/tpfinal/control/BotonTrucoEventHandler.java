package fiuba.algo3.tpfinal.control;

import fiuba.algo3.tpfinal.modelo.JuegoDeTruco;
import fiuba.algo3.tpfinal.modelo.error.NoPuedeCantarTrucoSeCantoEnvidoError;
import fiuba.algo3.tpfinal.modelo.error.NoPuedeCantarTrucoSeCantoFlorError;
import fiuba.algo3.tpfinal.modelo.error.NoRespetaJerarquiaDeTrucoError;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BotonTrucoEventHandler implements EventHandler<ActionEvent>{
	
	private JuegoDeTruco juego;
	
	public BotonTrucoEventHandler(JuegoDeTruco juego) {
		this.juego = juego;
	}
	
	@Override
	public void handle(ActionEvent actionEvent) {
		try {
			juego.truco();
		} catch (NoRespetaJerarquiaDeTrucoError x1) {
			Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
			jerarquiaNoValida.setTitle("Error");
			jerarquiaNoValida.setHeaderText(null);
			jerarquiaNoValida.setContentText("No respeta la jerarquia del truco");
			jerarquiaNoValida.showAndWait();
		} catch (NoPuedeCantarTrucoSeCantoEnvidoError x2) {
			Alert jugadaNoValida = new Alert(AlertType.ERROR);
			jugadaNoValida.setTitle("Error");
			jugadaNoValida.setHeaderText("No puede cantar truco, se canto envido");
			jugadaNoValida.setContentText("Debe responderle al equipo contrario");
			jugadaNoValida.showAndWait();
		} catch (NoPuedeCantarTrucoSeCantoFlorError x3) {
			Alert jugadaNoValida = new Alert(AlertType.ERROR);
			jugadaNoValida.setTitle("Error");
			jugadaNoValida.setHeaderText("No puede cantar truco, se canto flor");
			jugadaNoValida.setContentText("Debe responderle al equipo contrario");
			jugadaNoValida.showAndWait();
		}
	}

}
