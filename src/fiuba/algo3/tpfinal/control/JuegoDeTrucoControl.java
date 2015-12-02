package fiuba.algo3.tpfinal.control;

import fiuba.algo3.tpfinal.modelo.*;
import fiuba.algo3.tpfinal.modelo.error.*;
import fiuba.algo3.tpfinal.modelo.flor.*;
import fiuba.algo3.tpfinal.vista.Programa;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Node;

import java.io.IOException;
import java.util.*;

public class JuegoDeTrucoControl {

    private JuegoDeTruco juego;
    private Programa programa;
    private Jugador jugadorActual;
    private List<Carta> cartasJugadorActual;
    private List<Button> botonesFlor, botonesEnvido, botonesTruco, botonesQuiero;
    private boolean conFlor;

    @FXML private Label labelTurno, labelStatus;
    @FXML private Label labelPuntajeEquipo1, labelPuntajeEquipo2;
    @FXML private Button botonCarta1, botonCarta2, botonCarta3;
    @FXML private Button botonCartaJugada1J1, botonCartaJugada2J1, botonCartaJugada3J1;
    @FXML private Button botonCartaJugada1J2, botonCartaJugada2J2, botonCartaJugada3J2;
    @FXML private Button botonCartaJugada1J3, botonCartaJugada2J3, botonCartaJugada3J3;
    @FXML private Button botonCartaJugada1J4, botonCartaJugada2J4, botonCartaJugada3J4;
    @FXML private List<Button> botonesCartasJugadorActual;
    @FXML private List<Button> botonesCartasJugadasJugador1, botonesCartasJugadasJugador2, botonesCartasJugadasJugador3, botonesCartasJugadasJugador4;
    @FXML private List<List<Button>> botonesCartasJugadas;
    @FXML private Button botonFlor, botonContraFlor, botonContraFlorAlResto, botonQuieroFlor, botonNoQuieroFlor;
    @FXML private Button botonEnvido, botonRealEnvido, botonFaltaEnvido, botonQuieroEnvido, botonNoQuieroEnvido;
    @FXML private Button botonTruco, botonReTruco, botonValeCuatro, botonQuieroTruco, botonNoQuieroTruco;

    @FXML
    private void initialize() {

        this.botonesFlor = new ArrayList<>(Arrays.asList(this.botonFlor, this.botonContraFlor, this.botonContraFlorAlResto));
        this.botonesEnvido = new ArrayList<>(Arrays.asList(this.botonEnvido, this.botonRealEnvido, this.botonFaltaEnvido));
        this.botonesTruco = new ArrayList<>(Arrays.asList(this.botonTruco, this.botonReTruco, this.botonValeCuatro));
        this.botonesQuiero = new ArrayList<>(Arrays.asList(this.botonQuieroTruco, this.botonNoQuieroTruco, this.botonQuieroEnvido, this.botonNoQuieroEnvido, this.botonQuieroFlor, this.botonNoQuieroFlor));

        this.botonesCartasJugadorActual = new ArrayList<>(Arrays.asList(this.botonCarta1, this.botonCarta2, this.botonCarta3));
        this.botonesCartasJugadasJugador1 = new ArrayList<>(Arrays.asList(this.botonCartaJugada1J1, this.botonCartaJugada2J1, this.botonCartaJugada3J1));
        this.botonesCartasJugadasJugador2 = new ArrayList<>(Arrays.asList(this.botonCartaJugada1J2, this.botonCartaJugada2J2, this.botonCartaJugada3J2));
        this.botonesCartasJugadasJugador3 = new ArrayList<>(Arrays.asList(this.botonCartaJugada1J3, this.botonCartaJugada2J3, this.botonCartaJugada3J3));
        this.botonesCartasJugadasJugador4 = new ArrayList<>(Arrays.asList(this.botonCartaJugada1J4, this.botonCartaJugada2J4, this.botonCartaJugada3J4));
        this.botonesCartasJugadas = new ArrayList<>(Arrays.asList(this.botonesCartasJugadasJugador1, this.botonesCartasJugadasJugador2));
    }

    public void setPrograma(Programa programa, JuegoDeTruco juego, boolean conFlor) {
        this.programa = programa;
        this.juego = juego;
        this.conFlor = conFlor;
        juego.comenzarPartida(conFlor);

        this.mostrarTodosLosBotones(false);
        this.botonFlor.setVisible(conFlor);
        this.mostrarBotones(this.botonesEnvido, true);
        this.botonTruco.setVisible(true);
        this.mostrarPuntos();
        this.mostrarJugadorActual();
    }

    private void mostrarJugadorActual() {
        this.jugadorActual = juego.obtenerJugadorActual();
        this.labelTurno.setText(this.jugadorActual.getNombre());
        this.cartasJugadorActual = jugadorActual.mostrarCartas();
        for(Button boton : this.botonesCartasJugadorActual) {
            boton.setText("Carta dada vuelta");
            boton.setDisable(true);
        }
        this.mostrarTodosLosBotones(true);
        if (!juego.estaEnPrimera()) {
            this.mostrarBotones(botonesEnvido, false);
            this.mostrarBotones(botonesFlor, false);
        }
    }

    private void mostrarBotones(List<Button> botones, boolean mostrar) {
        for(Node boton : botones) {
            boton.setVisible(mostrar);
        }
    }

    private void mostrarTodosLosBotones(Boolean mostrar) {
        this.mostrarBotones(this.botonesFlor, this.conFlor);
        this.mostrarBotones(this.botonesEnvido, mostrar);
        this.mostrarBotones(this.botonesTruco, mostrar);
        this.mostrarBotones(this.botonesQuiero, mostrar);
    }

    public void mostrarPuntos() {
        this.labelPuntajeEquipo1.setText(Integer.toString(this.juego.puntosDeEquipo(Equipo.EQUIPO1)));
        this.labelPuntajeEquipo2.setText(Integer.toString(this.juego.puntosDeEquipo(Equipo.EQUIPO2)));
    }

    private void ponerCartaEnLaMesa(Carta carta) {
        // TODO: implementar un diccionario para que pueda decir el string de la carta
        int cantidad = this.jugadorActual.cantidadDeCartasJugadas();
        Button botonCartaAPonerEnLaMesa = botonesCartasJugadorActual.get(cantidad);
        botonCartaAPonerEnLaMesa.setVisible(true);
    }

    public void desactivarBotones(List<Button> botones, boolean desactivar) {
        for(Button boton : botones) {
            boton.setDisable(desactivar);
        }
    }

    private void setNuevaRonda() {

        int cantidadJugadores = 2; // Por ahora
        for(int i = 0; i < cantidadJugadores; i++) {
            for (Button botonCartaJugada : this.botonesCartasJugadas.get(i)) {
                botonCartaJugada.setText("");
                botonCartaJugada.setVisible(false);
            }
        }
        this.mostrarTodosLosBotones(false);
        this.mostrarBotones(this.botonesEnvido, true);
        this.mostrarBotones(this.botonesFlor, conFlor);
        this.botonTruco.setVisible(true);

    }

    private void setPartidaFinalizada() throws IOException {
        Alert mensaje = new Alert(AlertType.INFORMATION);
        mensaje.setTitle("La partida ha concluido");
        mensaje.setHeaderText("Puntajes: ");
        mensaje.setContentText("Puntaje equipo 1: " + juego.puntosDeEquipo(Equipo.EQUIPO1) + "\n"
                + "Puntaje equipo 2: " + juego.puntosDeEquipo(Equipo.EQUIPO2) + "\n");
        mensaje.showAndWait();
        this.programa.juegoFinalizado(juego.obtenerNombreGanadorDelJuego());
    }

    // **** Manejo de cartas ****
    @FXML
    public void jugarCarta1Handler() throws IOException {
        this.labelStatus.setText(this.jugadorActual.getNombre() + " ha jugado una carta.");
        juego.jugar(cartasJugadorActual.get(0));
        this.ponerCartaEnLaMesa(cartasJugadorActual.get(0));
        this.mostrarJugadorActual();
        this.mostrarPuntos();
        if (juego.concluyoLaPartida()) {
            this.setPartidaFinalizada();
        }
        if (juego.concluyoLaRonda())
            this.setNuevaRonda();
    }

    @FXML
    public void jugarCarta2Handler() throws IOException {
        this.labelStatus.setText(this.jugadorActual.getNombre() + " ha jugado una carta.");
        juego.jugar(cartasJugadorActual.get(1));
        this.ponerCartaEnLaMesa(cartasJugadorActual.get(1));
        this.mostrarJugadorActual();
        this.mostrarPuntos();
        if (juego.concluyoLaPartida())
            this.setPartidaFinalizada();
        if (juego.concluyoLaRonda())
            this.setNuevaRonda();
    }

    @FXML
    public void jugarCarta3Handler() throws IOException {
        this.labelStatus.setText(this.jugadorActual.getNombre() + " ha jugado una carta.");
        juego.jugar(cartasJugadorActual.get(2));
        this.ponerCartaEnLaMesa(cartasJugadorActual.get(2));
        this.mostrarJugadorActual();
        this.mostrarPuntos();
        if (juego.concluyoLaPartida())
            this.setPartidaFinalizada();
        if (juego.concluyoLaRonda())
            this.setNuevaRonda();
    }

    @FXML
    public void mostrarCartasHandler() {
        this.mostrarBotones(this.botonesCartasJugadorActual, true);
    }

    // **** Flor ****
    @FXML
    public void florHandler() {
        try {
            this.labelStatus.setText(this.jugadorActual.getNombre() + " cantó flor.");
            juego.flor();
        } catch (SeEstaJugandoSinFlorError x1) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("Se está jugando sin flor.");
            jerarquiaNoValida.showAndWait();
        } catch (SoloSePuedeCantarFlorEnPrimeraError x2) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("Sólo se puede cantar flor en primera.");
            jerarquiaNoValida.showAndWait();
        } catch (SoloSePuedeCantarFlorUnaVezPorRondaError x3) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("Sólo se puede cantar flor una vez por ronda.");
            jerarquiaNoValida.showAndWait();
        } catch (JugadorNoTieneFlorError x4) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("Usted no tiene flor!");
            jerarquiaNoValida.showAndWait();
        } catch (NoRespetaJerarquiaDeFlorError x5) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("No respeta la jerarquía de flor.");
            jerarquiaNoValida.showAndWait();
        }
        this.mostrarJugadorActual();
    }

    @FXML
    public void contraFlorHandler() {
        try {
            this.labelStatus.setText(this.jugadorActual.getNombre() + " cantó contraflor.");
            juego.contraFlor();
        } catch (JugadorNoTieneFlorError x1) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("Usted no tiene flor!");
            jerarquiaNoValida.showAndWait();
        } catch (NoRespetaJerarquiaDeFlorError x2) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("No respeta la jerarquía de flor.");
            jerarquiaNoValida.showAndWait();
        } catch (SoloSePuedeCantarFlorUnaVezPorRondaError x3) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("Sólo se puede cantar flor una vez por ronda.");
            jerarquiaNoValida.showAndWait();
        }
        this.mostrarJugadorActual();
    }

    @FXML
    public void contraFlorAlRestoHandler() {
        try {
            this.labelStatus.setText(this.jugadorActual.getNombre() + " cantó contraflor al resto.");
            juego.contraFlorAlResto();
        } catch (JugadorNoTieneFlorError x1) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("Usted no tiene flor!");
            jerarquiaNoValida.showAndWait();
        } catch (NoRespetaJerarquiaDeFlorError x2) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("No respeta la jerarquía de flor.");
            jerarquiaNoValida.showAndWait();
        } catch (SoloSePuedeCantarFlorUnaVezPorRondaError x3) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("Sólo se puede cantar flor una vez por ronda.");
            jerarquiaNoValida.showAndWait();
        }
        this.mostrarJugadorActual();
    }

    @FXML
    public void quieroFlorHandler() throws IOException {
        juego.quieroFlor();
        LinkedList<Object> estadosMostrarGanador = new LinkedList<Object>();
        estadosMostrarGanador.add(ContraFlor.class);
        estadosMostrarGanador.add(ContraFlorAlResto.class);
        if (estadosMostrarGanador.contains(juego.obtenerEstadoDeFlor().getClass())) {
            Alert jerarquiaNoValida = new Alert(AlertType.INFORMATION);
            jerarquiaNoValida.setTitle("Tantos de flor");
            jerarquiaNoValida.setHeaderText("Ganador: " + juego.obtenerNombreGanadorDeFlor());
            jerarquiaNoValida.setContentText("Puntaje del ganador: " + juego.puntosDeFlorGanador() + "\n");
            jerarquiaNoValida.showAndWait();
        }
        if (juego.concluyoLaPartida()) {
            this.setPartidaFinalizada();
        }
    }

    @FXML
    public void noQuieroFlorHandler() throws IOException {
        try {
            this.labelStatus.setText(this.jugadorActual.getNombre() + " no quiso la flor.");
            juego.noQuieroFlor();
        } catch (NoSePuedeRechazarFlorError x1) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("No se puede rechazar la flor!");
            jerarquiaNoValida.showAndWait();
        }
        if (juego.concluyoLaPartida()) {
            this.setPartidaFinalizada();
        }
    }

    // **** Envido ****
    @FXML
    public void envidoHandler() {
        try {
            this.labelStatus.setText(this.jugadorActual.getNombre() + " cantó envido.");
            juego.envido();
        } catch (SoloSePuedeCantarEnvidoEnPrimeraError x1) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("El envido sólo se puede cantar en primera.");
            jerarquiaNoValida.showAndWait();
        } catch (NoPuedeCantarEnvidoNoEsPieError x2) {
            Alert jugadaNoValida = new Alert(AlertType.ERROR);
            jugadaNoValida.setTitle("Error");
            jugadaNoValida.setHeaderText(null);
            jugadaNoValida.setContentText("Sólo el jugador que es pie puede cantar envido.");
            jugadaNoValida.showAndWait();
        } catch (NoRespetaJerarquiaDeEnvidoError x3) {
            Alert jugadaNoValida = new Alert(AlertType.ERROR);
            jugadaNoValida.setTitle("Error");
            jugadaNoValida.setHeaderText(null);
            jugadaNoValida.setContentText("No respeta la jerarquía del envido.");
            jugadaNoValida.showAndWait();
        } catch (SoloSePuedeCantarEnvidoUnaVezPorRondaError x4) {
            Alert jugadaNoValida = new Alert(AlertType.ERROR);
            jugadaNoValida.setTitle("Error");
            jugadaNoValida.setHeaderText(null);
            jugadaNoValida.setContentText("Sólo se puede cantar envido una vez por ronda.");
            jugadaNoValida.showAndWait();
        } catch (NoPuedeCantarEnvidoSeCantoFlorError x5) {
            Alert jugadaNoValida = new Alert(AlertType.ERROR);
            jugadaNoValida.setTitle("Error");
            jugadaNoValida.setHeaderText(null);
            jugadaNoValida.setContentText("Ya se cantó flor, no se puede cantar envido.");
            jugadaNoValida.showAndWait();
        }
        this.mostrarJugadorActual();
    }

    @FXML
    public void realEnvidoHandler() {
        try {
            this.labelStatus.setText(this.jugadorActual.getNombre() + " cantó real envido.");
            juego.realEnvido();
        } catch (SoloSePuedeCantarEnvidoEnPrimeraError x1) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("El real envido sólo se puede cantar en primera.");
            jerarquiaNoValida.showAndWait();
        } catch (NoPuedeCantarEnvidoNoEsPieError x2) {
            Alert jugadaNoValida = new Alert(AlertType.ERROR);
            jugadaNoValida.setTitle("Error");
            jugadaNoValida.setHeaderText(null);
            jugadaNoValida.setContentText("Sólo el jugador que es pie puede cantar real envido.");
            jugadaNoValida.showAndWait();
        } catch (NoRespetaJerarquiaDeEnvidoError x3) {
            Alert jugadaNoValida = new Alert(AlertType.ERROR);
            jugadaNoValida.setTitle("Error");
            jugadaNoValida.setHeaderText(null);
            jugadaNoValida.setContentText("No respeta la jerarquía del envido.");
            jugadaNoValida.showAndWait();
        } catch (SoloSePuedeCantarEnvidoUnaVezPorRondaError x4) {
            Alert jugadaNoValida = new Alert(AlertType.ERROR);
            jugadaNoValida.setTitle("Error");
            jugadaNoValida.setHeaderText(null);
            jugadaNoValida.setContentText("Sólo se puede cantar envido una vez por ronda.");
            jugadaNoValida.showAndWait();
        } catch (NoPuedeCantarEnvidoSeCantoFlorError x5) {
            Alert jugadaNoValida = new Alert(AlertType.ERROR);
            jugadaNoValida.setTitle("Error");
            jugadaNoValida.setHeaderText(null);
            jugadaNoValida.setContentText("Ya se cantó flor, no se puede cantar real envido.");
            jugadaNoValida.showAndWait();
        }
        this.mostrarJugadorActual();
    }

    @FXML
    public void faltaEnvidoHandler() {
        try {
            this.labelStatus.setText(this.jugadorActual.getNombre() + " cantó falta envido.");
            juego.faltaEnvido();
        } catch (SoloSePuedeCantarEnvidoEnPrimeraError x1) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("El falta envido sólo se puede cantar en primera.");
            jerarquiaNoValida.showAndWait();
        } catch (NoPuedeCantarEnvidoNoEsPieError x2) {
            Alert jugadaNoValida = new Alert(AlertType.ERROR);
            jugadaNoValida.setTitle("Error");
            jugadaNoValida.setHeaderText(null);
            jugadaNoValida.setContentText("Sólo el jugador que es pie puede cantar falta envido.");
            jugadaNoValida.showAndWait();
        } catch (SoloSePuedeCantarEnvidoUnaVezPorRondaError x3) {
            Alert jugadaNoValida = new Alert(AlertType.ERROR);
            jugadaNoValida.setTitle("Error");
            jugadaNoValida.setHeaderText(null);
            jugadaNoValida.setContentText("Sólo se puede cantar envido una vez por ronda.");
            jugadaNoValida.showAndWait();
        } catch (NoPuedeCantarEnvidoSeCantoFlorError x4) {
            Alert jugadaNoValida = new Alert(AlertType.ERROR);
            jugadaNoValida.setTitle("Error");
            jugadaNoValida.setHeaderText(null);
            jugadaNoValida.setContentText("Ya se cantó flor, no se puede cantar falta envido.");
            jugadaNoValida.showAndWait();
        }
        this.mostrarJugadorActual();
    }

    @FXML
    public void quieroEnvidoHandler() throws IOException {
        this.labelStatus.setText(this.jugadorActual.getNombre() + " ha aceptado el envido.");
        this.juego.quieroEnvido();
        Alert jerarquiaNoValida = new Alert(AlertType.INFORMATION);
        jerarquiaNoValida.setTitle("Tantos del envido");
        jerarquiaNoValida.setHeaderText("Ganador: " + juego.obtenerNombreGanadorDeEnvido());
        jerarquiaNoValida.setContentText("Puntaje del ganador: " + juego.puntosDeEnvidoGanador() + "\n");
        jerarquiaNoValida.showAndWait();
        if (juego.concluyoLaPartida()) {
            this.setPartidaFinalizada();
        }

        this.mostrarBotones(this.botonesFlor, false);
        this.mostrarBotones(this.botonesEnvido, false);
        this.mostrarBotones(this.botonesQuiero, false);
        this.mostrarBotones(Collections.singletonList(this.botonTruco), true);

        this.mostrarPuntos();
        this.mostrarJugadorActual();
    }

    @FXML
    public void noQuieroEnvidoHandler() throws IOException {
        this.labelStatus.setText(this.jugadorActual.getNombre() + " no quiso el envido.");
        this.juego.noQuieroEnvido();
        if (juego.concluyoLaPartida()) {
            this.setPartidaFinalizada();
        }

        this.mostrarBotones(this.botonesFlor, false);
        this.mostrarBotones(this.botonesEnvido, false);
        this.mostrarBotones(this.botonesQuiero, false);
        this.botonTruco.setVisible(true);

        this.mostrarPuntos();
        this.mostrarJugadorActual();
    }

    // **** Truco ****
    @FXML
    public void trucoHandler(){
        try {
            this.labelStatus.setText(this.jugadorActual.getNombre() + " cantó truco.");
            juego.truco();
            this.botonReTruco.setVisible(true);
            this.botonValeCuatro.setVisible(true);
            this.botonQuieroTruco.setVisible(true);
            this.botonNoQuieroTruco.setVisible(true);
        } catch (NoRespetaJerarquiaDeTrucoError x1) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("No respeta la jerarquía del truco.");
            jerarquiaNoValida.showAndWait();
        } catch (NoPuedeCantarTrucoSeCantoEnvidoError x2) {
            Alert jugadaNoValida = new Alert(AlertType.ERROR);
            jugadaNoValida.setTitle("Error");
            jugadaNoValida.setHeaderText("No puede cantar truco, se cantó envido.");
            jugadaNoValida.setContentText("Debe responderle al equipo contrario.");
            jugadaNoValida.showAndWait();
        } catch (NoPuedeCantarTrucoSeCantoFlorError x3) {
            Alert jugadaNoValida = new Alert(AlertType.ERROR);
            jugadaNoValida.setTitle("Error");
            jugadaNoValida.setHeaderText("No puede cantar truco, se cantó flor.");
            jugadaNoValida.setContentText("Debe responderle al equipo contrario.");
            jugadaNoValida.showAndWait();
        }
        this.mostrarJugadorActual();
    }

    @FXML
    public void reTrucoHandler(){
        try {
            this.labelStatus.setText(this.jugadorActual.getNombre() + " cantó quiero retruco.");
            juego.reTruco();
            this.botonValeCuatro.setVisible(true);
            this.botonQuieroTruco.setVisible(true);
            this.botonNoQuieroTruco.setVisible(true);
        } catch (NoRespetaJerarquiaDeTrucoError x1) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("No respeta la jerarquía del truco.");
            jerarquiaNoValida.showAndWait();
        }
        this.mostrarJugadorActual();
    }

    @FXML
    public void valeCuatroHandler(){
        try {
            this.labelStatus.setText(this.jugadorActual.getNombre() + " cantó quiero vale 4.");
            juego.valeCuatro();
            this.botonQuieroTruco.setVisible(true);
            this.botonNoQuieroTruco.setVisible(true);
        } catch (NoRespetaJerarquiaDeTrucoError x1) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("No respeta la jerarquía del truco.");
            jerarquiaNoValida.showAndWait();
        }
        this.mostrarJugadorActual();
    }

    @FXML
    public void quieroTrucoHandler(){
        this.labelStatus.setText(this.jugadorActual.getNombre() + " dijo quiero.");
        juego.quieroTruco();
        this.mostrarJugadorActual();
    }

    @FXML
    public void noQuieroTrucoHandler() throws IOException {
        this.labelStatus.setText(this.jugadorActual.getNombre() + " se fue al mazo.");
        juego.noQuieroTruco();
        if (juego.concluyoLaPartida()) {
            this.setPartidaFinalizada();
        } else {
            this.setNuevaRonda();
            this.mostrarPuntos();
            this.mostrarJugadorActual();
        }
    }

}