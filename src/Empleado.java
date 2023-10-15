import java.util.Calendar;
import java.util.GregorianCalendar;

public class Empleado {
    int numEmpleado;
    String nombreEmpleado;
    GregorianCalendar fechaAlta;
    int edad;
    float sal;

    public Empleado(int numEmpleado, String nombreEmpleado, GregorianCalendar fechaAlta, int edad, float sal) {
        this.numEmpleado = numEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.fechaAlta = fechaAlta;
        this.edad = edad;
        this.sal = sal;
    }

    public Empleado(int numEmpleado, String nombreEmpleado) {
        this(numEmpleado,nombreEmpleado,new GregorianCalendar(),0,0);
    }

    public Empleado(String nombreEmpleado) {
        this(999, nombreEmpleado, new GregorianCalendar(), 0, 0);
    }

    public int getNumEmpleado() {
        return numEmpleado;
    }

    public void setNumEmpleado(int numEmpleado) {
        this.numEmpleado = numEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public GregorianCalendar getFechaAlta() {
        return fechaAlta;
    }

    public String getFechaFormato() {
        GregorianCalendar fecha = getFechaAlta();
        String fecha_formateada = "" + fecha.get(Calendar.DAY_OF_MONTH) + "/" + (fecha.get(Calendar.MONTH)+1) + "/" + fecha.get(Calendar.YEAR);
        return fecha_formateada;
    }

    public void setFechaAlta(GregorianCalendar fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getSal() {
        return sal;
    }

    public void setSal(float sal) {
        this.sal = sal;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "numEmpleado=" + numEmpleado +
                ", nombreEmpleado='" + nombreEmpleado + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", edad=" + edad +
                ", sal=" + sal +
                '}';
    }
}