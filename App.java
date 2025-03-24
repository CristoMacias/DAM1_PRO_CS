/**
 * Videojuego
 * 
 * @author Sandra Moñino, Cristo Macias
 * @version 1.0
 * @since 2025-03-22
 * Licencia: GPL v3
 */

 import java.nio.file.Path;
 import java.nio.file.Paths;
 import java.nio.file.Files; 
 import java.io.IOException;
 import java.util.Scanner;
public class App{
	
	public static void main(String[] args){
		Path path=Paths.get("");//Ruta donde se encuentra el App y obtenemos la ruta absoluta
		Path pathConfiguracion = Paths.get("config.ini"); //Ruta del archivo de config
		
		try{
			if(Files.exists(pathConfiguracion)){ //Comprobar si existe el archivo de config
				//System.out.println("Ya existe el archivo.");
			}else{
				System.out.println("El archivo de configuración no existe y se va a crear.");
				Files.createFile(pathConfiguracion); //Creamos el archivo config
			}

		}catch(IOException e){ //Capturamos las exceptiones 
			e.printStackTrace();
		}


		try{
			//Instanciación del Scanner
			Scanner teclado = new Scanner(System.in);
			System.out.println("Dime el nombre del directorio a crear."); //Preguntar nombre del directorio
			String nombreDirectorio = teclado.nextLine(); //Recibir nombre del directorio
			Path rutaCompleta = path.resolve(nombreDirectorio); //Añadimos al path que es la direccion del directorio del proyecto, el nombre del directorio
			
			
			if(Files.exists(rutaCompleta)){
				System.out.println("El directorio ya existe.");
			}
			else{
				System.out.println("El directorio no existe y se va a crear en la ruta " + rutaCompleta);
				Files.createDirectories(rutaCompleta);
			}
		}
		catch(IOException e){
			System.out.println("Error al crear el directorio");
		}
		
	
	}
}