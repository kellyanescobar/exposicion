package expo;
import java.awt.Color;

public enum Partido {
    PARTIDO1(Color.RED, "Partido 1"),
    PARTIDO2(Color.GREEN, "Partido 2"),
    PARTIDO3(Color.BLUE, "Partido 3"),
    PARTIDO4(Color.ORANGE, "Partido 4"),
    PARTIDO5(Color.MAGENTA, "Partido 5");

    private Color color;
    private String nombre;

    private Partido(Color color, String nombre) {
        this.color = color;
        this.nombre = nombre;
    }

    public Color getColor() {
        return color;
    }

    public String getNombre() {
        return nombre;
    }
}
