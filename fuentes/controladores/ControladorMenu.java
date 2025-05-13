package controladores;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXML;
import modelos.Escenario;
/**
 * Clase de Controlador Menu. Es el que se encargará de recibir el nivel de dificultad que quiere el usuario
 * @author Sandra Moñino, Cristo Macias
 */ 
public class ControladorMenu extends Controlador{
	private Scene vistaMenu;
	@FXML private Button botonFacil;
	@FXML private Button botonMedio;
	@FXML private Button botonDificil;
	@FXML private Button botonSalir;
	@FXML private Button botonTopTen;
	@FXML private Button botonCambiar;
	/**
	 * Contructor del menu
	 * @param stage recibe el stage del controlador, desde la herencia
	 * @param controladorPp Recibe el controlador principal
	 */
	public ControladorMenu(Stage stage, ControladorPrincipal controladorPp){
		super(stage);
		this.controladorPp=controladorPp;
		this.vistaMenu = cargarVista(this, "vistaMenu");
		ventana.setTitle("Menu dificultad");
		cambiarVista(vistaMenu);
		capturarEventos();
	}
	/**
 	* Captura los eventos de los botones.
 	*/
	private void capturarEventos(){
		botonFacil.setOnAction(event -> cargarEscenario("facil"));
		botonMedio.setOnAction(event -> cargarEscenario("medio"));
		botonDificil.setOnAction(event -> cargarEscenario("dificil"));
		botonTopTen.setOnAction(event-> controladorPp.cargarTopJugadores());//Boton para cambiar la vista al Top 10 Mejores Jugadores
		botonCambiar.setOnAction(event -> controladorPp.cargarLogin());//Boton para cambiar la vista al login del jugador para cambiar de jugador
		botonSalir.setOnAction(event -> controladorPp.salirJuego());//Capturamos el evento del boton salir para terminar el juego
	}
	/**
	 * Método para cargar el escenario y recibe de capturar eventos la dificultad elegida
	 * @param dificultad Dificultad elegida por el usuario que a su vez es el nombre del txt
	 */
	private void cargarEscenario(String dificultad){
		controladorPp.cargarEscenario(dificultad);
	}
}