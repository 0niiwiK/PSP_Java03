import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Vista {
    private JPanel panel1;
    private JTextField num_empleado_f;
    private JTextField nombre_f;
    private JTextField fecha_f;
    private JTextField edad_f;
    private JComboBox filtro_b;
    private JTextField fechai_f;
    private JTextField fechaf_f;
    private JButton anteriorButton;
    private JButton siguienteButton;
    private JButton crearButton;
    private JButton borrarButton;
    private JTextField salario_f;
    private JButton cargarButton;
    private JButton cancelarButton;
    private JButton guardarButton;
    MyList<Empleado> lista_activa = null;
    boolean borrar = false;


    public Vista() {
        anteriorButton.setEnabled(false);
        siguienteButton.setEnabled(false);
        MyList<Empleado> lista_total = new MyList<>();
        lista_total.add(new Empleado(1, "Paco Fernández López", new GregorianCalendar(2022, 1, 17), 25, 1200));
        lista_total.add(new Empleado(2, "Pedro Sánchez Márquez", new GregorianCalendar(2020, 9, 25), 32, 1500));
        lista_total.add(new Empleado(3, "Patricia Franco Berber", new GregorianCalendar(), 27, 1000));

        fechai_f.setEnabled(false);
        fechaf_f.setEnabled(false);
        num_empleado_f.setEditable(false);
        nombre_f.setEditable(false);
        fecha_f.setEditable(false);
        edad_f.setEditable(false);
        salario_f.setEditable(false);

        siguienteButton.addActionListener(e -> {
            lista_activa.goNext();
            anteriorButton.setEnabled(true);
            if (lista_activa.isLast())
                siguienteButton.setEnabled(false);
            setCampos();
        });

        anteriorButton.addActionListener(e -> {
            lista_activa.goPrev();
            siguienteButton.setEnabled(true);
            if (lista_activa.isFirst())
                anteriorButton.setEnabled(false);
            setCampos();
        });

        cargarButton.addActionListener(e -> {
            System.out.println(filtro_b.getSelectedIndex());
            switch (filtro_b.getSelectedIndex()) {
                case 0:
                    cargarLista(lista_total);
                    anteriorButton.setEnabled(false);
                    if (lista_activa.isLast())
                        siguienteButton.setEnabled(false);
                    else
                        siguienteButton.setEnabled(true);
                    setCampos();
                    break;
                case 1:
                    MyList<Empleado> lista_mes = new MyList<>();
                    int mes_act = new GregorianCalendar().get(Calendar.MONTH);
                    lista_total.goFirst();
                    while (!lista_total.isLast()) {
                        if (lista_total.getAct().main.getFechaAlta().get(Calendar.MONTH) == mes_act) {
                            lista_mes.add(lista_total.getAct().main);
                        }
                        lista_total.goNext();
                    };
                    if (lista_total.getAct().main.getFechaAlta().get(Calendar.MONTH) == mes_act) {
                        lista_mes.add(lista_total.getAct().main);
                    }
                    cargarLista(lista_mes);
                    anteriorButton.setEnabled(false);
                    if (lista_activa.isLast())
                        siguienteButton.setEnabled(false);
                    else
                        siguienteButton.setEnabled(true);
                    setCampos();
                    break;
                case 2:
                    String fechainicial = fechai_f.getText();
                    String fechaini[] = fechainicial.split("/");
                    String fechafinal = fechaf_f.getText();
                    String fechafin[] = fechafinal.split("/");
                    GregorianCalendar calendar_ini = new GregorianCalendar(Integer.parseInt(fechaini[2]),Integer.parseInt(fechaini[1])-1,Integer.parseInt(fechaini[0]));
                    GregorianCalendar calendar_fin = new GregorianCalendar(Integer.parseInt(fechafin[2]),Integer.parseInt(fechafin[1])-1,Integer.parseInt(fechafin[0]));
                    MyList<Empleado> lista_rango = new MyList<>();
                    lista_total.goFirst();
                    while (!lista_total.isLast()) {
                        if (lista_total.getAct().main.getFechaAlta().compareTo(calendar_ini) >= 0
                        && lista_total.getAct().main.getFechaAlta().compareTo(calendar_fin) <= 0) {
                            lista_rango.add(lista_total.getAct().main);
                        }
                        lista_total.goNext();
                    };
                    if (lista_total.getAct().main.getFechaAlta().compareTo(calendar_ini) >= 0
                            && lista_total.getAct().main.getFechaAlta().compareTo(calendar_fin) <= 0) {
                        lista_rango.add(lista_total.getAct().main);
                    }
                    cargarLista(lista_rango);
                    anteriorButton.setEnabled(false);
                    if (lista_activa.isLast())
                        siguienteButton.setEnabled(false);
                    else
                        siguienteButton.setEnabled(true);
                    setCampos();
                    break;
            };
        });

        cancelarButton.setVisible(false);
        guardarButton.setVisible(false);


        ActionListener modoBorrar = e -> {
            if (lista_activa != null && lista_activa.getFirstNode() != null) {
                Object[] options = {"Cancelar",
                        "Borrar"};
                int n = JOptionPane.showOptionDialog(null,
                        "Estás seguro de que quieres borrar este empleado?",
                        "Borrar Empleado",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                System.out.println(n);
                if (n == 1) {
                    int num_borrar = lista_activa.getAct().main.getNumEmpleado();
                    lista_activa.borrarNodo();
                    lista_total.goFirst();
                    while (!lista_total.isLast()) {
                        if (lista_total.getAct().main.getNumEmpleado() == num_borrar)
                            lista_total.borrarNodo();
                        lista_total.goNext();
                    }
                    if (lista_activa.getFirstNode() != null && lista_total.getAct().main.getNumEmpleado() == num_borrar)
                        lista_total.borrarNodo();
                    cargarLista(lista_activa);
                    if (lista_activa != null && lista_activa.getFirstNode() != null) {
                        anteriorButton.setEnabled(false);
                        if (lista_activa.isLast())
                            siguienteButton.setEnabled(false);
                        else
                            siguienteButton.setEnabled(true);
                        setCampos();
                    } else {
                        setCamposVacio();
                    }
                }
            } else {
                setCamposVacio();
                //custom title, error icon
                JOptionPane.showMessageDialog(null,
                        "No hay ningun empleado que borrar",
                        "No hay Empleados",
                        JOptionPane.ERROR_MESSAGE);
            }
        };

        borrarButton.addActionListener(modoBorrar);
        cancelarButton.addActionListener(e -> {
            cargarLista(lista_activa);
            anteriorButton.setEnabled(false);
            if (lista_activa.isLast())
                siguienteButton.setEnabled(false);
            else
                siguienteButton.setEnabled(true);
            setCampos();
            num_empleado_f.setEditable(false);
            nombre_f.setEditable(false);
            fecha_f.setEditable(false);
            edad_f.setEditable(false);
            salario_f.setEditable(false);
            anteriorButton.setVisible(true);
            siguienteButton.setVisible(true);
            crearButton.setVisible(true);
            borrarButton.setVisible(true);
            guardarButton.setVisible(false);
            cancelarButton.setVisible(false);
            num_empleado_f.setEnabled(true);
        });

        filtro_b.addActionListener(e -> {
            if (filtro_b.getSelectedIndex() == 2) {
                fechai_f.setEnabled(true);
                fechaf_f.setEnabled(true);
            } else {
                fechai_f.setEnabled(false);
                fechaf_f.setEnabled(false);
            }
        });

        crearButton.addActionListener(e -> {
            setCamposVacio();
            num_empleado_f.setEditable(true);
            nombre_f.setEditable(true);
            fecha_f.setEditable(true);
            edad_f.setEditable(true);
            salario_f.setEditable(true);
            anteriorButton.setVisible(false);
            siguienteButton.setVisible(false);
            crearButton.setVisible(false);
            borrarButton.setVisible(false);
            guardarButton.setVisible(true);
            cancelarButton.setVisible(true);
            num_empleado_f.setEnabled(false);
            num_empleado_f.setText("" + (lista_total.getCounter()+1));
        });

        guardarButton.addActionListener(e -> {
            String nombre = nombre_f.getText();
            String fecha;
            GregorianCalendar fechaguardar;
            if (fecha_f.getText() != null) {
                fecha = fecha_f.getText();
                try {
                    String[] fechacortada = fecha.split("/");
                    fechaguardar = new GregorianCalendar(Integer.parseInt(fechacortada[2]),Integer.parseInt(fechacortada[1])-1,Integer.parseInt(fechacortada[0]));
                } catch (Exception exc) {
                    fechaguardar = new GregorianCalendar();
                }
            } else
                fechaguardar = new GregorianCalendar();
            int edad;
            if (edad_f.getText() != null) {
                try {
                    edad = Integer.parseInt(edad_f.getText());
                } catch (Exception ex) {
                    edad = 0;
                }
            } else
                edad = 0;
            float salario;
            if (salario_f.getText() != null) {
                try {
                    salario = Float.parseFloat(salario_f.getText());
                } catch (Exception ex) {
                    salario = 0;
                }
            } else
                salario = 0;
            lista_total.add(new Empleado(Integer.parseInt(num_empleado_f.getText()),nombre,fechaguardar,edad,salario));

            cargarLista(lista_activa);
            anteriorButton.setEnabled(false);
            siguienteButton.setEnabled(!lista_activa.isLast());
            setCampos();
            num_empleado_f.setEditable(false);
            nombre_f.setEditable(false);
            fecha_f.setEditable(false);
            edad_f.setEditable(false);
            salario_f.setEditable(false);
            anteriorButton.setVisible(true);
            siguienteButton.setVisible(true);
            crearButton.setVisible(true);
            borrarButton.setVisible(true);
            guardarButton.setVisible(false);
            cancelarButton.setVisible(false);
            num_empleado_f.setEnabled(true);
        });
    }

    public void cargarLista(MyList<Empleado> lista_carga) {
        lista_activa = lista_carga;
        if (lista_activa.getFirstNode() == null) {
            setCamposVacio();
        } else
        lista_activa.goFirst();
    }

    public void setCampos() {
        num_empleado_f.setText("" + lista_activa.getAct().main.getNumEmpleado());
        nombre_f.setText("" + lista_activa.getAct().main.getNombreEmpleado());
        fecha_f.setText("" + lista_activa.getAct().main.getFechaFormato());
        edad_f.setText("" + lista_activa.getAct().main.getEdad());
        salario_f.setText("" + lista_activa.getAct().main.getSal());
    }

    public void setCamposVacio() {
        siguienteButton.setEnabled(false);
        anteriorButton.setEnabled(false);
        num_empleado_f.setText(null);
        nombre_f.setText(null);
        fecha_f.setText(null);
        edad_f.setText(null);
        salario_f.setText(null);
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Vista");
        frame.setContentPane(new Vista().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
