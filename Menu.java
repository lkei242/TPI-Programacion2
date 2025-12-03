import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class Menu implements Serializable
{

    private Concesionaria conc = new Concesionaria();
    private Taller taller = new Taller();
    private Scanner sc = new Scanner(System.in);

    
    private AgregarVehiculo agregar = new AgregarVehiculo(conc, sc); 
    private ModificarVehiculo modificar = new ModificarVehiculo(conc, sc);

    public Menu(){}


    public void iniciar() 
    {   
        this.conc.loadFromFile();

        int opcion;
        do 
        {
            System.out.println("|============= MENU ============|");
            System.out.println("|1. Agregar vehiculo            |");
            System.out.println("|2. Realizar mantenimiento      |");
            System.out.println("|3. Eliminar vehiculo           |");
            System.out.println("|4. Modificar vehiculo          |");
            System.out.println("|5. Mostrar stock               |");
            System.out.println("|6. Mostrar cola mantenimiento  |");
            System.out.println("|7. Buscar Vehiculo             |");
            System.out.println("|0. Salir                       |");
            System.out.println("|===============================|");
            System.out.print("Opcion: ");
            
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } 
            catch (NumberFormatException e) 
            {
                opcion = -1; 
            }
            int tipo;
            int tipo2;
            switch (opcion) 
            {
                case 1:
                    tipo2 = tipoVehiculo();
                    tipo = vehiculo();
                    this.agregar.agregarVehiculo(tipo, tipo2);
                    break;
                case 2:
                    this.realizarMantenimiento();
                    break;
                case 3:
                    this.eliminarVehiculo();
                    break;
                case 4:
                    modificar.modificarVehiculo();
                    break; 
                case 5:
                    this.conc.mostrarStock();
                    break;
                case 6:
                    this.conc.mostrarColaMantenimiento();
                    break;
                case 7:
                    this.buscarVehiculo();
                    break;
                case 0:
                    this.conc.saveToFile();
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
            

        } while (opcion != 0);
    }

    public int tipoVehiculo()
    {
        System.out.println("===========================================");
        System.out.println(" ¿Desea agregar un vehiculo usado o nuevo?");
        System.out.println(" 1. Usado");
        System.out.println(" 2. Nuevo");
        System.out.println("===========================================");
        int opcion = 0;
        boolean valido = false;
        while (!valido) 
        {
            try 
            {

                System.out.print("Seleccione una opcion (1-2): ");
                opcion = this.sc.nextInt();
                this.sc.nextLine();

                if (opcion == 1 || opcion == 2) 
                {
                    valido = true;
                } 
                else 
                {
                    System.out.println("Opcion invalida. Ingrese 1 o 2");
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
    public int vehiculo() 
    {

        System.out.println("===========================================");
        System.out.println(" ¿Que tipo de vehiculo usado desea agregar?");
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
        return opcion;
        
    }



    public void eliminarVehiculo() 
    {
        if (!this.conc.getStock().isEmpty())
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
                try {
                    if (indice != -1) {
                        this.conc.eliminarVehiculo(indice);
                        System.out.println("Vehiculo eliminado correctamente.");
                    } else 
                    {
                        throw new VehiculoNoEncontradoException("Vehiculo no encontrado. ID invalido o inexistente.");
                    }
                } catch (VehiculoNoEncontradoException e) {
                    System.out.println(e.getMessage());
                }
            }
            else 
            {
                String marca = null;    
                while (marca == null || marca.trim().isEmpty()) 
                {
                    System.out.print("Ingrese la marca del vehiculo a eliminar: ");
                    marca = this.sc.nextLine();
                    if (marca.trim().isEmpty()) 
                    {
                        System.out.println("La marca no puede estar vacia. Intente nuevamente.");
                    }
                }

                String modelo = null;    
                while (modelo == null || modelo.trim().isEmpty()) 
                {
                    System.out.print("Ingrese el modelo del vehiculo a eliminar: ");
                    modelo = this.sc.nextLine();
                    if (modelo.trim().isEmpty()) 
                    {
                        System.out.println("La modelo no puede estar vacia. Intente nuevamente.");
                    }
                }

                this.conc.eliminarVehiculo(marca, modelo);
                System.out.println("Vehiculo eliminado correctamente.");

            
                System.out.println("Vehiculo no encontrado.");
                
                return;
            }
        }
        else 
        {
            System.out.println("No hay vehiculos en el stock.");
        }
    }


    public void realizarMantenimiento() 
    {
        if (!this.conc.getColaMantenimiento().isEmpty())
        {
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
                try 
                {
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
                                this.taller.realizarMantenimiento(vehiculo);
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
                        throw new VehiculoNoEncontradoException("Vehiculo no encontrado, ingrese un ID valido.");
                    }
                } 
                catch (VehiculoNoEncontradoException e) 
                {
                    System.out.println(e.getMessage());
                }

            }
        }
        else 
        {
            System.out.println("No hay vehiculos en la cola de mantenimiento.");
        }

    }

    public void buscarVehiculo() 
    {
        if (this.conc.getStock().isEmpty()) {
            System.out.println("No hay vehiculos en el stock.");
            return;
        }

        System.out.println("===========================================");
        System.out.println("¿En base a que desea buscar el vehiculo?");
        System.out.println(" 1. ID del vehiculo");
        System.out.println(" 2. Marca");
        System.out.println(" 3. Modelo");
        System.out.println(" 4. Carroceria");
        System.out.println(" 5. Precio");
        System.out.println("===========================================");

        int opcion = 0;
        boolean valido = false;

        while (!valido) {
            try {
                System.out.print("Seleccione una opcion (1-5): ");
                opcion = this.sc.nextInt();
                this.sc.nextLine();

                if (opcion >= 1 && opcion <= 5) {
                    valido = true;
                } else {
                    System.out.println("Opcion invalida. Ingrese un numero entre 1 y 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un numero.");
                this.sc.nextLine();
            }
        }

        switch (opcion) {
            case 1:
                String idVehiculo = null;
                while (idVehiculo == null || idVehiculo.trim().isEmpty()) {
                    System.out.print("Ingrese la identificacion del vehiculo: ");
                    idVehiculo = this.sc.nextLine();
                    if (idVehiculo.trim().isEmpty()) {
                        System.out.println("La identificacion no puede estar vacia. Intente nuevamente.");
                    }
                }
                try {
                    int indice = conc.buscarVehiculo(idVehiculo);
                    if (indice != -1) {
                        Vehiculo v = conc.getStock().get(indice);
                        System.out.println("\n=== Vehiculo encontrado ===");
                        System.out.println(v);
                    } else {
                        throw new VehiculoNoEncontradoException("Vehiculo no encontrado. ID invalido o inexistente.");
                    }
                } catch (VehiculoNoEncontradoException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 2:
                String marca = null;
                while (marca == null || marca.trim().isEmpty()) {
                    System.out.print("Ingrese la marca del vehiculo: ");
                    marca = this.sc.nextLine();
                    if (marca.trim().isEmpty()) {
                        System.out.println("La marca no puede estar vacia. Intente nuevamente.");
                    }
                }
                List<Vehiculo> resultadosMarca = conc.buscarVehiculoPorMarca(marca);
                if (resultadosMarca.isEmpty()) {
                    System.out.println("No se encontraron vehiculos con la marca indicada.");
                } else {
                    System.out.println("\n=== Vehiculos encontrados ===");
                    for (Vehiculo v : resultadosMarca) {
                        System.out.println(v);
                    }
                }
                break;

            case 3:
                String modelo = null;
                while (modelo == null || modelo.trim().isEmpty()) {
                    System.out.print("Ingrese el modelo del vehiculo: ");
                    modelo = this.sc.nextLine();
                    if (modelo.trim().isEmpty()) {
                        System.out.println("El modelo no puede estar vacio. Intente nuevamente.");
                    }
                }
                List<Vehiculo> resultadosModelo = conc.buscarVehiculoPorModelo(modelo);
                if (resultadosModelo.isEmpty()) {
                    System.out.println("No se encontraron vehiculos con el modelo indicado.");
                } else {
                    System.out.println("\n=== Vehiculos encontrados ===");
                    for (Vehiculo v : resultadosModelo) {
                        System.out.println(v);
                    }
                }
                break;

            case 4:
                String carroceria = null;
                while (carroceria == null || carroceria.trim().isEmpty()) {
                    System.out.print("Ingrese la carroceria del vehiculo: ");
                    carroceria = this.sc.nextLine();
                    if (carroceria.trim().isEmpty()) {
                        System.out.println("La carroceria no puede estar vacia. Intente nuevamente.");
                    }
                }
                List<Vehiculo> resultadosCarroceria = conc.buscarVehiculoPorCarroceria(carroceria);
                if (resultadosCarroceria.isEmpty()) {
                    System.out.println("No se encontraron vehiculos con la carroceria indicada.");
                } else {
                    System.out.println("\n=== Vehiculos encontrados ===");
                    for (Vehiculo v : resultadosCarroceria) {
                        System.out.println(v);
                    }
                }
                break;

                case 5:
                    double precio = -1;
                    boolean precioValido = false;
                    while (!precioValido || precio < 0) {
                        try {
                            System.out.print("Ingrese el precio minimo del vehiculo: ");
                            precio = this.sc.nextDouble();
                            this.sc.nextLine();
                            if (precio >= 0) {
                                precioValido = true;
                            } else {
                                System.out.println("El precio no puede ser negativo.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Debe ingresar un numero valido para el precio.");
                            this.sc.nextLine();
                        }
                    }
                    List<Vehiculo> resultadosPrecio = conc.buscarVehiculoPorPrecio(precio);
                    if (resultadosPrecio.isEmpty()) {
                        System.out.println("No se encontraron vehiculos con precio mayor o igual al indicado.");
                    } else {
                        System.out.println("\n=== Vehiculos encontrados ===");
                        for (Vehiculo v : resultadosPrecio) {
                            System.out.println(v);
                        }
                    }

                break;

            default:
                System.out.println("Opcion invalida.");
        }
    }



}