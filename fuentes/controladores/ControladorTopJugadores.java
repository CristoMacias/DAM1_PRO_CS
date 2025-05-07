package controladores;

import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jdbc.JDBC;
import modelos.MejoresJugadores;
import java.util.List;
import java.util.ArrayList;
/**
 * Controlador de la vista Top 10 Jugadores
 *
 *@author Sandra Moñino,Cristo Macías
 */
public class ControladorTopJugadores extends Controlador{
	private Scene vistaTopJugadores;
	@FXML private Label labelNombre1;
	@FXML private Label labelNombre2;
	@FXML private Label labelNombre3;
	@FXML private Label labelNombre4;
	@FXML private Label labelNombre5;
	@FXML private Label labelNombre6;
	@FXML private Label labelNombre7;
	@FXML private Label labelNombre8;
	@FXML private Label labelNombre9;
	@FXML private Label labelNombre10;
	@FXML private Label labelPuntuacion1;
	@FXML private Label labelPuntuacion2;
	@FXML private Label labelPuntuacion3;
	@FXML private Label labelPuntuacion4;
	@FXML private Label labelPuntuacion5;
	@FXML private Label labelPuntuacion6;
	@FXML private Label labelPuntuacion7;
	@FXML private Label labelPuntuacion8;
	@FXML private Label labelPuntuacion9;
	@FXML private Label labelPuntuacion10;
	private Label[] nombres;
	private Label[] puntuaciones;
	private JDBC jdbc;
	/**
	 * Constrcutor de ContorladorTopJugadores
	 * @param stage Recibe la ventana
	 * @param contorladorPp Recibe el contorlador pp
	 * @param jdbc Recibe el jdbc
	 */
	public ControladorTopJugadores(Stage stage,ControladorPrincipal controladorPp,JDBC jdbc){
		super(stage);
		this.controladorPp=controladorPp;
		this.vistaTopJugadores=cargarVista(this,"vistaTopJugadores");
		this.jdbc=jdbc;
		nombres=new Label[10];
		puntuaciones=new Label[10];
		guardarLabelNombre();
		guardarLabelPuntuacion();
		cambiarVista(vistaTopJugadores);
		actualizarDatos();
		volverMenu();
	}
	/**
	 * Método para guardar los label de nombres en un Array
	 */ 
	public void guardarLabelNombre(){
		nombres[0]=labelNombre1;
		nombres[1]=labelNombre2;
		nombres[2]=labelNombre3;
		nombres[3]=labelNombre4;
		nombres[4]=labelNombre5;
		nombres[5]=labelNombre6;
		nombres[6]=labelNombre7;
		nombres[7]=labelNombre8;
		nombres[8]=labelNombre9;
		nombres[9]=labelNombre10;
	}
	/**
	 * Método para guardar los label de puntuacion en un Array
	 */ 
	public void guardarLabelPuntuacion(){
		puntuaciones[0]=labelPuntuacion1;
		puntuaciones[1]=labelPuntuacion2;
		puntuaciones[2]=labelPuntuacion3;
		puntuaciones[3]=labelPuntuacion4;
		puntuaciones[4]=labelPuntuacion5;
		puntuaciones[5]=labelPuntuacion6;
		puntuaciones[6]=labelPuntuacion7;
		puntuaciones[7]=labelPuntuacion8;
		puntuaciones[8]=labelPuntuacion9;
		puntuaciones[9]=labelPuntuacion10;
	}
	/**
	 * Método para volver al menu
	 */ 
	public void volverMenu(){
		vistaTopJugadores.setOnKeyPressed(event->{

			controladorPp.cargarMenu();//Cuando presione, vuelve a la vista del Menús
		});
	}
	/**
	 * Método para actualizar los datos en los labels
	 */ 
	public void actualizarDatos(){
		List<MejoresJugadores> topTen=jdbc.getTopTen();
		for(int i=0;i<nombres.length;i++){
			nombres[i].setText(topTen.get(i).getNombre());
			puntuaciones[i].setText(String.valueOf(topTen.get(i).getPuntuacionTotal()));
		}
	}
}