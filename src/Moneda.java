import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Moneda {
    private String fechaYHoraFormateada;
    private String ultimaActualizacion;
    private String siguienteActulizacion;
    private String monedaAConvertir;
    private String monedaRequerida;
    private double cantidadAConvertir;
    private double tasaDeConversion;
    private double resultadoDeLaConversion;

    public Moneda(MonedaApi miMonedaApi){
        this.ultimaActualizacion = miMonedaApi.time_last_update_utc();
        this.siguienteActulizacion = miMonedaApi.time_next_update_utc();
        this.monedaAConvertir = miMonedaApi.base_code();
        this.monedaRequerida = miMonedaApi.target_code();
        this.tasaDeConversion = miMonedaApi.conversion_rate();
        this.resultadoDeLaConversion = miMonedaApi.conversion_result();


    }

//    public String fechaYHora(){
//        LocalDateTime fechaYHoraActual = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-uuuu | HH:mm:ss");
//        fechaYHoraFormateada = fechaYHoraActual.format(formatter);
//        return fechaYHoraFormateada;
//    }


    public String getFechaYHoraFormateada() {
        return fechaYHoraFormateada;
    }

    public void setFechaYHoraFormateada(String fechaYHoraFormateada) {
        this.fechaYHoraFormateada = fechaYHoraFormateada;
    }

    public String getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public void setUltimaActualizacion(String ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }

    public String getSiguienteActulizacion() {
        return siguienteActulizacion;
    }

    public void setSiguienteActulizacion(String siguienteActulizacion) {
        this.siguienteActulizacion = siguienteActulizacion;
    }

    public String getMonedaAConvertir() {
        return monedaAConvertir;
    }

    public void setMonedaAConvertir(String monedaAConvertir) {
        this.monedaAConvertir = monedaAConvertir;
    }

    public String getMonedaRequerida() {
        return monedaRequerida;
    }

    public void setMonedaRequerida(String monedaRequerida) {
        this.monedaRequerida = monedaRequerida;
    }

    public double getCantidadAConvertir() {
        return cantidadAConvertir;
    }

    public void setCantidadAConvertir(double cantidadAConvertir) {
        this.cantidadAConvertir = cantidadAConvertir;
    }

    public double getResultadoDeLaConversion() {
        return resultadoDeLaConversion;
    }

    public void setResultadoDeLaConversion(double resultadoDeLaConversion) {
        this.resultadoDeLaConversion = resultadoDeLaConversion;
    }

    public String toString() {
        return "Historial de conversiones \n"+
                "FechayHora: " + fechaYHoraFormateada+
                ", monedaAConvertir: " + monedaAConvertir+
                ", monedaRequerida: " + monedaRequerida +
                ", cantidadAConvertir :" + cantidadAConvertir +" "+ monedaAConvertir+
                ", resultadoDeLaConversion :" + resultadoDeLaConversion+" "+monedaRequerida;

    }
}
