package fiuba.algo3.tpfinal.vista;

import fiuba.algo3.tpfinal.control.OpcionesDeJuegoControl;
import fiuba.algo3.tpfinal.control.TrucoDosJugadoresControl;
import fiuba.algo3.tpfinal.control.VentanaInicialControl;
import fiuba.algo3.tpfinal.modelo.JuegoDeTruco;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Programa extends Application {

    private Stage principal;
    private BorderPane ventanaInicial;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.principal = primaryStage;
        irAVentanaInicial();
        elegirOpcionesDeJuego();

    }

    public void irAVentanaInicial() throws IOException {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Programa.class.getResource("VentanaInicial.fxml"));
            this.ventanaInicial = loader.load();

            VentanaInicialControl controlador = loader.getController();
            controlador.setPrograma(this);

            Scene scene = new Scene(this.ventanaInicial);
            this.principal.setScene(scene);
            this.principal.show();
    }

    public void elegirOpcionesDeJuego() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Programa.class.getResource("OpcionesDeJuego.fxml"));
        AnchorPane opcionesDeJuego = loader.load();
        this.ventanaInicial.setCenter(opcionesDeJuego);
        OpcionesDeJuegoControl controlador = loader.getController();
        controlador.setPrograma(this);

    }

    public void comenzarPartidaDeDos(JuegoDeTruco juego, boolean conFlor) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Programa.class.getResource("TrucoDosJugadores.fxml"));
        AnchorPane partidaDeDos = loader.load();
        this.ventanaInicial.setCenter(partidaDeDos);

        TrucoDosJugadoresControl controlador = loader.getController();
        controlador.setPrograma(this, juego, conFlor);
    }
}