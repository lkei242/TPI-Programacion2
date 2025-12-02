public abstract class Vehiculo implements Usado
{
    private String idVehiculo;
    private String marca;
    private String modelo;
    private int anio;
    private String color;
    private String carroceria; 
    private boolean esUsado;
    private boolean mantenimientoRealizado;
    private int kilometraje;
    private boolean lavado;
    

     //Accesores
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public int getAnio() {
        return anio;
    }
    public void setAnio(int anio) {
        this.anio = anio;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getCarroceria() {
        return carroceria;
    }
    public void setCarroceria(String carroceria) {
        this.carroceria = carroceria;
    }
    public boolean getEsUsado() {
        return esUsado;
    }
    public void setEsUsado(boolean esUsado) {
        this.esUsado = esUsado;
    }
    public int getKilometraje() {
        return kilometraje;
    }
    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }
    public boolean getMantenimiento() {
        return mantenimientoRealizado;
    }
    public void setMantenimiento(boolean realizado) {
        this.mantenimientoRealizado = realizado;
    }
    public String getIdVehiculo() {
        return idVehiculo;
    }
    public void setIdVehiculo(String idVehiculo) {
        this.idVehiculo = idVehiculo;
    }
    public boolean getLavado()
    {
        return this.lavado;
    }
    public void setLavado(boolean lavado)
    {
        this.lavado = lavado;
    }

     //Constructor
    public Vehiculo(String marca, String modelo, int anio, String color, String carroceria, boolean esUsado, int kilometraje, boolean mantenimientoRealizado, String idVehiculo, boolean lavado) 
    {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.color = color;
        this.carroceria = carroceria;
        this.esUsado = esUsado;
        this.kilometraje = kilometraje;
        this.mantenimientoRealizado = mantenimientoRealizado;
        this.idVehiculo = idVehiculo;
        this.lavado = lavado;
    }

 
    //Metodos
    public abstract void mostrarInfo(); //Cambiarlo a un toString() 
}
