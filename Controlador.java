import java.util.Scanner;
    
public class Controlador {

    static private Scanner teclado = new Scanner(System.in);

    private static VistaEscenario vistaEscenario = new VistaEscenario();
    private static VistaJugador vistaJugador = new VistaJugador();

        public Controlador(){
        }

        public void iniciar(){
            obtenerNombreUsuario();
            elegirEscenario();
        }

        //METODOS PARA JUGADOR

        public void obtenerNombreUsuario(){
            Scanner teclado = new Scanner(System.in);
            String nombre = "";
            vistaJugador.pedirJugador();
            nombre = teclado.nextLine();
            Jugador jugador = new Jugador(nombre);
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
            Boolean bandera = false;
            do{
                opcion = pedirOpcion();
            switch (opcion) {
                case "1":
                    dificultad = "facil";
                    bandera = true;
                    break;
                case "2":
                    dificultad = "medio";
                    bandera = true;
                    break;
                case "3":
                    dificultad = "dificil";
                    bandera = true;
                    break;
                default:
                    System.out.println("Elija una opcion válida.");
                    break;
            }
            
        }while(!bandera);

            Escenario escenario = new Escenario(dificultad); //Crea el objeto
            vistaEscenario.mostrarEscenario(escenario.getEscenario()); //Envía el objeto creado a la vista escenario para mostrarla

    }
        /**
         * Método que se encarga únicamente de recibir opciones del usuario
         * @return opción elegida por el usuario.
         */
        public String pedirOpcion(){
            Scanner teclado = new Scanner(System.in);
            String opcion = "";
            opcion = teclado.nextLine();
            return opcion;
        }
}

