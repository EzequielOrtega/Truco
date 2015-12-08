package fiuba.algo3.tpfinal.control;

import fiuba.algo3.tpfinal.modelo.*;
import fiuba.algo3.tpfinal.modelo.error.*;
import fiuba.algo3.tpfinal.modelo.flor.ContraFlor;
import fiuba.algo3.tpfinal.modelo.flor.ContraFlorAlResto;
import fiuba.algo3.tpfinal.vista.Programa;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.*;

public class JuegoDeTrucoControl {

	private JuegoDeTruco juego;
	private Diccionario diccionario = new Diccionario();
	private Programa programa;
	private Jugador jugadorActual;
	private List<Carta> cartasJugadorActual;
	private List<Button> botonesFlor, botonesEnvido, botonesTruco, botonesQuiero;
	private List<Button> botonesTrucoQuiero, botonesFlorQuiero, botonesEnvidoQuiero;
	private boolean conFlor;

	@FXML
	private Label labelTurno, labelStatus;
	@FXML
	private Label labelPuntajeEquipo1, labelPuntajeEquipo2;
	@FXML
	private Label nombreJ1, nombreJ2, nombreJ3, nombreJ4;
	@FXML
	private Button botonCarta1, botonCarta2, botonCarta3;
	@FXML
	private Button botonCartaJugada1J1, botonCartaJugada2J1, botonCartaJugada3J1;
	@FXML
	private Button botonCartaJugada1J2, botonCartaJugada2J2, botonCartaJugada3J2;
	@FXML
	private Button botonCartaJugada1J3, botonCartaJugada2J3, botonCartaJugada3J3;
	@FXML
	private Button botonCartaJugada1J4, botonCartaJugada2J4, botonCartaJugada3J4;
	@FXML
	private List<Button> botonesCartasJugadorActual;
	@FXML
	private List<Button> botonesCartasJugadasJugador1, botonesCartasJugadasJugador2, botonesCartasJugadasJugador3,
			botonesCartasJugadasJugador4;
	@FXML
	private List<List<Button>> botonesCartasJugadas;
	@FXML
	private Button botonFlor, botonContraFlor, botonContraFlorAlResto, botonQuieroFlor, botonNoQuieroFlor;
	@FXML
	private Button botonEnvido, botonRealEnvido, botonFaltaEnvido, botonQuieroEnvido, botonNoQuieroEnvido;
	@FXML
	private Button botonTruco, botonReTruco, botonValeCuatro, botonQuieroTruco, botonNoQuieroTruco;
	@FXML
	private Button botonIrseAlMazo;

	@FXML
	private void initialize() {

		this.botonesFlor = new ArrayList<>(
				Arrays.asList(this.botonFlor, this.botonContraFlor, this.botonContraFlorAlResto));
		this.botonesEnvido = new ArrayList<>(
				Arrays.asList(this.botonEnvido, this.botonRealEnvido, this.botonFaltaEnvido));
		this.botonesTruco = new ArrayList<>(Arrays.asList(this.botonTruco, this.botonReTruco, this.botonValeCuatro));
		this.botonesQuiero = new ArrayList<>(Arrays.asList(this.botonQuieroTruco, this.botonNoQuieroTruco,
				this.botonQuieroEnvido, this.botonNoQuieroEnvido, this.botonQuieroFlor, this.botonNoQuieroFlor));
		this.botonesTrucoQuiero = new ArrayList<>(Arrays.asList(this.botonQuieroTruco, this.botonNoQuieroTruco));
		this.botonesEnvidoQuiero = new ArrayList<>(Arrays.asList(this.botonQuieroEnvido, this.botonNoQuieroEnvido));
		this.botonesFlorQuiero = new ArrayList<>(Arrays.asList(this.botonQuieroFlor, this.botonNoQuieroFlor));

		this.botonesCartasJugadorActual = new ArrayList<>(
				Arrays.asList(this.botonCarta1, this.botonCarta2, this.botonCarta3));
		this.botonesCartasJugadasJugador1 = new ArrayList<>(
				Arrays.asList(this.botonCartaJugada1J1, this.botonCartaJugada2J1, this.botonCartaJugada3J1));
		this.botonesCartasJugadasJugador2 = new ArrayList<>(
				Arrays.asList(this.botonCartaJugada1J2, this.botonCartaJugada2J2, this.botonCartaJugada3J2));
		this.botonesCartasJugadasJugador3 = new ArrayList<>(
				Arrays.asList(this.botonCartaJugada1J3, this.botonCartaJugada2J3, this.botonCartaJugada3J3));
		this.botonesCartasJugadasJugador4 = new ArrayList<>(
				Arrays.asList(this.botonCartaJugada1J4, this.botonCartaJugada2J4, this.botonCartaJugada3J4));
		this.botonesCartasJugadas = new ArrayList<>(
				Arrays.asList(this.botonesCartasJugadasJugador1, this.botonesCartasJugadasJugador2,
						this.botonesCartasJugadasJugador3, this.botonesCartasJugadasJugador4));
	}

	public void setPrograma(Programa programa, JuegoDeTruco juego, boolean conFlor) {
		this.programa = programa;
		this.juego = juego;
		this.conFlor = conFlor;
		juego.comenzarPartida(conFlor);

		this.mostrarPuntos();
		this.mostrarJugadorActual();
		this.mostrarTodosLosBotones(false);
		this.botonFlor.setVisible(conFlor);
		this.mostrarBotones(this.botonesEnvido, true);
		this.botonTruco.setVisible(true);
		this.ponerNombres();
	}

	private void ponerNombres() {
		LinkedList<String> nombres = this.juego.obtenerNombres();
		LinkedList<Label> nombresLabels = new LinkedList<Label>();
		nombresLabels.add(nombreJ1);
		nombresLabels.add(nombreJ2);
		nombresLabels.add(nombreJ3);
		nombresLabels.add(nombreJ4);
		for (int x = 0; x < nombres.size(); x++) {
			nombresLabels.get(x).setText(nombres.get(x));
		}
	}

	private void mostrarJugadorActual() {
		this.jugadorActual = juego.obtenerJugadorActual();
		this.labelTurno.setText(this.jugadorActual.getNombre());
		this.cartasJugadorActual = jugadorActual.mostrarCartas();
		for (Button boton : this.botonesCartasJugadorActual) {
			boton.setText("Carta dada vuelta");
			boton.setDisable(true);
		}
	}

	private void mostrarBotones(List<Button> botones, boolean mostrar) {
		for (Node boton : botones) {
			boton.setVisible(mostrar);
		}
	}

	private void mostrarTodosLosBotones(Boolean mostrar) {
		this.mostrarBotones(this.botonesFlor, false);
		if (conFlor) {
			this.mostrarBotones(this.botonesFlor, mostrar);
		}
		this.mostrarBotones(this.botonesEnvido, mostrar);
		this.mostrarBotones(this.botonesTruco, mostrar);
		this.mostrarBotones(this.botonesQuiero, mostrar);
	}

	public void mostrarPuntos() {
		this.labelPuntajeEquipo1.setText(Integer.toString(this.juego.puntosDeEquipo(Equipo.EQUIPO1)));
		this.labelPuntajeEquipo2.setText(Integer.toString(this.juego.puntosDeEquipo(Equipo.EQUIPO2)));
	}

	private void ponerCartaEnLaMesa(Carta carta, int cantidad, String nombre) {
		int i = 0;
		for (String unNombre : juego.obtenerNombres()) {
			if (nombre.equals(unNombre)) {
				break;
			}
			i++;
		}

		Button botonCartaAJugar = this.botonesCartasJugadas.get(i).get(cantidad);
		botonCartaAJugar.setVisible(true);
		botonCartaAJugar.setDisable(false);
		botonCartaAJugar.setText(this.diccionario.Busqueda(carta));
	}

	public void desactivarBotones(List<Button> botones, boolean desactivar) {
		for (Button boton : botones) {
			boton.setDisable(desactivar);
		}
	}

	private void setNuevaRonda() {

		int cantidadJugadores = juego.obtenerNombres().size();
		for (int i = 0; i < cantidadJugadores; i++) {
			for (Button botonCartaJugada : this.botonesCartasJugadas.get(i)) {
				botonCartaJugada.setText("");
				botonCartaJugada.setVisible(false);
			}
		}
		this.mostrarTodosLosBotones(false);
		this.mostrarBotones(this.botonesEnvido, true);
		this.mostrarBotones(this.botonesFlor, conFlor);
		this.botonTruco.setVisible(true);
		this.modificarBotonesDeCartasJugadas();

	}

	private void modificarBotonesDeCartasJugadas() {
		List<Button> auxiliar = this.botonesCartasJugadas.get(0);
		this.botonesCartasJugadas.remove(auxiliar);
		if (this.juego.obtenerNombres().size() == 2) {
			this.botonesCartasJugadas.add(1, auxiliar);
		} else {
			this.botonesCartasJugadas.add(auxiliar);
		}
	}

	private void setPartidaFinalizada() throws IOException {
		Alert mensaje = new Alert(AlertType.INFORMATION);
		mensaje.setTitle("La partida ha concluido");
		mensaje.setHeaderText("Puntajes: ");
		mensaje.setContentText("Puntaje equipo 1: " + juego.puntosDeEquipo(Equipo.EQUIPO1) + "\n" + "Puntaje equipo 2: "
				+ juego.puntosDeEquipo(Equipo.EQUIPO2) + "\n");
		mensaje.showAndWait();
		this.programa.juegoFinalizado(juego.obtenerNombreGanadorDelJuego());
	}

	// **** Manejo de cartas ****

	private void jugarCarta(Carta carta) throws IOException {
		try {
			String nombre = this.jugadorActual.getNombre();
			int cantidad = this.jugadorActual.cantidadDeCartasJugadas();
			juego.jugar(carta);
			this.ponerCartaEnLaMesa(carta, cantidad, nombre);
			this.labelStatus.setText(nombre + " ha jugado un " + this.diccionario.Busqueda(carta));
			this.mostrarJugadorActual();
			this.mostrarPuntos();
			if (juego.concluyoLaPartida()) {
				this.setPartidaFinalizada();
			}
			if (juego.concluyoLaRonda())
				this.setNuevaRonda();
		} catch (NoPuedeJugarSeCantoTrucoError x1) {
			Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
			jerarquiaNoValida.setTitle("Error");
			jerarquiaNoValida.setHeaderText(null);
			jerarquiaNoValida.setContentText("No puede jugar una carta, se canto truco.");
			jerarquiaNoValida.showAndWait();
		}
	}

	@FXML
	public void jugarCarta1Handler() throws IOException {
		this.jugarCarta(this.cartasJugadorActual.get(0));
	}

	@FXML
	public void jugarCarta2Handler() throws IOException {
		this.jugarCarta(this.cartasJugadorActual.get(1));
	}

	@FXML
	public void jugarCarta3Handler() throws IOException {
		this.jugarCarta(this.cartasJugadorActual.get(2));
	}

	@FXML
	public void mostrarCartasHandler() {
		this.mostrarBotones(this.botonesCartasJugadorActual, true);
		this.desactivarBotones(this.botonesCartasJugadorActual, false);

		Vector<Carta> cartas = this.jugadorActual.mostrarCartas();
		for (int x = 0; x < cartas.size(); x++) {
			this.botonesCartasJugadorActual.get(x).setText(this.diccionario.Busqueda(cartas.get(x)));
		}
	}

	// **** Flor ****
	@FXML
	public void florHandler() {
		try {
			Jugador jugadorQueCanto = this.jugadorActual;
			juego.flor();
			this.labelStatus.setText(jugadorQueCanto.getNombre() + " canto flor.");
			this.mostrarTodosLosBotones(false);
			this.botonContraFlor.setVisible(conFlor);
			this.botonContraFlorAlResto.setVisible(conFlor);
			this.mostrarBotones(this.botonesFlorQuiero, true);
			this.mostrarJugadorActual();
		} catch (SeEstaJugandoSinFlorError x1) {
			Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
			jerarquiaNoValida.setTitle("Error");
			jerarquiaNoValida.setHeaderText(null);
			jerarquiaNoValida.setContentText("Se esta jugando sin flor.");
			jerarquiaNoValida.showAndWait();
		} catch (SoloSePuedeCantarFlorEnPrimeraError x2) {
			Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
			jerarquiaNoValida.setTitle("Error");
			jerarquiaNoValida.setHeaderText(null);
			jerarquiaNoValida.setContentText("Solo se puede cantar flor en primera.");
			jerarquiaNoValida.showAndWait();
		} catch (SoloSePuedeCantarFlorUnaVezPorRondaError x3) {
			Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
			jerarquiaNoValida.setTitle("Error");
			jerarquiaNoValida.setHeaderText(null);
			jerarquiaNoValida.setContentText("Solo se puede cantar flor una vez por ronda.");
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
			jerarquiaNoValida.setContentText("No respeta la jerarquia de flor.");
			jerarquiaNoValida.showAndWait();
		}

	}

	@FXML
	public void contraFlorHandler() {
		try {
			Jugador jugadorQueCanto = this.jugadorActual;
			juego.contraFlor();
			this.labelStatus.setText(jugadorQueCanto.getNombre() + " canto contraflor.");
			this.mostrarTodosLosBotones(false);
			this.botonContraFlorAlResto.setVisible(conFlor);
			this.mostrarBotones(this.botonesFlorQuiero, true);
			this.mostrarJugadorActual();
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
			jerarquiaNoValida.setContentText("No respeta la jerarquia de flor.");
			jerarquiaNoValida.showAndWait();
		} catch (SoloSePuedeCantarFlorUnaVezPorRondaError x3) {
			Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
			jerarquiaNoValida.setTitle("Error");
			jerarquiaNoValida.setHeaderText(null);
			jerarquiaNoValida.setContentText("Solo se puede cantar flor una vez por ronda.");
			jerarquiaNoValida.showAndWait();
		}

	}

	@FXML
	public void contraFlorAlRestoHandler() {
		try {
			Jugador jugadorQueCanto = this.jugadorActual;
			juego.contraFlorAlResto();
			this.labelStatus.setText(jugadorQueCanto.getNombre() + " canto contraflor al resto.");
			this.mostrarTodosLosBotones(false);
			this.mostrarBotones(this.botonesFlorQuiero, true);
			this.mostrarJugadorActual();
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
			jerarquiaNoValida.setContentText("No respeta la jerarquia de flor.");
			jerarquiaNoValida.showAndWait();
		} catch (SoloSePuedeCantarFlorUnaVezPorRondaError x3) {
			Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
			jerarquiaNoValida.setTitle("Error");
			jerarquiaNoValida.setHeaderText(null);
			jerarquiaNoValida.setContentText("Solo se puede cantar flor una vez por ronda.");
			jerarquiaNoValida.showAndWait();
		}

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
		this.mostrarTodosLosBotones(false);
		this.botonTruco.setVisible(true);
		if (juego.concluyoLaPartida()) {
			this.setPartidaFinalizada();
		}
		if (this.juego.seCantoTruco()) {
			this.mostrarBotones(this.botonesTruco, true);
			this.mostrarBotones(this.botonesTrucoQuiero, true);
		}

	}

	@FXML
	public void noQuieroFlorHandler() throws IOException {
		try {
			Jugador jugadorQueNoQuiso = this.jugadorActual;
			juego.noQuieroFlor();
			this.labelStatus.setText(jugadorQueNoQuiso.getNombre() + " no quiso la flor.");
			this.mostrarTodosLosBotones(false);
			this.botonTruco.setVisible(true);
			if (juego.concluyoLaPartida()) {
				this.setPartidaFinalizada();
			}
			if (this.juego.seCantoTruco()) {
				this.mostrarBotones(this.botonesTruco, true);
				this.mostrarBotones(this.botonesTrucoQuiero, true);
			}
		} catch (NoSePuedeRechazarFlorError x1) {
			Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
			jerarquiaNoValida.setTitle("Error");
			jerarquiaNoValida.setHeaderText(null);
			jerarquiaNoValida.setContentText("No se puede rechazar la flor!");
			jerarquiaNoValida.showAndWait();
		}

	}

	// **** Envido ****
	@FXML
	public void envidoHandler() {
		try {
			Jugador jugadorQueCanto = this.jugadorActual;
			juego.envido();
			this.labelStatus.setText(jugadorQueCanto.getNombre() + " canto envido.");
			this.mostrarTodosLosBotones(false);
			this.botonFlor.setVisible(conFlor);
			this.mostrarBotones(this.botonesEnvido, true);
			this.mostrarBotones(this.botonesEnvidoQuiero, true);
			this.mostrarJugadorActual();
		} catch (SoloSePuedeCantarEnvidoEnPrimeraError x1) {
			Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
			jerarquiaNoValida.setTitle("Error");
			jerarquiaNoValida.setHeaderText(null);
			jerarquiaNoValida.setContentText("El envido solo se puede cantar en primera.");
			jerarquiaNoValida.showAndWait();
		} catch (NoPuedeCantarEnvidoNoEsPieError x2) {
			Alert jugadaNoValida = new Alert(AlertType.ERROR);
			jugadaNoValida.setTitle("Error");
			jugadaNoValida.setHeaderText(null);
			jugadaNoValida.setContentText("Solo el jugador que es pie puede cantar envido.");
			jugadaNoValida.showAndWait();
		} catch (NoRespetaJerarquiaDeEnvidoError x3) {
			Alert jugadaNoValida = new Alert(AlertType.ERROR);
			jugadaNoValida.setTitle("Error");
			jugadaNoValida.setHeaderText(null);
			jugadaNoValida.setContentText("No respeta la jerarquia del envido.");
			jugadaNoValida.showAndWait();
		} catch (SoloSePuedeCantarEnvidoUnaVezPorRondaError x4) {
			Alert jugadaNoValida = new Alert(AlertType.ERROR);
			jugadaNoValida.setTitle("Error");
			jugadaNoValida.setHeaderText(null);
			jugadaNoValida.setContentText("Solo se puede cantar envido una vez por ronda.");
			jugadaNoValida.showAndWait();
		} catch (NoPuedeCantarEnvidoSeCantoFlorError x5) {
			Alert jugadaNoValida = new Alert(AlertType.ERROR);
			jugadaNoValida.setTitle("Error");
			jugadaNoValida.setHeaderText(null);
			jugadaNoValida.setContentText("Ya se canto flor, no se puede cantar envido.");
			jugadaNoValida.showAndWait();
		}

	}

	@FXML
	public void realEnvidoHandler() {
		try {
			Jugador jugadorQueCanto = this.jugadorActual;
			juego.realEnvido();
			this.labelStatus.setText(jugadorQueCanto.getNombre() + " canto real envido.");
			this.mostrarTodosLosBotones(false);
			this.botonFlor.setVisible(conFlor);
			this.mostrarBotones(this.botonesEnvido, true);
			this.mostrarBotones(this.botonesEnvidoQuiero, true);
			this.mostrarJugadorActual();
		} catch (SoloSePuedeCantarEnvidoEnPrimeraError x1) {
			Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
			jerarquiaNoValida.setTitle("Error");
			jerarquiaNoValida.setHeaderText(null);
			jerarquiaNoValida.setContentText("El real envido solo se puede cantar en primera.");
			jerarquiaNoValida.showAndWait();
		} catch (NoPuedeCantarEnvidoNoEsPieError x2) {
			Alert jugadaNoValida = new Alert(AlertType.ERROR);
			jugadaNoValida.setTitle("Error");
			jugadaNoValida.setHeaderText(null);
			jugadaNoValida.setContentText("Solo el jugador que es pie puede cantar real envido.");
			jugadaNoValida.showAndWait();
		} catch (NoRespetaJerarquiaDeEnvidoError x3) {
			Alert jugadaNoValida = new Alert(AlertType.ERROR);
			jugadaNoValida.setTitle("Error");
			jugadaNoValida.setHeaderText(null);
			jugadaNoValida.setContentText("No respeta la jerarquia del envido.");
			jugadaNoValida.showAndWait();
		} catch (SoloSePuedeCantarEnvidoUnaVezPorRondaError x4) {
			Alert jugadaNoValida = new Alert(AlertType.ERROR);
			jugadaNoValida.setTitle("Error");
			jugadaNoValida.setHeaderText(null);
			jugadaNoValida.setContentText("Solo se puede cantar envido una vez por ronda.");
			jugadaNoValida.showAndWait();
		} catch (NoPuedeCantarEnvidoSeCantoFlorError x5) {
			Alert jugadaNoValida = new Alert(AlertType.ERROR);
			jugadaNoValida.setTitle("Error");
			jugadaNoValida.setHeaderText(null);
			jugadaNoValida.setContentText("Ya se canto flor, no se puede cantar real envido.");
			jugadaNoValida.showAndWait();
		}

	}

	@FXML
	public void faltaEnvidoHandler() {
		try {
			Jugador jugadorQueCanto = this.jugadorActual;
			juego.faltaEnvido();
			this.labelStatus.setText(jugadorQueCanto.getNombre() + " canto falta envido.");
			this.mostrarTodosLosBotones(false);
			this.botonFlor.setVisible(conFlor);
			this.mostrarBotones(this.botonesEnvido, true);
			this.mostrarBotones(this.botonesEnvidoQuiero, true);
			this.mostrarJugadorActual();
		} catch (SoloSePuedeCantarEnvidoEnPrimeraError x1) {
			Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
			jerarquiaNoValida.setTitle("Error");
			jerarquiaNoValida.setHeaderText(null);
			jerarquiaNoValida.setContentText("El falta envido solo se puede cantar en primera.");
			jerarquiaNoValida.showAndWait();
		} catch (NoPuedeCantarEnvidoNoEsPieError x2) {
			Alert jugadaNoValida = new Alert(AlertType.ERROR);
			jugadaNoValida.setTitle("Error");
			jugadaNoValida.setHeaderText(null);
			jugadaNoValida.setContentText("Solo el jugador que es pie puede cantar falta envido.");
			jugadaNoValida.showAndWait();
		} catch (SoloSePuedeCantarEnvidoUnaVezPorRondaError x3) {
			Alert jugadaNoValida = new Alert(AlertType.ERROR);
			jugadaNoValida.setTitle("Error");
			jugadaNoValida.setHeaderText(null);
			jugadaNoValida.setContentText("Solo se puede cantar envido una vez por ronda.");
			jugadaNoValida.showAndWait();
		} catch (NoPuedeCantarEnvidoSeCantoFlorError x4) {
			Alert jugadaNoValida = new Alert(AlertType.ERROR);
			jugadaNoValida.setTitle("Error");
			jugadaNoValida.setHeaderText(null);
			jugadaNoValida.setContentText("Ya se canto flor, no se puede cantar falta envido.");
			jugadaNoValida.showAndWait();
		} catch (NoRespetaJerarquiaDeEnvidoError x3) {
			Alert jugadaNoValida = new Alert(AlertType.ERROR);
			jugadaNoValida.setTitle("Error");
			jugadaNoValida.setHeaderText(null);
			jugadaNoValida.setContentText("No respeta la jerarquia del envido.");
			jugadaNoValida.showAndWait();
		}
	}

	@FXML
	public void quieroEnvidoHandler() throws IOException {
		Jugador jugadorQueCanto = this.jugadorActual;
		this.juego.quieroEnvido();
		this.labelStatus.setText(jugadorQueCanto.getNombre() + " ha aceptado el envido.");
		Alert jerarquiaNoValida = new Alert(AlertType.INFORMATION);
		jerarquiaNoValida.setTitle("Tantos del envido");
		jerarquiaNoValida.setHeaderText("Ganador: " + juego.obtenerNombreGanadorDeEnvido());
		jerarquiaNoValida.setContentText("Puntaje del ganador: " + juego.puntosDeEnvidoGanador() + "\n");
		jerarquiaNoValida.showAndWait();
		this.mostrarTodosLosBotones(false);
		this.botonTruco.setVisible(true);
		if (juego.concluyoLaPartida()) {
			this.setPartidaFinalizada();
		}
		if (this.juego.seCantoTruco()) {
			this.mostrarBotones(this.botonesTruco, true);
			this.mostrarBotones(this.botonesTrucoQuiero, true);
		}

		this.mostrarPuntos();
		this.mostrarJugadorActual();
	}

	@FXML
	public void noQuieroEnvidoHandler() throws IOException {
		Jugador jugadorQueCanto = this.jugadorActual;
		this.juego.noQuieroEnvido();
		this.labelStatus.setText(jugadorQueCanto.getNombre() + " no quiso el envido.");
		this.mostrarTodosLosBotones(false);
		this.botonTruco.setVisible(true);
		if (juego.concluyoLaPartida()) {
			this.setPartidaFinalizada();
		}
		this.mostrarPuntos();
		this.mostrarJugadorActual();
		if (this.juego.seCantoTruco()) {
			this.mostrarBotones(this.botonesTruco, true);
			this.mostrarBotones(this.botonesTrucoQuiero, true);
		}
	}

	// **** Truco ****
	@FXML
	public void trucoHandler() {
		try {
			Jugador jugadorQueCanto = this.jugadorActual;
			juego.truco();
			this.labelStatus.setText(jugadorQueCanto.getNombre() + " canto truco.");
			this.mostrarTodosLosBotones(false);
			this.botonFlor.setVisible(conFlor);
			this.mostrarBotones(this.botonesEnvido, true);
			this.botonReTruco.setVisible(true);
			this.mostrarBotones(this.botonesTrucoQuiero, true);
			this.mostrarJugadorActual();
		} catch (NoRespetaJerarquiaDeTrucoError x1) {
			Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
			jerarquiaNoValida.setTitle("Error");
			jerarquiaNoValida.setHeaderText(null);
			jerarquiaNoValida.setContentText("No respeta la jerarquia del truco.");
			jerarquiaNoValida.showAndWait();
		} catch (NoPuedeCantarTrucoSeCantoEnvidoError x2) {
			Alert jugadaNoValida = new Alert(AlertType.ERROR);
			jugadaNoValida.setTitle("Error");
			jugadaNoValida.setHeaderText("No puede cantar truco, se canto envido.");
			jugadaNoValida.setContentText("Debe responderle al equipo contrario.");
			jugadaNoValida.showAndWait();
		} catch (NoPuedeCantarTrucoSeCantoFlorError x3) {
			Alert jugadaNoValida = new Alert(AlertType.ERROR);
			jugadaNoValida.setTitle("Error");
			jugadaNoValida.setHeaderText("No puede cantar truco, se canto flor.");
			jugadaNoValida.setContentText("Debe responderle al equipo contrario.");
			jugadaNoValida.showAndWait();
		}

	}

	@FXML
	public void reTrucoHandler() {
		try {
			Jugador jugadorQueCanto = this.jugadorActual;
			juego.reTruco();
			this.labelStatus.setText(jugadorQueCanto.getNombre() + " canto quiero retruco.");
			this.mostrarTodosLosBotones(false);
			this.botonValeCuatro.setVisible(true);
			this.mostrarBotones(this.botonesTrucoQuiero, true);
			this.mostrarJugadorActual();
		} catch (NoRespetaJerarquiaDeTrucoError x1) {
			Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
			jerarquiaNoValida.setTitle("Error");
			jerarquiaNoValida.setHeaderText(null);
			jerarquiaNoValida.setContentText("No respeta la jerarquia del truco.");
			jerarquiaNoValida.showAndWait();
		} catch (NoPuedeRedoblarTrucoSuEquipoLoCantoError x2) {
			Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
			jerarquiaNoValida.setTitle("Error");
			jerarquiaNoValida.setHeaderText(null);
			jerarquiaNoValida.setContentText("No puede redoblar el truco, su equipo lo canto.");
			jerarquiaNoValida.showAndWait();
		}

	}

	@FXML
	public void valeCuatroHandler() {
		try {
			Jugador jugadorQueCanto = this.jugadorActual;
			juego.valeCuatro();
			this.labelStatus.setText(jugadorQueCanto.getNombre() + " canto quiero vale 4.");
			this.mostrarTodosLosBotones(false);
			this.mostrarBotones(this.botonesTrucoQuiero, true);
			this.mostrarJugadorActual();
		} catch (NoRespetaJerarquiaDeTrucoError x1) {
			Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
			jerarquiaNoValida.setTitle("Error");
			jerarquiaNoValida.setHeaderText(null);
			jerarquiaNoValida.setContentText("No respeta la jerarquia del truco.");
			jerarquiaNoValida.showAndWait();
		} catch (NoPuedeRedoblarTrucoSuEquipoLoCantoError x2) {
			Alert jerarquiaNoValida = new Alert(AlertType.ERROR);
			jerarquiaNoValida.setTitle("Error");
			jerarquiaNoValida.setHeaderText(null);
			jerarquiaNoValida.setContentText("No puede redoblar el truco, su equipo lo canto.");
			jerarquiaNoValida.showAndWait();
		}

	}

	@FXML
	public void quieroTrucoHandler() {
		Jugador jugadorQueCanto = this.jugadorActual;
		juego.quieroTruco();
		this.labelStatus.setText(jugadorQueCanto.getNombre() + " dijo quiero.");
		this.mostrarBotones(this.botonesEnvido, false);
		this.mostrarBotones(this.botonesFlor, false);
		this.mostrarBotones(this.botonesTrucoQuiero, false);

		this.mostrarJugadorActual();
	}

	@FXML
	public void noQuieroTrucoHandler() throws IOException {
		Jugador jugadorQueCanto = this.jugadorActual;
		juego.noQuieroTruco();
		this.labelStatus.setText(jugadorQueCanto.getNombre() + " se fue al mazo.");
		if (juego.concluyoLaPartida()) {
			this.setPartidaFinalizada();
		} else {
			this.setNuevaRonda();
			this.mostrarPuntos();
			this.mostrarJugadorActual();
		}
	}

	@FXML
	public void irseAlMazoHandler() throws IOException {
		Jugador jugadorQueCanto = this.jugadorActual;
		juego.irseAlMazo();
		this.labelStatus.setText(jugadorQueCanto.getNombre() + " se fue al mazo.");
		if (juego.concluyoLaPartida()) {
			this.setPartidaFinalizada();
		} else {
			this.setNuevaRonda();
			this.mostrarPuntos();
			this.mostrarJugadorActual();
		}
	}

}