import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        double tarifaInicial = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese la tarifa inicial:"));
        Parqueadero parqueadero = new Parqueadero(tarifaInicial);

        while (true) {
            String[] opciones = {
                    "Ingresar un carro",
                    "Dar salida a un carro",
                    "Consultar cantidad de puestos disponibles",
                    "Avanzar el reloj",
                    "Cambiar la tarifa",
                    "Salir"
            };

            int opcion = JOptionPane.showOptionDialog(null,
                    "Seleccione una opción:",
                    "Parqueadero",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);

            switch (opcion) {
                case 0:
                    ingresarCarro(parqueadero);
                    break;
                case 1:
                    darSalidaCarro(parqueadero);
                    break;
                case 2:
                    consultarPuestosDisponibles(parqueadero);
                    break;
                case 3:
                    avanzarReloj(parqueadero);
                    break;
                case 4:
                    cambiarTarifa(parqueadero);
                    break;
                case 5:
                    System.exit(0); // Salir del programa
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        }
    }

    private static void ingresarCarro(Parqueadero parqueadero) {
        String placa = JOptionPane.showInputDialog(null, "Ingrese la placa del carro:");
        if (placa == null) return; // Si cancela, no hace nada

        int horaEntrada = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la hora de entrada (entre 6 y 21):"));
        if (horaEntrada < 6 || horaEntrada > 21) {
            JOptionPane.showMessageDialog(null, "Hora de entrada no válida. Debe ser entre 6:00 y 21:00.");
            return;
        }

        parqueadero.ingresoCarro(placa, horaEntrada);
    }

    private static void darSalidaCarro(Parqueadero parqueadero) {
        String placa = JOptionPane.showInputDialog(null, "Ingrese la placa del carro a retirar:");
        if (placa == null) return; // Si cancela, no hace nada

        parqueadero.darSalidaCarro(placa);
    }

    private static void consultarPuestosDisponibles(Parqueadero parqueadero) {
        JOptionPane.showMessageDialog(null, "Puestos disponibles: " + parqueadero.consultarPuestosDisponibles() +
                "\nIngresos totales: $" + parqueadero.getIngresos());
    }

    private static void avanzarReloj(Parqueadero parqueadero) {
        int horas = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese las horas a avanzar:"));
        parqueadero.avanzarReloj(horas);
    }

    private static void cambiarTarifa(Parqueadero parqueadero) {
        double nuevaTarifa = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese la nueva tarifa:"));
        parqueadero.cambiarTarifa(nuevaTarifa);
    }
}
