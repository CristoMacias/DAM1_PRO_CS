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

}
