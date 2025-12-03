import java.io.Serializable;
public class SeleccionColorAuto implements Serializable {

    public enum ColorAuto {
        ROJO,
        AZUL,
        NEGRO,
        BLANCO,
        VERDE,
        GRIS;
    }

    public static void mostrarColoresDisponibles() 
    {
        ColorAuto[] colores = ColorAuto.values();
        for (int i = 0; i < colores.length; i++) 
        {
            System.out.println((i + 1) + ". " + colores[i]);
        }
    }

    public static ColorAuto obtenerColorPorIndice(int indice) 
    {
        return ColorAuto.values()[indice - 1];
    }
}
