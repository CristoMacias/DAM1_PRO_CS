package controladores;

import javafx.stage.Stage;
import modelos.Jugador;
import modelos.Escenario;
import java.util.Arrays;
/**
 * Clase de Controlador Principal. Es el que se encargará de la lógica general del juego
 * @author Sandra Moñino, Cristo Macias
 */ 
public class ControladorPrincipal extends Controlador{
	private Jugador jugador;
	private ControladorLoginJugador controladorLogin;
	private ControladorMenu controladorMenu;
	private ControladorEscenario controladorEscenario;
	
	public ControladorPrincipal(Stage stage){
		super(stage);
		new ControladorBienvenida(getVentana(),this);//Instanciamos el ControladorBienvenida para que se incie la vista
	}
	/**
	 * Método para instanciar y mostrar las vistas para el login además de la lógica.
	 */ 
	public void cargarLogin(){
		new ControladorLoginJugador(getVentana(),this);//Instanciamos el ControladorLoginJugador para que se inicie la vista y el controlador
	}
	/**
	 * Método para instanciar al jugador
	 * @param nombre Recibe el nombre
	 * @param email Recibe el email
	 */ 
	public void cargarJugador(String nombre,String email){
		jugador=new Jugador(nombre,email);
		System.out.println("Jugador cargado: "+jugador.getNombre());
	}
	/**
	 * Método para instanciar y mostrar las vistas para el menú de selección de niveles
	 */ 
	public void cargarMenu(){
		controladorMenu = new ControladorMenu(getVentana(),this);
	}

	/**
	 * Método para instanciar el escenario seleccionado y mostrar las vistas para el escenario
	 */
	public void cargarEscenario(String dificultad){
		Escenario escenario = new Escenario(dificultad);
		//System.out.println("¿matriz cargada? " + Arrays.deepToString(escenario.getEscenario()));
		controladorEscenario = new ControladorEscenario(getVentana(), this, escenario);
		
	}
}