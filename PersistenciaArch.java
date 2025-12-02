import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *persistencia simple en JSON (un objeto JSON por línea).
 *no usa librerías externas para mantener compatibilidad.
 */
//
public class PersistenciaArch {

    private final File file;

    public PersistenciaArch(String filepath) {
        this.file = new File(filepath);
    }

    public void saveAll(List<Vehiculo> stock, List<Vehiculo> cola) throws IOException {
        //crea carpeta si no existe
        File parent = file.getParentFile();
        if (parent != null) parent.mkdirs();

        //escribe un objeto JSON por línea
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
            //guardar stock
            for (Vehiculo v : stock) {
                bw.write(vehiculoToJsonLine(v, true, cola.contains(v)));
                bw.newLine();
            }
            //si algún vehículo en cola no está en stock (caso improbable), también se guarda
            for (Vehiculo v : cola) {
                if (!stock.contains(v)) {
                    bw.write(vehiculoToJsonLine(v, true, true));
                    bw.newLine();
                }
            }
        }
    }

    public LoadedData loadAll() throws IOException {
        List<Vehiculo> stock = new ArrayList<>();
        List<Vehiculo> cola = new ArrayList<>();

        if (!file.exists()) {
            return new LoadedData(stock, cola);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                // linea JSON simple
                String tipo = getStringValue(line, "tipo");
                String id = getStringValue(line, "id");
                String marca = getStringValue(line, "marca");
                String modelo = getStringValue(line, "modelo");
                int anio = getIntValue(line, "anio", 0);
                String color = getStringValue(line, "color");
                String carroceria = getStringValue(line, "carroceria");
                boolean esUsado = getBooleanValue(line, "esUsado", true);
                int kilometraje = getIntValue(line, "kilometraje", 0);
                boolean mantenimiento = getBooleanValue(line, "mantenimiento", false);
                boolean lavado = getBooleanValue(line, "lavado", false);
                boolean enCola = getBooleanValue(line, "enCola", false);

                Vehiculo v = null;
                if ("AUTO".equalsIgnoreCase(tipo)) {
                    v = new Automovil(marca, modelo, anio, color, carroceria, esUsado, kilometraje, mantenimiento, id, lavado);
                } else if ("CAMIONETA".equalsIgnoreCase(tipo)) {
                    v = new Camioneta(marca, modelo, anio, color, carroceria, esUsado, kilometraje, maintenanceOrDefault(mantenimiento), id, lavado);
                } else if ("MOTO".equalsIgnoreCase(tipo) || "MOTOCICLETA".equalsIgnoreCase(tipo)) {
                    v = new Motocicleta(marca, modelo, anio, color, carroceria, esUsado, kilometraje, maintenanceOrDefault(mantenimiento), id, lavado);
                } else {
                    continue; //desconociido
                }

                //ajustes de estado
                v.setMantenimiento(mantenimiento);
                v.setLavado(lavado);
                v.setKilometraje(kilometraje);
                v.setEsUsado(esUsado);

                stock.add(v);
                if (enCola) {
                    cola.add(v);
                }
            }
        }

        return new LoadedData(stock, cola);
    }

    private static boolean maintenanceOrDefault(boolean val) {
        return val;
    }

    private String vehiculoToJsonLine(Vehiculo v, boolean inStock, boolean enCola) {
        String tipo = "AUTO";
        if (v instanceof Camioneta) tipo = "CAMIONETA";
        else if (v instanceof Motocicleta) tipo = "MOTO";
        else if (v instanceof Automovil) tipo = "AUTO";

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"tipo\":").append(quote(tipo)).append(",");
        sb.append("\"id\":").append(quote(v.getIdVehiculo())).append(",");
        sb.append("\"marca\":").append(quote(v.getMarca())).append(",");
        sb.append("\"modelo\":").append(quote(v.getModelo())).append(",");
        sb.append("\"anio\":").append(v.getAnio()).append(",");
        sb.append("\"color\":").append(quote(v.getColor())).append(",");
        sb.append("\"carroceria\":").append(quote(v.getCarroceria())).append(",");
        sb.append("\"esUsado\":").append(v.getEsUsado()).append(",");
        sb.append("\"kilometraje\":").append(v.getKilometraje()).append(",");
        sb.append("\"mantenimiento\":").append(v.getMantenimiento()).append(",");
        sb.append("\"lavado\":").append(v.getLavado()).append(",");
        sb.append("\"enCola\":").append(enCola);
        sb.append("}");
        return sb.toString();
    }

    //helpers de serialización
    private static String quote(String s) {
        if (s == null) return "\"\"";
        //escape simple de comillas y backslash
        String escaped = s.replace("\\", "\\\\").replace("\"", "\\\"");
        return "\"" + escaped + "\"";
    }

    private static String getStringValue(String jsonLine, String key) {
        String pattern = "\"" + key + "\":";
        int idx = jsonLine.indexOf(pattern);
        if (idx == -1) return "";
        idx += pattern.length();

        while (idx < jsonLine.length() && Character.isWhitespace(jsonLine.charAt(idx))) idx++;
        if (idx >= jsonLine.length()) return "";
        if (jsonLine.charAt(idx) == '"') {
            idx++;
            StringBuilder sb = new StringBuilder();
            while (idx < jsonLine.length()) {
                char c = jsonLine.charAt(idx++);
                if (c == '\\' && idx < jsonLine.length()) { // escape
                    char next = jsonLine.charAt(idx++);
                    sb.append(next);
                    continue;
                }
                if (c == '"') break;
                sb.append(c);
            }
            return sb.toString();
        } else {
            StringBuilder sb = new StringBuilder();
            while (idx < jsonLine.length()) {
                char c = jsonLine.charAt(idx);
                if (c == ',' || c == '}' || Character.isWhitespace(c)) break;
                sb.append(c);
                idx++;
            }
            return sb.toString();
        }
    }

    private static int getIntValue(String jsonLine, String key, int defaultVal) {
        String s = getStringValue(jsonLine, key);
        if (s == null || s.isEmpty()) {

            int idx = jsonLine.indexOf("\"" + key + "\":");
            if (idx == -1) return defaultVal;
            idx = jsonLine.indexOf(":", idx) + 1;
            if (idx <= 0) return defaultVal;
            StringBuilder sb = new StringBuilder();
            while (idx < jsonLine.length()) {
                char c = jsonLine.charAt(idx);
                if (c == ',' || c == '}') break;
                if (!Character.isWhitespace(c)) sb.append(c);
                idx++;
            }
            try {
                return Integer.parseInt(sb.toString());
            } catch (Exception ex) {
                return defaultVal;
            }
        }
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return defaultVal;
        }
    }

    private static boolean getBooleanValue(String jsonLine, String key, boolean defaultVal) {
        String s = getStringValue(jsonLine, key);
        if (s == null || s.isEmpty()) {

            int idx = jsonLine.indexOf("\"" + key + "\":");
            if (idx == -1) return defaultVal;
            idx = jsonLine.indexOf(":", idx) + 1;
            if (idx <= 0) return defaultVal;
            StringBuilder sb = new StringBuilder();
            while (idx < jsonLine.length()) {
                char c = jsonLine.charAt(idx);
                if (c == ',' || c == '}') break;
                if (!Character.isWhitespace(c)) sb.append(c);
                idx++;
            }
            String raw = sb.toString();
            return "true".equalsIgnoreCase(raw);
        }
        return "true".equalsIgnoreCase(s) || "1".equals(s);
    }

    //clase auxiliar para devolver datos cargados
    public static class LoadedData {
        public final List<Vehiculo> stock;
        public final List<Vehiculo> cola;

        public LoadedData(List<Vehiculo> stock, List<Vehiculo> cola) {
            this.stock = stock;
            this.cola = cola;
        }
    }
}
