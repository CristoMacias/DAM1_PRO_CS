package controladores;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
/**
 * Clase de ControladorMedia
 * Se encarga de la lógica y control de la musica del videojuego
 * 
 * @author Sandra Moñino, Cristo Macias
 */ 
public class ControladorMedia{

	private MediaPlayer mpBienvenida;
	private MediaPlayer mpIntroduccion;
	private MediaPlayer mpLaberinto;
	private MediaPlayer mpFinal;
	/**
	 * Constructor de ControladorMedia.
	 */
	public ControladorMedia(){
		cargarSonidos();//Llamamos al método cargarSonidos para instrancias los Media Player
	}
	/**
	 * Método para cargar todos los MediaPlayer para los sonidos
	 */ 
	public void cargarSonidos(){
		mpBienvenida=cargarMediaPlayer("compilados/sonidos/bienvenida.mp3");//Cada media player hace una llamada al metodo cargarMediaPlayer para instanciarse, pasando el path
		mpIntroduccion=cargarMediaPlayer("compilados/sonidos/introduccion.mp3");
		mpLaberinto=cargarMediaPlayer("compilados/sonidos/laberinto.mp3");
		mpFinal=cargarMediaPlayer("compilados/sonidos/sonidoFinal.mp3");

	}
	/**
	 * Método para instanciar la Media
	 * @param path Recibe el path del sonido
	 * @return Devuelve la instancia de Media 
	 */ 
	public Media cargarMedia(String path){
		return new Media(new File(path).toURI().toString());
	}
	/**
	 * Método para instanciar MediaPlayer
	 * @param media Recibe la media
	 * @return Devuelve la instancia de MediaPlayer
	 */ 
	public MediaPlayer cargarMediaPlayer(String path){
		return new MediaPlayer(cargarMedia(path));
	}
	/**
	 * Método genérico para reproducir el sonido que reciba por parámetro. Además la reproduccion será en bucle.
	 * @param mediaPlayer Recibe por parámetro el MediaPlayer a reproducir
	 */ 
	public void reproducir(MediaPlayer mediaPlayer){
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
	}
	/**
	 * Método genérico para parar el sonido que reciba por parámetro.
	 * @param mediaPlayer Recibe por parámetro el MediaPlayer a parar
	 */
	public void parar(MediaPlayer mediaPlayer){
		mediaPlayer.stop();
	}
	/**
	 * Método para reproducir el sonido de bienvenida
	 */ 
	public void reproducirBienvenida(){
		reproducir(mpBienvenida);
	}
	/**
	 * Método para parar el sonido de bienvenida
	 */ 
	public void pararBienvenida(){
		parar(mpBienvenida);
	}
	/**
	 * Método para reproducir el sonido de introducción
	 */ 
	public void reproducirIntroduccion(){
		reproducir(mpIntroduccion);
	}
	/**
	 * Método para parar el sonido de introduccióm
	 */ 
	public void pararIntroduccion(){
		parar(mpIntroduccion);
	}
	/**
	 * Método para reproducir el sonido de laberinto
	 */
	public void reproducirLaberinto(){
		reproducir(mpLaberinto);
	}
	/**
	 * Método para parar el sonido de laberinto
	 */ 
	public void pararLaberinto(){
		parar(mpLaberinto);
	}
	/**
	 * Método para reproducir el sonido del final
	 */ 
	public void reproducirFinal(){
		reproducir(mpFinal);
	}
	/**
	 * Método para parar el sonido de final
	 */ 
	public void pararFinal(){
		parar(mpFinal);
	}

}