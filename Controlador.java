/**
 * Clase Controlador
 * 
 * @author Cristo Macias, Sandra Moñino
 * 
 */
 import java.util.Scanner;  
 import java.nio.file.Path;
 import java.nio.file.Paths;
 import java.nio.file.Files;
 import java.io.IOException; 

 import java.io.ObjectInputStream;
 import java.io.InputStream; 
 import java.io.ObjectOutputStream;
 import java.io.OutputStream;
public class Controlador {

    private static Scanner teclado = new Scanner(System.in);

    private static VistaEscenario vistaEscenario = new VistaEscenario();
    private static VistaJugador vistaJugador = new VistaJugador();
    private static Jugador jugador;

        public Controlador(){
        }
        /**
         * Método para iniciar el controlador
         */
        public void iniciar(){
            obtenerJugador();
            elegirEscenario();
        }

        //METODOS PARA JUGADOR
        /**
         * Método para obtener el nombre del usuario
         */ 
        public void obtenerJugador(){
            String nombre = "";
            vistaJugador.pedirJugador();
            nombre = pedirString();
            if(!cargarJugador(nombre)){
                String email = "";
                vistaJugador.pedirEmail();
                email=pedirString();
                guardarJugador(nombre,email);//Llamamos al metodo guardarJugador para crear el fichero y guardar los datos
                vistaJugador.mensajeJugadorGuardado();
                instanciarJugador(nombre,email);//Volvemos a llamar al metodo cargarJugador() para que se instancie el jugador
            }
            vistaJugador.mostrarMensajeBienvenida(nombre);
        }
        /**
         * Método para cargar los datos del jugador. Comprueba si existe el archivo jugador.bin
         * @param nombre Recibe el nombre del jugador
         * @return Devuelve true si existe y false si no
         */ 
        @SuppressWarnings ("unchecked")
        public boolean cargarJugador(String nombre){
            Path pathJugadores=Paths.get("jugadores");//Creamos la ruta del directorio jugadores

            Path pathJugador=pathJugadores.resolve(nombre+".bin"); //Creamos la ruta del archivo del jugador
            String email;
            if(Files.exists(pathJugador)){//Comprobamos si existe el fichero.bin del jugador
                try(ObjectInputStream flujoEntradaJugador = new ObjectInputStream(Files.newInputStream(pathJugador))){
                    email=(String) flujoEntradaJugador.readObject(); //Asignamos al email directamente leido del email
                    instanciarJugador(nombre,email); //Instanciammos el jugador con el nombre y el email
                    return true;
                }catch(IOException e){
                    System.out.println("Otro error inesperado al leer el fichero jugador.");
                    e.printStackTrace();
                }catch(ClassNotFoundException e){
                    System.out.println("Error con la clase (String) al convertir del fichero jugador.");
                    e.printStackTrace();
                }
            }
            return false;
        }
        /**
         * Método crear el fichero.bin del jugador y guardar sus datos
         * @param nombre Recibe el nombre del jugador
         * @param email Recibe el email del jugador
         */ 
        public void guardarJugador(String nombre, String email){
            Path pathJugadores = Paths.get("jugadores");//Creamos la ruta del directorio jugadores
            comprobarDirectorioJugadores(pathJugadores);
            Path pathJugador = pathJugadores.resolve(nombre+".bin"); //Creamos el path del fichero del jugador
            try(ObjectOutputStream flujoSalidaJugador = new ObjectOutputStream(Files.newOutputStream(pathJugador))){ //Abrimos el flujo de salida para guardar los datos del jugador
                flujoSalidaJugador.writeObject(nombre); //Guardamos/Escribimos el nombre
                flujoSalidaJugador.writeObject(email); //Guardamos/Escribimos el email
            }catch(IOException e){
                System.out.println("Error en el guardado del jugador en el fichero .bin");
                e.printStackTrace();
            }
        }
        /**
         * Método para instanciar al jugador
         * @param nombre Recibe el valor del nombre
         * @param email Recibe el valor del email
         */ 
        public void instanciarJugador(String nombre, String email){
            jugador=new Jugador(nombre,email);
        }
        /**
         * Método para comprobar si no existe el directorio de jugadores, y si no existe, crearlo
         */ 
        public void comprobarDirectorioJugadores(Path pathJugadores){
            try{
                if(Files.notExists(pathJugadores)){
                    Files.createDirectory(pathJugadores);
                }
            }catch(IOException e){
                System.out.println("Error al comprobar y crear el directorio de jugadores.");
                e.printStackTrace();
            }
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

            Escenario escenario = new Escenario(dificultad); //Crea el objeto
            escenario.cargarEscenario();
            escenario.recrearEscenario();
            vistaEscenario.mostrarEscenario(escenario.getEscenario()); //Envía el objeto creado a la vista escenario para mostrarla
            vistaEscenario.mostrarElementos(escenario.contabilizarElementos()); //Llama al método que contabiliza los elementos de la clase escenario, ya que son características suyas.

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
}

