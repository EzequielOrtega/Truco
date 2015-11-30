package fiuba.algo3.tpfinal.vista;

import fiuba.algo3.tpfinal.modelo.Equipo;
import fiuba.algo3.tpfinal.modelo.JuegoDeTruco;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;

public class Programa extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Stage principal;
    private BorderPane presentacion = new BorderPane();
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

        MenuBar menuBar = new MenuBar();
        // El '_' es un shortcut (Alt + la primer letra del menu)
        Menu menuFile = new Menu("_File");
        Menu menuEdit = new Menu("_Edit");
        Menu menuView = new Menu("_View");

        MenuItem nuevoJuego = new MenuItem("New");
        //nuevoJuego.setOnAction(e -> );
        menuFile.getItems().add(nuevoJuego);
        menuFile.getItems().add(new MenuItem("Open.."));
        menuFile.getItems().add(new MenuItem("Save.."));
        menuFile.getItems().add(new MenuItem("Exit"));

        menuBar.getMenus().addAll(menuFile, menuEdit, menuView);

        ChoiceBox<String> cantidadJugadores = new ChoiceBox<>();
        cantidadJugadores.getItems().addAll("2 jugadores", "4 jugadores");
        cantidadJugadores.setValue("2 jugadores");
        GridPane.setConstraints(cantidadJugadores, 1, 0);
        //cantidadJugadores.getSelectionModel().selectedItemProperty().addListener( (v, valAnterior, valNuevo) -> {
        //});

        Label j1Label = new Label("Jugador 1: ");
        GridPane.setConstraints(j1Label, 0, 1);

        TextField j1Input = new TextField();
        j1Input.setPromptText("Ingrese un nombre");
        GridPane.setConstraints(j1Input, 1, 1);

        Label j2Label = new Label("Jugador 2::");
        GridPane.setConstraints(j2Label, 0, 2);

        TextField j2Input = new TextField();
        j2Input.setPromptText("Ingrese un nombre");
        GridPane.setConstraints(j2Input, 1, 2);

        CheckBox conFlor = new CheckBox("Con Flor");
        GridPane.setConstraints(conFlor, 1, 3);
        Button nuevoJuegoButton = new Button("Iniciar juego");
        nuevoJuegoButton.setOnAction(e -> {
            System.out.println("Nombre j1: " + j1Input.getText());
            System.out.println("Nombre j2: " + j2Input.getText());
            JuegoDeTruco juego = new JuegoDeTruco(j1Input.getText(), j2Input.getText());
            //handleOptions(conFlor);
        });
        GridPane.setConstraints(nuevoJuegoButton, 1, 4);

        presentacion.setTop(menuBar);
        // Agrego al gridPane
        grid.getChildren().addAll(cantidadJugadores, j1Label, j1Input, j2Label, j2Input, conFlor, nuevoJuegoButton);
        presentacion.setCenter(grid);
        Scene scene = new Scene(presentacion, 400, 300);

        URL url = this.getClass().getResource("AlgoTruco.css");
        String css = url.toExternalForm();
        scene.getStylesheets().add(css);
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