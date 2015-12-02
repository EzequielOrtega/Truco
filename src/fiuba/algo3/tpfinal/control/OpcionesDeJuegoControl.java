package fiuba.algo3.tpfinal.control;

import fiuba.algo3.tpfinal.modelo.JuegoDeTruco;
import fiuba.algo3.tpfinal.vista.Programa;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import java.io.IOException;
import java.util.*;

public class OpcionesDeJuegoControl {

    private Programa programa;
    private List<TextField> nombresJugadores;
    private int cantidadDeJugadores;

    @FXML private TextField textJ1E1, textJ2E1, textJ1E2, textJ2E2;
    @FXML private CheckBox conFlor;
    @FXML private Button botonComenzarJuego;

    @FXML
    private void initialize() {

        this.nombresJugadores = new ArrayList<>(Arrays.asList(this.textJ1E1, this.textJ1E2, this.textJ2E1, this.textJ2E2));
        this.visibilizar(this.nombresJugadores, false);
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    @FXML
    public void opcionDosJugadoresHandler() {
        this.cantidadDeJugadores = 2;
        this.visibilizar(this.nombresJugadores, false);
        this.nombresJugadores.get(0).setVisible(true);
        this.nombresJugadores.get(1).setVisible(true);
    }

    @FXML
    public void opcionCuatroJugadoresHandler() {
        this.cantidadDeJugadores = 4;
        this.visibilizar(this.nombresJugadores, true);
    }

    @FXML
    public void comenzarJuegoHandler() throws IOException {

        if(this.cantidadDeJugadores == 2) {
            JuegoDeTruco juego = new JuegoDeTruco(textJ1E1.getText(), textJ1E2.getText());
            this.programa.comenzarPartidaDosJugadores(juego, conFlor.isSelected());
        }

        if(this.cantidadDeJugadores == 4) {
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
