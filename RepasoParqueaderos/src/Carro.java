public class Carro {
    // Mi clase para poder tener varios carros

    private String placa;
    private int horaEntrada;

    // Mediante el constructor es que voy a asignar o setear los valores
    public Carro(String placa, int horaEntrada) {
        this.placa = placa;
        this.horaEntrada = horaEntrada;
    }

    // Metodos solo para leer
    public String getPlaca() {
        return placa;
    }

    public int getHoraEntrada() {
        return horaEntrada;
    }
}
