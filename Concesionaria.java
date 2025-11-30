import java.util.ArrayList;
import java.util.List;

public class Concesionaria 
{

    private List<Vehiculo> stock = new ArrayList<>();


    //Accedores = HACER CRUD
    public void agregarVehiculo(Vehiculo v) 
    {
        stock.add(v);
    }

    public void mostrarStock() 
    {
        System.out.println("\n========= STOCK DE VEHICULOS =========");
        for (Vehiculo v : stock) {
            v.mostrarInfo();
        }
    }
}

