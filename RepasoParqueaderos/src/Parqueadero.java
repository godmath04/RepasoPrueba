import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Parqueadero {
    //Puesto es la clase que va a almacenar la lista
    // puestos es el nombre de la variable decladrada y hace referencia a un objeto de tipo Puesto
    //Aqui he creado una lista que contiene objetos de tipo Puesto
    private List<Puesto> puestos;
    private double tarifa;
    private int reloj;
    private double ingresos;

    //Constructor para que se asginen los valores
    public Parqueadero(double tarifaInicial){
        this.tarifa = tarifaInicial;
        this.reloj = 6;
        this.puestos = new ArrayList<>();
        for (int i = 1; i<=5; i++){
            this.puestos.add(new Puesto(i));
        }
        this.ingresos = 0.0;
    }


    // Metodo de ingreso de vehiculo
    public void ingresoCarro(String placa, int horaEntrada){
        if(horaEntrada < 6 || horaEntrada > 21){
            JOptionPane.showMessageDialog(null, "Hora invalida. Vuelva en horario disponible.");
            return;
        }

        //Verifica disponibidlidad y placa igual
        for (int i = 0; i < puestos.size(); i++){
            // Nos referimos a la clase Puesto y scamos el nombre de la variable puesto para referirse a un objeto de esa clase
            Puesto puesto = puestos.get(i);

            //Si el puesto no esta disponible
            if( !puesto.estaDisponible() && puesto.getCarro().getPlaca().equals(placa)){
                JOptionPane.showMessageDialog(null, "Ya hay un carro con esa placa");
                return;
            }

        }

        for (int i = 0; i<puestos.size(); i++){
            Puesto puesto = puestos.get(i);
            if (puesto.estaDisponible()){
                puesto.asignarCarro(new Carro(placa, horaEntrada));
                JOptionPane.showMessageDialog(null, "Carro ingresado en el puesto" + puesto.getNumero());
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "No hay puestos disponibles");

    }

    public void darSalidaCarro(String placa) {
        for (int i = 0; i < puestos.size(); i++) {
            Puesto puesto = puestos.get(i);
            if (!puesto.estaDisponible() && puesto.getCarro().getPlaca().equals(placa)) {
                int horaActual = reloj;
                int horaEntrada = puesto.getCarro().getHoraEntrada();

                // Calcula el tiempo transcurrido en horas y minutos
                int horasEstacionado;
                if (horaActual >= horaEntrada) {
                    horasEstacionado = horaActual - horaEntrada;
                } else {
                    horasEstacionado = (24 - horaEntrada) + horaActual;
                }

                double minutosEstacionado = (horasEstacionado % 1) * 60; // Calcula los minutos

                double costo = horasEstacionado * tarifa;
                JOptionPane.showMessageDialog(null, "El costo por estacionamiento es: $" + costo);
                ingresos += costo; // Actualiza los ingresos
                puesto.liberarCarro();
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "No hay un carro con esa placa");
    }


    public int consultarPuestosDisponibles(){
        int disponibles = 0;
        for (int i = 0; i < puestos.size(); i++){
            Puesto puesto = puestos.get(i);
            if (puesto.estaDisponible()){
                disponibles++;
            }
        }
        return disponibles;
    }

    // Avanzar el reloj
    public void avanzarReloj(int horas) {
        if (reloj + horas > 21) {
            JOptionPane.showMessageDialog(null, "No se puede avanzar el reloj más allá de las 21:00.");
        } else {
            reloj += horas;
           JOptionPane.showMessageDialog(null,"La hora actual es: " + reloj + ":00.");
        }
    }

    // Cambiar la tarifa
    public void cambiarTarifa(double nuevaTarifa){
        this.tarifa = nuevaTarifa;
        JOptionPane.showMessageDialog(null, "La nueva tarifa es : $" + tarifa + "por hora");
    }

    public int getReloj() {
        return reloj;
    }

    public double getTarifa() {
        return tarifa;
    }

    public double getIngresos(){
        return ingresos;
    }


}
