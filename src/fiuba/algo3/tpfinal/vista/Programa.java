package fiuba.algo3.tpfinal.vista;

import fiuba.algo3.tpfinal.modelo.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Programa extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Stage principal;
    private BorderPane disenioRaiz;
    private JuegoDeTruco juego;
    private Equipo equipo1;
    private Equipo equipo2;

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.principal = primaryStage;
        this.principal.setTitle("Truco argentino");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label j1Label = new Label("Jugador 1: ");
        GridPane.setConstraints(j1Label, 0, 0);

        TextField j1Input = new TextField();
        j1Input.setPromptText("Ingrese un nombre");
        GridPane.setConstraints(j1Input, 1, 0);

        Label j2Label = new Label("Jugador 2::");
        GridPane.setConstraints(j2Label, 0, 1);

        TextField j2Input = new TextField();
        j2Input.setPromptText("Ingrese un nombre");
        GridPane.setConstraints(j2Input, 1, 1);

        CheckBox conFlor = new CheckBox("Con Flor");
        GridPane.setConstraints(conFlor, 1, 2);
        Button nuevoJuegoButton = new Button("Iniciar juego");
        nuevoJuegoButton.setOnAction(e -> {
            System.out.println("Nombre j1: " + j1Input.getText());
            System.out.println("Nombre j2: " + j2Input.getText());
            JuegoDeTruco juego = new JuegoDeTruco(j1Input.getText(), j2Input.getText());
            //handleOptions(conFlor);
        });
        GridPane.setConstraints(nuevoJuegoButton, 1, 3);

        // Agrego al gridPane
        grid.getChildren().addAll(j1Label, j1Input, j2Label, j2Input, conFlor, nuevoJuegoButton);

        Scene scene = new Scene(grid, 300, 200);
        principal.setScene(scene);
        principal.show();

    }

//    private void handleOptions(CheckBox conFlor) {
//        if (conFlor.isSelected()) {
//            juego.comenzarPartida(true);
//        }
//        else { juego.comenzarPartida(false);}
//    }
}