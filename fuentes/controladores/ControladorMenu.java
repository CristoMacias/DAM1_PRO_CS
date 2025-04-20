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

	public ControladorMenu(Stage stage, ControladorPrincipal controladorPp){
		super(stage);
		this.controladorPp=controladorPp;
		this.vistaMenu = cargarVista(this, "vistaMenu");
		ventana.setTitle("Menu dificultad");
		cambiarVista(vistaMenu);
		capturarEventos();
	}

	private void capturarEventos(){
		botonFacil.setOnAction(event -> cargarEscenario("facil"));
		botonMedio.setOnAction(event -> cargarEscenario("medio"));
		botonDificil.setOnAction(event -> cargarEscenario("dificil"));
		botonSalir.setOnAction(event -> controladorPp.salirJuego());
	}

	private void cargarEscenario(String dificultad){
		ventana.close(); //Cerramos la ventana
		controladorPp.cargarEscenario(dificultad);
	}

	
}