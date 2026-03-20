import javax.swing.*;
import java.awt.*;

public class RegistroAcademicoGUI extends JFrame {

    private JTextField txtNombre, txtMatricula, txtCarrera;
    private JTextField txtPracticas, txtParciales, txtAsignaciones, txtExamen;
    private JLabel lblResultado;

    public RegistroAcademicoGUI() {
        setTitle("Registro Académico");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panelFormulario = new JPanel(new GridLayout(7, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelFormulario.add(new JLabel("Nombre Completo:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Matrícula:"));
        txtMatricula = new JTextField();
        panelFormulario.add(txtMatricula);

        panelFormulario.add(new JLabel("Carrera:"));
        txtCarrera = new JTextField();
        panelFormulario.add(txtCarrera);

        panelFormulario.add(new JLabel("Prácticas (Máx 40):"));
        txtPracticas = new JTextField();
        panelFormulario.add(txtPracticas);

        panelFormulario.add(new JLabel("Parciales (Máx 20):"));
        txtParciales = new JTextField();
        panelFormulario.add(txtParciales);

        panelFormulario.add(new JLabel("Asignaciones (Máx 20):"));
        txtAsignaciones = new JTextField();
        panelFormulario.add(txtAsignaciones);

        panelFormulario.add(new JLabel("Examen Final (Máx 20):"));
        txtExamen = new JTextField();
        panelFormulario.add(txtExamen);

        add(panelFormulario, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new BorderLayout(5, 5));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnCalcular = new JButton("Calcular Resultado");
        lblResultado = new JLabel("Nota Total: -- | Estado: --", SwingConstants.CENTER);
        lblResultado.setFont(new Font("Arial", Font.BOLD, 14));

        panelInferior.add(btnCalcular, BorderLayout.NORTH);
        panelInferior.add(lblResultado, BorderLayout.SOUTH);

        add(panelInferior, BorderLayout.SOUTH);

        btnCalcular.addActionListener(e -> procesarCalificacion());
    }

    private void procesarCalificacion() {
        try {
            if (txtNombre.getText().trim().isEmpty() || 
                txtMatricula.getText().trim().isEmpty() || 
                txtCarrera.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Completa todos los datos personales.");
            }

            double practicas = Double.parseDouble(txtPracticas.getText());
            double parciales = Double.parseDouble(txtParciales.getText());
            double asignaciones = Double.parseDouble(txtAsignaciones.getText());
            double examen = Double.parseDouble(txtExamen.getText());

            if (practicas < 0 || practicas > 40) throw new IllegalArgumentException("Prácticas: 0 a 40.");
            if (parciales < 0 || parciales > 20) throw new IllegalArgumentException("Parciales: 0 a 20.");
            if (asignaciones < 0 || asignaciones > 20) throw new IllegalArgumentException("Asignaciones: 0 a 20.");
            if (examen < 0 || examen > 20) throw new IllegalArgumentException("Examen Final: 0 a 20.");

            Estudiante estudiante = new Estudiante();
            estudiante.setNombre(txtNombre.getText());
            estudiante.setMatricula(txtMatricula.getText());
            estudiante.setCarrera(txtCarrera.getText());
            estudiante.setPracticas(practicas);
            estudiante.setParciales(parciales);
            estudiante.setAsignaciones(asignaciones);
            estudiante.setExamenFinal(examen);

            double notaFinal = estudiante.calcularNotaFinal();
            String estado = estudiante.obtenerEstado();
            
            lblResultado.setText(String.format("Nota Total: %.2f | Estado: %s", notaFinal, estado));

            if (estado.equals("APROBADO")) {
                lblResultado.setForeground(new Color(0, 128, 0)); 
            } else {
                lblResultado.setForeground(Color.RED); 
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Solo números en las calificaciones.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }
}
