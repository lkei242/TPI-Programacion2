public class Taller
{

    public void realizarMantenimiento(Vehiculo vehiculoUsado) 
    {
        System.out.println("Realizando mantenimiento tecnico...");
        vehiculoUsado.setMantenimiento(true);
        System.out.println("Se procedera con el lavado del auto.");

        LavaderoGenerico<Vehiculo> lavadero = new LavaderoGenerico<>();
        lavadero.lavarVehiculo(vehiculoUsado);
    }
}
