package fiuba.algo3.tpfinal.control.truco;

import fiuba.algo3.tpfinal.modelo.Equipo;
import fiuba.algo3.tpfinal.modelo.JuegoDeTruco;
import fiuba.algo3.tpfinal.vista.Programa;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BotonNoQuieroTrucoEventHandler implements EventHandler<ActionEvent> {
	
	private JuegoDeTruco juego;
	private Programa programa;
	
	public BotonNoQuieroTrucoEventHandler(JuegoDeTruco juego, Programa programa) {
		this.programa = programa;
		this.juego = juego;
	}

	@Override
	public void handle(ActionEvent arg0) {
		juego.noQuieroTruco();
		//programa.seCanto("");		
		//programa.deshabilitarBotonQuieroTruco();
		//programa.actualizarPuntajes();
		if (juego.concluyoLaPartida()) {
			//programa.deshabilitarTodosLosBotones();
			Alert jerarquiaNoValida = new Alert(AlertType.INFORMATION);
			jerarquiaNoValida.setTitle("La partida ha concluido");
			jerarquiaNoValida.setHeaderText("Puntajes: ");
			jerarquiaNoValida.setContentText("Puntaje equipo 1: " + juego.puntosDeEquipo(Equipo.EQUIPO1) + "\n"
										   + "Puntaje equipo 2: " + juego.puntosDeEquipo(Equipo.EQUIPO2) + "\n");
			jerarquiaNoValida.showAndWait();
		} else {
			//programa.prepararProximaRonda();
		}
	}
}