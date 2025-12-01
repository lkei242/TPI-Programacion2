public class Camioneta extends Vehiculo implements Usado 
{

    private boolean mantenimientoRealizado = false;

    //Accesores
    public boolean getMantenimiento()
    {
        return mantenimientoRealizado;
    }

    public void setMantenimiento(boolean realizado) 
    {
        this.mantenimientoRealizado = realizado;
    }

    //Constructor
    public Camioneta(String marca, String modelo, int anio, String color, String carroceria, boolean esUsado, int kilometraje,  boolean mantenimientoRealizado)
    {
        super(marca, modelo, anio, color, carroceria, esUsado, kilometraje, mantenimientoRealizado);
    }
    
    //Metodos
    public void mostrarInfo() 
    {
        System.out.println("=== Camioneta ===");
        System.out.println("Marca: " + super.getMarca());
        System.out.println("Modelo: " + super.getModelo());
        System.out.println("Anio: " + super.getAnio());
        System.out.println("Color: " + super.getColor());
        System.out.println("Carroceria: " + super.getCarroceria());
        System.out.println("Kilometraje: " + super.getKilometraje());
        System.out.println("Mantenimiento: " + (super.getMantenimiento() ? "Ha sido mantenido" : "Pendiente"));

        if (super.getEsUsado()) 
        {
            System.out.println("Usada - Mantenimiento: " + (mantenimientoRealizado ? "Ha sido mantenido" : "Pendiente"));
        } 


        System.out.println("-------------------------");
    }
}
