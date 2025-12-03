import java.io.Serializable;
public class LavaderoGenerico<T extends Vehiculo>  implements Serializable
{    
    public LavaderoGenerico(){}
    public void lavarVehiculo(T vehiculoUsado) 
    {
        System.out.println("Lavando y detallando el vehiculo para exhibicion...");
        vehiculoUsado.setLavado(true);
    }
}


