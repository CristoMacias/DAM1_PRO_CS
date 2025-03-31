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
import java.util.Arrays;
public class Escenario{
	private String nombre;
	private String[][] matrizEscenario;
	/**
	 * Constructor de escenario
	 */ 
	public Escenario(String nombre){
		this.nombre=nombre;
		this.matrizEscenario=cargarEscenario();
	}
	/**
	 * Método para cargar el atributo matrizEscenario , inicializa una matriz en tamaño y contenido desde un archivo .txt
	 */ 
	public String[][] cargarEscenario(){
		String[][] matriz;

		Path pathEscenarios=Paths.get("escenarios"); //Creamos la ruta del directorio escenarios
		Path pathArchivoEscenario=pathEscenarios.resolve(this.nombre+".txt"); //Creamos la ruta de este escenario
		List<String> lineasEscenario=new ArrayList<>(); //Creamos una lista donde guardaremos cada linea del archivo
		try{
			lineasEscenario=(List<String>) Files.readAllLines(pathArchivoEscenario); //Leemos cada linea del archivo y lo guardamos en la lista
		}catch(IOException e){
			e.printStackTrace();
		}
		//Tomar las medidas para la matriz
		String lineaMedidas="";
		lineaMedidas=lineasEscenario.get(0); //Obtenemos la linea que contiene las dimensiones
		String[] dimensiones=lineaMedidas.split("x");
		Integer fila=Integer.valueOf(dimensiones[0]);//Obtenemos el numero de filas
		Integer columna=Integer.valueOf(dimensiones[1]); //Obtenemos el numero de columnas
		matriz=new String[fila][columna]; //Inicializamos la matriz
		//Rellenar matriz
		lineasEscenario.remove(0);//Eliminamos la linea de las dimensiones
		for(int i=0;i<matriz.length;i++){
			String linea=lineasEscenario.get(i);//Obtenemos cada linea
			if(linea.isEmpty()){ //Si está vacía 
				Arrays.fill(matriz[i]," ");//Rellenamos con espacios
				continue; //Continua el array
			}
			//Le sumamos uno para que salte la linea de las dimensiones
			System.out.println(linea);
			for(int j=0;j<matriz[i].length;j++){
				String caracter=String.valueOf(linea.charAt(j));//Recogemos cada caracter de la linea
				matriz[i][j]=caracter;//Añadimos a cada posicion el caracter que encuentre
			}
		}

		return matriz; //Devuelve la matriz rellena
	}

	public String getNombre(){
		return this.nombre;
	}

	public String[][] getMatrizEscenario(){
		return this.matrizEscenario;
	}

}