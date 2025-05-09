import java.io.IOException;
import java.util.Scanner;

public class ConversorApp {
    public static void main(String[] args) throws IOException, InterruptedException {

        Conversor conversor = new Conversor();
        Scanner sn = new Scanner(System.in);

        while(true) {

            Conversor.mostrarMenu();

            try{

                var opcionInt = Integer.parseInt(sn.nextLine());
                if(opcionInt == 7) {
                    break;
                }

                conversor.setCodes(opcionInt);

                System.out.println("Ingrese el valor que desea convertir: ");
                double monto = Double.parseDouble(sn.nextLine());

                conversor.convertir(monto);
                System.out.println("El valor "+ monto +" ["+conversor.getBaseCode()+"] "+"corresponde al valor final de =>>> "+ conversor.getConversion() +" ["+conversor.getTargetCode()+"]");

            }catch (NumberFormatException e) {
                System.out.println("Opción no encontrado");
            } catch (RuntimeException  e){
                System.out.println(e.getMessage());
                System.out.println("Finalizando la aplicación");
            }

        }
    }

}
