import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class calculator {
    public static void main(String[] args) {
        //Scanner input = new Scanner(System.in);

        //System.out.println("Enter factory config file: ");
        //String confile = input.next();
        String confile = args[0];
        //System.out.println("Enter input work file: ");
        //String workfile = input.next();
        String workfile = args[1];
        calc(confile, workfile);
    }
    public static void calc(String confile, String workfile){
        HashMap<String, worker> workers = new HashMap<String, worker>();
        Factory factory  = new Factory (confile);
        //System.out.println("factory created");
        workContext context = new workContext();
        Scanner parser = null;
        try {
            parser = new Scanner(new File(workfile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (parser.hasNextLine()){
            String line = parser.nextLine();
            //System.out.println(line);
            String[] command = line.split(" ");

            worker operator = null;
            if (workers.containsKey(command[0]) == true) operator = workers.get(command[0]);
            else {
                //System.out.println("create was called with" + command[0]);
                operator = factory.create(command[0]);
                workers.put(command[0], operator);
            }
            operator.todo(command, context);
        }
    }
}
