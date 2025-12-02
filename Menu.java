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
                        System.out.print("Ingrese el año del automovil: ");
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
                    //Aca estoy un 100% seguro que puedo hacer una excepcion personalizada
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
                        System.out.print("Ingrese el año del automovil: ");
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
                    //Aca estoy un 100% seguro que puedo hacer una excepcion personalizada
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
                        System.out.print("Ingrese el año del automovil: ");
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
                    //Aca estoy un 100% seguro que puedo hacer una excepcion personalizada
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
                        System.out.print("Ingrese el año del automovil: ");
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
                    //Aca estoy un 100% seguro que puedo hacer una excepcion personalizada
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
                        System.out.print("Ingrese el año del automovil: ");
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
                    //Aca estoy un 100% seguro que puedo hacer una excepcion personalizada
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
                        System.out.print("Ingrese el año del automovil: ");
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
                    //Aca estoy un 100% seguro que puedo hacer una excepcion personalizada
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

    
    
}

