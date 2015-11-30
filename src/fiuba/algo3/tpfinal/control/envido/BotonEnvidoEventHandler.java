package fiuba.algo3.tpfinal.control.envido;

import fiuba.algo3.tpfinal.modelo.JuegoDeTruco;
import fiuba.algo3.tpfinal.modelo.error.NoPuedeCantarEnvidoNoEsPieError;
import fiuba.algo3.tpfinal.modelo.error.NoPuedeCantarEnvidoSeCantoFlorError;
import fiuba.algo3.tpfinal.modelo.error.NoRespetaJerarquiaDeEnvidoError;
import fiuba.algo3.tpfinal.modelo.error.SoloSePuedeCantarEnvidoEnPrimeraError;
import fiuba.algo3.tpfinal.modelo.error.SoloSePuedeCantarEnvidoUnaVezPorRondaError;
import fiuba.algo3.tpfinal.vista.Programa;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BotonEnvidoEventHandler implements EventHandler<ActionEvent> {

	private JuegoDeTruco juego;
	private Programa programa;
	
	public BotonEnvidoEventHandler(JuegoDeTruco juego, Programa programa) {
		this.juego = juego;
		this.programa = programa;
	}
	
	@Override
	public void handle(ActionEvent actionEvent) {
		try {
			juego.envido();
			//programa.seCanto("Envido");
		} catch (SoloSePuedeCantarEnvidoEnPrimeraError x1) {
			Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
			jerarquiaNoValida.setTitle("Error");
			jerarquiaNoValida.setHeaderText(null);
			jerarquiaNoValida.setContentText("El envido solo se puede cantar en primera");
			jerarquiaNoValida.showAndWait();
		} catch (NoPuedeCantarEnvidoNoEsPieError x2) {
			Alert jugadaNoValida = new Alert(AlertType.ERROR);
			jugadaNoValida.setTitle("Error");
			jugadaNoValida.setHeaderText(null);
			jugadaNoValida.setContentText("Solo el jugador que es pie puede cantar envido");
			jugadaNoValida.showAndWait();
		} catch (NoRespetaJerarquiaDeEnvidoError x3) {
			Alert jugadaNoValida = new Alert(AlertType.ERROR);
			jugadaNoValida.setTitle("Error");
			jugadaNoValida.setHeaderText(null);
			jugadaNoValida.setContentText("No respeta la jerarquia del envido");
			jugadaNoValida.showAndWait();
		} catch (SoloSePuedeCantarEnvidoUnaVezPorRondaError x4) {
			Alert jugadaNoValida = new Alert(AlertType.ERROR);
			jugadaNoValida.setTitle("Error");
			jugadaNoValida.setHeaderText(null);
			jugadaNoValida.setContentText("Solo se puede cantar envido una vez por ronda");
			jugadaNoValida.showAndWait();
		} catch (NoPuedeCantarEnvidoSeCantoFlorError x5) {
			Alert jugadaNoValida = new Alert(AlertType.ERROR);
			jugadaNoValida.setTitle("Error");
			jugadaNoValida.setHeaderText(null);
			jugadaNoValida.setContentText("Se canto flor, no se puede cantar envido");
			jugadaNoValida.showAndWait();
		}
	}
}
