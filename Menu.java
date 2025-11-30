import java.util.InputMismatchException;
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
        //Por ahora esto es solo para autos usados

        try {
            System.out.print("Ingrese la marca del automóvil: ");
            String marca = this.sc.nextLine();

            System.out.print("Ingrese el modelo del automóvil: ");
            String modelo = this.sc.nextLine();

            int anio = 0;
            boolean valido = false;

            while (!valido) 
            {
                try {
                    System.out.print("Ingrese el año del automóvil: ");
                    anio = this.sc.nextInt();
                    this.sc.nextLine();
                    valido = true;
                } 
                catch (InputMismatchException e) 
                {
                    System.out.println("Error: debe ingresar un número entero.");
                    this.sc.nextLine();
                }
            }

            this.sc.nextLine(); 
            System.out.print("Ingrese el color del automóvil: ");
            String color = this.sc.nextLine();

            System.out.print("Ingrese el tipo del automóvil (sedán, hatchback, etc.): ");
            String tipo = this.sc.nextLine();

            boolean usado = true; 

            Automovil auto = new Automovil(marca, modelo, anio, color, tipo, usado);
            this.conc.agregarVehiculo(auto);

            System.out.println("Automóvil agregado correctamente.");

        } 
        //cambiar la excepcion generica por una mas especifica o una propia... o quizas no
        catch (Exception e) 
        {
            System.out.println("Ocurrió un error al ingresar el automóvil: " + e.getMessage());
        }
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

