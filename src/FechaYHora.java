import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FechaYHora {
    public String FechaYHora(){
        LocalDateTime fechaYHoraActual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-uuuu | HH:mm:ss");
        String  fechaYHoraFormateada = fechaYHoraActual.format(formatter);
        return fechaYHoraFormateada;
    }

}
