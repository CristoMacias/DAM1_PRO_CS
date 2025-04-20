 package controladores;

 import modelos.Jugador;
 import modelos.Escenario;
 import java.io.IOException; 
 import javafx.scene.Scene;
 import javafx.scene.Parent;
 import javafx.fxml.FXMLLoader;
 import javafx.fxml.FXML;
 import javafx.stage.Stage;

/**
 * Clase Controlador
 * @author Cristo Macias, Sandra Moñino
 * 
 */
public class Controlador {

    private static Escenario escenario;
    private static final String PATH_VISTAS = "/vistas/";
    protected Stage ventana;
    protected ControladorPrincipal controladorPp;
    /**
     * Constructor para el controlador
     * @param stage Recibe el stage de ventana
     */ 
    public Controlador(Stage stage){
        this.ventana=stage;
    }
    /**
     * Método para cargar las vistas
     * @param controlador Recibe el controlador
     * @param nombre Recibe el nombre de la vista
     */ 
    protected Scene cargarVista(Controlador controlador,String nombre){
        Scene vista=null;
        try{
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource(PATH_VISTAS+nombre+".fxml"));
            fxmlLoader.setController(controlador);
            Parent raiz = fxmlLoader.load();
            vista=new Scene(raiz);
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("ERROR FATAL. No se encuentra la vista "+nombre+".");
            System.exit(1);
        }
        return vista;
    }
    /**
     * Método para cambiar las vistas 
     * @param controlador Recibe el controlador
     * @param vista Recibe la vista
     */ 
    protected void cambiarVista(Scene vista){
        ventana.setScene(vista);
        ventana.show();
    }
    /**
     * Getter de ventana
     * @return Devuelve el valor de ventana
     */  
    public Stage getVentana(){
        return this.ventana;
    }
    /**
     * Método para cerrar el juego
     */ 
    public void salirJuego(){
        ventana.close();
    }
}

