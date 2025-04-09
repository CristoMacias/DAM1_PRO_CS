import java.util.List;

public class VistaEscenario {

    /**
     * Método que muestra las opciones del menú.
     */
    public void mostrarMenu() {
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
     * 
     * @param escenario recibe el escenario a mostrar
     */
    public void mostrarEscenario(char[][] escenario) {
        int filas = escenario.length;
        int columnas = escenario[0].length;

        // Imprimir borde superior
        for (int i = 0; i < columnas + 2; i++) {
            System.out.print("#");
        }
        System.out.println();

        // Imprimir filas con borde lateral
        for (int i = 0; i < filas; i++) {
            if(i != 0){
                System.out.print("#"); // Borde izquierdo
            }
            else{
                System.out.print("E"); //Entrada
            }
            
            for (int j = 0; j < columnas; j++) {
                System.out.print(escenario[i][j]);
            }
            if(i != filas -1){
                System.out.print("#"); // Borde derecho
            }
            else{
                System.out.print("S"); // Borde derecho
            }
            System.out.println();
        }

        // Imprimir borde inferior
        for (int i = 0; i < columnas + 2; i++) {
            System.out.print("#");
        }
        System.out.println();
    }

    /**
     * Método que muestra los elementos del escenario.
     * 
     * @param elementos recibe un array donde la posicion 1 son los obstaculos, y la
     *                  posicion 2 son los espacios.
     */
    public void mostrarElementos(Integer[] elementos) {
        System.out.println("Obstaculos: " + elementos[0]);
        System.out.println("Espacios: " + elementos[1]);
    }
}
