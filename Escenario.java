/**
 * Clase Escenario
 * 
 * Crea objetos de tipo escenario 
 * 
 * @author Sandra Moñino, Cristo Macias
 * 
 * Licence: GPL v3
 */ 
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Escenario{
	private String nombre;
	private List<String> escenarioCargado;
	/**
	 * Constructor de escenario
	 */ 
	public Escenario(String nombre){
		this.nombre=nombre;
		this.escenarioCargado = cargarEscenario();
	}
	/**
	 * Método para cargar el atributo matrizEscenario , inicializa una matriz en tamaño y contenido desde un archivo .txt
	 */ 
	@SuppressWarnings ("unchecked")
	public List<String> cargarEscenario(){
		
		Path pathEscenarios=Paths.get("escenarios"); //Creamos la ruta del directorio escenarios
		Path pathArchivoEscenario=pathEscenarios.resolve(this.nombre+".txt"); //Creamos la ruta de este escenario
		List<String> escenarioCargado=new ArrayList<>(); //Creamos una lista donde guardaremos cada linea del archivo
		try{
			escenarioCargado=(List<String>) Files.readAllLines(pathArchivoEscenario); //Leemos cada linea del archivo y lo guardamos en la lista
		}catch(IOException e){
			e.printStackTrace();
		}
		return escenarioCargado; //Devuelve la lista con el escenario cargado.
	}

	/**
	 * Contabiliza los espacios y los obstáculos del escenario
	 * @return revuelve un Array donde la posición 1 son los obstáculos y la posición 2 son los espacios
	 */
	public Integer[] contabilizarElementos(){
		Integer contadorObstaculos = 0;
		Integer contadorEspacios = 0;

		for(String linea : this.escenarioCargado){
			for(char elementos : linea.toCharArray()){
				if(elementos == 'X'){
					contadorObstaculos++;
				}
				else if(elementos == ' '){
					contadorEspacios++;
				}
			}
		}

		return new Integer[]{contadorObstaculos, contadorEspacios};
	}

	/**
	 * Getter de nombre
	 * @return devuelve el nombre
	 */
	public String getNombre(){
		return this.nombre;
	}

	/**
	 * Método de devolver escenario
	 * @return devuelve el ArrayList de escenario
	 */
	public List<String> getEscenario(){
		return this.escenarioCargado;
	}

}