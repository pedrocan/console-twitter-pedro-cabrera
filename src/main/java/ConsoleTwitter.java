import infrastructure.ui.ProcessorCommandInput;
import java.util.Scanner;

public class ConsoleTwitter {

    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);

        //Añadir instrucciones de uso
        System.out.println("Starting.....");

        StringBuilder instructions = new StringBuilder();
        instructions.append("The application must use the console for input and output.\n");
        instructions.append("Users submit commands to the application.\n");
        instructions.append("There are four commands. “posting”, “reading”, etc. are not part of the commands.\n");
        instructions.append("Commands always start with the user’s name.\n");
        instructions.append("\u001B[32m").append("posting:").append("\u001B[0m").append(" user name -> message\n");
        instructions.append("\u001B[32m").append("reading:").append("\u001B[0m").append(" user name\n");
        instructions.append("\u001B[32m").append("following:").append("\u001B[0m").append(" user name follows another user\n");
        instructions.append("\u001B[32m").append("wall:").append("\u001B[0m").append(" user name wall\n");

        System.out.println(instructions);

        String input = "";

        //Extraer procesador de comandos
        while(!"exit".equals(input)){

            input = scan.nextLine();
            ProcessorCommandInput.process(input);
            System.out.println("Has introducido : " + input);
        }

        System.out.println("Finished.....");

    }
}
