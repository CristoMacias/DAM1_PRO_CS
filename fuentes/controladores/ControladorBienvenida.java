package controladores;
import javafx.stage.Stage;
import javafx.scene.Scene;
/**
 * Clase ControladorBienvenida
 * Hereda de Controlador. Se encarga de la lógica de la vista "splash"/bienvenida
 * @author Sandra Moñino,Cristo Macias
 */ 
public class ControladorBienvenida extends Controlador{
	private Scene vistaBienvenida;
	private Scene vistaLoginNombre;
	private ControladorPrincipal controladorPp;
	public ControladorBienvenida(Stage stage,ControladorPrincipal controladorPp){
		super(stage);
		this.controladorPp=controladorPp;
		this.vistaLoginNombre=cargarVista(this,"vistaLoginNombre");
		this.vistaBienvenida=cargarVista(this,"vistaBienvenida");
		cambiarVista(vistaBienvenida);
		getVentana().setTitle("Bienvenida a LABYRINTHUS");
		capturarEventos();
	}
	/**
	 * Método para capturar los eventos de las ventanas
	 */ 
	public void capturarEventos(){
		vistaBienvenida.setOnKeyPressed(event->{
			getVentana().close();//Cerramos la ventana
			controladorPp.cargarLogin();//Llamada al método cargarLogin() para comnezar con la parte del login
		});

	}
}