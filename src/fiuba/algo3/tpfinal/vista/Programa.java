package fiuba.algo3.tpfinal.vista;

import fiuba.algo3.tpfinal.control.JuegoFinalizadoControl;
import fiuba.algo3.tpfinal.control.OpcionesDeJuegoControl;
import fiuba.algo3.tpfinal.control.JuegoDeTrucoControl;
import fiuba.algo3.tpfinal.control.VentanaPrincipalControl;
import fiuba.algo3.tpfinal.modelo.JuegoDeTruco;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Programa extends Application {

	private BorderPane ventanaPrincipal;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Programa.class.getResource("VentanaPrincipal.fxml"));
		this.ventanaPrincipal = loader.load();
		VentanaPrincipalControl controlador = loader.getController();
		controlador.setPrograma(this);
		primaryStage.setScene(new Scene(this.ventanaPrincipal));
		primaryStage.show();

	}

	public void elegirOpcionesDeJuego() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Programa.class.getResource("OpcionesDeJuego.fxml"));
		AnchorPane opcionesDeJuego = loader.load();
		this.ventanaPrincipal.setCenter(opcionesDeJuego);
		OpcionesDeJuegoControl controlador = loader.getController();
		controlador.setPrograma(this);

	}

	public void comenzarPartidaDosJugadores(JuegoDeTruco juego, boolean conFlor) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Programa.class.getResource("TrucoDosJugadores.fxml"));
		AnchorPane partidaDosJugadores = loader.load();
		this.ventanaPrincipal.setCenter(partidaDosJugadores);
		JuegoDeTrucoControl controlador = loader.getController();
		controlador.setPrograma(this, juego, conFlor);
	}

	public void comenzarPartidaCuatroJugadores(JuegoDeTruco juego, boolean conFlor) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Programa.class.getResource("TrucoCuatroJugadores.fxml"));
		AnchorPane partidaCuatroJugadores = loader.load();
		this.ventanaPrincipal.setCenter(partidaCuatroJugadores);
		JuegoDeTrucoControl controlador = loader.getController();
		controlador.setPrograma(this, juego, conFlor);
	}

	public void juegoFinalizado(String ganador) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Programa.class.getResource("JuegoFinalizado.fxml"));
		AnchorPane juegoFinalizado = loader.load();
		this.ventanaPrincipal.setCenter(juegoFinalizado);
		JuegoFinalizadoControl controlador = loader.getController();
		controlador.setPrograma(this, ganador);
	}

}