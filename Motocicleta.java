import java.io.Serializable;
    public class Motocicleta extends Vehiculo implements Serializable
    {
        public Motocicleta() {}
        public Motocicleta(String marca, String modelo, int anio, String color, String carroceria, boolean esUsado, int kilometraje, boolean mantenimientoRealizado, String idVehiculo, boolean lavado, Double precio)
        {
            super(marca, modelo, anio, color, carroceria, esUsado, kilometraje, mantenimientoRealizado, idVehiculo, lavado, precio);
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
            System.out.println("Precio: $" + String.format("%.0f",super.getPrecio()));

            System.out.println("-------------------------");
        }
        public String toString() 
        {
            return  "=== Automovil ===\n" +
                    "idVehiculo: " + super.getIdVehiculo() + "\n" +
                    "Marca: " + super.getMarca() + "\n" +
                    "Modelo: " + super.getModelo() + "\n" +
                    "Anio: " + super.getAnio() + "\n" +
                    "Color: " + super.getColor() + "\n" +
                    "Carroceria: " + super.getCarroceria() + "\n" +
                    "Kilometraje: " + super.getKilometraje() + "\n" +
                    "Mantenimiento: " + (super.getMantenimiento() ? "Ha sido mantenido" : "Pendiente") + "\n" +
                    "Usado: " + (super.getEsUsado() ? "Si" : "No") + "\n" +
                    "Lavado: " + (super.getLavado() ? "Si" : "No") + "\n" +
                    "Precio: $" + String.format("%.0f", super.getPrecio()) + "\n" +
                    "-------------------------";
        }
    }

