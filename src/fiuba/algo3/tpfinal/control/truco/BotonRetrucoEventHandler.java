package fiuba.algo3.tpfinal.control.truco;

import fiuba.algo3.tpfinal.modelo.JuegoDeTruco;
import fiuba.algo3.tpfinal.modelo.error.NoRespetaJerarquiaDeTrucoError;
import fiuba.algo3.tpfinal.vista.Programa;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BotonRetrucoEventHandler implements EventHandler<ActionEvent> {
	
	private JuegoDeTruco juego;
	private Programa programa;
	
	public BotonRetrucoEventHandler(JuegoDeTruco juego, Programa programa) {
		this.programa = programa;
		this.juego = juego;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		try {
			juego.reTruco();
			//programa.seCanto("ReTruco");
			//programa.habilitarBotonQuieroTruco();
		} catch (NoRespetaJerarquiaDeTrucoError x1) {
			Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
			jerarquiaNoValida.setTitle("Error");
			jerarquiaNoValida.setHeaderText(null);
			jerarquiaNoValida.setContentText("No respeta la jerarquia del truco");
			jerarquiaNoValida.showAndWait();
		}
	}

}
