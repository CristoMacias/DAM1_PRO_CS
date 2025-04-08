
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

public class Escenario {
	private String nombre;
	private List<String> escenarioCargado;
	char[][] escenario;

	/**
	 * Constructor de escenario
	 */
	public Escenario(String nombre) {
		this.nombre = nombre;
		this.escenarioCargado = cargarEscenario();
	}

	/**
	 * Método para cargar el atributo matrizEscenario , inicializa una matriz en
	 * tamaño y contenido desde un archivo .txt
	 */
	@SuppressWarnings("unchecked")
	public List<String> cargarEscenario() {

		Path pathEscenarios = Paths.get("escenarios"); // Creamos la ruta del directorio escenarios
		Path pathArchivoEscenario = pathEscenarios.resolve(this.nombre + ".txt"); // Creamos la ruta de este escenario
		List<String> escenarioCargado = new ArrayList<>(); // Creamos una lista donde guardaremos cada linea del archivo
		try {
			escenarioCargado = (List<String>) Files.readAllLines(pathArchivoEscenario); // Leemos cada linea del archivo
																						// y lo guardamos en la lista
		} catch (IOException e) {
			e.printStackTrace();
		}
		return escenarioCargado; // Devuelve la lista con el escenario cargado.
	}

	/**
	 * Método para recrear el escenario en una matriz una vez guardado este en el
	 * ArrayList.
	 */
	public void recrearEscenario() {

		if (escenarioCargado.isEmpty()) { // Comprobamos que es escenario este relleno, si no vuelve.
			System.out.println("Escenario vacío, no se puede recrear.");
			return;
		}

		try {
			String[] tamanio = escenarioCargado.get(0).split("x"); // Guardamos las dimensiones de la matriz que
																	// aparecen al inicio del txt del escenario en un
																	// vector.
			Integer columnas = Integer.parseInt(tamanio[0]); // Sacamos las columnas que se guardan en la primera
																// posicion y las pasamos a Integer.
			Integer filas = Integer.parseInt(tamanio[1]); // Lo mismo que el anterior para las filas

			this.escenario = new char[filas][columnas]; // Le damos el tamaño a la matriz de escenario.

			for (int i = 0; i < filas; i++) {
				String linea = escenarioCargado.get(i + 1); // Se elige la posicion 1, ya que la 0 contiene las
															// dimensiones
				// System.out.println(linea);
				String[] trozos = linea.split(",");
				// System.out.println(trozos[0]);
				// System.out.println(trozos[1]);

				int col = 0;

				for (String trozo : trozos) {

					int noNumerico = 0;

					while (noNumerico < trozo.length() && Character.isDigit(trozo.charAt(noNumerico))) {
						noNumerico++;
					}

					int veces = Integer.parseInt(trozo.substring(0, noNumerico)); // Coge las veces que se repite.
					char accion = trozo.charAt(noNumerico); // Coge el elemento que se va a imprimir.

					// System.out.println(veces);
					// System.out.println(accion);

					char elemento = ' ';
					if (accion == 'E') {
						accion = '_';
					} else if (accion == 'O') {
						elemento = 'X';
					}

					for (int j = 0; j < veces && col < columnas; j++) {
						this.escenario[i][col++] = elemento;
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Contabiliza los espacios y los obstáculos del escenario
	 * 
	 * @return revuelve un Array donde la posición 1 son los obstáculos y la
	 *         posición 2 son los espacios
	 */
	public Integer[] contabilizarElementos() {
		Integer contadorObstaculos = 0;
		Integer contadorEspacios = 0;

		for (int i = 0; i < escenario.length; i++) {
			for (int j = 0; j < escenario[i].length; j++) {
				if (escenario[i][j] == 'X') {
					contadorObstaculos++;
				} else if (escenario[i][j] == ' ') {
					contadorEspacios++;
				}
			}
		}

		return new Integer[] { contadorObstaculos, contadorEspacios };
	}

	/**
	 * Getter de nombre
	 * 
	 * @return devuelve el nombre
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * Método de devolver escenario
	 * 
	 * @return devuelve el ArrayList de escenario
	 */
	public char[][] getEscenario() {
		return this.escenario;
	}

}