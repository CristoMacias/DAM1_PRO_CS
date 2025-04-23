package controladores;

import javafx.stage.Stage;
import javafx.scene.Scene;
import modelos.Jugador;
import modelos.Escenario;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
/**
 * Clase de Controlador Principal. Es el que se encargará de la lógica general del juego
 * @author Sandra Moñino, Cristo Macias
 */ 
public class ControladorPrincipal extends Controlador{
	private Jugador jugador;
	private ControladorLoginJugador controladorLogin;
	private ControladorMenu controladorMenu;
	private ControladorEscenario controladorEscenario;
	@FXML private Label labelNombreJugador;
	@FXML private Label labelTotalChocado;
	/**
	 * Constrcutor para ControladorPrincipal
	 * @param stage Recibe el stage de ventana
	 */ 
	public ControladorPrincipal(Stage stage){
		super(stage);
		new ControladorBienvenida(ventana,this);//Instanciamos el ControladorBienvenida para que se incie la vista
	}
	/**
	 * Método para instanciar y mostrar las vistas para el login además de la lógica.
	 */ 
	public void cargarLogin(){
		new ControladorLoginJugador(ventana,this);//Instanciamos el ControladorLoginJugador para que se inicie la vista y el controlador
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
		controladorMenu = new ControladorMenu(ventana,this);
	}

	/**
	 * Método para instanciar el escenario seleccionado y mostrar las vistas para el escenario
	 */
	public void cargarEscenario(String dificultad){
		Escenario escenario = new Escenario(dificultad);
		controladorEscenario = new ControladorEscenario(ventana, this,escenario);
	}
	/**
	 * Método para cargar la imagen final del juego al temrinar el laberinto
	 */ 
	public void cargarFin(){
		Scene vistaFinal=cargarVista(this,"vistaFinJuego");
		ventana.setTitle("FIN"); // Cambiar el titulo de la ventana
		cambiarVista(vistaFinal);//Cambiamos la vista
		labelNombreJugador.setText("¡"+jugador.getNombre().toUpperCase()+"!");
		labelTotalChocado.setText("TOTAL VECES CHOCADO: "+jugador.getTotalChocado());
		vistaFinal.setOnKeyPressed(event->{
			jugador.setTotalChocado(0); //Evento para cambiar la vista pulsando cualquier tecla
			new ControladorMenu(ventana,this);
		});
	}
	
	/**
	 * Getter de jugador
	 */
	public Jugador getJugador(){
		return this.jugador;
	}

	public void cargarIntroduccion(){
		Scene vistaIntroduccion=cargarVista(this,"vistaIntroduccion");
		cambiarVista(vistaIntroduccion);
		vistaIntroduccion.setOnKeyPressed(event->{
			ventana.close();
			cargarLogin();
		});
	}
}