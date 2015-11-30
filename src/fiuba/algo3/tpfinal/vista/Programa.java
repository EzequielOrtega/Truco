package fiuba.algo3.tpfinal.vista;

import fiuba.algo3.tpfinal.control.VentanaInicialControl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Programa extends Application {

    private Stage principal;
    private BorderPane presentacion = new BorderPane();
    private BorderPane ventanaInicial;
    private FXMLLoader loader = new FXMLLoader();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.principal = primaryStage;
        irAVentanaInicial();

    }

    public void irAVentanaInicial() throws IOException {
            loader.setLocation(Programa.class.getResource("VentanaInicial.fxml"));
            this.ventanaInicial = loader.load();

            VentanaInicialControl controlador = loader.getController();
            controlador.setPrograma(this);

            Scene scene = new Scene(this.ventanaInicial);
            this.principal.setScene(scene);
            this.principal.show();
    }
}