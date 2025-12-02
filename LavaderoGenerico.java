// Clase genérica Lavadero que puede lavar cualquier tipo de Vehiculo o subclase de Vehiculo
public class LavaderoGenerico<T extends Vehiculo> {

    // Método para "lavar" el vehículo genérico
    public void lavarVehiculo(T vehiculoUsado) {
        System.out.println("Lavando y detallando el vehículo para exhibición...");
        vehiculoUsado.setLavado(true);
    }
}


