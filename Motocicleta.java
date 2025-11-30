public class Motocicleta extends Vehiculo implements Usado {

    private boolean mantenimientoRealizado = false;

    public Motocicleta(String marca, String modelo, int anio, String color, String tipo, boolean esUsado) {
        super(marca, modelo, anio, color, tipo, esUsado);
    }

    public boolean getMantenimiento() {
        return mantenimientoRealizado;
    }

    public void setMantenimiento(boolean realizado) {
        this.mantenimientoRealizado = realizado;
    }

    public void mostrarInfo() {
        System.out.println("=== Motocicleta ===");
        System.out.println("Marca: " + super.getMarca());
        System.out.println("Modelo: " + super.getModelo());
        System.out.println("Anio: " + super.getAnio());
        System.out.println("Color: " + super.getColor());
        System.out.println("Tipo: " + super.getCarroceria());
        if (super.getEsUsado()) 
        {
            System.out.println("Usada - Mantenimiento: " + (mantenimientoRealizado ? "OK" : "Pendiente"));
        } 
        else 
        {
            System.out.println("Nueva 0 KM");
        }

        System.out.println("-------------------------");
    }
}

