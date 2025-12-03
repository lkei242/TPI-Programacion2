import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.Serializable;
public class ModificarVehiculo 
{
    private Concesionaria conc;
    private Scanner sc;

    public ModificarVehiculo() {}
    public ModificarVehiculo(Concesionaria conc, Scanner sc) 
    {
        this.conc = conc;
        this.sc = sc;
    }

   public void modificarVehiculo()
    {
        
        if (!this.conc.getStock().isEmpty())
        {
            System.out.println("===========================================");
            System.out.println(" MODIFICAR VEHICULO POR ID");
            System.out.println("===========================================");

            System.out.print("Ingrese la identificacion del vehiculo (ID) a modificar: ");
            boolean existe = false;
            String idVehiculo = null;


            while (!existe) 
            {
                System.out.print("Ingrese la identificacion del vehiculo: ");
                idVehiculo = this.sc.nextLine();

                
                if (idVehiculo == null || idVehiculo.trim().isEmpty()) {
                    System.out.println("La identificacion no puede estar vacia. Intente nuevamente.");
                    continue;
                }

                
                try {
                    if (this.conc.vehiculoUnico(idVehiculo)) {
                        existe = true;
                    } else {
                        throw new VehiculoNoEncontradoException("El ID ingresado no existe: " + idVehiculo);
                    }
                } catch (VehiculoNoEncontradoException e) {
                    System.out.println(e.getMessage());
                }
            }

            int indice = this.conc.buscarVehiculo(idVehiculo);

            if (indice != -1)
            {
                Vehiculo vehiculo = this.conc.getStock().get(indice);
                System.out.println("\n=== Vehiculo Encontrado (ID: " + vehiculo.getIdVehiculo() + ") ===");
                System.out.println("\n===========================================\n");
                    

                    boolean valido = true;
                    while (valido)
                    {
                        System.out.println("\nSeleccione el atributo a modificar:");
                        System.out.println(" 1. Marca");
                        System.out.println(" 2. Modelo");
                        System.out.println(" 3. Anio");
                        System.out.println(" 4. Color");
                        System.out.println(" 5. Carroceria");
                        System.out.println(" 6. Es Usado");
                        System.out.println(" 7. Mantenimiento");
                        System.out.println(" 8. Kilometraje");
                        System.out.println(" 9. Lavado");
                        System.out.println(" 10. Precio");
                        System.out.println(" 11. Salir");
                    
                        int opcion = 0;
                        valido = false;

                        while (!valido)
                        {
                            try
                            {
                                System.out.print("Opcion: ");
                                opcion = this.sc.nextInt();
                                this.sc.nextLine();

                                if (opcion >= 1 && opcion <= 11)
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
                                        System.out.print("Ingrese el nuevo anio del vehiculo (1900-2026): ");
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
                                System.out.println("Anio modificado a: " + vehiculo.getAnio());
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
                                            System.out.println("Numero fuera de rango. Intente de nuevo.");
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Entrada invalida. Debe ser un numero entero.");
                                    }
                                }
                                SeleccionColorAuto.ColorAuto colorEnum = SeleccionColorAuto.obtenerColorPorIndice(colorAuto);
                                String nuevoColor = colorEnum.toString();
                                vehiculo.setColor(nuevoColor);
                                System.out.println("Color modificado a: " + vehiculo.getColor());
                                break;
                            case 5: 
                                if (vehiculo instanceof Automovil || vehiculo instanceof Camioneta)
                                {
                                    System.out.print("Ingrese la carroceria del vehiculo: ");
                                    
                                }
                                else if (vehiculo instanceof Motocicleta)
                                {
                                    System.out.print("Ingrese el tipo de motocicleta: ");
                                }
                                String nuevaCarroceria = this.sc.nextLine();
                                vehiculo.setCarroceria(nuevaCarroceria);
                                System.out.println("Carroceria modificada a: " + vehiculo.getCarroceria());
                                break;
                            case 6:
                                boolean usado = false;
                                boolean entradaValida = false;

                                while (!entradaValida) 
                                {
                                    System.out.print("Ingrese si el vehiculo es usado: usado (true) o no usado (false): ");

                                    String entrada = this.sc.nextLine().trim().toLowerCase();
                                    
                                    if (entrada.equalsIgnoreCase("true") || entrada.equalsIgnoreCase("false")) 
                                    {
                                        usado = Boolean.parseBoolean(entrada);
                                        entradaValida = true;
                                    } 
                                    else 
                                    {
                                        System.out.println("Entrada invalida. Debe ingresar 'true' o 'false'. Intente nuevamente.");
                                    }
                                }

                                vehiculo.setEsUsado(usado);
                                System.out.println("Uso modificado a: " + vehiculo.getEsUsado());
                                break;
                            case 7:
                                boolean mantenido = false;
                                boolean validar = false;

                                while (!validar) 
                                {
                                    System.out.print("¿El vehiculo ha sido mantenido? true o false:");

                                    String entrada = this.sc.nextLine().trim().toLowerCase();
                                    
                                    if (entrada.equalsIgnoreCase("true") || entrada.equalsIgnoreCase("false")) 
                                    {
                                        mantenido = Boolean.parseBoolean(entrada);
                                        validar = true;
                                    } 
                                    else 
                                    {
                                        System.out.println("Entrada invalida. Debe ingresar 'true' o 'false'. Intente nuevamente.");
                                    }
                                }
                                vehiculo.setMantenimiento(mantenido);
                                System.out.println("Mantenimiento modificado a: " + vehiculo.getMantenimiento());
                                break;
                            case 8:
                                int nuevoKilometraje = -1;
                                boolean kmValido = false;
                                while (!kmValido || nuevoKilometraje < 0)
                                {
                                    try 
                                    {
                                        System.out.print("Ingrese el nuevo kilometraje del vehiculo: ");
                                        nuevoKilometraje = this.sc.nextInt();
                                        this.sc.nextLine();
                                        if (nuevoKilometraje >= 0) {
                                            kmValido = true;
                                        }
                                    }
                                    catch (InputMismatchException e)
                                    {
                                        System.out.println("Error: debe ingresar un numero entero.");
                                        this.sc.nextLine();
                                    }
                                }
                                vehiculo.setKilometraje(nuevoKilometraje);
                                System.out.println("Kilometraje modificado a: " + vehiculo.getKilometraje());
                                break;
                            case 9:
                                boolean lavado = false;
                                boolean validar2 = false;

                                while (!validar2) 
                                {
                                    System.out.print("Ingrese si esta lavado: lavado (true) o no lavado (false):");

                                    String entrada = this.sc.nextLine().trim().toLowerCase();
                                    
                                    if (entrada.equalsIgnoreCase("true") || entrada.equalsIgnoreCase("false")) 
                                    {
                                        lavado = Boolean.parseBoolean(entrada);
                                        validar2 = true;
                                    } 
                                    else 
                                    {
                                        System.out.println("Entrada invalida. Debe ingresar 'true' o 'false'. Intente nuevamente.");
                                    }
                                }
                                vehiculo.setLavado(lavado);
                                System.out.println("Lavado modificado a: " + vehiculo.getLavado());
                                break;
                            case 10:
                                System.out.print("Ingrese el nuevo precio del vehiculo: ");
                                double nuevoPrecio = -1;
                                boolean precioValido = false;
                                while (!precioValido || nuevoPrecio < 0)
                                {
                                    try 
                                    {
                                        System.out.print("Ingrese el nuevo precio del vehiculo: ");
                                        nuevoPrecio = this.sc.nextDouble();
                                        this.sc.nextLine();
                                        if (nuevoPrecio >= 0) {
                                            precioValido = true;
                                        }
                                    }
                                    catch (InputMismatchException e)
                                    {
                                        System.out.println("Error: debe ingresar un numero valido para el precio.");
                                        this.sc.nextLine();
                                    }
                                }
                                vehiculo.setPrecio(nuevoPrecio);
                                System.out.println("Precio modificado a: " + String.format("%.0f", vehiculo.getPrecio()));
                                break;
                            case 11:
                                System.out.print("Saliendo...");
                                valido = false;
                                break;
                            default:
                                System.out.println("Modificacion cancelada o invalida.");
                        }
                    }
            
                
            }
            else
            {
                System.out.println("Vehiculo no encontrado. Ingrese un ID valido.");
            }
        }
        else 
        {
            System.out.println("No existen vehiculos cargados.");
        }
    }

}