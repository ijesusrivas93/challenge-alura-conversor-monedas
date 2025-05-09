import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Conversor {

    private String baseCode;
    private String targetCode;
    private double conversion;

    public void setCodes(int opcion){
        switch (opcion){
            case 1: this.setBaseCode("USD");
                this.setTargetCode("ARS");
                break;
            case 2: this.setBaseCode("ARS");
                this.setTargetCode("USD");
                break;
            case 3: this.setBaseCode("USD");
                this.setTargetCode("BRL");
                break;
            case 4: this.setBaseCode("BRL");
                this.setTargetCode("USD");
                break;
            case 5: this.setBaseCode("USD");
                this.setTargetCode("COP");
                break;
            case 6: this.setBaseCode("COP");
                this.setTargetCode("USD");
                break;
        }
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public String getTargetCode() {
        return targetCode;
    }

    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode;
    }

    public double getConversion() {
        return conversion;
    }

    public void convertir(double monto){

        String url = "https://v6.exchangerate-api.com/v6/16461615b8d3b17487678428/pair/"+this.getBaseCode()+"/"+this.getTargetCode()+"/"+monto;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonElement element = JsonParser.parseString(response.body());
            JsonObject objectRoot = element.getAsJsonObject();
            this.conversion = objectRoot.get("conversion_result").getAsDouble();
        } catch (IOException|InterruptedException e) {
            throw new RuntimeException("Error al realizar la solicitud: " + e.getMessage());
        }

    }

    public static void mostrarMenu(){
        System.out.println("""
                ****************************************************************************
                Sea bienvenido/a al Conversor de Monedas =)
                
                1) Dólar =>> Peso Argentino
                2) Peso Argentino =>> Dólar
                3) Dólar =>> Real Brasileño
                4) Real Brasileño =>> Dólar
                5) Dólar =>> Peso Colombiano
                6) Peso Colombiano =>> Dólar
                7) Salir
                
                Elija una opción válida.
                ****************************************************************************
                """);
    }
}
