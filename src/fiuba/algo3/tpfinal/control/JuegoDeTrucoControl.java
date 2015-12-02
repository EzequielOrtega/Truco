package fiuba.algo3.tpfinal.control;

import fiuba.algo3.tpfinal.modelo.*;
import fiuba.algo3.tpfinal.modelo.error.*;
import fiuba.algo3.tpfinal.modelo.flor.*;
import fiuba.algo3.tpfinal.vista.Programa;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import java.util.*;

public class JuegoDeTrucoControl {

    private JuegoDeTruco juego;
    private Programa programa;
    private Jugador jugadorActual;
    private List<Carta> cartasJugadorActual;
    private int cantidadJugadasJugador1 = 0, cantidadJugadasJugador2 = 0;
    private List<Integer> cantidadJugadasJugador;
    private int indiceJugador = 0;
    private List<Button> botonesFlor, botonesEnvido, botonesTruco, botonesQuiero, botonesNoQuiero;
    private boolean conFlor;

    @FXML private Label turno;
    @FXML private Label puntajeEquipo1, puntajeEquipo2;
    @FXML private Button botonMostrarCartas;
    @FXML private Button botonCarta1, botonCarta2, botonCarta3;
    @FXML private Button botonCartaJugada1J1, botonCartaJugada2J1, botonCartaJugada3J1;
    @FXML private Button botonCartaJugada1J2, botonCartaJugada2J2, botonCartaJugada3J2;
    @FXML private List<Button> botonesCartasJugadorActual;
    @FXML private List<Button> botonesCartasJugadasJugador1, botonesCartasJugadasJugador2;
    @FXML private List<List<Button>> botonesCartasJugadas;
    @FXML private Button botonFlor, botonContraFlor, botonContraFlorAlResto;
    @FXML private Button botonEnvido, botonRealEnvido, botonFaltaEnvido;
    @FXML private Button botonTruco, botonReTruco, botonValeCuatro;
    @FXML private Button botonQuieroTruco, botonNoQuieroTruco, botonQuieroEnvido, botonQuieroFlor, botonNoQuieroTanto;

    @FXML
    private void initialize() {

        this.botonesCartasJugadorActual = new ArrayList<>(Arrays.asList(this.botonCarta1, this.botonCarta2, this.botonCarta3));
        this.botonesCartasJugadasJugador1 = new ArrayList<>(Arrays.asList(this.botonCartaJugada1J1, this.botonCartaJugada2J1, this.botonCartaJugada3J1));
        this.botonesCartasJugadasJugador2 = new ArrayList<>(Arrays.asList(this.botonCartaJugada1J2, this.botonCartaJugada2J2, this.botonCartaJugada3J2));
        this.botonesCartasJugadas = new ArrayList<>(Arrays.asList(this.botonesCartasJugadasJugador1, this.botonesCartasJugadasJugador2));
        this.cantidadJugadasJugador = new ArrayList<>(Arrays.asList(this.cantidadJugadasJugador1, this.cantidadJugadasJugador2));
        //this.diccionarioCartas = new DiccionarioCartas();
        this.botonesEnvido = new ArrayList<>(Arrays.asList(this.botonEnvido, this.botonRealEnvido, this.botonFaltaEnvido));
        this.botonesFlor = new ArrayList<>(Arrays.asList(this.botonFlor, this.botonContraFlor, this.botonContraFlorAlResto));
        this.botonesTruco = new ArrayList<>(Arrays.asList(this.botonTruco, this.botonReTruco, this.botonValeCuatro));
        this.botonesQuiero = new ArrayList<>(Arrays.asList(this.botonQuieroTruco, this.botonNoQuieroTruco, this.botonQuieroEnvido, this.botonQuieroFlor, this.botonNoQuieroTanto));

        //this.diccionarioEstadosJuego = new DiccionarioEstadosJuego(this.botonesTruco, this.botonesTanto, this.botonesQuiero);

        //this.visibilizarBotones(this.botonesQuiero, false);
//        this.visibilizarBotones(Arrays.asList(this.botonReTruco, this.botonValeCuatro), false);

    }


    public void setPrograma(Programa programa, JuegoDeTruco juego, boolean conFlor) {

        this.programa = programa;
        this.juego = juego;
        this.conFlor = conFlor;
        juego.comenzarPartida(conFlor);
        this.mostrarPuntos();

        this.visibilizarBotones(this.botonesFlor, conFlor);
        this.visibilizarBotones(Arrays.asList(this.botonContraFlor, this.botonContraFlorAlResto), false);

        this.jugadorActual = this.juego.obtenerJugadorActual();
        this.mostrarJugadorActual();

    }

    public void mostrarPuntos() {
        this.puntajeEquipo1.setText(Integer.toString(this.juego.puntosDeEquipo(Equipo.EQUIPO1)));
        this.puntajeEquipo2.setText(Integer.toString(this.juego.puntosDeEquipo(Equipo.EQUIPO2)));
    }


    public void visibilizarBotones(List<Button> botones, boolean visibilidad) {
        for(Button boton : botones) {
            boton.setVisible(visibilidad);
        }
    }

    public void desactivarBotones(List<Button> botones, boolean desactivar) {
        for(Button boton : botones) {
            boton.setDisable(desactivar);
        }
    }

    @FXML
    public void JugarCarta1Handler() {
        juego.jugar(cartasJugadorActual.get(0));
        this.mostrarCartaEnMesa(cartasJugadorActual.get(0));
        this.mostrarJugadorActual();
        this.mostrarPuntos();
    }

    @FXML
    public void JugarCarta2Handler() {
        juego.jugar(cartasJugadorActual.get(1));
        this.mostrarCartaEnMesa(cartasJugadorActual.get(1));
        this.mostrarJugadorActual();
        this.mostrarPuntos();
    }

    @FXML
    public void JugarCarta3Handler() {
        juego.jugar(cartasJugadorActual.get(2));
        this.mostrarCartaEnMesa(cartasJugadorActual.get(2));
        this.mostrarJugadorActual();
        this.mostrarPuntos();
    }

    public void nuevaRonda() {

        int cantidadJugadores = 2; // Por ahora
        for(int i = 0; i < cantidadJugadores; i++) {
            for (Button botonCartaJugada : this.botonesCartasJugadas.get(i)) {
                botonCartaJugada.setText("");
                botonCartaJugada.setVisible(false);
            }
            this.cantidadJugadasJugador.set(i, 0);

        }

        this.visibilizarBotones(this.botonesQuiero, false);
        this.visibilizarBotones(Arrays.asList(this.botonReTruco, this.botonValeCuatro), false);
        this.botonTruco.setVisible(true);

        this.visibilizarBotones(this.botonesEnvido, true);
        this.visibilizarBotones(this.botonesFlor, conFlor);

    }

    public void mostrarCartaEnMesa(Carta cartaJugada) {
        // TODO: implementar
    }


    @FXML
    public void trucoHandler(){
    	try {
			juego.truco();
			//programa.seCanto("Truco");
			//programa.habilitarBotonQuieroTruco();
		} catch (NoRespetaJerarquiaDeTrucoError x1) {
			Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
			jerarquiaNoValida.setTitle("Error");
			jerarquiaNoValida.setHeaderText(null);
			jerarquiaNoValida.setContentText("No respeta la jerarquia del truco");
			jerarquiaNoValida.showAndWait();
		} catch (NoPuedeCantarTrucoSeCantoEnvidoError x2) {
			Alert jugadaNoValida = new Alert(AlertType.ERROR);
			jugadaNoValida.setTitle("Error");
			jugadaNoValida.setHeaderText("No puede cantar truco, se canto envido");
			jugadaNoValida.setContentText("Debe responderle al equipo contrario");
			jugadaNoValida.showAndWait();
		} catch (NoPuedeCantarTrucoSeCantoFlorError x3) {
			Alert jugadaNoValida = new Alert(AlertType.ERROR);
			jugadaNoValida.setTitle("Error");
			jugadaNoValida.setHeaderText("No puede cantar truco, se canto flor");
			jugadaNoValida.setContentText("Debe responderle al equipo contrario");
			jugadaNoValida.showAndWait();
		}
        this.mostrarJugadorActual();
    }

    @FXML
    public void reTrucoHandler(){
        try {
            juego.reTruco();
            //programa.seCanto("ReTruco");
            //programa.habilitarBotonQuieroTruco();
        } catch (NoRespetaJerarquiaDeTrucoError x1) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("No respeta la jerarquia del truco");
            jerarquiaNoValida.showAndWait();
        }
        this.mostrarJugadorActual();
    }

    @FXML
    public void valeCuatroHandler(){
        try {
            juego.valeCuatro();
            //programa.seCanto("ValeCuatro");
            //programa.habilitarBotonQuieroTruco();
        } catch (NoRespetaJerarquiaDeTrucoError x1) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("No respeta la jerarquia del truco");
            jerarquiaNoValida.showAndWait();
        }
        this.mostrarJugadorActual();
    }

    @FXML
    public void quieroTrucoHandler(){
        juego.quieroTruco();
        this.mostrarJugadorActual();
    }

    @FXML
    public void noQuieroTrucoHandler() {
        juego.noQuieroTruco();
        if (juego.concluyoLaPartida()) {
            //programa.deshabilitarTodosLosBotones();
            Alert jerarquiaNoValida = new Alert(AlertType.INFORMATION);
            jerarquiaNoValida.setTitle("La partida ha concluido");
            jerarquiaNoValida.setHeaderText("Puntajes: ");
            jerarquiaNoValida.setContentText("Puntaje equipo 1: " + juego.puntosDeEquipo(Equipo.EQUIPO1) + "\n"
                    + "Puntaje equipo 2: " + juego.puntosDeEquipo(Equipo.EQUIPO2) + "\n");
            jerarquiaNoValida.showAndWait();
        } else {
            //programa.prepararProximaRonda();
        }
        this.nuevaRonda();
        this.mostrarPuntos();
        this.mostrarJugadorActual();
    }

    @FXML
    public void cantarFlorHandler() {
        try {
            juego.flor();
            //programa.habilitarBotonesQuieroFlor();
        } catch (SeEstaJugandoSinFlorError x1) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("Se esta jugando sin flor");
            jerarquiaNoValida.showAndWait();
        } catch (SoloSePuedeCantarFlorEnPrimeraError x2) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("Solo se puede cantar flor en primera");
            jerarquiaNoValida.showAndWait();
        } catch (SoloSePuedeCantarFlorUnaVezPorRondaError x3) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("Solo se puede cantar flor una vez por ronda");
            jerarquiaNoValida.showAndWait();
        } catch (JugadorNoTieneFlorError x4) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("No tiene flor");
            jerarquiaNoValida.showAndWait();
        } catch (NoRespetaJerarquiaDeFlorError x5) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("No respeta la jerarquia de flor");
            jerarquiaNoValida.showAndWait();
        }
        this.mostrarJugadorActual();
    }

    @FXML
    public void cantarContraFlorHandler() {
        try {
            juego.contraFlor();
        } catch (JugadorNoTieneFlorError x1) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("No tiene flor");
            jerarquiaNoValida.showAndWait();
        } catch (NoRespetaJerarquiaDeFlorError x2) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("No respeta la jerarquia de flor");
            jerarquiaNoValida.showAndWait();
        } catch (SoloSePuedeCantarFlorUnaVezPorRondaError x3) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("Solo se puede cantar flor una vez por ronda");
            jerarquiaNoValida.showAndWait();
        }
        this.mostrarJugadorActual();
    }

    @FXML
    public void cantarContraFlorAlRestoHandler() {
        try {
            juego.contraFlorAlResto();
        } catch (JugadorNoTieneFlorError x1) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("No tiene flor");
            jerarquiaNoValida.showAndWait();
        } catch (NoRespetaJerarquiaDeFlorError x2) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("No respeta la jerarquia de flor");
            jerarquiaNoValida.showAndWait();
        } catch (SoloSePuedeCantarFlorUnaVezPorRondaError x3) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("Solo se puede cantar flor una vez por ronda");
            jerarquiaNoValida.showAndWait();
        }
        this.mostrarJugadorActual();
    }

    @FXML
    public void quieroFlorHandler() {
        juego.quieroFlor();
        //programa.actualizarPuntos();
        //programa.deshabilitarBotonesEnvidoYFlor();
        LinkedList<Object> estadosMostrarGanador = new LinkedList<Object>();
        estadosMostrarGanador.add(ContraFlor.class);
        estadosMostrarGanador.add(ContraFlorAlResto.class);
        if (estadosMostrarGanador.contains(juego.obtenerEstadoDeFlor().getClass())) {
            Alert jerarquiaNoValida = new Alert(AlertType.INFORMATION);
            jerarquiaNoValida.setTitle("Tantos de la flor");
            jerarquiaNoValida.setHeaderText("Ganador: " + juego.obtenerNombreGanadorDeFlor());
            jerarquiaNoValida.setContentText("Puntaje del ganador: " + juego.puntosDeFlorGanador() + "\n");
            jerarquiaNoValida.showAndWait();
        }
    }

    @FXML
    public void noQuieroFlorHandler() {
        try {
            juego.noQuieroFlor();
            //programa.actualizarPuntos();
            //programa.deshabilitarBotonesEnvidoYFlor();
        } catch (NoSePuedeRechazarFlorError x1) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("No se puede rechazar la flor");
            jerarquiaNoValida.showAndWait();
        }
    }
    @FXML
    public void cantarEnvidoHandler() {
        try {
            juego.envido();
            //programa.seCanto("Envido");
            //programa.habilitarBotonQuieroEnvido();
        } catch (SoloSePuedeCantarEnvidoEnPrimeraError x1) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("El envido solo se puede cantar en primera");
            jerarquiaNoValida.showAndWait();
        } catch (NoPuedeCantarEnvidoNoEsPieError x2) {
            Alert jugadaNoValida = new Alert(AlertType.ERROR);
            jugadaNoValida.setTitle("Error");
            jugadaNoValida.setHeaderText(null);
            jugadaNoValida.setContentText("Solo el jugador que es pie puede cantar envido");
            jugadaNoValida.showAndWait();
        } catch (NoRespetaJerarquiaDeEnvidoError x3) {
            Alert jugadaNoValida = new Alert(AlertType.ERROR);
            jugadaNoValida.setTitle("Error");
            jugadaNoValida.setHeaderText(null);
            jugadaNoValida.setContentText("No respeta la jerarquia del envido");
            jugadaNoValida.showAndWait();
        } catch (SoloSePuedeCantarEnvidoUnaVezPorRondaError x4) {
            Alert jugadaNoValida = new Alert(AlertType.ERROR);
            jugadaNoValida.setTitle("Error");
            jugadaNoValida.setHeaderText(null);
            jugadaNoValida.setContentText("Solo se puede cantar envido una vez por ronda");
            jugadaNoValida.showAndWait();
        } catch (NoPuedeCantarEnvidoSeCantoFlorError x5) {
            Alert jugadaNoValida = new Alert(AlertType.ERROR);
            jugadaNoValida.setTitle("Error");
            jugadaNoValida.setHeaderText(null);
            jugadaNoValida.setContentText("Se canto flor, no se puede cantar envido");
            jugadaNoValida.showAndWait();
        }
        this.mostrarJugadorActual();
    }

    @FXML
    public void cantarRealEnvidoHandler() {
        try {
            juego.realEnvido();
            //programa.seCanto("RealEnvido");
            //programa.habilitarBotonQuieroEnvido();
        } catch (SoloSePuedeCantarEnvidoEnPrimeraError x1) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("El real envido solo se puede cantar en primera");
            jerarquiaNoValida.showAndWait();
        } catch (NoPuedeCantarEnvidoNoEsPieError x2) {
            Alert jugadaNoValida = new Alert(AlertType.ERROR);
            jugadaNoValida.setTitle("Error");
            jugadaNoValida.setHeaderText(null);
            jugadaNoValida.setContentText("Solo el jugador que es pie puede cantar real envido");
            jugadaNoValida.showAndWait();
        } catch (NoRespetaJerarquiaDeEnvidoError x3) {
            Alert jugadaNoValida = new Alert(AlertType.ERROR);
            jugadaNoValida.setTitle("Error");
            jugadaNoValida.setHeaderText(null);
            jugadaNoValida.setContentText("No respeta la jerarquia del envido");
            jugadaNoValida.showAndWait();
        } catch (SoloSePuedeCantarEnvidoUnaVezPorRondaError x4) {
            Alert jugadaNoValida = new Alert(AlertType.ERROR);
            jugadaNoValida.setTitle("Error");
            jugadaNoValida.setHeaderText(null);
            jugadaNoValida.setContentText("Solo se puede cantar envido una vez por ronda");
            jugadaNoValida.showAndWait();
        } catch (NoPuedeCantarEnvidoSeCantoFlorError x5) {
            Alert jugadaNoValida = new Alert(AlertType.ERROR);
            jugadaNoValida.setTitle("Error");
            jugadaNoValida.setHeaderText(null);
            jugadaNoValida.setContentText("Se canto flor, no se puede cantar real envido");
            jugadaNoValida.showAndWait();
        }
        this.mostrarJugadorActual();
    }

    @FXML
    public void cantarFaltaEnvidoHandler() {
        try {
            juego.faltaEnvido();
            //programa.seCanto("FaltaEnvido");
            //programa.habilitarBotonQuieroEnvido();
        } catch (SoloSePuedeCantarEnvidoEnPrimeraError x1) {
            Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
            jerarquiaNoValida.setTitle("Error");
            jerarquiaNoValida.setHeaderText(null);
            jerarquiaNoValida.setContentText("El falta envido solo se puede cantar en primera");
            jerarquiaNoValida.showAndWait();
        } catch (NoPuedeCantarEnvidoNoEsPieError x2) {
            Alert jugadaNoValida = new Alert(AlertType.ERROR);
            jugadaNoValida.setTitle("Error");
            jugadaNoValida.setHeaderText(null);
            jugadaNoValida.setContentText("Solo el jugador que es pie puede cantar falta envido");
            jugadaNoValida.showAndWait();
        } catch (SoloSePuedeCantarEnvidoUnaVezPorRondaError x3) {
            Alert jugadaNoValida = new Alert(AlertType.ERROR);
            jugadaNoValida.setTitle("Error");
            jugadaNoValida.setHeaderText(null);
            jugadaNoValida.setContentText("Solo se puede cantar envido una vez por ronda");
            jugadaNoValida.showAndWait();
        } catch (NoPuedeCantarEnvidoSeCantoFlorError x4) {
            Alert jugadaNoValida = new Alert(AlertType.ERROR);
            jugadaNoValida.setTitle("Error");
            jugadaNoValida.setHeaderText(null);
            jugadaNoValida.setContentText("Se canto flor, no se puede cantar falta envido");
            jugadaNoValida.showAndWait();
        }
        this.mostrarJugadorActual();
    }

    @FXML
    public void quieroEnvidoHandler() {
        this.juego.quieroEnvido();

        Alert jerarquiaNoValida = new Alert(AlertType.INFORMATION);
        jerarquiaNoValida.setTitle("Tantos del envido");
        jerarquiaNoValida.setHeaderText("Ganador: " + juego.obtenerNombreGanadorDeEnvido());
        jerarquiaNoValida.setContentText("Puntaje del ganador: " + juego.puntosDeEnvidoGanador() + "\n");
        jerarquiaNoValida.showAndWait();
        if (juego.concluyoLaPartida()) {
            //programa.deshabilitarTodosLosBotones();
            Alert mensaje = new Alert(AlertType.INFORMATION);
            mensaje.setTitle("La partida ha concluido");
            mensaje.setHeaderText("Puntajes: ");
            mensaje.setContentText("Puntaje equipo 1: " + juego.puntosDeEquipo(Equipo.EQUIPO1) + "\n"
                    + "Puntaje equipo 2: " + juego.puntosDeEquipo(Equipo.EQUIPO2) + "\n");
            mensaje.showAndWait();
        }

        this.visibilizarBotones(this.botonesFlor, false);
        this.visibilizarBotones(this.botonesEnvido, false);
        this.visibilizarBotones(this.botonesQuiero, false);
        this.visibilizarBotones(Collections.singletonList(this.botonTruco), true);

        this.mostrarPuntos();
        this.mostrarJugadorActual();
    }

    @FXML
    public void noQuieroEnvidoHandler() {
        this.juego.noQuieroEnvido();

        if (juego.concluyoLaPartida()) {
            //programa.deshabilitarTodosLosBotones();
            Alert mensaje = new Alert(AlertType.INFORMATION);
            mensaje.setTitle("La partida ha concluido");
            mensaje.setHeaderText("Puntajes: ");
            mensaje.setContentText("Puntaje equipo 1: " + juego.puntosDeEquipo(Equipo.EQUIPO1) + "\n"
                    + "Puntaje equipo 2: " + juego.puntosDeEquipo(Equipo.EQUIPO2) + "\n");
            mensaje.showAndWait();
        }

        this.visibilizarBotones(this.botonesFlor, false);
        this.visibilizarBotones(this.botonesEnvido, false);
        this.visibilizarBotones(this.botonesQuiero, false);
        this.visibilizarBotones(Collections.singletonList(this.botonTruco), true);

        this.mostrarPuntos();
        this.mostrarJugadorActual();
    }


    private void mostrarJugadorActual() {

        // TODO: revisar esta implementacion
        this.setearBotones();
        this.jugadorActual = juego.obtenerJugadorActual();
        this.cartasJugadorActual = jugadorActual.mostrarCartas();
        for(Button boton : this.botonesCartasJugadorActual) {
            boton.setText("Carta dada vuelta");
            boton.setDisable(true);
        }
    }



    private void setearBotones() {

        // TODO: completar esto tambien
        this.visibilizarBotones(this.botonesTruco, true);
        this.visibilizarBotones(this.botonesEnvido, true);
        this.visibilizarBotones(this.botonesFlor, this.conFlor);
        this.visibilizarBotones(this.botonesQuiero, true);
    }

}