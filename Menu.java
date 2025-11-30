import java.util.Scanner;

public class Menu 
{

    private Concesionaria conc = new Concesionaria();
    private Taller taller = new Taller();
    private Lavadero lavadero = new Lavadero();
    private Scanner sc = new Scanner(System.in);

    public void iniciar() 
    {
        int opcion;
        do 
        {
            System.out.println("╔══════════ MENU ══════════╗");
            System.out.println("║1. Agregar vehículo usado ║");
            System.out.println("║2. Agregar vehículo nuevo ║");
            System.out.println("║3. Realizar mantenimiento ║");
            System.out.println("║4. Lavar vehículo         ║");
            System.out.println("║5. Mostrar stock          ║");
            System.out.println("║0. Salir                  ║");
            System.out.println("╚══════════════════════════╝");
            System.out.print("Opción: ");
            
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1; // Opción inválida
            }

            switch (opcion) 
            {
                case 1:
                    agregarVehiculoUsado();
                    break;
                case 2:
                    agregarVehiculoNuevo();
                    break;
                case 3:
                    realizarMantenimiento();
                    break;
                case 4:
                    lavadero.lavarVehiculo();
                    break;
                case 5:
                    conc.mostrarStock();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción invalida");
            }
            

        } while (opcion != 0);
    }

    private void agregarVehiculoUsado() 
    {
        Automovil auto = new Automovil("Ford", "Fiesta", 2015, "Rojo", "Hatchback", true);
        conc.agregarVehiculo(auto);
    }

    private void agregarVehiculoNuevo() 
    {
        Camioneta cam = new Camioneta("Toyota", "Hilux", 2024, "Blanca", "Pickup", false);
        conc.agregarVehiculo(cam);
    }

    private void realizarMantenimiento() 
    {
        Automovil auto = new Automovil("Ford", "Focus", 2017, "Gris", "Sedán", true);
        taller.realizarMantenimiento(auto);
    }
}

