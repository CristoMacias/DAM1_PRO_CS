package controladores;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
/**
 * Clase ControladorLoginJugador
 * Hereda de Controlador. Se encargará de manejar la lógica y las vistas para el login del jugador.
 * Mostrará las vistas para recoger los datos del jugador y cargarlo
 * @author Sandra Moñino,Cristo Macias
 */ 
public class ControladorLoginJugador extends Controlador{
	private Scene vistaLoginNombre;
	private Scene vistaLoginEmail;
	//Elementos fx para capturar eventos
	@FXML private Button botonNombre;
	@FXML private TextField campoNombre;
	@FXML private Button botonEmail;
	@FXML private TextField campoEmail;
	private String nombreJugador;
	private String emailJugador;
	private Path pathJugadores=Paths.get("jugadores");
	private Path pathJugador;
	private ControladorPrincipal controladorPp;
	/**
	 * Constructor para el controlador
	 * @param ventana Recibe el stage/ventana
	 */ 
	public ControladorLoginJugador(Stage stage,ControladorPrincipal controladorPp){
		super(stage);
		this.controladorPp=controladorPp;
		this.vistaLoginNombre=cargarVista(this,"vistaLoginNombre"); //Instanciamos al vista llamando al método del super cargarVista
		this.vistaLoginEmail=cargarVista(this,"vistaLoginEmail"); 
		getVentana().setTitle("Login Jugador"); //Cambiamos el titulo de de la ventana
		cambiarVista(vistaLoginNombre); // Cambiamos la vista para pasar de la bienvenida al del login del jugador
		capturarEventosNombre(); //Llamamos al método para capturar el boton del login nombre
	}
	/**
	 * Método para capturar el evento de botonAceptar para recoger el nombre del jugador
	 */ 
	private void capturarEventosNombre(){
		botonNombre.setOnAction(event->{
			nombreJugador=campoNombre.getText().trim().toLowerCase();//Recogemos el nombre del jugador quitando espacios y en minúscula
			if(!nombreJugador.isEmpty() && !nombreJugador.isBlank()){
				comprobarExiste();//Llamamos al método comprobarExiste para ver si el jugador existe
			}
		});
	}
	/**
	 * Método para capturar el evento del botonEmail para recoger el email del jugador
	 */ 
	private void capturarEventosEmail(){
		botonEmail.setOnAction(event->{
			emailJugador=campoEmail.getText().trim().toLowerCase();//Recogemos el email del jugador, quitando espacioes y en minusucla
			if(!emailJugador.isEmpty() && !emailJugador.isBlank() && emailJugador.contains("@")){ //Comprobar que el email no tenga espacios ni esté vacio ni le falte el @
				guardarJugador();//Llamamos al metodo guardarJugador para guardar los datos
			}
		});
	}
	/**
	 * Método para cargar el jugador
	 * @return Devuelve true si existe o false  si no
	 */  
	private boolean cargarJugador(){
		pathJugador=pathJugadores.resolve(nombreJugador+".bin");//Creamos el path del fichero del jugador
		if(Files.exists(pathJugador)){//Comprobamos si existe el fichero.bin del jugador
			try(ObjectInputStream flujoEntradaJugador=new ObjectInputStream(Files.newInputStream(pathJugador))){
				emailJugador=(String) flujoEntradaJugador.readObject();//Asignamos al email directamente leido del email
				return true;
			}catch(IOException e){
				System.out.println("Error inesperado al leer el fichero de jugador");
				e.printStackTrace();
			}catch(ClassNotFoundException e){
				System.out.println("Error con la clase (String) al convertir del fichero jugador");
				e.printStackTrace();
			}
		}
		return false;
	}
	/**
	 * Método para crear el fichero de jugador y guardar sus datos
	 */ 
	private void guardarJugador(){
		pathJugador=pathJugadores.resolve(nombreJugador+".bin");//Creamos el path del fichero del jugador
		try(ObjectOutputStream flujoSalidaJugador = new ObjectOutputStream(Files.newOutputStream(pathJugador))){//Abrimos el flujo de salida para guardar los datos del jugador
			flujoSalidaJugador.writeObject(nombreJugador);//Guardamos/Escribimos el nombre
			flujoSalidaJugador.writeObject(emailJugador); //Guardamos/Escribimos el email
		}catch(IOException e){
			System.out.println("Error en el guardado del jugador");
			e.printStackTrace();
		}
		terminarLogin();
	}
	/**
	 * Método para comprobar si existe el fichero con el nombre del jugador, si no existe muestra la vista para epdir el email y llama al metodo guardar jugador
	 */ 
	private void comprobarExiste(){
		if(!cargarJugador()){//Llamamos al método cargarJugador para comprobar si existe el jugador,esperamos que si devuelve false entonces ejectuamos el siguiente bloque
			getVentana().setTitle("Registro jugador");//Cambiamos el título de la ventana
			cambiarVista(vistaLoginEmail);//Llamamos al método para cambiar al vista,le pasamos la ventana,y la vista
			capturarEventosEmail();//Llamamos al método para capturar el evento para el bóton de aceptar de email
			return;//Terminamos para que no pase a lo siguiente 
		}
		terminarLogin();//Llamamos al método para terminar el login si existe el jugador
	
	}
	/**
	 *Método para terminar el login 
	 */ 
	private void terminarLogin(){
		controladorPp.cargarJugador(nombreJugador,emailJugador);
		getVentana().close(); //Cerramos la ventana
		//Llama a metodo para instanciar cnotroladormenu y vistamenu
		//controladorPp.cargarMenu();
	}
}