import java.util.Scanner;

public class ConsoleTwitter {

    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        //Añadir instrucciones de uso
        System.out.println("Probando.....");

        String input = scan.nextLine();
        //Extraer procesador de comandos
        while(!"exit".equals(input)){

            System.out.println("Has introducido : " + input);
            input = scan.nextLine();

        }


    }
}
