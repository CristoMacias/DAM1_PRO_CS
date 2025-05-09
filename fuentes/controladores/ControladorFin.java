package controladores;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import modelos.Jugador;
/**
 * Controlador de Fin. Maneja toda la lógica que ocurre en la vista Fin
 * 
 * @author Sandra Moñino
 */ 
public class ControladorFin extends Controlador{
	private Scene vistaFin;
	//Label para lo conseguido
	@FXML private Label labelNombreJugador;
	@FXML private Label labelTotalChocado;
	@FXML private Label labelMonedas;
	@FXML private Label labelLlave;
	@FXML private Label labelPuntuacion;
	@FXML private Label labelTiempo;
	//Label para la puntuacion de cada objeto
	@FXML private Label labelPuntosMonedas;
	@FXML private Label labelPuntosLlave;
	@FXML private Label labelPuntosChocado;
	@FXML private Label labelPuntosTiempo;
	private Jugador jugador;
	/**
	 * Constructor de ControladorFin
	 * @param ventana Recibe el stage
	 * @param controladorPp Recibe el controladorPp
	 * @param jugador Recibe el jugador
	 */ 
	public ControladorFin(Stage ventana,ControladorPrincipal controladorPp,Jugador jugador){
		super(ventana);
		this.vistaFin=cargarVista(this,"vistaFinJuego");
		this.jugador=jugador;
		this.controladorPp=controladorPp;
		this.ventana.setTitle("FIN"); // Cambiar el titulo de la ventana
		cambiarVista(this.vistaFin);
		modificarEstadisticas();
		calcularMostrarPuntuaciones();
		cambiarTopJugadores();
	}
	/**
	 * Método para modificar las estadísticas y mostrar las del jugador
	 */ 
	public void modificarEstadisticas(){
		labelNombreJugador.setText("¡"+jugador.getNombre().toUpperCase()+"!");//Mostrar nombre del jugador
		labelTotalChocado.setText(String.valueOf(jugador.getTotalChocado()));//Mostrar el total de veces chocado
		labelMonedas.setText(String.valueOf(jugador.getTotalMonedas()));//Mostrar el total de monedas
		labelLlave.setText(jugador.comprobarLlave()); //Mostrar si tiene llave o no
		labelTiempo.setText(jugador.formatoTiempo()); //Mostrar el total de segundos formateado
		labelPuntuacion.setText(String.valueOf(jugador.getTotalPuntuacion()));//Mostrar el total de puntos 
	}

	/**
	 * Método para calcular y mostrar las puntuaciones según la estadística
	 */ 
	public void calcularMostrarPuntuaciones(){
		labelPuntosMonedas.setText(String.valueOf(jugador.puntuarMonedas()));//Calcula los puntos por monedas y lo muestra
		labelPuntosLlave.setText(String.valueOf(jugador.puntuarLlave()));//Calcula los puntos por tener la llave o noy lo muestra
		labelPuntosChocado.setText(String.valueOf(jugador.puntuarChocado())); //Calcula los puntos por las veces chocados y lo muestra
		labelPuntosTiempo.setText(String.valueOf(jugador.puntuarTiempo()));//Calcula los puntos correspondientes al tiempo restante y lo muestra
	}
	/**
	 * Método para capturar el evento de presionar cualquier tecla y cambiar a la vists Top Jugadores
	 */ 
	public void cambiarTopJugadores(){
		vistaFin.setOnKeyPressed(event->{
			controladorPp.cargarTopJugadores();//Cambiar la vista al top ten
		});
	}
}