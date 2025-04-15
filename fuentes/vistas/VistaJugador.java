package vistas;
public class VistaJugador {
    
    /**
     * Método que se encarga únicamente de pedirle el nombre del usuario.
     */
    public void pedirJugador(){
        System.out.print("Escriba el nombre del usuario: ");
    }
    /**
     * Método para pedir al usuario su email
     */ 
    public void pedirEmail(){
        System.out.print("Introduce su email: ");
    }
    /**
     * Método para mostrar un mensaje cuando se guarde el jugador
     */ 
    public void mensajeJugadorGuardado(){
        System.out.println("Se ha guardado tu jugador.");
    }
    /**
     * Método para mostrar un mensaje cuando se cargue el jugador
     */ 
    public void mensajeCargarJugador(){
        System.out.println("Se ha cargado tu jugador.");
    }
    /**
     * Método para mostrar un mensajr de bienvenida por pantalla con el nombre del usuario
     * @param nombre Recibe el valor del nombre del usuario
     */ 
    public void mostrarMensajeBienvenida(String nombre){
        System.out.println("¡Bienvenido "+nombre+"!");
    }
    /**
     * Método para mostrar por pantalla los comandos de movimientos WASD
     */ 
    public void mostrarWASD(){
        System.out.print("WASD>");
    }
    /**
     * Método para msotrar por pantalla un mensaje cuando se encuentre con un obstáculo
     */ 
    public void mostrarMensajeObstaculo(){
        System.out.println("¡Ouch! Eso ha dolido...");
    }
    /**
     * Método para mostrar por pantalla un mensaje cunado intente cruzar la pared
     */ 
    public void mostrarMensajeBordes(){
        System.out.println("Todavía no puedes traspasar paredes...");
    }
    /**
     * Método para mostrar por pantalla un mensaje al terminar el laberinto
     */
    public void mensajeGanador(){
        System.out.println("\n¡ENHORABUENA!\n¡Has terminado el Laberinto!");
    }

}
