import java.util.List;

public class VistaEscenario {
    
    /**
     * Método que muestra las opciones del menú.
     */
    public void mostrarMenu(){
        System.out.println("¿Con qué nivel quiere jugar?");
        System.out.println("=============================");
        System.out.println("1) Nivel fácil");
        System.out.println("2) Nivel medio");
        System.out.println("3) Nivel difícil");
        System.out.println("0) Salir");
        System.out.println("=============================");

    }

    /**
     * Método que muestra el escenario
     * @param escenario recibe el escenario a mostrar
     */
    public void mostrarEscenario(List <String> escenario){
        for(String linea : escenario){
            System.out.println(linea);
        }
    }

    /**
     * Método que muestra los elementos del escenario.
     * @param elementos recibe un array donde la posicion 1 son los obstaculos, y la posicion 2 son los espacios.
     */
    public void mostrarElementos(Integer [] elementos){
        System.out.println("Obstaculos: " + elementos[0]);
        System.out.println("Espacios: " + elementos[1]);
    }
}
