package controladores;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Rectangle2D;
import javafx.fxml.FXML;
import modelos.Escenario;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
/**
 * Clase ControladorEscenario
 * Hereda de Controlador. Se encargará de reconstruir el escenario a través del txt que lo representa, recibiéndolo del modelo creado en la versión anterior.
 * @author Sandra Moñino,Cristo Macias
 */ 
public class ControladorEscenario extends Controlador{
	private Scene vista1;
	private Scene vista2;
	private Escenario escenario;
	private char[][] matriz;
	private Integer filas;
	private Integer cols;
	private StackPane[][] stackPanes;
	@FXML private GridPane grid;
	@FXML private Label labelWASD;
	@FXML private Label labelOuch;
	private String movimiento;
	private static final Integer LADO=32;
	private Image imagenJugador;
	private ImageView ivJugador;
	private Integer filaJugador=1;
	private Integer columnaJugador=1;
	@FXML private Label labelTitulo;
	private Integer filaMoneda;
	private Integer columnaMoneda;
	private ImageView ivMoneda;
	private Integer monedasColocadas=3;
	private Integer filaLlave;
	private Integer columnaLlave;
	private ImageView ivLlave;
	private	@FXML Label labelMonedas;
	private @FXML Label labelLlave;
	private	@FXML Label labelChocado;
	private	@FXML Label labelPuntos;
	private @FXML Label labelTiempo;
	private Timeline lineaTiempo; //Ejecuta algo cada X tiempo
	private int segundosTranscurridos = 0; //Contador de segundos transcurridos
	
	

	/**
	 * Controlador de Escenario
	 * @param stage Lo recibe de la herencia del Controlador
	 * @param controladorPp Para instanciar el controlador principal
	 * @param escenario Recibe el escenario creado.
	 */
	public ControladorEscenario(Stage stage, ControladorPrincipal controladorPp, Escenario escenario){
		super(stage);
		this.controladorPp=controladorPp;
		this.escenario=escenario;
		escenario.cargarEscenario();
        escenario.recrearEscenario();
		matriz = escenario.getEscenario();
		filas = matriz.length;
		cols = matriz[0].length;
		stackPanes = new StackPane[filas][cols];
		//Cargamos las vistas
		vista1 = cargarVista(this,"vista1Escenario");
		vista2 = cargarVista(this,"vista2Escenario");
		HBox raizVista1 = (HBox) vista1.getRoot();
		VBox raizVista2 = (VBox) vista2.getRoot();
		raizVista1.getChildren().add(raizVista2);
		ivMoneda=new ImageView(new Image(this.getClass().getResourceAsStream("/moneda.gif")));
		ivLlave=new ImageView(new Image(this.getClass().getResourceAsStream("/llave2.gif")));
		crearGrid(filas,cols);
		ponerSuelo();
		ponerBordes();
		comenzarJuego();
	}
	/**
	 * Método para poner el suelo del escenario
	 */
	private void ponerSuelo(){
		Rectangle2D vpSuelo = new Rectangle2D(0*LADO,0*LADO,LADO,LADO);
		for(int i = 0; i < filas; i++){
			for(int j = 0; j < cols; j++){
				asignarCelda(vpSuelo, i, j);
			}
		}
	}
	/**
	 * Método para poner el borde del escenario.
	 */
	private void ponerBordes(){
		Rectangle2D vpSuelo = new Rectangle2D(0*LADO,0*LADO,LADO,LADO);
		Rectangle2D vpBorde = new Rectangle2D(1*LADO,0*LADO,LADO,LADO);
		Rectangle2D vpObstaculo = new Rectangle2D(6*LADO,0*LADO,LADO,LADO);
		Rectangle2D vpPuerta = new Rectangle2D(5*LADO,1*LADO,LADO,LADO);
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < cols; j++) {
				if (i == 0 || i == filas - 1 || j == 0 || j == cols - 1) {
					if((i==1 && j==0) || (i==filas-2&&j==cols-1)){
						asignarCelda(vpPuerta, i, j);
					}
					else{
						asignarCelda(vpBorde, i, j);
					}
				}
				else{
					char celda = matriz[i][j];
					if(celda == 'X'){
						asignarCelda(vpObstaculo, i, j);
					}
					else{
						asignarCelda(vpSuelo, i, j);
					}
				}
			}
		}
	}
	/**
	 * Asignas a las celdas elegidas el imageView en la fila y columna indicadas.
	 */
	private void asignarCelda(Rectangle2D viewport, Integer fila, Integer col){
		ImageView imageView = (ImageView) stackPanes[fila][col].getChildren().get(0);
		imageView.setViewport(viewport);
	}

	/**
	 * Método para crear el GridPane con los filas y columnas de la matriz hecha en la versión anterior del videojuego
	 */
	private void crearGrid(Integer filas, Integer cols){
		for(int i = 0;i < filas; i++){
			grid.getRowConstraints().add(new RowConstraints());
		}
		for(int i = 0;i < cols; i++){
			grid.getColumnConstraints().add(new ColumnConstraints());
		}
		Image imagen = new Image(this.getClass().getResourceAsStream("/elementos.png"));
		for(int i = 0; i < filas; i++){
				for(int j = 0; j < cols; j++){
				StackPane stackPane = new StackPane();
				stackPanes[i][j] = stackPane;
				ImageView imageView = new ImageView(imagen);
				imageView.setFitWidth(32);
				imageView.setFitHeight(32);
				imageView.setPreserveRatio(true);
				stackPane.getChildren().add(imageView);
				grid.add(stackPane, j, i);
			}
		}
	}
	/**
	 * Método para comenzar el juego con el jugador. Carga la imagen dell jugador, crea su iamgeview y lo añade
	 */ 
	private void comenzarJuego(){
		//Mostrar jugador en el escenario
		imagenJugador=new Image(this.getClass().getResourceAsStream("/jugador.gif"));//Instanciamos la iamgen para el jugador
		ivJugador=new ImageView(imagenJugador);//Creamos el ImageView del jugador
		ivJugador.setFitWidth(32);//Ajustamos ancho
		ivJugador.setFitHeight(32);//Ajustamos largo
		ivJugador.setPreserveRatio(true);//Nos aseguremos que no se deforme
		colocarJugador();
		colocarMonedaMatriz();
		colocarMonedaEscenario();
		ventana.setTitle("Laberinto"); // Cambiamos el titulo
		cambiarVista(vista1); //Cambiamos a la ista 1
		labelTitulo.setText("NIVEL "+escenario.getNombre().toUpperCase());
		captuarMovimiento();
		iniciarContador();
	}

	/**
	 * Método para captuar el evento para capturar por teclado los movimientos de WASD
	 */ 
	private void captuarMovimiento(){
		vista1.setOnKeyPressed(event->{
			movimiento=event.getCode().toString();
			movimiento=movimiento.trim().toUpperCase();
			if(movimiento.equals("W")||movimiento.equals("A")||movimiento.equals("S")||movimiento.equals("D"))
				labelWASD.setText("  WASD>"+movimiento);
			direccionJugador();//Llamamos al método para mover al jugador
			ganarJuego();
		});
	}
	/**
	 * Método para mover al jugador en función del movimiento introducido por el jugador 
	 */ 
	private void direccionJugador(){
		Integer fila=filaJugador;
		Integer columna=columnaJugador;
		switch(movimiento){
            case "W":
                moverJugador(fila-1,columna);//Si el movimiento elegido es la W entonces se resta una posicion de la fila(simila el movimiento hacia arriba)
                    break;
            case "A":
                moverJugador(fila,columna-1);//Si el movimiento elegido es la A entonces se resta una posicion a la columna(simula el movimiento hacia la izquierda)
                    break;
            case "S":
                moverJugador(fila+1,columna);//Si el movimiento elegido es la S entonces se suma una posicion a la columna(simula el movmimiento hacia abajo)
                    break;
            case "D":
                moverJugador(fila,columna+1);// Si el movimiento elegido es la D entonces se suma una posición a la D(simula el movimiento hacia la derecha)
                break;
        }
	}
	/**
	 * Método para mover al personje por el escenario
	 */ 
	private void moverJugador(Integer fila, Integer columna){
		if(fila==filas-2 && columna==cols-1){//Comprobamos primero si ha llegado al final
			stackPanes[fila][columna].getChildren().add(ivJugador);//Colocamos al jugador para mostrarlo
			filaJugador=fila; //Asignamos la fila actual en la que se encuentra el jugador
			columnaJugador=columna;//Asignamos la columna actual en la que se encuentra el jugador
			reiniciarLabel(); //Reiniciamos el label de ouch para que desaperzca el mensaje 
			return;//Terminamos la funcion
		}
		if(fila==1 && columna==0){//Mensaje por si intenta volver a al puerta de entrada
			labelOuch.setText(" No seas cobarde...");//Mensaje de cobarde si intenta ir por la puerta de inicio
			return;//Terminamos la funcion
		}
		if(fila<1 || fila>filas-2 ||columna>cols-2 ||columna<1){//Comprobamos que no intente pasar las paredes
			controladorPp.getControladorMedia().reproducirChocarse();
			controladorPp.getJugador().chocarse();
			labelOuch.setText(" ¡OUCH! Todavía no puedes traspasar paredes...");//Mensaje si intenta pasar las paredes
			actualizarEstadistica();
			return;//Terminamos la funcion
		}
		if(matriz[fila][columna]=='X'){ //Para controlar los obstáculos 
			controladorPp.getJugador().chocarse();
			controladorPp.getControladorMedia().reproducirChocarse();
			labelOuch.setText(" ¡OUCH! Eso ha dolido...");//Mensaje si se choca contra un obstaculo
			actualizarEstadistica();
			return;//Terminamos la funcion
		}
		comprobarMoneda(fila,columna);
		actualizarEstadistica();
		comprobarLlave(fila,columna);
		actualizarEstadistica();
		stackPanes[fila][columna].getChildren().add(ivJugador); //Colocamos al jugador en la fila y columna
		filaJugador=fila; //Asignamos la fila actual en la que se encuentra el jugador
		columnaJugador=columna; //Asignamo la columna actual en la que se encuentre el jugador
		reiniciarLabel(); //Borramos los mensajes
	}
	/**
	 *Método para cambiar la vista al finalizar el juego
	 */ 
	private void ganarJuego(){
		if(filaJugador==filas-2 && columnaJugador==cols-1){//Si la fila del jugador y colummna del jugador es la del final
			if(lineaTiempo != null){
				lineaTiempo.stop();
			}
			controladorPp.getJugador().setTotalSegundos(segundosTranscurridos);//Añadimos el totalSegundos al jugador
			//controladorPp.getJugador().comprobarPuntuacion();//Calculamos la puntuación total ?,comprobar en ControladorFin
			controladorPp.cargarFin();
		}
	}
	/**
	 * Método para reinicar el label
	 */ 
	private void reiniciarLabel(){
		labelOuch.setText("");
	}
	/**
	 * Método para colocar al jugador en la primera posicion disponible
	 */ 
	private void colocarJugador(){
		for(int i=1;i<matriz.length;i++){
			for(int j=1;j<matriz[i].length;j++){
				if(matriz[i][j]==' '){
					stackPanes[i][j].getChildren().add(ivJugador);
					filaJugador=i;
					columnaJugador=j;
					return;
				}
			}
		}
	}
	/**
	 * Método para colcocar la moneda en la matriz de manera aleatoria donde hay espacio.
	 */ 
	private void colocarMonedaMatriz(){
		for(int i=0;i<matriz.length;i++){
			for(int j=0;j<matriz[i].length;j++){
				int fila=(int)(Math.random()*8)+1;
				int columna=(int)(Math.random()*38)+1;
				if(matriz[fila][columna]==' '){
					matriz[fila][columna]='C';
					filaMoneda=fila;
					columnaMoneda=columna;
					monedasColocadas--;
					return;
				}	
			}
		}
	}
	/**
	 * Método para colocar la ImageView dela moneda en el escenario
	 */ 
	private void colocarMonedaEscenario(){
		ivMoneda.setFitWidth(20);//Ajustamos ancho
		ivMoneda.setFitHeight(20);//Ajustamos largo
		ivMoneda.setPreserveRatio(true);
		stackPanes[filaMoneda][columnaMoneda].getChildren().add(ivMoneda);
	}
	/**
	 * Método para comprobar si el jugador se encuentra en la posición de la moneda para cogerla, adenás colocar la llave cuando termina de recoger todas
	 */ 
	private void comprobarMoneda(Integer fila,Integer columna){
		if(matriz[fila][columna]=='C'){
			controladorPp.getControladorMedia().reproducirMoneda();
			stackPanes[fila][columna].getChildren().remove(ivMoneda);
			controladorPp.getJugador().recogerMoneda(1);
			matriz[fila][columna]=' ';
			if(monedasColocadas!=0){
				colocarMonedaMatriz();
				colocarMonedaEscenario();
			}if(controladorPp.getJugador().getTotalMonedas()==3){
				colocarLlaveMatriz();
				colocarLlaveEscenario();
			}
		}
	}

	/**
	 * Método para colocar la llave en la matriz en una posición fija según el escenario
	 */ 
	private void colocarLlaveMatriz(){
		switch(escenario.getNombre()){
		case "facil":
			matriz[1][25]='K';
			filaLlave=1;
			columnaLlave=25;
			break;
		case "medio":
			filaLlave=1;
			columnaLlave=16;
			matriz[1][16]='K';
			break;
		case "dificil":
			filaLlave=7;
			columnaLlave=2;
			matriz[7][2]='K';
			break;
		}
	}
	/**
	 * Método para colocar la ImageView de la llave en el escenario
	 */ 
	private void colocarLlaveEscenario(){
		ivLlave.setFitWidth(28);
		ivLlave.setFitHeight(28);
		ivLlave.setPreserveRatio(true);
		stackPanes[filaLlave][columnaLlave].getChildren().add(ivLlave);
	}
	/**
	 *Método para comprobar si el jugador se coloca en la posición de la llave para recogerla
	 */
	private void comprobarLlave(Integer fila,Integer columna){
		if(matriz[fila][columna]=='K'){
			controladorPp.getControladorMedia().reproducirLlaves();
			controladorPp.getJugador().setTieneLlave(true);
			stackPanes[fila][columna].getChildren().remove(ivLlave);
		}
	}
	/**
	 * Método encargado de iniciar el contador
	 */
	private void iniciarContador(){
		lineaTiempo = new Timeline(new KeyFrame(Duration.seconds(1), e -> { //ejecuta el bloque de codigo cada 1 segundo
			segundosTranscurridos++;	//Va sumando 1 cada segundo
			labelTiempo.setText(formatoTiempo(segundosTranscurridos)); //Muestra los segundos actuales
		}));
		lineaTiempo.setCycleCount(Timeline.INDEFINITE); //Esto hace que nunca se pare el contador, hasta que nosotros lo ordenemos
		lineaTiempo.play(); //Inicia el temporizador
	}
	/**
	 * Método encargado de formatear el tiempo en 00:00, para que no sea 179 segundos por ejemplo.
	 */
	private String formatoTiempo(int totalSegundos){
		int minutos = totalSegundos / 60;
		int segundos = totalSegundos % 60;
		return String.format("%02d:%02d", minutos, segundos);
	}

	/**
	 * Método para actualizar las estadísticas en la vista2
	 */ 
	private void actualizarEstadistica(){
		labelMonedas.setText(String.valueOf(controladorPp.getJugador().getTotalMonedas()));
		labelLlave.setText(controladorPp.getJugador().comprobarLlave());
		labelChocado.setText(String.valueOf(controladorPp.getJugador().getTotalChocado()));
		labelPuntos.setText(String.valueOf(controladorPp.getJugador().getTotalPuntuacion()));
	}

	/**
	 * Getter del total de segundos
	 * @return segundosTranscurridos en la partida.
	 */
	public int getTotalSegundos(){
		return segundosTranscurridos;
	}

}