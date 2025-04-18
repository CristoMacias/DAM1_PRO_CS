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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Rectangle2D;
import javafx.fxml.FXML;

import modelos.Escenario;
/**
 * Clase ControladorEscenario
 * Hereda de Controlador. Se encargará de reconstruir el escenario a través del txt que lo representa, recibiéndolo del modelo creado en la versión anterior.
 * @author Sandra Moñino,Cristo Macias
 */ 
public class ControladorEscenario extends Controlador{
	private Stage stage;
	private ControladorPrincipal controladorPp;
	private Scene vista1;
	private Scene vista2;

	private Escenario escenario;

	private Integer filas;
	private Integer cols;
	private StackPane[][] stackPanes;

	@FXML
	private GridPane grid;

	private static final Integer LADO=32;

	public ControladorEscenario(Stage stage, ControladorPrincipal controladorPp, Escenario escenario){
		super(stage);
 
		this.controladorPp=controladorPp;
		this.escenario=escenario;

		escenario.cargarEscenario();
        escenario.recrearEscenario();
		char[][] matriz = escenario.getEscenario();

		filas = matriz.length;
		cols = matriz[0].length;
		stackPanes = new StackPane[filas][cols];

		

		//Cargamos las vistas
		vista1 = cargarVista(this,"vista1Escenario");
		vista2 = cargarVista(this,"vista2Escenario");

		HBox raizVista1 = (HBox) vista1.getRoot();
		VBox raizVista2 = (VBox) vista2.getRoot();
		raizVista1.getChildren().add(raizVista2);


		//Iniciamos la vista principal
		stage.setScene(vista1);
		stage.setTitle("Laberinto");
		stage.show();

		crearGrid(filas,cols);

		Rectangle2D vpSuelo = new Rectangle2D(0*LADO,0*LADO,LADO,LADO);
		Rectangle2D vpBorde = new Rectangle2D(1*LADO,0*LADO,LADO,LADO);
		Rectangle2D vpObstaculo = new Rectangle2D(6*LADO,0*LADO,LADO,LADO);
		Rectangle2D vpPuerta = new Rectangle2D(5*LADO,1*LADO,LADO,LADO);
		//Rectangle2D vpSuelo = new Rectangle2D();
		//Rectangle2D vpSuelo = new Rectangle2D();
		//Rectangle2D vpSuelo = new Rectangle2D();


		//Ponemos suelo
		for(int i = 0; i < filas; i++){
			for(int j = 0; j < cols; j++){
				asignarCelda(vpSuelo, i, j);
			}
		}

		//Ponemos bordes
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

	private void asignarCelda(Rectangle2D viewport, Integer fila, Integer col){
		ImageView imageView = (ImageView) stackPanes[fila][col].getChildren().get(0);
		imageView.setViewport(viewport);
		

	}

	private void crearGrid(Integer filas, Integer cols){

		for(int i = 0;i < filas; i++){
			grid.getRowConstraints().add(new RowConstraints());
		}
		for(int i = 0;i < cols; i++){
			grid.getColumnConstraints().add(new ColumnConstraints());
		}

		Image imagen = new Image(this.getClass().getResourceAsStream("/vistas/elementos.png"));

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

	
}