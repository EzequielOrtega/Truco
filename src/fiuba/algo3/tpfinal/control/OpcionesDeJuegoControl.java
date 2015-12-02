package fiuba.algo3.tpfinal.control;

import fiuba.algo3.tpfinal.modelo.JuegoDeTruco;
import fiuba.algo3.tpfinal.vista.Programa;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OpcionesDeJuegoControl {

    @FXML
    private TextField textJ1E1, textJ2E1, textJ1E2, textJ2E2;
    @FXML
    private CheckBox conFlor;
    @FXML
    private Button botonComenzarJuego;
    private Programa programa;
    private List<TextField> nombresJugadores;
    private int cantidadJugadores;

    @FXML
    private void initialize() {

        this.nombresJugadores = new ArrayList<>(Arrays.asList(this.textJ1E1, this.textJ1E2, this.textJ2E1, this.textJ2E2));
        this.visibilizar(this.nombresJugadores, false);
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    @FXML
    private void opcionDosJugadoresHandler() {
        this.cantidadJugadores = 2;
        this.visibilizar(this.nombresJugadores, false);
        this.nombresJugadores.get(0).setVisible(true);
        this.nombresJugadores.get(1).setVisible(true);
    }

    @FXML
    private void opcionCuatroJugadoresHandler() {
        this.cantidadJugadores = 4;
        this.visibilizar(this.nombresJugadores, true);
    }

    @FXML
    private void comenzarJuegoHandler() throws IOException {

        if(this.cantidadJugadores == 2) {
            JuegoDeTruco juego = new JuegoDeTruco(textJ1E1.getText(), textJ1E2.getText());
            this.programa.comenzarPartidaDosJugadores(juego, conFlor.isSelected());
        }

        if(this.cantidadJugadores == 4) {
            JuegoDeTruco juego = new JuegoDeTruco(textJ1E1.getText(), textJ1E2.getText(), textJ2E1.getText(), textJ2E2.getText());
            this.programa.comenzarPartidaCuatroJugadores(juego, conFlor.isSelected());
        }

    }

    private void visibilizar(List<TextField> elementos, boolean visibilidad) {
        for(Node text : elementos) {
            text.setVisible(visibilidad);
        }
    }

}
