package Principal;

import Service.ConsumoAPI;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static Gson gson = new GsonBuilder()
            .setFieldNamingPolicy (FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();
    static Scanner leer = new Scanner(System.in);

    public static void main(String[] args) {

        String response;
        String opcion;

        try {
            response = ConsumoAPI.consulta();
            System.out.println(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        DivisaER divisaEr = gson.fromJson(response, DivisaER.class);
        Divisa divisa = new Divisa(divisaEr);

        do {
            System.out.println("""
                    ********************************************************************************
                    Bienvenido al Conversor de Monedas, selecciona una opcion para continuar:
                    
                    1) Peso Mexicano (MXN) =>> Peso Argentino (ARS)                
                    2) Peso Argentino (ARS) =>> Peso Mexicano (MXN) 
                    3) Peso Mexicano (MXN) =>> Real brasileño (BRL)                
                    4) Real brasileño (BRL) =>> Peso Mexicano (MXN) 
                    5) Peso Mexicano (MXN) =>> Peso colombiano (COP)                
                    6) Peso colombiano (COP) =>> Peso Mexicano (MXN)                  
                    7) Salir
                    Elija una opcion válida:
                    ********************************************************************************
                    """);
            opcion = leer.next();
            leer.nextLine();

            switch(opcion){
                case "1":
                    mensaje("MXN","ARS", divisa.getCambios());
                    break;
                case "2":
                    mensaje("ARS","MXN", divisa.getCambios());
                    break;
                case "3":
                    mensaje("MXN","BRL", divisa.getCambios());
                    break;
                case "4":
                    mensaje("BRL","ARS", divisa.getCambios());
                    break;
                case "5":
                    mensaje("MXN","COP", divisa.getCambios());
                    break;
                case "6":
                    mensaje("COP","MXN", divisa.getCambios());
                    break;
                case "7":
                    System.out.println("Nos vemos!");
                    break;
                default:
                    System.out.println("Opción no válida!");

            }

        } while (!opcion.equals("7"));



    }
    public static void mensaje(String base, String tipoC, Map<String, Double> cambios){
        System.out.println("Ingresa el valor que deseas convertir:");
        Double valor = leer.nextDouble();

        Double cambioFinal = cambios.get(tipoC)/cambios.get(base)*valor;
        System.out.printf("El valor %.2f [%s] corresponde al valor final de =>>> %.4f [%s]\n\n",valor,base,cambioFinal, tipoC );
    }
}
