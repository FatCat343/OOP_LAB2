import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;

public class Factory {
    private HashMap<String, String> speclist;
    Factory(String config) {
        try {
            speclist = new HashMap<String, String>();
            Properties prop = new Properties();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(config);
            if (inputStream != null) prop.load(inputStream);
            else throw new IOException("unable to use config file");
            for (String key : prop.stringPropertyNames())
            {
                String line = prop.getProperty(key);
                speclist.put(line, key);

            }
        }
        catch(IOException err){
            calculator.LOGGER.log(Level.INFO,err.getMessage());
        }
    }
    worker create(String type) {
        worker operator = null;
        try {
            String wtype = speclist.get(type);
            Class<?> wclass = Class.forName(wtype); 
            Constructor cons = wclass.getConstructor();
            operator = (worker) cons.newInstance();
        }
        catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException err) {
            calculator.LOGGER.log(Level.INFO,err.getMessage());
        }

        return operator;
    }
}
