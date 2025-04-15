package controladores;

import javafx.stage.Stage;
import modelos.Jugador;
/**
 * Clase de Controlador Principal. Es el que se encargará de la lógica general del juego
 * @author Sandra Moñino, Cristo Macias
 */ 
public class ControladorPrincipal extends Controlador{
	private Jugador jugador;
	//private ControladorLoginJugador controladorLogin;
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
		//new ControladorMenu(getVentana(),this); //this es este controlador, debe pasarse al constructor
		// para que al terminar lo que sea se haga la llamada para los siguientes controladores/vistas
		//el getVentana() es una llamada al getter para que se modifique la ventana del controlador padre, que 
		// es el atributo Stage ventana que tendrán todos
		//vistaMenu=cargarVista(this,"vistaMenu");
		//cambiarVista(this.ventana,vistaMenu);
		//Aquí tienes que instanciarte el controlador del menú o cargar la vista para el menu 
		//y recoger los eventos
		//Tienes que descomentar en controladorLoginJugador en la linea 125 para que se cargue
		//cuando termines el login y vayas probando, okey ?? 
		//La misma para el escenario. No tienes porqué usar otros controaldores la verdad,peudes ahcerlo
		//todo aquí pero creo que así está más organziado
		//Está todo comentado lo que creo que es más complicado y eso.
		//Echa un buen vistazo a los controladores para que veas la estructura
		//Si decides no usar más controladores y usar este puedes, pero usa este método para ya cambiar 
	}
}