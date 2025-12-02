import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu 
{

    private Concesionaria conc = new Concesionaria();
    private Taller taller = new Taller();
    private Scanner sc = new Scanner(System.in);

    public void iniciar() 
    {
        int opcion;
        do 
        {
            //falta opcion de poner vehiculo en cola de mantenimiento y despues realizar mantenimiento de todos los vehiculos en cola
            //Para eliminar, modificar y realizar mantenimiento hay que poner un control de que existan vehículos en el stock
            System.out.println("╔═════════════ MENU ════════════╗");
            System.out.println("║1. Agregar vehiculo usado      ║");
            System.out.println("║2. Agregar vehiculo nuevo      ║");
            System.out.println("║3. Realizar mantenimiento      ║");
            System.out.println("║4. Eliminar vehiculo           ║"); 
            System.out.println("║5. Mostrar stock               ║");
            System.out.println("║6. Mostrar cola mantenimiento  ║");
            System.out.println("║0. Salir                       ║");
            System.out.println("╚═══════════════════════════════╝");
            System.out.print("Opcion: ");
            
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1; // Opcion invalida
            }

            switch (opcion) 
            {
                case 1:
                    System.out.println("===========================================");
                    System.out.println(" ¿Que tipo de vehiculo usado desea agregar?");
                    System.out.println(" 1. Auto");
                    System.out.println(" 2. Camioneta");
                    System.out.println(" 3. Moto");
                    System.out.println("===========================================");
                    int tipo = this.vehiculo();
                    this.agregarVehiculoUsado(tipo);
                    break;  
                case 2:
                    System.out.println("===========================================");
                    System.out.println(" ¿Que tipo de vehiculo nuevo desea agregar?");
                    System.out.println(" 1. Auto");
                    System.out.println(" 2. Camioneta");
                    System.out.println(" 3. Moto");
                    System.out.println("===========================================");
                    tipo = vehiculo();
                    this.agregarVehiculoNuevo(tipo);
                    break;
                case 3:
                    realizarMantenimiento();
                    break;
                case 4:
                    eliminarVehiculo();
                    break; 
                case 5:
                    conc.mostrarStock();
                    break;
                case 6:
                    conc.mostrarColaMantenimiento();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
            

        } while (opcion != 0);
    }

    private int vehiculo() 
    {
       int opcion = 0;

        boolean valido = false;
        while (!valido) 
        {
            try 
            {

                System.out.print("Seleccione una opcion (1-3): ");
                opcion = this.sc.nextInt();
                this.sc.nextLine();

                if (opcion == 1 || opcion == 2 || opcion == 3) 
                {
                    valido = true;
                } 
                else 
                {
                    System.out.println("Opcion invalida. Ingrese 1, 2 o 3.");
                }
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("Debe ingresar un numero.");
                this.sc.nextLine();
            }
        }
        return opcion;
    }


    //Capaz mover cada caso del switch a un metodo dentro de sus respectivas clases
    private void agregarVehiculoUsado(int tipo) 
    {
        System.out.println("Seleccione un color para el auto:");
        SeleccionColorAuto.mostrarColoresDisponibles();
        System.out.print("Ingrese un numero: ");
        int colorAuto = -1; 
        boolean valido1 = false;

        while (!valido1) 
        {
            try {
                System.out.print("Ingrese un numero del 1 al 6: ");
                colorAuto = Integer.parseInt(this.sc.nextLine());
                

                if (colorAuto >= 1 && colorAuto <= 6) 
                {
                    valido1 = true;
                } 
                else 
                {
                    System.out.println("Numero fuera de rango. Intente de nuevo.");
                    valido1 = false;
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Debe ser un numero entero.");
                valido1 = false;
            }
        }

        SeleccionColorAuto.ColorAuto colorEnum = SeleccionColorAuto.obtenerColorPorIndice(colorAuto);
        String color = colorEnum.toString();


        boolean lavado = false;
        switch (tipo)
        {
        case 1:
            
            try {
                System.out.print("Ingrese la marca del automovil: ");
                String marca = this.sc.nextLine();

                System.out.print("Ingrese el modelo del automovil: ");
                String modelo = this.sc.nextLine();

                int anio = 0;
                boolean valido = false;

                while (!valido || (anio < 1900 || anio > 2026)) 
                {
                    try {
                        System.out.print("Ingrese el año del automovil(1900-2026): ");
                        anio = this.sc.nextInt();
                        this.sc.nextLine();
                        valido = true;
                    } 
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Error: debe ingresar un numero entero.");
                        this.sc.nextLine();
                        valido = false;
                    }
                }
                
                int kilometraje = 0;
                valido = false;

                while (!valido && kilometraje < 0) 
                {
                    try {
                        System.out.print("Ingrese el kilometraje del automovil: ");
                        kilometraje = this.sc.nextInt();
                        this.sc.nextLine();
                        valido = true;
                    } 
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Error: debe ingresar un numero entero.");
                        this.sc.nextLine();
                    }
                }


                System.out.print("Ingrese el tipo del automovil (sedan, hatchback, etc.): ");
                String carroceria = this.sc.nextLine();

                boolean usado = true; 
                boolean mantenimientoRealizado = false;

                //falta controlar que el idVehiculo sea unico
                boolean esUnico = false;
                String idVehiculo = null;

                while (!esUnico) 
                {
                    try 
                    {
                        System.out.print("Ingrese la identificacion del vehiculo: ");
                        idVehiculo = this.sc.nextLine();

                        
                        if (this.conc.vehiculoUnico(idVehiculo)==false) 
                        {
                            esUnico = true;
                        } 
                        else 
                        {
                            System.out.println("El ID ingresado ya existe. Intente nuevamente.");
                        }

                    }
                    //Aca estoy un 100% seguro que puedo hacer una excepcion personalizada... creo que no hace falta este try catch
                    catch (Exception e) 
                    {
                        System.out.println("El vehiculo ya existe: ");
                    }
                }

                Automovil auto = new Automovil(marca, modelo, anio, color, carroceria, usado, kilometraje, mantenimientoRealizado, idVehiculo, lavado);
                this.conc.agregarVehiculo(auto);

                System.out.println("Automovil agregado correctamente.");

            } 
            //cambiar la excepcion generica por una mas especifica o una propia... o quizas no
            catch (Exception e) 
            {
                System.out.println("Ocurrio un error al ingresar el automovil: " + e.getMessage());
            }
            break;
        case 2:
            
            try {
                System.out.print("Ingrese la marca del automovil: ");
                String marca = this.sc.nextLine();

                System.out.print("Ingrese el modelo del automovil: ");
                String modelo = this.sc.nextLine();

                int anio = 0;
                boolean valido = false;

                while (!valido || (anio < 1900 || anio > 2026)) 
                {
                    try {
                        System.out.print("Ingrese el año del automovil(1900-2026): ");
                        anio = this.sc.nextInt();
                        this.sc.nextLine();
                        valido = true;
                    } 
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Error: debe ingresar un numero entero.");
                        this.sc.nextLine();
                        valido = false;
                    }
                }
                
                int kilometraje = 0;
                valido = false;

                while (!valido  && kilometraje < 0) 
                {
                    try {
                        System.out.print("Ingrese el kilometraje del automovil: ");
                        kilometraje = this.sc.nextInt();
                        this.sc.nextLine();
                        valido = true;
                    } 
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Error: debe ingresar un numero entero.");
                        this.sc.nextLine();
                    }
                }


                System.out.print("Ingrese la marca de la camioneta (Van, Furgon, Chasis cabina, Minibus, Camion ligero, etc.): ");
                String carroceria = this.sc.nextLine();

                boolean usado = true; 
                boolean mantenimientoRealizado = false;

                boolean esUnico = false;
                String idVehiculo = null;

                while (!esUnico) 
                {
                    try 
                    {
                        System.out.print("Ingrese la identificacion del vehiculo: ");
                        idVehiculo = this.sc.nextLine();

                        
                        if (this.conc.vehiculoUnico(idVehiculo)==false) 
                        {
                            esUnico = true;
                        } 
                        else 
                        {
                            System.out.println("El ID ingresado ya existe. Intente nuevamente.");
                        }

                    }
                    //Aca estoy un 100% seguro que puedo hacer una excepcion personalizada... creo que no hace falta este try catch
                    catch (Exception e) 
                    {
                        System.out.println("El vehiculo ya existe: ");
                    }
                }

                Camioneta camioneta = new Camioneta(marca, modelo, anio, color, carroceria, usado, kilometraje, mantenimientoRealizado, idVehiculo, lavado);
                this.conc.agregarVehiculo(camioneta);

                System.out.println("Automovil agregado correctamente.");

            } 
            //cambiar la excepcion generica por una mas especifica o una propia... o quizas no
            catch (Exception e) 
            {
                System.out.println("Ocurrio un error al ingresar el automovil: " + e.getMessage());
            }
            break;
        case 3:
            
            try {
                System.out.print("Ingrese la marca del automovil: ");
                String marca = this.sc.nextLine();

                System.out.print("Ingrese el modelo del automovil: ");
                String modelo = this.sc.nextLine();

                int anio = 0;
                boolean valido = false;

                while (!valido || (anio < 1900 || anio > 2026)) 
                {
                    try {
                        System.out.print("Ingrese el año del automovil(1900-2026): ");
                        anio = this.sc.nextInt();
                        this.sc.nextLine();
                        valido = true;
                    } 
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Error: debe ingresar un numero entero.");
                        this.sc.nextLine();
                        valido = false;
                    }
                }
                
                int kilometraje = 0;
                valido = false;

                while (!valido  && kilometraje < 0) 
                {
                    try {
                        System.out.print("Ingrese el kilometraje del automovil: ");
                        kilometraje = this.sc.nextInt();
                        this.sc.nextLine();
                        valido = true;
                    } 
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Error: debe ingresar un numero entero.");
                        this.sc.nextLine();
                    }
                }


                System.out.print("Ingrese el tipo de motocicleta Naked, Sport, Scooter, Cruiser, Adventure, Enduro, etc.): ");
                String chasis = this.sc.nextLine();

                boolean usado = true; 
                boolean mantenimientoRealizado = false;

                //falta controlar que el idVehiculo sea unico
                boolean esUnico = false;
                String idVehiculo = null;

                while (!esUnico) 
                {
                    try 
                    {
                        System.out.print("Ingrese la identificacion del vehiculo: ");
                        idVehiculo = this.sc.nextLine();

                        
                        if (this.conc.vehiculoUnico(idVehiculo)==false) 
                        {
                            esUnico = true;
                        } 
                        else 
                        {
                            System.out.println("El ID ingresado ya existe. Intente nuevamente.");
                        }

                    }
                    //Aca estoy un 100% seguro que puedo hacer una excepcion personalizada... creo que no hace falta este try catch
                    catch (Exception e) 
                    {
                        System.out.println("El vehiculo ya existe: ");
                    }
                }

                Motocicleta moto = new Motocicleta(marca, modelo, anio, color, chasis, usado, kilometraje, mantenimientoRealizado, idVehiculo, lavado);
                this.conc.agregarVehiculo(moto);

                System.out.println("Automovil agregado correctamente.");

            } 
            //cambiar la excepcion generica por una mas especifica o una propia... o quizas no
            catch (Exception e) 
            {
                System.out.println("Ocurrio un error al ingresar el automovil: " + e.getMessage());
            }
            break;

        default:
            System.out.println("Tipo de vehiculo invalido.");
        
        }

    }
        

    private void agregarVehiculoNuevo(int tipo) 
    {
        System.out.println("Seleccione un color para el auto:");
        SeleccionColorAuto.mostrarColoresDisponibles();
        System.out.print("Ingrese un numero: ");
        int colorAuto = -1; 
        boolean valido1 = false;

        while (!valido1) 
        {
            try {
                System.out.print("Ingrese un numero del 1 al 6: ");
                colorAuto = Integer.parseInt(this.sc.nextLine());
            
                if (colorAuto >= 1 && colorAuto <= 6) 
                {
                    valido1 = true;
                } 
                else 
                {
                    System.out.println("Numero fuera de rango. Intente de nuevo.");
                    valido1 = false;
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Debe ser un numero entero.");
                valido1 = false;
            }
        }

        SeleccionColorAuto.ColorAuto colorEnum = SeleccionColorAuto.obtenerColorPorIndice(colorAuto);
        String color = colorEnum.toString();

        boolean lavado = true;
        switch (tipo)
        {
        case 1:
            
            try {
                System.out.print("Ingrese la marca del automovil: ");
                String marca = this.sc.nextLine();

                System.out.print("Ingrese el modelo del automovil: ");
                String modelo = this.sc.nextLine();

                int anio = 0;
                boolean valido = false;

                while (!valido || (anio < 1900 || anio > 2026)) 
                {
                    try {
                        System.out.print("Ingrese el año del automovil(1900-2026): ");
                        anio = this.sc.nextInt();
                        this.sc.nextLine();
                        valido = true;
                    } 
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Error: debe ingresar un numero entero.");
                        this.sc.nextLine();
                        valido = false;
                    }
                }
                
                int kilometraje = 0;



                System.out.print("Ingrese el tipo del automovil (sedan, hatchback, etc.): ");
                String carroceria = this.sc.nextLine();

                boolean usado = false; 
                boolean mantenimientoRealizado = false;
                
                //falta controlar que el idVehiculo sea unico
                boolean esUnico = false;
                String idVehiculo = null;

                while (!esUnico) 
                {
                    try 
                    {
                        System.out.print("Ingrese la identificacion del vehiculo: ");
                        idVehiculo = this.sc.nextLine();

                        
                        if (this.conc.vehiculoUnico(idVehiculo)==false) 
                        {
                            esUnico = true;
                        } 
                        else 
                        {
                            System.out.println("El ID ingresado ya existe. Intente nuevamente.");
                        }

                    }
                    //Aca estoy un 100% seguro que puedo hacer una excepcion personalizada... creo que no hace falta este try catch
                    catch (Exception e) 
                    {
                        System.out.println("El vehiculo ya existe: ");
                    }
                }

                Automovil auto = new Automovil(marca, modelo, anio, color, carroceria, usado, kilometraje, mantenimientoRealizado, idVehiculo, lavado);
                this.conc.agregarVehiculo(auto);

                System.out.println("Automovil agregado correctamente.");

            } 
            //cambiar la excepcion generica por una mas especifica o una propia... o quizas no
            catch (Exception e) 
            {
                System.out.println("Ocurrio un error al ingresar el automovil: " + e.getMessage());
            }
            break;
        case 2:
            
            try {
                System.out.print("Ingrese la marca del automovil: ");
                String marca = this.sc.nextLine();

                System.out.print("Ingrese el modelo del automovil: ");
                String modelo = this.sc.nextLine();

                int anio = 0;
                boolean valido = false;

                while (!valido || (anio < 1900 || anio > 2026)) 
                {
                    try {
                        System.out.print("Ingrese el año del automovil(1900-2026): ");
                        anio = this.sc.nextInt();
                        this.sc.nextLine();
                        valido = true;
                    } 
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Error: debe ingresar un numero entero.");
                        this.sc.nextLine();
                        valido = false;
                    }
                }
                
                int kilometraje = 0;


                System.out.print("Ingrese la marca de la camioneta (Van, Furgon, Chasis cabina, Minibus, Camion ligero, etc.): ");
                String carroceria = this.sc.nextLine();

                boolean usado = false; 
                boolean mantenimientoRealizado = false;

                //falta controlar que el idVehiculo sea unico
                boolean esUnico = false;
                String idVehiculo = null;

                while (!esUnico) 
                {
                    try 
                    {
                        System.out.print("Ingrese la identificacion del vehiculo: ");
                        idVehiculo = this.sc.nextLine();

                        
                        if (this.conc.vehiculoUnico(idVehiculo)==false) 
                        {
                            esUnico = true;
                        } 
                        else 
                        {
                            System.out.println("El ID ingresado ya existe. Intente nuevamente.");
                        }

                    }
                    //Aca estoy un 100% seguro que puedo hacer una excepcion personalizada... creo que no hace falta este try catch
                    catch (Exception e) 
                    {
                        System.out.println("El vehiculo ya existe: ");
                    }
                }

                Camioneta camioneta = new Camioneta(marca, modelo, anio, color, carroceria, usado, kilometraje, mantenimientoRealizado, idVehiculo, lavado);
                this.conc.agregarVehiculo(camioneta);

                System.out.println("Automovil agregado correctamente.");

            } 
            //cambiar la excepcion generica por una mas especifica o una propia... o quizas no
            catch (Exception e) 
            {
                System.out.println("Ocurrio un error al ingresar el automovil: " + e.getMessage());
            }
            break;
        case 3:
            
            try {
                System.out.print("Ingrese la marca del automovil: ");
                String marca = this.sc.nextLine();

                System.out.print("Ingrese el modelo del automovil: ");
                String modelo = this.sc.nextLine();

                int anio = 0;
                boolean valido = false;

                while (!valido || (anio < 1900 || anio > 2026)) 
                {
                    try {
                        System.out.print("Ingrese el año del automovil(1900-2026): ");
                        anio = this.sc.nextInt();
                        this.sc.nextLine();
                        valido = true;
                    } 
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Error: debe ingresar un numero entero.");
                        this.sc.nextLine();
                        valido = false;
                    }
                }
                
                int kilometraje = 0;

                System.out.print("Ingrese el tipo de motocicleta Naked, Sport, Scooter, Cruiser, Adventure, Enduro, etc.): ");
                String chasis = this.sc.nextLine();

                boolean usado = false; 
                boolean mantenimientoRealizado = false;
                //falta controlar que el idVehiculo sea unico
boolean esUnico = false;
                String idVehiculo = null;

                while (!esUnico) 
                {
                    try 
                    {
                        System.out.print("Ingrese la identificacion del vehiculo: ");
                        idVehiculo = this.sc.nextLine();

                        
                        if (this.conc.vehiculoUnico(idVehiculo) == false) 
                        {
                            esUnico = true;
                        } 
                        else 
                        {
                            System.out.println("El ID ingresado ya existe. Intente nuevamente.");
                        }

                    }
                    //Aca estoy un 100% seguro que puedo hacer una excepcion personalizada... creo que no hace falta este try catch
                    catch (Exception e) 
                    {
                        System.out.println("El vehiculo ya existe: ");
                    }
                }


                Motocicleta moto = new Motocicleta(marca, modelo, anio, color, chasis, usado, kilometraje, mantenimientoRealizado, idVehiculo, lavado);
                this.conc.agregarVehiculo(moto);
                

                System.out.println("Automovil agregado correctamente.");

            } 
            //cambiar la excepcion generica por una mas especifica o una propia... o quizas no
            catch (Exception e) 
            {
                System.out.println("Ocurrio un error al ingresar el automovil: " + e.getMessage());
            }
            break;

        default:
            System.out.println("Tipo de vehiculo invalido.");
        
        }

    }


    public void eliminarVehiculo() 
    {

        System.out.println("===========================================");
        System.out.println(" ¿Que tipo de vehiculo desea eliminar?");
        System.out.println(" 1. Auto");
        System.out.println(" 2. Camioneta");
        System.out.println(" 3. Moto");
        System.out.println("===========================================");

        int opcion = 0;

        boolean valido = false;
        while (!valido) 
        {
            try 
            {

                System.out.print("Seleccione una opcion (1-3): ");
                opcion = this.sc.nextInt();
                this.sc.nextLine();

                if (opcion == 1 || opcion == 2 || opcion == 3) 
                {
                    valido = true;
                } 
                else 
                {
                    System.out.println("Opcion invalida. Ingrese 1, 2 o 3.");
                }
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("Debe ingresar un numero.");
                this.sc.nextLine();
            }
        }

        System.out.println("===========================================");
        System.out.println(" ¿En base a que desea eliminar: el ID del vehiculo (elimina solo uno) o la marcar y el modelo (todas las ocurrencias)?");
        System.out.println(" 1. idVehiculo");
        System.out.println(" 2. Marca y modelo");
        System.out.println("===========================================");

        opcion = 0;

        valido = false;
        while (!valido) 
        {
            try 
            {

                System.out.print("Seleccione una opcion (1-2): ");
                opcion = this.sc.nextInt();
                this.sc.nextLine();

                if (opcion == 1 || opcion == 2 ) 
                {
                    valido = true;
                } 
                else 
                {
                    System.out.println("Opcion invalida. Ingrese 1 o 2.");
                }
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("Debe ingresar un numero.");
                this.sc.nextLine();
            }
        }
        if (opcion == 1) 
        {
            System.out.println("Ingrese la identificacion del vehiculo a eliminar: ");
            String idVehiculo = sc.nextLine();

            int indice = conc.buscarVehiculo(idVehiculo);
            if (indice != -1) 
            {
                conc.eliminarVehiculo(indice);
                System.out.println("Vehiculo eliminado correctamente.");
            } 
            else 
            {
                System.out.println("Vehiculo no encontrado.");
            }
        }
        else 
        {
            System.out.println("Ingrese la marca del vehiculo a eliminar: ");
            String marca = sc.nextLine();
            System.out.println("Ingrese el modelo del vehiculo a eliminar: ");
            String modelo = sc.nextLine();

            conc.eliminarVehiculo(marca, modelo);
            System.out.println("Vehiculo eliminado correctamente.");

        
            System.out.println("Vehiculo no encontrado.");
            
            return;
        }
    }


    private void realizarMantenimiento() 
    {
        //Hay que hacer el codigo para verificar si existe dicho vehiculo usado (si es nuevo debe mostrar un mensaje de que no se puede):
        System.out.println("===========================================");
        System.out.println(" Se realizara el mantenimiento de un vehiculo usado.");
        System.out.println(" A continuacion, debe ingresar el ID del vehiculo usado.");
        System.out.println("===========================================");
        String idVehiculo;
        boolean valido = false;        
        
        while (!valido) 
        {
            System.out.print("Ingrese el ID del vehiculo: ");
            idVehiculo = sc.nextLine();
            int encontrado = this.conc.buscarVehiculo(idVehiculo);
            if (encontrado >= 0) 
            {
                Vehiculo vehiculo = this.conc.getStock().get(encontrado);
                if (vehiculo.getEsUsado()) 
                {
                    if (vehiculo.getMantenimiento())
                    {
                        System.out.println("El vehiculo ya ha sido mantenido previamente.");
                        return;
                    }
                    else 
                    {
                        valido = true;
                        taller.realizarMantenimiento(vehiculo);
                        this.conc.borrarVehiculoMantenimiento(vehiculo);
                        System.out.println("Mantenimiento realizado correctamente.");
                    }
                } 
                else 
                {
                    System.out.println("El vehiculo es nuevo, no se puede realizar mantenimiento.");
                }
            } 
            else 
            {
                System.out.println("Vehiculo no encontrado, ingrese un ID valido.");
            }

        }

    }

    public void modificarAutomovil()
{
    // Usamos el Scanner 'sc' y la Concesionaria 'conc' definidos en Menu [5].

    System.out.println("===========================================");
    System.out.println(" MODIFICAR AUTOMOVIL POR ID");
    System.out.println("===========================================");

    System.out.print("Ingrese la identificacion del vehiculo (ID) a modificar: ");
    boolean existe = false;
    String idVehiculo = null;

    while (!existe) 
    {
        try 
        {
            System.out.print("Ingrese la identificacion del vehiculo: ");
            idVehiculo = this.sc.nextLine();

            
            if (this.conc.vehiculoUnico(idVehiculo)==true) 
            {
                existe = true;
            } 
            else 
            {
                System.out.println("El ID ingresado no existe. Intente nuevamente.");
            }

        }
        //Aca estoy un 100% seguro que puedo hacer una excepcion personalizada... creo que no hace falta este try catch
        catch (Exception e) 
        {
            System.out.println("El vehiculo no existe: ");
        }
    }

    // Buscar el vehiculo por ID usando el método de Concesionaria [1]
    int indice = this.conc.buscarVehiculo(idVehiculo);

    if (indice != -1)
    {
        // Obtener el objeto Vehiculo del stock [6, 7]
        Vehiculo vehiculo = this.conc.getStock().get(indice);


        // Verificar que sea un Automovil (aunque los setters son de Vehiculo, 
        // la solicitud es específica para autos)
        //aca tengo que cambiar para que sea para moto y camioneta tambien

            System.out.println("\n=== Vehiculo Encontrado (ID: " + vehiculo.getIdVehiculo() + ") ===");
            // Mostrar información actual (Basado en mostrarInfo [8, 9])
            System.out.println("Marca actual: " + vehiculo.getMarca()); // [2, 9]
            System.out.println("\n===========================================\n");
            
            System.out.println("\nSeleccione el atributo a modificar:");
            System.out.println(" 1. Marca");
            System.out.println(" 2. Modelo");
            System.out.println(" 3. Año");
            System.out.println(" 4. Color");
            System.out.println(" 5. Carroceria");
            System.out.println(" 6. Es Usado");
            System.out.println(" 7. Mantenimiento");
            System.out.println(" 8. Kilometraje");
            System.out.println(" 9. Lavado");
            System.out.println(" 10. Salir");
            
            

            int opcion = 0;
            boolean valido = false;

            
            while (!valido)
            {
                try
                {
                    System.out.print("Opcion: ");
                    opcion = this.sc.nextInt();
                    this.sc.nextLine();

                    if (opcion >= 1 && opcion <= 10)
                    {
                        valido = true;
                    }
                    else
                    {
                        System.out.println("Opcion invalida.");
                    }
                }
                catch (InputMismatchException e)
                {
                    System.out.println("Debe ingresar un numero.");
                    this.sc.nextLine();
                }
            }
            valido = false;
            while (!valido)
            {
                switch (opcion)
                {
                    case 1:
                        System.out.print("Ingrese la nueva marca: ");
                        String nuevaMarca = this.sc.nextLine();
                        vehiculo.setMarca(nuevaMarca);
                        System.out.println("Marca modificada a: " + vehiculo.getMarca());
                        break;
                    case 2:
                        System.out.print("Ingrese el nuevo modelo: ");
                        String nuevoModelo = this.sc.nextLine();
                        vehiculo.setModelo(nuevoModelo);
                        System.out.println("Modelo modificado a: " + vehiculo.getModelo());
                        break;
                    case 3:
                        int nuevoAnio = 0;
                        boolean anioValido = false;
                        
                        while (!anioValido || (nuevoAnio < 1900 || nuevoAnio > 2026))
                        {
                            try {
                                System.out.print("Ingrese el nuevo año del automovil (1900-2026): ");
                                nuevoAnio = this.sc.nextInt();
                                this.sc.nextLine();
                                anioValido = true;
                            }
                            catch (InputMismatchException e)
                            {
                                System.out.println("Error: debe ingresar un numero entero.");
                                this.sc.nextLine();
                                anioValido = false;
                            }
                        }
                        vehiculo.setAnio(nuevoAnio);
                        System.out.println("Año modificado a: " + vehiculo.getAnio());
                        break;
                    case 4:
                        System.out.println("Seleccione un nuevo color:");
                        SeleccionColorAuto.mostrarColoresDisponibles();
                        
                        int colorAuto = -1;
                        boolean colorValido = false;
                        
                        while (!colorValido)
                        {
                            try {
                                System.out.print("Ingrese un numero del 1 al 6: ");
                                colorAuto = Integer.parseInt(this.sc.nextLine());
                                if (colorAuto >= 1 && colorAuto <= 6)
                                {
                                    colorValido = true;
                                }
                                else
                                {
                                    System.out.println("Numero fuera de rango. Intente de nuevo."); // Mensaje similar a [14]
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada invalida. Debe ser un numero entero."); // Mensaje similar a [16]
                            }
                        }
                        SeleccionColorAuto.ColorAuto colorEnum = SeleccionColorAuto.obtenerColorPorIndice(colorAuto); // [15]
                        String nuevoColor = colorEnum.toString();
                        vehiculo.setColor(nuevoColor); // Usando setter de Vehiculo [3]
                        System.out.println("Color modificado a: " + vehiculo.getColor());
                        break;
                    case 5: // Modificar Carroceria
                        if (vehiculo instanceof Automovil)
                        {
                            System.out.println("\n=== Automovil Encontrado (ID: " + vehiculo.getIdVehiculo() + ") ===");
                            existe = true;
                        }
                        else if (vehiculo instanceof Motocicleta)
                        {
                            System.out.println("\n=== Motocicleta Encontrada (ID: " + vehiculo.getIdVehiculo() + ") ===");
                            existe = true;
                        }
                        else if (vehiculo instanceof Camioneta)
                        {
                            System.out.println("\n=== Camioneta Encontrada (ID: " + vehiculo.getIdVehiculo() + ") ===");
                            existe = true;
                        }
                        System.out.print("Ingrese la carroceria: ");
                        String nuevaCarroceria = this.sc.nextLine();
                        vehiculo.setCarroceria(nuevaCarroceria);
                        System.out.println("Carrocería modificada a: " + vehiculo.getCarroceria());
                        break;
                    case 6: // Modificar Uso
                        System.out.print("Ingrese si es usado: usado (true) o no usado (false):");
                        //aca poner un try catch, o quizas cambiar a un int
                        boolean usado = Boolean.parseBoolean(this.sc.nextLine());
                        vehiculo.setEsUsado(usado);
                        System.out.println("Uso modificado a: " + vehiculo.getEsUsado());
                        break;
                    case 7: // Modificar Mantenimiento
                        System.out.print("Ingrese si es usado: usado (true) o no usado (false):");
                        //aca poner un try catch, o quizas cambiar a un int
                        boolean mantenimiento = Boolean.parseBoolean(this.sc.nextLine());
                        vehiculo.setMantenimiento(mantenimiento);
                        System.out.println("Mantenimiento modificado a: " + vehiculo.getMantenimiento());
                        break;
                    case 8: // Modificar Kilometraje
                        int nuevoKilometraje = -1;
                        boolean kmValido = false;
                        // Lógica de validación de kilometraje similar a agregarVehiculoUsado [13, 17]
                        while (!kmValido || nuevoKilometraje < 0)
                        {
                            try 
                            {
                                System.out.print("Ingrese el nuevo kilometraje del automovil: ");
                                nuevoKilometraje = this.sc.nextInt();
                                this.sc.nextLine();
                                if (nuevoKilometraje >= 0) {
                                    kmValido = true;
                                }
                            }
                            catch (InputMismatchException e)
                            {
                                System.out.println("Error: debe ingresar un numero entero."); // Mensaje similar a [17]
                                this.sc.nextLine();
                            }
                        }
                        vehiculo.setKilometraje(nuevoKilometraje); // Usando setter de Vehiculo [4]
                        System.out.println("Kilometraje modificado a: " + vehiculo.getKilometraje());
                        break;
                    case 9: // Modificar Uso
                        System.out.print("Ingrese si es usado: usado (true) o no usado (false):");
                        //aca poner un try catch, o quizas cambiar a un int
                        boolean lavado = Boolean.parseBoolean(this.sc.nextLine());
                        vehiculo.setEsUsado(lavado);
                        System.out.println("Uso modificado a: " + vehiculo.getLavado());
                        break;
                    case 10: // Modificar Uso
                        System.out.print("Saliendo...");
                        valido = true;
                        break;
                    default:
                        System.out.println("Modificacion cancelada o invalida.");
                }
            }
       
        
    }
    else
    {
        System.out.println("Vehiculo no encontrado. Ingrese un ID valido."); // Mensaje similar a [18, 19]
    }
}
    
}

