import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Concesionaria {

    private List<Vehiculo> stock = new ArrayList<>();
    private List<Vehiculo> cola_mantenimiento = new ArrayList<>();

    private final PersistenciaArch persistence;

    // Constructor default
    public Concesionaria() {
        this.persistence = new PersistenciaArch("vehiculos.json");
    }

    // Constructor alternativo
    public Concesionaria(String filepath) {
        this.persistence = new PersistenciaArch(filepath);
    }

    // ==========================
    //       GETTERS / SETTERS
    // ==========================

    public List<Vehiculo> getStock() {
        return stock;
    }

    public void setStock(List<Vehiculo> stock) {
        this.stock = stock;
    }

    public List<Vehiculo> getColaMantenimiento() {
        return cola_mantenimiento;
    }

    public void setColaMantenimiento(List<Vehiculo> cola_mantenimiento) {
        this.cola_mantenimiento = cola_mantenimiento;
    }

    // ==========================
    //       CRUD PRINCIPAL
    // ==========================

    public void agregarVehiculo(Vehiculo v) {
        stock.add(v);

        // Si es usado va a mantenimiento automáticamente
        if (v.getEsUsado()) {
            agregarVehiculoMantenimiento(v);
        }
    }

    public void agregarVehiculoMantenimiento(Vehiculo v) {
        if (!cola_mantenimiento.contains(v)) {
            cola_mantenimiento.add(v);
        }
    }

    public void borrarVehiculoMantenimiento(Vehiculo v) {
        cola_mantenimiento.remove(v);
    }

    // ==========================
    //       BÚSQUEDAS
    // ==========================

    // Busca por ID
    public int buscarVehiculo(String idVehiculo) {
        for (int i = 0; i < stock.size(); i++) {
            if (this.stock.get(i).getIdVehiculo().equalsIgnoreCase(idVehiculo)) {
                return i;
            }
        }
        return -1;
    }

    public boolean vehiculoUnico(String idVehiculo) {
        return buscarVehiculo(idVehiculo) >= 0;
    }

    // ==========================
    //       ELIMINAR
    // ==========================

    public void eliminarVehiculo(int indice) {
        if (indice >= 0 && indice < stock.size()) {

            Vehiculo v = this.stock.get(indice);

            // eliminar también si estaba en la cola
            cola_mantenimiento.remove(v);

            stock.remove(indice);

            System.out.println("Vehiculo eliminado del stock.");

        } else {
            System.out.println("Índice inválido, no se pudo eliminar el vehículo.");
        }
    }

    public void eliminarVehiculo(String marca, String modelo) {

        List<Vehiculo> eliminados = new ArrayList<>();

        stock.removeIf(v -> 
            {boolean encontrado = v.getMarca().equalsIgnoreCase(marca) && v.getModelo().equalsIgnoreCase(modelo);
            if (encontrado) eliminados.add(v);
            return encontrado;
        });

        if (!eliminados.isEmpty()) {
            cola_mantenimiento.removeAll(eliminados);
            System.out.println("Se eliminaron todos los vehículos que coincidían con la marca y modelo.");
        } else {
            System.out.println("No se encontró ningún vehículo con esa marca y modelo.");
        }
    }

    // ==========================
    //       MOSTRAR DATOS
    // ==========================

    public void mostrarStock() {
        System.out.println("\n========= STOCK DE VEHICULOS =========");

        if (stock.isEmpty()) {
            System.out.println("No hay vehiculos en stock.\n");
            return;
        }

        for (Vehiculo v : stock) {
            v.mostrarInfo();
        }
    }

    public void mostrarColaMantenimiento() {
        System.out.println("\n========= COLA DE MANTENIMIENTO =========");

        if (cola_mantenimiento.isEmpty()) {
            System.out.println("No hay vehiculos en mantenimiento.\n");
            return;
        }

        for (Vehiculo v : cola_mantenimiento) {
            v.mostrarInfo();
        }
    }

    // ==========================
    //       PERSISTENCIA JSON
    // ==========================

    public void saveToFile() {
        try {
            persistence.saveAll(stock, cola_mantenimiento);
            System.out.println("Datos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar datos: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        try {
            PersistenciaArch.LoadedData data = persistence.loadAll();
            this.stock = data.stock;
            this.cola_mantenimiento = data.cola;
            System.out.println("Datos cargados correctamente.");
        } catch (IOException e) {
            System.out.println("No se pudo cargar datos: " + e.getMessage());
        }
    }
}
