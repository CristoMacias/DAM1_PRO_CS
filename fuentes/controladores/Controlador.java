 package controladores;

 import vistas.VistaEscenario;
 import vistas.VistaJugador;
 import modelos.Jugador;
 import modelos.Escenario;
 import java.util.Scanner;
 import java.io.IOException; 

 import java.io.ObjectInputStream;
 import java.io.InputStream; 
 import java.io.ObjectOutputStream;
 import java.io.OutputStream;

 import javafx.scene.Scene;
 import javafx.scene.Parent;
 import javafx.fxml.FXMLLoader;
 import javafx.fxml.FXML;
 import javafx.stage.Stage;

/**
 * Clase Controlador
 * 
 * @author Cristo Macias, Sandra Moñino
 * 
 */
public class Controlador {

    private static Scanner teclado = new Scanner(System.in);

    private static VistaEscenario vistaEscenario = new VistaEscenario();
    private static VistaJugador vistaJugador = new VistaJugador();
    private static Jugador jugador;
    private static char muniecoJugador='@';
    private Integer filaJugador;
    private Integer columnaJugador;
    private static Escenario escenario;
    private static final String PATH_VISTAS = "/vistas/";
    private Stage ventana;

    public Controlador(Stage stage){
        this.ventana=stage;
    }
    /**
     * Método para iniciar el controlador
     */
    public void iniciar(){
        elegirEscenario();
    }

    //METODOS PARA JUGADOR
    /**
     * Método para instanciar al jugador
     * @param nombre Recibe el valor del nombre
     * @param email Recibe el valor del email
     */ 
    public void instanciarJugador(String nombre, String email){
        jugador=new Jugador(nombre,email);
    }

    //METODOS PARA ESCENARIO
    
    /**
     * Método para elegir escenario
     */
    public void elegirEscenario(){
        vistaEscenario.mostrarMenu();//Llama a la vista Escenario para mostrar menu
        menuPrincipal(); //Se encarga de recibir la opcion del usuario y crear el objeto de escenario con ella
    }

    /**
     * Método que se encarga de recibir la opcion del usuario y con ella crear el objeto escenario.
     */
    public void menuPrincipal(){
        String opcion = "";
        String dificultad = "";
        do{
            opcion = pedirString();
            switch (opcion) {
                case "1":
                    dificultad = "facil";
                    break;
                case "2":
                    dificultad = "medio";
                    break;
                case "3":
                    dificultad = "dificil";
                    break;
                case "0":
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Elija una opcion válida.");
                    break;
            }
        }while(dificultad.isEmpty());

        escenario = new Escenario(dificultad); //Crea el objeto
        escenario.cargarEscenario();//Carga el escenario
        escenario.recrearEscenario();//Método para interpretar los datos del fichero y pasarlos a matriz
        //vistaEscenario.mostrarElementos(escenario.contabilizarElementos()); //Llama al método que contabiliza los elementos de la clase escenario, ya que son características suyas.
        jugar();//Llamamos al método jugar() para comenzar
        
    }
    /**
     * Método para pedir texto al usuario
     * @return Devuelve el texto
     */
    public String pedirString(){
        String texto = "";
        texto = teclado.nextLine();
        return texto;
    }
    /**
     * Método para cerrar el Scanner
     */
    public void cerrarScanner(){
        teclado.close();
    }
    /**
     * Método para empezar a jugar
     */ 
    public void jugar(){
        colocarJugador();//Llamada al método para colocar al jugador en el escenario
        char movimiento=' ';//Decalramos char de movmiento
        do{
            vistaEscenario.mostrarEscenario(escenario.getEscenario()); //Llamada a mostrar por pantalla el escenario
            vistaJugador.mostrarWASD(); //Lllamada para mostrar WASD>
            movimiento=pedirMovimiento();//Pedimos por teclado que introduzca la direcion con el mensaje anterior
            direccion(movimiento); //Llamada al metodo direccion para realizar las acciones en funcion de la direccion introducida
            if(ganarJuego(escenario.getEscenario())){//Llamamos al metodo para comprobar si ha llegado la final del juego o no y si es así terminar
                vistaEscenario.mostrarEscenario(escenario.getEscenario()); 
                vistaJugador.mensajeGanador();
                break;
            }
        }while(movimiento!='Q');//Bucle termina cuando introdueQ
     
    }
    /**
     * Método para colocar al jugador en el primer espacio que encuentra
     */ 
    public void colocarJugador(){
        char[][] escenarioMatriz=escenario.getEscenario(); //Recogemos la matriz del escenario
        for(int i =0;i<escenarioMatriz.length;i++){ //Iteramos sobre la fila
            for(int j=0;j<escenarioMatriz[i].length;j++){ //Iteramos sobre la columna
                if(escenarioMatriz[i][j]==' '){ //Si lo que encuentra es un espacio
                    filaJugador=i; //Inicializamos la posicion del jugador en la fila
                    columnaJugador=j; //Inicializamos la posicion del jugador en la columna
                    escenarioMatriz[i][j]=muniecoJugador; //Añadimos el caracter del jugador 
                    return; //Terminamos tanto el bucle como el metodo ya que se ha añadido el jugador
                }
            }
        }
    }
    /**
     * Método para mover al personaje por el escenario
     */ 
    public void direccion(char movimiento){
        Integer fila=filaJugador;
        Integer columna=columnaJugador;
        switch(movimiento){
            case 'W':
                moverJugador(fila-1,columna);//Si el movimiento elegido es la W entonces se resta una posicion de la fila(simila el movimiento hacia arriba)
                    break;
            case 'A':
                moverJugador(fila,columna-1);//Si el movimiento elegido es la A entonces se resta una posicion a la columna(simula el movimiento hacia la izquierda)
                    break;
            case 'S':
                moverJugador(fila+1,columna);//Si el movimiento elegido es la S entonces se suma una posicion a la columna(simula el movmimiento hacia abajo)
                    break;
            case 'D':
                moverJugador(fila,columna+1);// Si el movimiento elegido es la D entonces se suma una posición a la D(simula el movimiento hacia la derecha)
                break;
            case 'Q':
                break;
            default:
                vistaJugador.mostrarWASD();
        }
    }
    /**
     * Método para pedir el movimiento al jugador (WASD)
     * @return Devuelve el movimiento
     */ 
    public char pedirMovimiento(){
        String recogida=" ";
        char movimiento=' ';
        recogida=teclado.nextLine().toUpperCase();//Convertimos a mayusculas
          if(recogida.isBlank()){//Si está vacio asignamos 0 para que lo recoja para default
            movimiento='0';
        }else{//Si no cogemos 
            movimiento=recogida.charAt(0);//Cogemos la primera posicion y convertimos a char
        }
        return movimiento;
    }
    /**
     * Método para mover al jugador por el escenario
     * @param fila Recibe el valor para la fila 
     * @param columna Recibe el valor para la columna
     */ 
    public void moverJugador(Integer fila,Integer columna){
        char[][]escenarioMatriz=escenario.getEscenario();
        if(fila<0 || fila>escenarioMatriz.length-1 || columna>escenarioMatriz[0].length-1 ||columna<0){
            vistaJugador.mostrarMensajeBordes();
            return;
        }
        if(escenarioMatriz[fila][columna]=='X'){
            vistaJugador.mostrarMensajeObstaculo();
            return;
        }
        escenarioMatriz[fila][columna]=muniecoJugador;//Colocamos al jugador en la posicion
        quitarRastroJugador();//Quitamos la posicion anterior al jugador
        filaJugador=fila;//Actualizamos la nueva fila
        columnaJugador=columna;   //Actualizamos la nueva columna
    }
    /**
     * Método para quitar rastro del jugador
     */ 
    public void quitarRastroJugador(){
        char[][] escenarioMatriz=escenario.getEscenario();
        escenarioMatriz[filaJugador][columnaJugador]=' ';
    }
    /**
     * Método para comprobar si ha terminado el laberinto y llamar a la vistajugador para que muestre el mensaje 
     * @return Devuelve un boolean en funcion de si ha llegado al final o no
     */ 
    public Boolean ganarJuego(char[][] escenario){
        if(escenario.length-1==filaJugador && escenario[0].length-1==columnaJugador){//Comprueba si la fila y columna en la que se encuentra actualmente el jugador es la del final
            return true;
        }
        return false;
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

    public Stage getVentana(){
        return this.ventana;
    }
}

