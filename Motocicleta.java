public class Motocicleta extends Vehiculo
{



    public Motocicleta(String marca, String modelo, int anio, String color, String carroceria, boolean esUsado, int kilometraje, boolean mantenimientoRealizado, String idVehiculo, boolean lavado)
    {
        super(marca, modelo, anio, color, carroceria, esUsado, kilometraje, mantenimientoRealizado, idVehiculo, lavado);
    }

    public void mostrarInfo() {
        System.out.println("=== Motocicleta ===");
        System.out.println("idVehiculo: " + super.getIdVehiculo());
        System.out.println("Marca: " + super.getMarca());
        System.out.println("Modelo: " + super.getModelo());
        System.out.println("Anio: " + super.getAnio());
        System.out.println("Color: " + super.getColor());
        System.out.println("Carroceria: " + super.getCarroceria());
        System.out.println("Kilometraje: " + super.getKilometraje());
        System.out.println("Mantenimiento: " + (super.getMantenimiento() ? "Ha sido mantenido" : "Pendiente"));
        System.out.println("Usado: " + (super.getEsUsado() ? "Si" : "No"));
        System.out.println("Lavado: " + (super.getLavado() ? "Si" : "No"));

        System.out.println("-------------------------");
    }
}

