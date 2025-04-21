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
	
	/**
	 * Constructor para inicializar los atributos del personaje e instanciarlo
	 * @param nombre Recibe el nombre del jugador
	 * @param email Recibe el email del jugador
	 */ 
	public Jugador(String nombre,String email){
		this.nombre=nombre;
		this.email=email;
		this.totalChocado=0;
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
	 * Método del jugador para chocarse
	 */ 
	public void chocarse(){
		this.totalChocado+=1;
	}
}