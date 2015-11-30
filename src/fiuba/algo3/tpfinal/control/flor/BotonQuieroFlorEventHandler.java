package fiuba.algo3.tpfinal.control.flor;

import java.util.LinkedList;

import fiuba.algo3.tpfinal.modelo.Equipo;
import fiuba.algo3.tpfinal.modelo.JuegoDeTruco;
import fiuba.algo3.tpfinal.modelo.flor.ContraFlor;
import fiuba.algo3.tpfinal.modelo.flor.ContraFlorAlResto;
import fiuba.algo3.tpfinal.vista.Programa;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BotonQuieroFlorEventHandler implements EventHandler<ActionEvent> {
	
	private JuegoDeTruco juego;
	private Programa programa;
	private static LinkedList<Object> estadosMostrarGanador;
	
	static {
		estadosMostrarGanador = new LinkedList<Object>();
		estadosMostrarGanador.add(ContraFlor.class);
		estadosMostrarGanador.add(ContraFlorAlResto.class);
	}
	
	public BotonQuieroFlorEventHandler(JuegoDeTruco juego, Programa programa) {
		this.programa = programa;
		this.juego = juego;
	}

	@Override
	public void handle(ActionEvent event) {
		juego.quieroFlor();
		//programa.actualizarPuntos();
		//programa.deshabilitarBotonesEnvidoYFlor();
		if (estadosMostrarGanador.contains(juego.obtenerEstadoDeFlor().getClass())) {
			Alert jerarquiaNoValida = new Alert(AlertType.INFORMATION);
			jerarquiaNoValida.setTitle("Tantos de la flor");
			jerarquiaNoValida.setHeaderText("Ganador: " + juego.obtenerNombreGanadorDeFlor());
			jerarquiaNoValida.setContentText("Puntaje del ganador: " + juego.puntosDeFlorGanador() + "\n");
			jerarquiaNoValida.showAndWait();
		}
	}
}