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
	/**
	 * Controlador de Bienvenida
	 * @param stage Recibe el stage de ventana
	 * @param controladorPp Recibe el controlador Principal
	 */ 
	public ControladorBienvenida(Stage stage,ControladorPrincipal controladorPp){
		super(stage);
		this.controladorPp=controladorPp;//Instanciamos el controlador principal
		this.vistaBienvenida=cargarVista(this,"vistaBienvenida");//Cargamos la vista de bienvenida
		cambiarVista(vistaBienvenida);//Llamada al metodo cambiarVista para mostrar la vistaBienvenida
		ventana.setTitle("Bienvenida a LABYRINTHUS");//Cambiamos el titulo de la ventana/stage
		capturarEventos();//Llamada a caputar el evento para cambiar de ventana
	}
	/**
	 * Método para capturar los eventos de las ventanas
	 */ 
	public void capturarEventos(){
		vistaBienvenida.setOnKeyPressed(event->{
			ventana.close();//Cerramos la ventana
			controladorPp.cargarLogin();//Llamada al método cargarLogin() para comnezar con la parte del login
		});

	}
}