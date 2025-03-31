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
public class App{
	
	public static void main(String[] args){
		Path path=Paths.get("");//Ruta donde se encuentra el App y obtenemos la ruta absoluta
		Path pathConfiguracion = Paths.get("config.conf"); //Ruta del archivo de config
		try{
			if(Files.exists(pathConfiguracion)){ //Comprobar si existe el archivo de config
				System.out.println("Ya existe el archivo.");
			}else{
				System.out.println("El archivo de configuración no existe y se va a crear.");
				Files.createFile(pathConfiguracion); //Creamos el archivo config
				Path pathEscenas = path.resolve("escenarios");
				Files.createDirectories(pathEscenas);
				Path pathPersonajes = path.resolve("jugadores");
				Files.createDirectories(pathPersonajes);
				Path pathPartidas = path.resolve("partidas");
				Files.createDirectories(pathPartidas);
				System.out.println("Se han creado el archivo de configuración y los directorios.");
			}

		}catch(IOException e){ //Capturamos las exceptiones 
			e.printStackTrace();
		}

		/* Instanciar jugadores y mostrar atributos para ver que se cargan y guardan jugadores si no existe
		Jugador prueba = new Jugador("Sandra");	
		Jugador prueba2 = new Jugador("Cristo");
		Jugador prueba3 = new Jugador("Miguel");
		System.out.println(prueba.getNombre()+" / "+prueba.getEmail());
		System.out.println(prueba2.getNombre()+" / "+prueba2.getEmail());
		System.out.println(prueba3.getNombre()+" / "+prueba3.getEmail());
		*/
	
	}
}