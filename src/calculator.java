import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class calculator {
    static Logger LOGGER;
    static {
        try(FileInputStream ins = new FileInputStream("log.config.txt")){
            LogManager.getLogManager().readConfiguration(ins);
            LOGGER = Logger.getLogger(calculator.class.getName());
        } catch (Exception ignore){
            ignore.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String confile = args[0];
        String workfile = args[1];
        calc(confile, workfile);
    }
    public static void calc(String confile, String workfile){
        HashMap<String, worker> workers = new HashMap<String, worker>();
        LOGGER.log(Level.INFO,"Создаем фабрику");
        Factory factory  = new Factory (confile);
        LOGGER.log(Level.INFO,"Создаем контекст работы");
        workContext context = new workContext();
        Scanner parser = null;
        try {
            LOGGER.log(Level.INFO,"Создаем парсер рабочего файла");
            parser = new Scanner(new File(workfile));
            LOGGER.log(Level.INFO,"Начинаем алгоритм");
            while (parser.hasNextLine()) {
                String line = parser.nextLine();
                LOGGER.log(Level.INFO,"Считали строку рабочего файла - " + line);
                String[] command = line.split(" ");
                worker operator = null;
                if (workers.containsKey(command[0])) {
                    LOGGER.log(Level.INFO,"Достаем готовый объект рабочего - " + command[0]);
                    operator = workers.get(command[0]);
                }
                else {
                    LOGGER.log(Level.INFO,"Создем новый объект рабочего - " + command[0]);
                    operator = factory.create(command[0]);
                    workers.put(command[0], operator);
                }
                LOGGER.log(Level.INFO,"Вызвали рабочего - " + command[0] + " с параметрами  " + line);
                operator.todo(command, context);
            }
        }
        catch (FileNotFoundException e) {
            calculator.LOGGER.log(Level.INFO,e.getMessage());
        }
    }
}
