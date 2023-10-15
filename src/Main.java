import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {
        MyList<Empleado> lista = new MyList<>();
        lista.add(new Empleado(1,"Paco Fernández López", new GregorianCalendar(2022, 1, 17), 25, 1200));
        lista.add(new Empleado(1,"Pedro Sánchez Márquez", new GregorianCalendar(2020, 6, 25), 32, 1500));
        lista.add(new Empleado(1,"Patricia Franco Berber", new GregorianCalendar(), 27, 1000));

        lista.goFirst();
        System.out.println(lista.getAct());
        lista.goNext();
        System.out.println(lista.getAct());
        System.out.println(lista.getAct().main.getFechaAlta().get(Calendar.MONTH));
    }
}