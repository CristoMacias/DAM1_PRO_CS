import java.util.Scanner;

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

    public void mostrarMensajeBienvenida(String nombre){
        System.out.println("¡Bienvenido "+nombre+"!");
    }

}
