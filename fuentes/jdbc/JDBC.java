package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import modelos.MejoresJugadores;
import controladores.ControladorEscenario;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
/**
 * Clase del JDBC
 * Se encarga de la gestiones a realizar con la base de datos.
 * 
 * @author Sandra Moñino, Cristo Macias
 */ 
public class JDBC{

	private final Integer NUMEROJUGADORES = 10;
	private String url = "jdbc:sqlite:puntuaciones.db";
	private String usuario = "CristoSandra";
	private String clave = "videojuego";
	private String [] puntuaciones = new String[NUMEROJUGADORES];  

	/**
	 * Método para crear la conexión y la tabla de la base de datos del videojuego
	 */
	public void crearConexion(){
		try(Connection conexion = DriverManager.getConnection(url, usuario, clave)){
			System.out.println("Conexion realizada");

			String sql = "CREATE TABLE IF NOT EXISTS puntuaciones (" +
					"Id INTEGER," +
					"Nombre TEXT,"+
					"Puntuacion INTEGER," +
					"Tiempo INTEGER," + 
					"Oro INTEGER," + 
					"Choques INTEGER" + 
					")";
			Statement sentencia = conexion.createStatement();
			int num = sentencia.executeUpdate(sql);
			if(num == 1){
				System.out.println("Tabla creada");
			}
			else{
				System.out.println("La tabla ya existe");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	/**
	 * Método para introducir los datos predeterminados en la tabla, ya que tiene que haber únicamente 10 personajes.
	 * Comprueba si hay datos introducidos para que no se dupliquen.
	 */
	public void introducirDatosPredeterminados(){
		
	String comprobarSQL = "SELECT COUNT(*) FROM puntuaciones";
    String sql = "INSERT INTO puntuaciones (Id, Nombre, Puntuacion, Tiempo, Oro, Choques) VALUES (?, ?, ?, ?, ?, ?)";

	    try (Connection conexion = DriverManager.getConnection(url, usuario, clave);
	         PreparedStatement comprobacion = conexion.prepareStatement(comprobarSQL);
	         ResultSet resultado = comprobacion.executeQuery()) {

	        resultado.next(); // Necesario para acceder al resultado del COUNT
	        int totalFilas = resultado.getInt(1);
	        if (totalFilas == 1) {
	        		System.out.println("Los datos ya están introducidos");
		         
		    }else{
	        	try (PreparedStatement datos = conexion.prepareStatement(sql)) {
		            for (int i = 0; i < NUMEROJUGADORES; i++) {
		                datos.setInt(1, i + 1);
		                datos.setString(2, "Jugador" + (i + 1));
		                datos.setInt(3, 10);
		                datos.setInt(4, 10);
		                datos.setInt(5, 10);
		                datos.setInt(6, 10);
		                datos.executeUpdate();
	        		}
	        	}
	        } 
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void modificarRanking(MejoresJugadores nuevoJugador){
		List<MejoresJugadores> topTen = new ArrayList<>();

		try(Connection conexion = DriverManager.getConnection(url, usuario, clave)){
			String sql = "SELECT Id, Nombre, Puntuacion, Tiempo, Oro, Choques FROM puntuaciones";
			try(Statement sentencia = conexion.createStatement();
				ResultSet resultado = sentencia.executeQuery(sql)){
				while(resultado.next()){
					topTen.add(new MejoresJugadores(
						resultado.getInt("Id"),
						resultado.getString("Nombre"),
						resultado.getInt("Puntuacion"),
						resultado.getInt("Tiempo"),
						resultado.getInt("Oro"),
						resultado.getInt("Choques")
					));
				}
			}
			topTen.add(nuevoJugador);
			Collections.sort(topTen);

			topTen = topTen.subList(0, 10); //Recorta la lista de la posicion 0 a la 10.

			for(int i = 0; i < topTen.size(); i++){
				topTen.get(i).setId(i + 1); //Reasignamos los id segun su posicion.
			}

			try(Statement sentencia = conexion.createStatement()){
				sentencia.executeUpdate("DELETE FROM puntuaciones");
			}

			sql = "INSERT INTO puntuaciones(Id, Nombre, Puntuacion, Tiempo, Oro, Choques) VALUES (?, ?, ?, ?, ?, ?)";
			try(PreparedStatement sentenciaPreparada = conexion.prepareStatement(sql)){
				for(MejoresJugadores mj : topTen){
					sentenciaPreparada.setInt(1, mj.getId());
					sentenciaPreparada.setString(2, mj.getNombre());
					sentenciaPreparada.setInt(3, mj.getPuntuacionTotal());
					sentenciaPreparada.setInt(4, mj.getTotalSegundos());
					sentenciaPreparada.setInt(5, mj.getOro());
					sentenciaPreparada.setInt(6, mj.getChoques());
					sentenciaPreparada.addBatch(); //Añade al lote de la sentencia preparada.
				}
				sentenciaPreparada.executeBatch(); //Ejecuta todas las instrucciones del lote a la vez.
			}
			
		}
		catch(SQLException e){
				e.printStackTrace();
			}
	}

}