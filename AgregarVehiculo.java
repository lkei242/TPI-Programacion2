import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.Serializable;

public class AgregarVehiculo implements Serializable
{

    private Concesionaria conc;
    private Scanner sc;
    public AgregarVehiculo(){}
    public AgregarVehiculo(Concesionaria conc, Scanner sc) {
        this.conc = conc;
        this.sc = sc;
    }


    public void agregarVehiculo(int tipo, int tipo2) 
    {
        String idVehiculo;
        boolean esUnico;
        boolean mantenimientoRealizado;
        String carroceria;
        String marca;
        String modelo;
        int anio;
        boolean valido;
        boolean lavado;
        boolean usado;
        int kilometraje;
        if (tipo2 == 1)
        {
            lavado = false;
            usado = true;
            kilometraje = 0;
            valido = false;
            mantenimientoRealizado = false;
            while (!valido || kilometraje < 0) 
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
        }
        else 
        {
            lavado = true;
            usado = false;
            kilometraje = 0;
            mantenimientoRealizado = true;
        }
        System.out.println("Seleccione un color para el auto:");
        SeleccionColorAuto.mostrarColoresDisponibles();
        System.out.print("Ingrese un numero: ");
        Double precio = -1.0;
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

        switch (tipo)
        {
        case 1:
            
                marca = null;    
                while (marca == null || marca.trim().isEmpty()) 
                {
                    System.out.print("Ingrese la marca del automovil: ");
                    marca = this.sc.nextLine();
                    if (marca.trim().isEmpty()) 
                    {
                        System.out.println("La marca no puede estar vacia. Intente nuevamente.");
                    }
                }

                modelo = null;    
                while (modelo == null || modelo.trim().isEmpty()) 
                {
                    System.out.print("Ingrese la modelo del automovil: ");
                    modelo = this.sc.nextLine();
                    if (modelo.trim().isEmpty()) 
                    {
                        System.out.println("La modelo no puede estar vacia. Intente nuevamente.");
                    }
                }

                anio = 0;
                valido = false;

                while (!valido || (anio < 1900 || anio > 2026)) 
                {
                    try {
                        System.out.print("Ingrese el anio del automovil: ");
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
                
                carroceria = null;    
                while (carroceria == null || carroceria.trim().isEmpty()) 
                {
                    System.out.print("Ingrese la carroceria del automovil: ");
                    carroceria = this.sc.nextLine();
                    if (carroceria.trim().isEmpty()) 
                    {
                        System.out.println("La carroceria no puede estar vacia. Intente nuevamente.");
                    }
                }

                esUnico = false;
                idVehiculo = null;

                while (!esUnico) 
                {
                    System.out.print("Ingrese la identificacion del vehiculo: ");
                    idVehiculo = this.sc.nextLine();

                    
                    if (idVehiculo == null || idVehiculo.trim().isEmpty()) {
                        System.out.println("La identificacion no puede estar vacia. Intente nuevamente.");
                        continue; 
                    }
 
                    try {
                        if (!this.conc.vehiculoUnico(idVehiculo)) {
                            esUnico = true;
                        } 
                        else 
                        {
                            throw new VehiculoNoEncontradoException("El ID ingresado ya existe. Intente nuevamente.");
                        }
                    } catch (VehiculoNoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }

                }



                while (precio<0.0) 
                {
                    try 
                    {
                        System.out.print("Ingrese el precio del automovil: ");
                        precio = Double.parseDouble(this.sc.nextLine());
                        
                        if (precio < 0.0) 
                        {
                            System.out.println("El precio no puede ser negativo. Intente nuevamente.");
                        }
                    } 
                    catch (NumberFormatException e) 
                    {
                        System.out.println("Error: debe ingresar un numero valido para el precio.");
                    }
                    
                }

                Automovil auto = new Automovil(marca, modelo, anio, color, carroceria, usado, kilometraje, mantenimientoRealizado, idVehiculo, lavado, precio);
                this.conc.agregarVehiculo(auto);

                System.out.println("Automovil agregado correctamente.");

            
            break;
        case 2:

                marca = null;    
                while (marca == null || marca.trim().isEmpty()) 
                {
                    System.out.print("Ingrese la marca del automovil: ");
                    marca = this.sc.nextLine();
                    if (marca.trim().isEmpty()) 
                    {
                        System.out.println("La marca no puede estar vacia. Intente nuevamente.");
                    }
                }

                modelo = null;    
                while (modelo == null || modelo.trim().isEmpty()) 
                {
                    System.out.print("Ingrese la modelo del automovil: ");
                    modelo = this.sc.nextLine();
                    if (modelo.trim().isEmpty()) 
                    {
                        System.out.println("La modelo no puede estar vacia. Intente nuevamente.");
                    }
                }


                anio = 0;
                valido = false;

                while (!valido || (anio < 1900 || anio > 2026)) 
                {
                    try {
                        System.out.print("Ingrese el anio de la camioneta: ");
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

                carroceria = null;    
                while (carroceria == null || carroceria.trim().isEmpty()) 
                {
                    System.out.print("Ingrese la carroceria de la camioneta: ");
                    carroceria = this.sc.nextLine();
                    if (carroceria.trim().isEmpty()) 
                    {
                        System.out.println("La carroceria no puede estar vacia. Intente nuevamente.");
                    }
                }

                esUnico = false;
                idVehiculo = null;

                while (!esUnico) 
                {
                    System.out.print("Ingrese la identificacion del vehiculo: ");
                    idVehiculo = this.sc.nextLine();

                    
                    if (idVehiculo == null || idVehiculo.trim().isEmpty()) {
                        System.out.println("La identificacion no puede estar vacia. Intente nuevamente.");
                        continue;
                    }

                    
                    try {
                        if (!this.conc.vehiculoUnico(idVehiculo)) {
                            esUnico = true; 
                        } else {
                            throw new VehiculoNoEncontradoException("El ID ingresado ya existe. Intente nuevamente.");
                        }
                    } catch (VehiculoNoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }

                }

                while (precio<0.0) 
                {
                    try 
                    {
                        System.out.print("Ingrese el precio de la camioneta: ");
                        precio = Double.parseDouble(this.sc.nextLine());
                        
                        if (precio < 0.0) 
                        {
                            System.out.println("El precio no puede ser negativo. Intente nuevamente.");
                        }
                    } 
                    catch (NumberFormatException e) 
                    {
                        System.out.println("Error: debe ingresar un numero valido para el precio.");
                    }
                    
                }

                Camioneta camioneta = new Camioneta(marca, modelo, anio, color, carroceria, usado, kilometraje, mantenimientoRealizado, idVehiculo, lavado, precio);
                this.conc.agregarVehiculo(camioneta);

                System.out.println("Camioneta agregado correctamente.");

            break;
        case 3:
            
                marca = null;    
                while (marca == null || marca.trim().isEmpty()) 
                {
                    System.out.print("Ingrese la marca del automovil: ");
                    marca = this.sc.nextLine();
                    if (marca.trim().isEmpty()) 
                    {
                        System.out.println("La marca no puede estar vacia. Intente nuevamente.");
                    }
                }

                modelo = null;    
                while (modelo == null || modelo.trim().isEmpty()) 
                {
                    System.out.print("Ingrese la modelo de la motocicleta: ");
                    modelo = this.sc.nextLine();
                    if (modelo.trim().isEmpty()) 
                    {
                        System.out.println("La modelo no puede estar vacia. Intente nuevamente.");
                    }
                }

                anio = 0;
                valido = false;

                while (!valido || (anio < 1900 || anio > 2026)) 
                {
                    try {
                        System.out.print("Ingrese el anio de la motocicleta: ");
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

                String chasis = null;    
                while (chasis == null || chasis.trim().isEmpty()) 
                {
                    System.out.print("Ingrese el tipo de motocicleta Naked, Sport, Scooter, Cruiser, Adventure, Enduro, etc.): ");
                    chasis = this.sc.nextLine();
                    if (chasis.trim().isEmpty()) 
                    {
                        System.out.println("El chasis no puede estar vacio. Intente nuevamente.");
                    }
                }

                esUnico = false;
                idVehiculo = null;

                while (!esUnico) 
                {
                    System.out.print("Ingrese la identificacion del vehiculo: ");
                    idVehiculo = this.sc.nextLine();
                    
                    if (idVehiculo == null || idVehiculo.trim().isEmpty()) {
                        System.out.println("La identificacion no puede estar vacia. Intente nuevamente.");
                        continue;
                    }
                    
                    try {
                        if (!this.conc.vehiculoUnico(idVehiculo)) {
                            esUnico = true;
                        } else {
                            throw new VehiculoNoEncontradoException("El ID ingresado ya existe. Intente nuevamente.");
                        }
                    } catch (VehiculoNoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                }

                while (precio<0.0) 
                {
                    try 
                    {
                        System.out.print("Ingrese el precio de la motocicleta: ");
                        precio = Double.parseDouble(this.sc.nextLine());
                        
                        if (precio < 0.0) 
                        {
                            System.out.println("El precio no puede ser negativo. Intente nuevamente.");
                        }
                    } 
                    catch (NumberFormatException e) 
                    {
                        System.out.println("Error: debe ingresar un numero valido para el precio.");
                    }
                    
                }

                Motocicleta moto = new Motocicleta(marca, modelo, anio, color, chasis, usado, kilometraje, mantenimientoRealizado, idVehiculo, lavado, precio);
                this.conc.agregarVehiculo(moto);

                System.out.println("Motocicleta agregado correctamente.");

            
            break;

        default:
            System.out.println("Tipo de vehiculo invalido.");
        
        }

    }
}

