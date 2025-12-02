import java.util.ArrayList;
import java.util.List;

public class Concesionaria 
{

    private List<Vehiculo> stock = new ArrayList<>();
    private List<Vehiculo> cola_mantenimiento = new ArrayList<>();


    public List<Vehiculo> getStock() 
    {
        return stock;
    }
    public void setStock(List<Vehiculo> stock) 
    {
        this.stock = stock;
    }
    public List<Vehiculo> getColaMantenimiento() 
    {
        return cola_mantenimiento;
    }
    public void setColaMantenimiento(List<Vehiculo> cola_mantenimiento) 
    {
        this.cola_mantenimiento = cola_mantenimiento;
    }

    //Accedores = HACER CRUD
    public void agregarVehiculo(Vehiculo v) 
    {
        stock.add(v);
        agregarVehiculoMantenimiento(v);
    }

    public void agregarVehiculoMantenimiento(Vehiculo v) 
    {
        cola_mantenimiento.add(v);
    }

    public void borrarVehiculoMantenimiento(Vehiculo v) 
    {
        cola_mantenimiento.remove(v);
    }

    //Se elimina por indice
    //por lo tanto se debe buscar primero el indice del vehiculo
/**/
    //Uso sobrecarga para usar modelo y marca
    public int buscarVehiculo(String idVehiculo) 
    {
        for (int i = 0; i < stock.size(); i++) 
        {
            if (this.stock.get(i).getIdVehiculo().equalsIgnoreCase(idVehiculo)) 
            {
                return i;
            }
        }
        return -1; // No encontrado
    }

    public void eliminarVehiculo(int indice) 
    {
        //Elimina solo una coincidencia
        if (indice >= 0 && indice < stock.size() && cola_mantenimiento.contains(stock.get(indice))) 
        {
            this.cola_mantenimiento.remove(this.stock.get(indice));
            this.stock.remove(indice);
            System.out.println("Vehiculo eliminado del stock.");

        } 
        else if (indice >= 0 && indice < stock.size() && !cola_mantenimiento.contains(stock.get(indice))) 
        {
            this.stock.remove(indice);
            System.out.println("Vehiculo eliminado del stock.");
        }
        else 
        {
            System.out.println("indice invalido, no se pudo eliminar el vehiculo");
        }
    }
    public void eliminarVehiculo(String marca, String modelo) 
    {
        //Elimina todas las coincidencias
        if (stock.removeIf(v -> v.getMarca().equalsIgnoreCase(marca) && v.getModelo().equalsIgnoreCase(modelo))) 
        {
            System.out.println("Se eliminaron todos los vehiculos, que cumplen con la marca y modelo especificados, del stock.");
        } 
        else 
        {
            System.out.println("No se encontro un vehiculo con la marca y modelo especificados.");
        }

    }

    
    public void mostrarStock() 
    {
        
        System.out.println("\n========= STOCK DE VEHICULOS =========");
        for (Vehiculo v : stock) 
        {
            
            v.mostrarInfo();
        }
        if (this.stock.isEmpty()) 
        {
            System.out.println();
            System.out.println("No hay vehiculos en el stock.");
            System.out.println();
        }
    }

    public void mostrarColaMantenimiento() 
    {
        
        System.out.println("\n========= COLA DE MANTENIMIENTO =========");
        for (Vehiculo v : cola_mantenimiento) 
        {
            v.mostrarInfo();
            
        }
        //poner el isEmpty en otro metodo para controlar que está vacio en Menu
        if (this.cola_mantenimiento.isEmpty()) 
        {
            System.out.println();
            System.out.println("No hay vehiculos en el stock.");
            System.out.println();
        }
    }
    public boolean vehiculoUnico (String idVehiculo)
    {
        int existe = buscarVehiculo(idVehiculo);
        if (existe >= 0)
        {
            return true;
        }
        return false;
    }
}

