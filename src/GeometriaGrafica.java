import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeometriaGrafica extends JFrame {
    private JComboBox<String> figuraCombo;
    private JTextField campoMedida1;
    private JTextField campoMedida2;
    private JPanel panelDibujo;

    public GeometriaGrafica() {
        setTitle("Geometría Gráfica");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior para seleccionar la figura y las medidas
        JPanel panelOpciones = new JPanel();
        panelOpciones.setLayout(new GridLayout(3, 2));

        panelOpciones.add(new JLabel("Seleccione la figura:"));
        figuraCombo = new JComboBox<>(new String[]{"Círculo", "Cuadrado", "Rectángulo"});
        panelOpciones.add(figuraCombo);

        panelOpciones.add(new JLabel("Medida 1 (Radio/Lado/Largo):"));
        campoMedida1 = new JTextField();
        panelOpciones.add(campoMedida1);

        panelOpciones.add(new JLabel("Medida 2 (Ancho para Rectángulo):"));
        campoMedida2 = new JTextField();
        campoMedida2.setEnabled(false); // Deshabilitado hasta que se seleccione Rectángulo
        panelOpciones.add(campoMedida2);

        // Añadir ActionListener al combo box para habilitar/deshabilitar campoMedida2
        figuraCombo.addActionListener(e -> {
            if (figuraCombo.getSelectedItem().equals("Rectángulo")) {
                campoMedida2.setEnabled(true);
            } else {
                campoMedida2.setEnabled(false);
            }
        });

        add(panelOpciones, BorderLayout.NORTH);

        // Panel para el botón de dibujar
        JButton botonDibujar = new JButton("Dibujar Figura");
        botonDibujar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelDibujo.repaint(); // Llama a repaint para redibujar la figura
            }
        });
        add(botonDibujar, BorderLayout.SOUTH);

        // Panel de dibujo
        panelDibujo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarFigura(g);
            }
        };
        add(panelDibujo, BorderLayout.CENTER);
    }

    private void dibujarFigura(Graphics g) {
        try {
            String figuraSeleccionada = (String) figuraCombo.getSelectedItem();
            int medida1 = Integer.parseInt(campoMedida1.getText());
            int medida2 = campoMedida2.isEnabled() ? Integer.parseInt(campoMedida2.getText()) : 0;

            g.setColor(Color.BLUE); // Color de la figura

            switch (figuraSeleccionada) {
                case "Círculo":
                    g.drawOval(100, 50, medida1 * 2, medida1 * 2); // Dibuja el círculo usando el radio
                    break;
                case "Cuadrado":
                    g.drawRect(100, 50, medida1, medida1); // Dibuja el cuadrado usando el lado
                    break;
                case "Rectángulo":
                    g.drawRect(100, 50, medida1, medida2); // Dibuja el rectángulo usando largo y ancho
                    break;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese medidas válidas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GeometriaGrafica ventana = new GeometriaGrafica();
            ventana.setVisible(true);
        });
    }
}
