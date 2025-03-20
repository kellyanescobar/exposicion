package expo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GraficaVotacion extends JFrame {
    private JTextField[] campos = new JTextField[Partido.values().length];
    private int[] votos = new int[Partido.values().length];
    private JPanel panelGrafica;

    public GraficaVotacion() {
        setTitle("Grafica de Votaciones");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel titulo = new JLabel("Votaciones", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(200, 10, 300, 30);
        add(titulo);

        int y = 60;
        for (int i = 0; i < Partido.values().length; i++) {
            JLabel label = new JLabel(Partido.values()[i].getNombre() + ":");
            label.setBounds(30, y, 80, 25);
            add(label);

            campos[i] = new JTextField("0");
            campos[i].setBounds(110, y, 100, 25);
            add(campos[i]);
            y += 35;
        }

        // Boton de graficar
        JButton graficar = new JButton("Graficar");
        graficar.setBounds(50, y, 100, 30);
        add(graficar);

        // Panel donde se dibuja la grafica
        panelGrafica = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int totalVotos = 0;
                for (int v : votos) totalVotos += v;
                if (totalVotos == 0) return;

                int startAngle = 0;
                for (int i = 0; i < votos.length; i++) {
                    int arcAngle = (int) Math.round((double) votos[i] / totalVotos * 360);
                    g.setColor(Partido.values()[i].getColor());
                    g.fillArc(50, 50, 200, 200, startAngle, arcAngle);
                    startAngle += arcAngle;
                }

                // Leyenda
                int LeyendoColor = 50;
                for (Partido p : Partido.values()) {
                    g.setColor(p.getColor());
                    g.fillRect(300, LeyendoColor, 20, 20);
                    g.setColor(Color.BLACK);
                    g.drawString(p.getNombre(), 330, LeyendoColor + 15);
                    LeyendoColor += 30;
                }
            }
        };
        panelGrafica.setBounds(250, 60, 400, 400);
        add(panelGrafica);

        // Accion del boton
        graficar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < campos.length; i++) {
                    try {
                        votos[i] = Integer.parseInt(campos[i].getText());
                    } catch (NumberFormatException ex) {
                        votos[i] = 0;
                    }
                }
                panelGrafica.repaint();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GraficaVotacion().setVisible(true));
    }
}
