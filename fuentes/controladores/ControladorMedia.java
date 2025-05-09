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
	private MediaPlayer mpTopTen;
	private MediaPlayer mpMenu;
	private MediaPlayer mpLogin;
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
		mpTopTen=cargarMediaPlayer("compilados/sonidos/topTen.mp3");
		mpMenu=cargarMediaPlayer("compilados/sonidos/menu.mp3");
		mpLogin=cargarMediaPlayer("compilados/sonidos/login.mp3");
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
	 * @return Devuelve el media player para saber cuál es el que se reproduce actualmente
	 */ 
	public MediaPlayer reproducirBienvenida(){
		reproducir(mpBienvenida);
		return mpBienvenida;
	}
	/**
	 * Método para reproducir el sonido de introducción
	 * @return Devuelve el media player para saber cuál es el que se reproduce actualmente
	 */ 
	public MediaPlayer reproducirIntroduccion(){
		reproducir(mpIntroduccion);
		return mpIntroduccion;
	}
	/**
	 * Método para reproducir el sonido de laberinto
	 * @return Devuelve el media player para saber cuál es el que se reproduce actualmente
	 */
	public MediaPlayer reproducirLaberinto(){
		reproducir(mpLaberinto);
		return mpLaberinto;
	}
	/**
	 * Método para reproducir el sonido del final
	 * @return Devuelve el media player para saber cuál es el que se reproduce actualmente
	 */ 
	public MediaPlayer reproducirFinal(){
		reproducir(mpFinal);
		return mpFinal;
	}
	/**
	 * Método para reproducir el sonido de la vista Top Ten
	 * @return Devuelve el media player para saber cuál es el que se reproduce actualmente
	 */ 
	public MediaPlayer reproducirTopTen(){
		reproducir(mpTopTen);
		return mpTopTen;
	}
	/**
	 * Método para reproducir el sonido de la vista Menu
	 */ 
	public MediaPlayer reproducirMenu(){
		reproducir(mpMenu);
		return mpMenu;
	}
	/**
	 * Método para reproducir el sonido de la vista Login
	 */ 
	public MediaPlayer reproducirLogin(){
		reproducir(mpLogin);
		return mpLogin;
	}
	/**
	 * Método que instancia y reproduce el sonido de la moneda 
	 */ 
	public void reproducirMoneda(){
		MediaPlayer mpMoneda=cargarMediaPlayer("compilados/sonidos/moneda.mp3");
		mpMoneda.play();
	}
	/**
	 * Método que instancia y reproduce el sonido de las llaves
	 */ 
	public void reproducirLlaves(){
		MediaPlayer mpLlaves=cargarMediaPlayer("compilados/sonidos/llaves.mp3");
		mpLlaves.play();
	}

	public void reproducirChocarse(){
		MediaPlayer mpChocarse=cargarMediaPlayer("compilados/sonidos/chocarse.mp3");
		mpChocarse.play();
	}

}