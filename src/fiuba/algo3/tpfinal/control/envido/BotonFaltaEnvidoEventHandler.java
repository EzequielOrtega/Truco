package fiuba.algo3.tpfinal.control.envido;

import fiuba.algo3.tpfinal.modelo.JuegoDeTruco;
import fiuba.algo3.tpfinal.modelo.error.NoPuedeCantarEnvidoNoEsPieError;
import fiuba.algo3.tpfinal.modelo.error.NoPuedeCantarEnvidoSeCantoFlorError;
import fiuba.algo3.tpfinal.modelo.error.SoloSePuedeCantarEnvidoEnPrimeraError;
import fiuba.algo3.tpfinal.modelo.error.SoloSePuedeCantarEnvidoUnaVezPorRondaError;
import fiuba.algo3.tpfinal.vista.Programa;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BotonFaltaEnvidoEventHandler implements EventHandler<ActionEvent> {

	private JuegoDeTruco juego;
	@SuppressWarnings("unused")
	private Programa programa;
	
	public BotonFaltaEnvidoEventHandler(JuegoDeTruco juego, Programa programa) {
		this.juego = juego;
		this.programa = programa;
	}
	
	@Override
	public void handle(ActionEvent actionEvent) {
		try {
			juego.faltaEnvido();
			//programa.seCanto("FaltaEnvido");
			//programa.habilitarBotonQuieroEnvido();
		} catch (SoloSePuedeCantarEnvidoEnPrimeraError x1) {
			Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
			jerarquiaNoValida.setTitle("Error");
			jerarquiaNoValida.setHeaderText(null);
			jerarquiaNoValida.setContentText("El falta envido solo se puede cantar en primera");
			jerarquiaNoValida.showAndWait();
		} catch (NoPuedeCantarEnvidoNoEsPieError x2) {
			Alert jugadaNoValida = new Alert(AlertType.ERROR);
			jugadaNoValida.setTitle("Error");
			jugadaNoValida.setHeaderText(null);
			jugadaNoValida.setContentText("Solo el jugador que es pie puede cantar falta envido");
			jugadaNoValida.showAndWait();
		} catch (SoloSePuedeCantarEnvidoUnaVezPorRondaError x3) {
			Alert jugadaNoValida = new Alert(AlertType.ERROR);
			jugadaNoValida.setTitle("Error");
			jugadaNoValida.setHeaderText(null);
			jugadaNoValida.setContentText("Solo se puede cantar envido una vez por ronda");
			jugadaNoValida.showAndWait();
		} catch (NoPuedeCantarEnvidoSeCantoFlorError x4) {
			Alert jugadaNoValida = new Alert(AlertType.ERROR);
			jugadaNoValida.setTitle("Error");
			jugadaNoValida.setHeaderText(null);
			jugadaNoValida.setContentText("Se canto flor, no se puede cantar falta envido");
			jugadaNoValida.showAndWait();
		}
	}
}