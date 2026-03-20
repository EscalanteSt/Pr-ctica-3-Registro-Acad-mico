public class Estudiante {
    
    private String nombre;
    private String matricula;
    private String carrera;
    
    private double practicas;
    private double parciales;
    private double asignaciones;
    private double examenFinal;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }

    public double getPracticas() { return practicas; }
    public void setPracticas(double practicas) { this.practicas = practicas; }

    public double getParciales() { return parciales; }
    public void setParciales(double parciales) { this.parciales = parciales; }

    public double getAsignaciones() { return asignaciones; }
    public void setAsignaciones(double asignaciones) { this.asignaciones = asignaciones; }

    public double getExamenFinal() { return examenFinal; }
    public void setExamenFinal(double examenFinal) { this.examenFinal = examenFinal; }

    public double calcularNotaFinal() {
        return practicas + parciales + asignaciones + examenFinal;
    }

    public String obtenerEstado() {
        if (calcularNotaFinal() >= 60) {
            return "APROBADO";
        } else {
            return "REPROBADO";
        }
    }
}
