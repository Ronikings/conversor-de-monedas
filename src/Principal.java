import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {


        System.out.println("****************************************************************");

        String menu = """
                
                *** SEA BIENVENIDO/A AL CONVERSOR DE MONEDAS.***
                
                 Moneda             Código        Moneda                 Código
                
                Dólar               [USD]        Euro                    [EUR]
                Peso mexicano       [MXN]        Dólar canadiense        [CAD]  
                Real brasileño      [BRL]        Franco suizo            [CHF]
                Peso argentino      [ARS]        Rupia india             [INR]
                Peso colombiano     [COP]        Corona danesa           [DKK]
                Boliviano Bolivia   [BOB]        Libra egipcia           [EGP]
                Peso chileno        [CLP]        Dólar australiano       [AUD]
                Peso filipino       [PHP]        Dirham de EAU           [AED]
                Peso uruguayo       [UYU]        Nuevo séquel(Israel)    [ILS] 
                Peso dominicano     [DOP]        Rublo ruso              [RUB]
                Peso cubano         [CUP]        Riyal catarí            [QAR]
                Colón costarricense [CRC]        Renminbi chino          [CNY]
                Bolivar soberano    [VES]        Libra esterlina (UK)    [GBP]
                Lempira hondureña   [HNL]        Yen                     [JPY]
                                       
                    Rellene los campos a continuación... 
                """;

        Scanner lectura = new Scanner(System.in);
        FechaYHora fechaYHora = new FechaYHora();
        List<Moneda> monedas = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while (true){

            System.out.println(menu);
            System.out.println("         "+fechaYHora.FechaYHora());

            System.out.println("Escriba enter para continuar o escriba salir para terminar con la operación:");
            var opcion = lectura.next();
            if (opcion.equalsIgnoreCase("salir")){
                break;
            }

            System.out.println("Indique el código de la moneda a convertir (por ejemplo USD):");
            var monedaAConvertir = lectura.next();
            if (monedaAConvertir.equalsIgnoreCase("salir")){
                break;
            }

            System.out.println("Indique el código de la moneda que requiere : ");
            var monedaRequerida = lectura.next();
            if (monedaRequerida.equalsIgnoreCase("salir")){
                break;
            }
            System.out.println("Indique la cantidad a convertir");
            var cantidadAConvertir = lectura.nextDouble();

            String direccion = "https://v6.exchangerate-api.com/v6/2a9ce6ea5ca09ea57f2b506a/pair/"+
                    monedaAConvertir+"/"+monedaRequerida+"/"+cantidadAConvertir;

            try{

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(direccion))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();
                //System.out.println(json);

                MonedaApi miMonedaApi = new Gson().fromJson(json, MonedaApi.class); // Para convertir de JSON a JAVA
                //System.out.println(miMonedaApi);

                Moneda miMoneda = new Moneda(miMonedaApi);
                miMoneda.setCantidadAConvertir(cantidadAConvertir);
                miMoneda.setFechaYHoraFormateada(fechaYHora.FechaYHora());

                //System.out.println("Moneda ya convertida: " +

                System.out.println("\n---------------------------------------------------------------------------------");
                System.out.println(" El valor de "+cantidadAConvertir+" "+miMoneda.getMonedaAConvertir()+ " corresponde a la cantidad de: "+miMoneda.getResultadoDeLaConversion()+" "+miMoneda.getMonedaRequerida());
                System.out.println("---------------------------------------------------------------------------------");

//                System.out.println(fechaYHora.FechaYHora());
//                System.out.println(miMoneda.fechaYHora());
//                System.out.println(miMoneda.getFechaYHoraFormateada());

                monedas.add(miMoneda);

                //System.out.println(monedas);

            }catch (NumberFormatException e){
                System.out.println("Ocurrió un error: ");
                System.out.println(e.getMessage());
            }catch (IllegalArgumentException e){
                System.out.println("Error en la URI, verifique la dirección.");
            }

        }

        System.out.println(monedas);

        FileWriter escritura = new FileWriter("monedas.json");
        escritura.write(gson.toJson(monedas));
        escritura.close();
        System.out.println("¡Finalizó la ejecución del programa!");
    }
}
