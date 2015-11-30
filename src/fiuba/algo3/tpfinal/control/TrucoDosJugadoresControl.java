package fiuba.algo3.tpfinal.control;

import fiuba.algo3.tpfinal.modelo.Carta;
import fiuba.algo3.tpfinal.modelo.Equipo;
import fiuba.algo3.tpfinal.modelo.JuegoDeTruco;
import fiuba.algo3.tpfinal.modelo.Jugador;
import fiuba.algo3.tpfinal.vista.Programa;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrucoDosJugadoresControl {

    @FXML
    protected Label turno;
    @FXML
    protected Label puntajeEquipo1, puntajeEquipo2;
    @FXML
    protected Button botonMostrarCartas;
    @FXML
    protected Button botonCarta1, botonCarta2, botonCarta3;
    @FXML
    protected Button botonCartaJugada1J1, botonCartaJugada2J1, botonCartaJugada3J1;
    @FXML
    protected Button botonCartaJugada1J2, botonCartaJugada2J2, botonCartaJugada3J2;
    @FXML
    protected List<Button> botonesCartasJugadorActual;
    @FXML
    protected List<Button> botonesCartasJugadasJugador1, botonesCartasJugadasJugador2;
    @FXML
    protected List<List<Button>> botonesCartasJugadas;
    @FXML
    protected Button botonFlor, botonContraFlor, botonContraFlorAlResto;
    @FXML
    protected Button botonEnvido, botonRealEnvido, botonFaltaEnvido;
    @FXML
    protected Button botonTruco, botonReTruco, botonValeCuatro;
    @FXML
    protected Button botonQuieroTruco, botonMeVoyAlMazo, botonQuieroEnvido, botonQuieroFlor, botonNoQuieroTanto;

    protected JuegoDeTruco juego;
    protected Programa programa;
    protected List<Carta> cartasJugadorActual;
    //protected DiccionarioCartas diccionarioCartas;
    protected int cantidadJugadasJugador1 = 0;
    protected int cantidadJugadasJugador2 = 0;
    protected List<Integer> cantidadJugadasJugador;
    protected Jugador jugadorPrevio;
    protected int indiceJugador = 0;
    protected List<Button> botonesEnvido;
    protected List<Button> botonesFlor;
    protected List<Button> botonesTanto;
    protected List<Button> botonesTruco;
    protected List<Button> botonesQuiero;
    //protected DiccionarioEstadosJuego diccionarioEstadosJuego;

    @FXML
    protected void initialize() {

        this.botonesCartasJugadorActual = new ArrayList<>(Arrays.asList(this.botonCarta1, this.botonCarta2, this.botonCarta3));
        this.botonesCartasJugadasJugador1 = new ArrayList<>(Arrays.asList(this.botonCartaJugada1J1, this.botonCartaJugada2J1, this.botonCartaJugada3J1));
        this.botonesCartasJugadasJugador2 = new ArrayList<>(Arrays.asList(this.botonCartaJugada1J2, this.botonCartaJugada2J2, this.botonCartaJugada3J2));
        this.botonesCartasJugadas = new ArrayList<>(Arrays.asList(this.botonesCartasJugadasJugador1, this.botonesCartasJugadasJugador2));
        this.cantidadJugadasJugador = new ArrayList<>(Arrays.asList(this.cantidadJugadasJugador1, this.cantidadJugadasJugador2));
        //this.diccionarioCartas = new DiccionarioCartas();
        this.botonesEnvido = new ArrayList<>(Arrays.asList(this.botonEnvido, this.botonRealEnvido, this.botonFaltaEnvido));
        this.botonesFlor = new ArrayList<>(Arrays.asList(this.botonFlor, this.botonContraFlor, this.botonContraFlorAlResto));
        this.botonesTanto = new ArrayList<>(this.botonesEnvido);
        this.botonesTanto.addAll(this.botonesFlor);
        this.botonesTruco = new ArrayList<>(Arrays.asList(this.botonTruco, this.botonReTruco, this.botonValeCuatro));
        this.botonesQuiero = new ArrayList<>(Arrays.asList(this.botonQuieroTruco, this.botonMeVoyAlMazo, this.botonQuieroEnvido, this.botonQuieroFlor, this.botonNoQuieroTanto));

        //this.diccionarioEstadosJuego = new DiccionarioEstadosJuego(this.botonesTruco, this.botonesTanto, this.botonesQuiero);

        //this.visibilizarBotones(this.botonesQuiero, false);
//        this.visibilizarBotones(Arrays.asList(this.botonReTruco, this.botonValeCuatro), false);

    }


    public void setPrograma(Programa programa, JuegoDeTruco juego, boolean conFlor) {

        this.programa = programa;
        this.juego = juego;
        juego.comenzarPartida(conFlor);
        this.mostrarPuntos();

        //this.visibilizarBotones(this.botonesFlor, conFlor);
        //this.visibilizarBotones(Arrays.asList(this.botonContraFlor, this.botonContraFlorAlResto), false);

        this.jugadorPrevio = this.juego.obtenerJugadorActual();
       // this.mostrarJugadorActual();

    }

    protected void mostrarPuntos() {
        this.puntajeEquipo1.setText(Integer.toString(this.juego.puntosDeEquipo(Equipo.EQUIPO1)));
        this.puntajeEquipo2.setText(Integer.toString(this.juego.puntosDeEquipo(Equipo.EQUIPO2)));
    }


    protected void visibilizarBotones(List<Button> botones, boolean visibilidad) {
        for(Button boton : botones) {
            boton.setVisible(visibilidad);
        }
    }

    protected void desactivarBotones(List<Button> botones, boolean activar) {
        for(Button boton : botones) {
            boton.setDisable(activar);
        }
    }

}