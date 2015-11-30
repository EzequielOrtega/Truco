package fiuba.algo3.tpfinal.control.flor;

import java.util.LinkedList;

import fiuba.algo3.tpfinal.modelo.JuegoDeTruco;
import fiuba.algo3.tpfinal.modelo.error.JugadorNoTieneFlorError;
import fiuba.algo3.tpfinal.modelo.error.NoRespetaJerarquiaDeFlorError;
import fiuba.algo3.tpfinal.modelo.error.NoSePuedeRechazarFlorError;
import fiuba.algo3.tpfinal.modelo.error.SeEstaJugandoSinFlorError;
import fiuba.algo3.tpfinal.modelo.error.SoloSePuedeCantarFlorEnPrimeraError;
import fiuba.algo3.tpfinal.modelo.error.SoloSePuedeCantarFlorUnaVezPorRondaError;
import fiuba.algo3.tpfinal.modelo.flor.ContraFlor;
import fiuba.algo3.tpfinal.modelo.flor.ContraFlorAlResto;
import fiuba.algo3.tpfinal.vista.Programa;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BotonFlorEventHandler implements EventHandler<ActionEvent> {
	
	private JuegoDeTruco juego;
	private Programa programa;
	
	public BotonFlorEventHandler(JuegoDeTruco juego, Programa programa) {
		this.programa = programa;
		this.juego = juego;
	}

	@Override
	public void handle(ActionEvent event) {
		try {
			juego.flor();
			//programa.habilitarBotonesQuieroFlor();
		} catch (SeEstaJugandoSinFlorError x1) {
			Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
			jerarquiaNoValida.setTitle("Error");
			jerarquiaNoValida.setHeaderText(null);
			jerarquiaNoValida.setContentText("Se esta jugando sin flor");
			jerarquiaNoValida.showAndWait();
		} catch (SoloSePuedeCantarFlorEnPrimeraError x2) {
			Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
			jerarquiaNoValida.setTitle("Error");
			jerarquiaNoValida.setHeaderText(null);
			jerarquiaNoValida.setContentText("Solo se puede cantar flor en primera");
			jerarquiaNoValida.showAndWait();
		} catch (SoloSePuedeCantarFlorUnaVezPorRondaError x3) {
			Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
			jerarquiaNoValida.setTitle("Error");
			jerarquiaNoValida.setHeaderText(null);
			jerarquiaNoValida.setContentText("Solo se puede cantar flor una vez por ronda");
			jerarquiaNoValida.showAndWait();
		} catch (JugadorNoTieneFlorError x4) {
			Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
			jerarquiaNoValida.setTitle("Error");
			jerarquiaNoValida.setHeaderText(null);
			jerarquiaNoValida.setContentText("No tiene flor");
			jerarquiaNoValida.showAndWait();
		} catch (NoRespetaJerarquiaDeFlorError x5) {
			Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
			jerarquiaNoValida.setTitle("Error");
			jerarquiaNoValida.setHeaderText(null);
			jerarquiaNoValida.setContentText("No respeta la jerarquia de flor");
			jerarquiaNoValida.showAndWait();
		}
	}
}