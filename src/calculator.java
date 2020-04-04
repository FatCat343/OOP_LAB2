import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class calculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter factory config file: ");
        String confile = input.next();
        System.out.println("Enter input work file: ");
        String workfile = input.next();
        Factory factory  = new Factory (confile);
        System.out.println("factory created");
        workContext context = new workContext();
        Scanner parser = null;
        try {
            parser = new Scanner(new File(workfile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (parser.hasNextLine()){
            String line = parser.nextLine();
            System.out.println(line);
            String[] command = line.split(" ");
            System.out.println("create was called with" + command[0]);
            worker operator = factory.create(command[0]);
            operator.todo(command, context);
        }
    }
}
