package controladores;

import javafx.stage.Stage;
import javafx.scene.Scene;
import modelos.Jugador;
import modelos.Escenario;
import modelos.MejoresJugadores;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import jdbc.JDBC;
import javafx.scene.media.MediaPlayer;
/**
 * Clase de Controlador Principal. Es el que se encargará de la lógica general del juego
 * @author Sandra Moñino, Cristo Macias
 */ 
public class ControladorPrincipal extends Controlador{
	private Jugador jugador;
	private ControladorLoginJugador controladorLogin;
	private ControladorMenu controladorMenu;
	private ControladorEscenario controladorEscenario;
	private ControladorMedia controladorMedia;
	private ControladorFin controladorFin;
	private ControladorTopJugadores ControladorTopJugadores;
	private JDBC jdbc;
	@FXML private Label labelNombreJugador;
	@FXML private Label labelTotalChocado;
	@FXML private Label labelMonedas;
	@FXML private Label labelLlave;
	@FXML private Label labelPuntuacion;
	@FXML private Label labelTiempo;
	private MediaPlayer mpActual;
	/**
	 * Constrcutor para ControladorPrincipal
	 * @param stage Recibe el stage de ventana
	 */ 
	public ControladorPrincipal(Stage stage){
		super(stage);
		controladorMedia=new ControladorMedia();//Instanciamos el controladorMedia para manejar todo lo relacionado con el media.
		mpActual=controladorMedia.reproducirBienvenida();
		new ControladorBienvenida(ventana,this);//Instanciamos el ControladorBienvenida para que se incie la vista
	}
	/**
	 * Método para instanciar y mostrar las vistas para el login además de la lógica.
	 */ 
	public void cargarLogin(){
		jdbc = new JDBC();
		jdbc.crearConexion();
		jdbc.introducirDatosPredeterminados();
		controladorMedia.parar(mpActual);
		mpActual=controladorMedia.reproducirLogin();
		controladorLogin=new ControladorLoginJugador(ventana,this);//Instanciamos el ControladorLoginJugador para que se inicie la vista y el controlador
	}
	/**
	 * Método para instanciar al jugador
	 * @param nombre Recibe el nombre
	 * @param email Recibe el email
	 */ 
	public void cargarJugador(String nombre,String email){
		jugador=new Jugador(nombre,email);
	}
	/**
	 * Método para instanciar y mostrar las vistas para el menú de selección de niveles
	 */ 
	public void cargarMenu(){
		jugador.setTotalChocado(0); //Evento para cambiar la vista pulsando cualquier tecla
		jugador.setTotalMonedas(0);
		jugador.setTieneLlave(false);
		jugador.setTotalSegundos(0);
		jugador.setTotalPuntuacion(0);
		controladorMedia.parar(mpActual);
		mpActual=controladorMedia.reproducirMenu();
		controladorMenu = new ControladorMenu(ventana,this);
	}
	/**
	 * Método para instanciar el escenario seleccionado y mostrar las vistas para el escenario
	 */
	public void cargarEscenario(String dificultad){
		Escenario escenario = new Escenario(dificultad);
		controladorMedia.parar(mpActual);
		mpActual=controladorMedia.reproducirLaberinto();
		controladorEscenario = new ControladorEscenario(ventana, this,escenario);
	}
	/**
	 * Método para cargar la imagen final del juego al temrinar el laberinto
	 */ 
	public void cargarFin(){
		controladorMedia.parar(mpActual);
		mpActual=controladorMedia.reproducirFinal();
		jugador.calcularPuntuacion();//Calculamos la puntuacion total
		jugador.comprobarPuntos();//Comprobamos los puntos para que si el resutlado es negativo, sea 0.
		MejoresJugadores nuevoJugador = new MejoresJugadores(0, jugador.getNombre(), jugador.getTotalPuntuacion(), controladorEscenario.getTotalSegundos(), jugador.getTotalMonedas(), jugador.getTotalChocado());
		jdbc.modificarRanking(nuevoJugador);
		controladorFin=new ControladorFin(ventana,this,jugador);
	}
	/**
	 * Getter de jugador
	 */
	public Jugador getJugador(){
		return this.jugador;
	}
	/**
	 * Método para cargar la vista introduccion del videojuego
	 */ 
	public void cargarIntroduccion(){
		Scene vistaIntroduccion=cargarVista(this,"vistaIntroduccion");
		controladorMedia.parar(mpActual);
		mpActual=controladorMedia.reproducirIntroduccion();
		cambiarVista(vistaIntroduccion);
		vistaIntroduccion.setOnKeyPressed(event->{
			cargarLogin();
		});
	}
	/**
	 * getter de ControladorMedia
	 */ 
	public ControladorMedia getControladorMedia(){
		return this.controladorMedia;
	}
	/**
	 * Método para cargar la vista del Top 10 Mejores Jugadores
	 */ 
	public void cargarTopJugadores(){
		controladorMedia.parar(mpActual);
		mpActual=controladorMedia.reproducirTopTen();
		new ControladorTopJugadores(ventana,this,jdbc);
	}
}