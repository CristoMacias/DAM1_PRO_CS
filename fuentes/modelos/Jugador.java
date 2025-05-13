package  modelos;

import java.io.Serializable;
/**
 * Clase Jugador
 * 
 * El objeto jugador tendrá los atributos nombre y email.
 * 
 * @author Sandra Moñino, Cristo Macias
 * Licence: GPL v3
 * 
 */ 
public class Jugador implements Serializable{ //Implementamos la interfaz Serializable para hacer que el jugador se serialice
	private String nombre;
	private String email;
	private Integer totalChocado;
	private Integer totalMonedas;
	private Boolean tieneLlave;
	private Integer totalPuntuacion;
	private int totalSegundos;
	private final int TIEMPOMAXIMO = 120;
	/**
	 * Constructor para inicializar los atributos del personaje e instanciarlo
	 * @param nombre Recibe el nombre del jugador
	 * @param email Recibe el email del jugador
	 */ 
	public Jugador(String nombre,String email){
		this.nombre=nombre;
		this.email=email;
		this.totalChocado=0;
		this.totalMonedas=0;
		this.tieneLlave=false;
		this.totalPuntuacion=0;
		this.totalSegundos=0;
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
	/**
	 * Getter de total chocado
	 * @return totalChocado, El total de veces que se ha chocado el jugador.
	 */ 
	public Integer getTotalChocado(){
		return this.totalChocado;
	}
	/**
	 * Setter de total chocado
	 * @param total Recibe el total de veces que se ha chocado el jugador
	 */
	public void setTotalChocado(Integer total){
		this.totalChocado=total;
	}

	/**
	 * Getter para totalMonedas
	 * @return Devuelve el valor de totalMonedas
	 */ 
	public Integer getTotalMonedas(){
		return this.totalMonedas;
	}
	/**
	 * Setter de totalMonedas
	 * @param monedas Recibe el total de monedas
	 */ 
	public void setTotalMonedas(Integer monedas){
		this.totalMonedas=monedas;
	}
	/**
	 * Getter de tieneLlave
	 * @return Devuelve true si tiene la llave y false si no
	 */ 
	public Boolean getTieneLlave(){
		return this.tieneLlave;
	}
	/**
	 * Setter de tieneLlave
	 * @param tieneLlave Recibe el valor de true o false para tieneLlave
	 */ 
	public void setTieneLlave(Boolean tieneLlave){
		this.tieneLlave=tieneLlave;
	}
	/**
	 * Getter de totalPuntuacion
	 * @return Devuelve el valor de totalPuntuacion
	 */ 
	public Integer getTotalPuntuacion(){
		return this.totalPuntuacion;
	}
	/**
	 * Setter de totalPuntuacion
	 * @param puntuacion Recibe el valor de la puntuacion a modfiicar
	 */ 
	public void setTotalPuntuacion(Integer puntuacion){
		this.totalPuntuacion=puntuacion;
	}
	/**
	 * Getter del total de segundos
	 * @return totalSegundos
	 */
	public int getTotalSegundos(){
		return this.totalSegundos;
	}
	/**
	 * Setter de total de segundos
	 * @param totalSegundos recibe el total de segundos en el que el nuevo jugador realizó el juego.
	 */
	public void setTotalSegundos(int totalSegundos){
		this.totalSegundos=totalSegundos;
	}
	/**
	 * Método del jugador para chocarse
	 */ 
	public void chocarse(){
		this.totalChocado+=1;
	}
	/**
	 * Método para añadir una moneda 
	 * @param moneda Recibe el valor de la moneda a sumar
	 */ 
	public void recogerMoneda(Integer moneda){
		this.totalMonedas+=moneda;
		sumarPuntuacion(25);
	}
	/**
	 * Método para sumar puntos
	 * @param puntos Recibe los puntos a sumar
	 */ 
	public void sumarPuntuacion(Integer puntos){
		this.totalPuntuacion+=puntos;
	}
	/**
	 * Método que comprueba si tiene la llave. Devuelve un string con si o no
	 * @return Devuelve un string
	 */ 
	public String comprobarLlave(){
		if(this.tieneLlave){
			return "SÍ";
		}else{
			return "NO";
		}		
	}
	/**
	 * Método para comprobar las puntuciones
	 */ 
	public void calcularPuntuacion(){
		this.totalPuntuacion=puntuarMonedas()+puntuarLlave()+puntuarChocado()+puntuarTiempo();
	}
	/**
	 * Calcula el total de puntos por moneda
	 * @return Devuelve los puntos
	 */ 
	public int puntuarMonedas(){
		return this.totalMonedas*25;
	}
	/**
	 * Método para calcular los puntos si tiene la llave o no
	 * @return Devuelve los puntos
	 */ 
	public int puntuarLlave(){
		if(this.tieneLlave)
			return 100;
		else
			return 0;
	}
	/**
	 * Método que comprueba los puntos de totalChocado. 
	 * @return Devuelve 100 para totalChocado 0, y - totalChocado*5
	 */ 
	public int puntuarChocado(){
		if(this.totalChocado==0)
			return 100;
		else
			return -(this.totalChocado*5);
	}
	/**
	 * Método para puntuar el tiempo, con un máximo de dos minutos, que si se supera, no gana puntuacion por tiempo.
	 * Si el jugador realiza el juego antes del tiempo máximo, el tiempo restante se le sumará como un punto por segundo.
	 * @return puntuacion.
	 */
	public int puntuarTiempo(){
		if(totalSegundos < TIEMPOMAXIMO){
			return TIEMPOMAXIMO - this.totalSegundos;
		}
		else{
			return 0;
		}
	}
	/**
	 * Método que comprueba la puntuacion total, si es menor que 0, se mantenga en 0.
	 */
	public void comprobarPuntos(){
		if(this.totalPuntuacion<0)
			this.totalPuntuacion= 0;
	}	
	/**
	 *Método para formatear el tiempo y que se muestre en la tabla de puntuaciones.
	 * @return tiempo formateado.
	 */
	public String formatoTiempo(){
		int minutos = this.totalSegundos / 60;
		int segundos = this.totalSegundos % 60;
		return String.format("%02d:%02d", minutos, segundos);
	}
}