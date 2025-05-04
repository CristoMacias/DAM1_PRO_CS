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
	 */ 
	public Integer getTotalChocado(){
		return this.totalChocado;
	}
	/**
	 * Setter de total chocado
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
	public void restarPuntuacion(Integer puntos){
		this.totalPuntuacion-=puntos;
		if(this.totalPuntuacion<0)
			setTotalPuntuacion(0);
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
	 * Método para comprobar el totalChocados y dar puntos
	 */ 
	public void comprobarPuntuacion(){
		if(this.tieneLlave)
			sumarPuntuacion(100);
		if(this.totalChocado==0)
			sumarPuntuacion(100);
		else
			restarPuntuacion(this.totalChocado*5);
	}
}