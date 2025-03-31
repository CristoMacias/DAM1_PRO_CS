/**
 * Clase Jugador
 * 
 * El objeto jugador tendrá los atributos nombre y email.
 * 
 * @author Sandra Moñino, Cristo Macias
 * Licence: GPL v3
 * 
 */ 
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;

import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.io.InputStream;
import java.io.ObjectInputStream;


public class Jugador implements Serializable{ //Implementamos la interfaz Serializable para hacer que el jugador se serialice
	private String nombre;
	private String email;

	/**
	 * Constructor para inicializar los atributos del personaje e instanciarlo
	 * @param nombre Recibe el nombre del jugador
	 */ 
	public Jugador(String nombre){
		this.nombre=nombre;
		
		cargarJugador();
	}
	//GETTER Y SETTER
	/**
	 * Getter de nombre
	 * @return Devuelve el valor de nombre
	 */ 
	public String getNombre(){
		return this.nombre;
	}
	/**
	 * Setter para nombre
	 * @param nombre Recibe el nuevo valor para el nombre
	 */ 
	public void setNombre(String nombre){
		this.nombre=nombre;
	}
	/**
	 * Getter para email
	 * @return Devuelve el valor de email
	 */ 
	public String getEmail(){
		return this.email;
	}
	/**
	 * Setter de email
	 * @param email Recibe el nuevo valor para el email
	 */ 
	public void setEmail(String email){
		this.email=email;
	}

	//MÉTODOS
	/**
	 * Método para guardar los datos del jugador en el archivo .bin del jugador
	 */ 
	public void guardarJugador(){
		Path pathJugadores=Paths.get("jugadores");
		Path pathJugador=pathJugadores.resolve(this.nombre+".bin");
		try(ObjectOutputStream flujoSalidaJugador = new ObjectOutputStream(Files.newOutputStream(pathJugador))){ //Abrimos el flujo para serializar
			flujoSalidaJugador.writeObject(this.nombre); //Serializamos y guardamoss el nombre
			flujoSalidaJugador.writeObject(this.email); //Serializamos y guardamos el email
		}catch(IOException e){
			e.printStackTrace();
			e.getMessage();
		}
	}
	/**
	 * Método para cargar los datos del jugador. Comprueba si existe el archivo de config con su nombre, si no es así, lo crea
	 * Añadimos @SuppressWarning para que el metodo ignore el uncheked a la hora de convertir en string los atributos
	 */ 
	@SuppressWarnings ("unchecked")
	public void cargarJugador(){
		Path pathJugadores=Paths.get("jugadores"); //Creamos la ruta del directorio de jugadores
		Path pathJugador=pathJugadores.resolve(this.nombre+".bin"); //Creamos la ruta del archivo del jugador

		if(Files.exists(pathJugador)){ //Comprobamos que existe el archivo del jugador
			String nombre="";
			String email="";
			try(ObjectInputStream flujoEntradaJugador = new ObjectInputStream(Files.newInputStream(pathJugador))){
				nombre=(String)flujoEntradaJugador.readObject();
				email=(String)flujoEntradaJugador.readObject();
				this.nombre=nombre;//Asignamo el nombre al jugador
				this.email=email; //Asignamos el email al jugador
			}catch(IOException e){
				e.printStackTrace();
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}
		}else{
			String email = "email@.es";//Por ahora email por defecto para probar.
			System.out.println("Introduce tu email: "+email);//Pedimos el email
			this.email=email;//Asignamos el email al atributo
			guardarJugador();//Usamos el propio método de guardarJugador() para serializar y guardar los datos en el archivo .bin
		}
	}
}