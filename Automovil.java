public class Automovil extends Vehiculo implements Usado 
{

    private boolean mantenimientoRealizado = false;

    //Accesores
    public boolean getMantenimiento() {
        return mantenimientoRealizado;
    }
    public void setMantenimiento(boolean realizado) {
        this.mantenimientoRealizado = realizado;
    }

    //Constructor
    public Automovil(String marca, String modelo, int anio, String color, String carroceria, boolean esUsado, int kilometraje, boolean mantenimientoRealizado)
    {
        super(marca, modelo, anio, color, carroceria, esUsado, kilometraje, mantenimientoRealizado);
    }


    public void mostrarInfo() 
    {
        System.out.println("=== Automovil ===");
        System.out.println("Marca: " + super.getMarca());
        System.out.println("Modelo: " + super.getModelo());
        System.out.println("Anio: " + super.getAnio());
        System.out.println("Color: " + super.getColor());
        System.out.println("Carroceria: " + super.getCarroceria());

        if (super.getEsUsado()) 
        {
            System.out.println("Usado - Mantenimiento: " + (mantenimientoRealizado ? "OK" : "Pendiente"));
        } 
        else 
        {
            System.out.println("Nuevo 0 KM");
        }

        System.out.println("-------------------------");
    }
}

