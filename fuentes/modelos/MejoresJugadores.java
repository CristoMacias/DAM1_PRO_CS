package modelos;

/**
 * Clase de Mejores Jugadores. Es el que se encargará de gestionar los 10 mejores jugadores.
 * @author Sandra Moñino, Cristo Macias
 */ 
public class MejoresJugadores implements Comparable<MejoresJugadores>{
	
	private Integer id;
	private String nombre;
	private Integer puntuacionTotal;
	private Integer totalSegundos;
	private Integer oro;
	private Integer choques;


	public MejoresJugadores(Integer id, String nombre, Integer puntuacionTotal, Integer totalSegundos, Integer oro, Integer choques){
		this.id = id;
		this.nombre = nombre;
		this.puntuacionTotal = puntuacionTotal;
		this.totalSegundos = totalSegundos;
		this.oro = oro;
		this.choques = choques;
	}
	/**
	 * Getter de Id del personaje.
	 * @return id
	 */
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	/**
	 * Getter de nombre del personaje.
	 * @return nombre
	 */
	public String getNombre(){
		return nombre;
	}
	/**
	 * Getter de puntuacion total del personaje.
	 * @return puntuacion total
	 */
	public Integer getPuntuacionTotal(){
		return puntuacionTotal;
	}

	/**
	 * Getter de totalSegundos del personaje.
	 * @return totalSegundos
	 */
	public Integer getTotalSegundos(){
		return totalSegundos;
	}
	/**
	 * Getter del oro del personaje.
	 * @return oro
	 */
	public Integer getOro(){
		return oro;
	}
	/**
	 * Getter de choques del personaje.
	 * @return choques
	 */
	public Integer getChoques(){
		return choques;
	}
	/**
	 * Método para comparar el total de puntuaciones de los personajes.
	 */
	@Override
	public int compareTo(MejoresJugadores otro){
		return Integer.compare(otro.puntuacionTotal, this.puntuacionTotal); //Ordena de manera descendente.
	}
}