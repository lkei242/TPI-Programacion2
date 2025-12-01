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
                    System.out.println("===========================================");
                    System.out.println(" ¿Qué tipo de vehículo usado desea agregar?");
                    System.out.println(" 1. Auto");
                    System.out.println(" 2. Camioneta");
                    System.out.println(" 3. Moto");
                    System.out.println("===========================================");
                    if (opcion == 0)
                    {
                        System.out.println("Opción inválida. Debe seleccionar un tipo de vehículo.");
                        return;
                    }
                    int tipo = this.vehículo();
                    this.agregarVehiculoUsado(tipo);
                    break;  
                case 2:
                    System.out.println("===========================================");
                    System.out.println(" ¿Qué tipo de vehículo nuevo desea agregar?");
                    System.out.println(" 1. Auto");
                    System.out.println(" 2. Camioneta");
                    System.out.println(" 3. Moto");
                    System.out.println("===========================================");
                    tipo = vehículo();
                    this.agregarVehiculoNuevo(tipo);
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

    private int vehículo() 
    {
       int opcion = 0;

        boolean valido = false;
        while (!valido) 
        {
            try 
            {

                System.out.print("Seleccione una opción (1-3): ");
                opcion = this.sc.nextInt();
                this.sc.nextLine();

                if (opcion == 1 || opcion == 2 || opcion == 3) 
                {
                    valido = true;
                } 
                else 
                {
                    System.out.println("Opción inválida. Ingrese 1, 2 o 3.");
                }
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("Debe ingresar un número.");
                this.sc.nextLine();
            }
        }
        return opcion;
    }


    //Capaz mover cada caso del switch a un metodo dentro de sus respectivas clases
    private void agregarVehiculoUsado(int tipo) 
    {

        switch (tipo)
        {
        case 1:
            //agregar los finally
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
                
                int kilometraje = 0;
                valido = false;

                while (!valido) 
                {
                    try {
                        System.out.print("Ingrese el año del automóvil: ");
                        kilometraje = this.sc.nextInt();
                        this.sc.nextLine();
                        valido = true;
                    } 
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Error: debe ingresar un número entero.");
                        this.sc.nextLine();
                    }
                }

                System.out.print("Ingrese el color del automóvil: ");
                String color = this.sc.nextLine();

                System.out.print("Ingrese el tipo del automóvil (sedán, hatchback, etc.): ");
                String carroceria = this.sc.nextLine();

                boolean usado = true; 
                boolean mantenimientoRealizado = false;

                Automovil auto = new Automovil(marca, modelo, anio, color, carroceria, usado, kilometraje, mantenimientoRealizado);
                this.conc.agregarVehiculo(auto);

                System.out.println("Automóvil agregado correctamente.");

            } 
            //cambiar la excepcion generica por una mas especifica o una propia... o quizas no
            catch (Exception e) 
            {
                System.out.println("Ocurrió un error al ingresar el automóvil: " + e.getMessage());
            }
            break;
        case 2:
            //agregar los finally
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
                
                int kilometraje = 0;
                valido = false;

                while (!valido) 
                {
                    try {
                        System.out.print("Ingrese el año del automóvil: ");
                        kilometraje = this.sc.nextInt();
                        this.sc.nextLine();
                        valido = true;
                    } 
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Error: debe ingresar un número entero.");
                        this.sc.nextLine();
                    }
                }

                System.out.print("Ingrese el color del automóvil: ");
                String color = this.sc.nextLine();

                System.out.print("Ingrese el tipo del automóvil (sedán, hatchback, etc.): ");
                String chasis = this.sc.nextLine();

                boolean usado = true; 
                boolean mantenimientoRealizado = false;

                Motocicleta moto = new Motocicleta(marca, modelo, anio, color, chasis, usado, kilometraje, mantenimientoRealizado);
                this.conc.agregarVehiculo(moto);

                System.out.println("Automóvil agregado correctamente.");

            } 
            //cambiar la excepcion generica por una mas especifica o una propia... o quizas no
            catch (Exception e) 
            {
                System.out.println("Ocurrió un error al ingresar el automóvil: " + e.getMessage());
            }
            break;
        case 3:
            //agregar los finally
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
                
                int kilometraje = 0;
                valido = false;

                while (!valido) 
                {
                    try {
                        System.out.print("Ingrese el año del automóvil: ");
                        kilometraje = this.sc.nextInt();
                        this.sc.nextLine();
                        valido = true;
                    } 
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Error: debe ingresar un número entero.");
                        this.sc.nextLine();
                    }
                }

                System.out.print("Ingrese el color del automóvil: ");
                String color = this.sc.nextLine();

                System.out.print("Ingrese el tipo del automóvil (sedán, hatchback, etc.): ");
                String carrocería = this.sc.nextLine();

                boolean usado = true; 

                boolean mantenimientoRealizado = false;
                Camioneta camioneta = new Camioneta(marca, modelo, anio, color, carrocería, usado, kilometraje, mantenimientoRealizado);
                this.conc.agregarVehiculo(camioneta);

                System.out.println("Automóvil agregado correctamente.");

            } 
            //cambiar la excepcion generica por una mas especifica o una propia... o quizas no
            catch (Exception e) 
            {
                System.out.println("Ocurrió un error al ingresar el automóvil: " + e.getMessage());
            }
            break;

        default:
            System.out.println("Tipo de vehículo inválido.");
        
        }

    }
        

    private void agregarVehiculoNuevo(int tipo) 
    {

        switch (tipo)
        {
        case 1:
            //agregar los finally
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
                
                int kilometraje = 0;


                System.out.print("Ingrese el color del automóvil: ");
                String color = this.sc.nextLine();

                System.out.print("Ingrese el tipo del automóvil (sedán, hatchback, etc.): ");
                String carroceria = this.sc.nextLine();

                boolean usado = false; 

                boolean mantenimientoRealizado = false;
                Automovil auto = new Automovil(marca, modelo, anio, color, carroceria, usado, kilometraje, mantenimientoRealizado);
                this.conc.agregarVehiculo(auto);

                System.out.println("Automóvil agregado correctamente.");

            } 
            //cambiar la excepcion generica por una mas especifica o una propia... o quizas no
            catch (Exception e) 
            {
                System.out.println("Ocurrió un error al ingresar el automóvil: " + e.getMessage());
            }
            break;
        case 2:
            //agregar los finally
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
                
                int kilometraje = 0;

                System.out.print("Ingrese el color del automóvil: ");
                String color = this.sc.nextLine();

                System.out.print("Ingrese el tipo del automóvil (sedán, hatchback, etc.): ");
                String chasis = this.sc.nextLine();

                boolean usado = false; 

                boolean mantenimientoRealizado = false;
                Motocicleta moto = new Motocicleta(marca, modelo, anio, color, chasis, usado, kilometraje, mantenimientoRealizado);
                this.conc.agregarVehiculo(moto);

                System.out.println("Automóvil agregado correctamente.");

            } 
            //cambiar la excepcion generica por una mas especifica o una propia... o quizas no
            catch (Exception e) 
            {
                System.out.println("Ocurrió un error al ingresar el automóvil: " + e.getMessage());
            }
            break;
        case 3:
            //agregar los finally
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
                
                int kilometraje = 0;

                System.out.print("Ingrese el color del automóvil: ");
                String color = this.sc.nextLine();

                System.out.print("Ingrese el tipo del automóvil (sedán, hatchback, etc.): ");
                String carrocería = this.sc.nextLine();

                boolean usado = false; 
                boolean mantenimientoRealizado = false;

                Camioneta camioneta = new Camioneta(marca, modelo, anio, color, carrocería, usado, kilometraje, mantenimientoRealizado);
                this.conc.agregarVehiculo(camioneta);

                System.out.println("Automóvil agregado correctamente.");

            } 
            //cambiar la excepcion generica por una mas especifica o una propia... o quizas no
            catch (Exception e) 
            {
                System.out.println("Ocurrió un error al ingresar el automóvil: " + e.getMessage());
            }
            break;

        default:
            System.out.println("Tipo de vehículo inválido.");
        
        }

    }
    /*
    private void agregarVehiculoNuevo() 
    {
        Camioneta cam = new Camioneta("Toyota", "Hilux", 2024, "Blanca", "Pickup", false, 0);
        conc.agregarVehiculo(cam);
    }
    */

    private void realizarMantenimiento() 
    {
        //Hay que hacer el código para verificar si existe dicho vehícul usado (si es nuevo debe mostrar un mensaje de que no se puede):
        
        Automovil auto = new Automovil("Ford", "Focus", 2017, "Gris", "Sedán", true, 45000, false);
        taller.realizarMantenimiento(auto);
    }
}

