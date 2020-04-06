import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;

public class Factory {
    private HashMap<String, String> speclist;
    Factory(String config) {
        try {
            //Scanner reader = new Scanner(new File(config));
            speclist = new HashMap<String, String>();
            Properties prop = new Properties();
            //String result = "";
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(config);
            prop.load(inputStream);
            for (String key : prop.stringPropertyNames())
            {
                //System.out.println(key + "_   _" + prop.getProperty(key));
                String line = prop.getProperty(key);
                speclist.put(line, key);

            }
            //String line;
            /*while(reader.hasNextLine()){
                    line = reader.nextLine();
                    String pair[] = line.split(" ");
                    speclist.put(pair[0], pair[1]);
                }*/
        }
        catch(IOException err){
            System.out.println(err.getMessage());
        }
    }
    worker create(String type) {
        worker operator = null;
        try {
            String wtype = speclist.get(type);
            Class<?> wclass = Class.forName(wtype); 
            Constructor cons = wclass.getConstructor();
            operator = (worker) cons.newInstance();
        } catch (ClassNotFoundException err) {
            System.out.println(err.getMessage());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return operator;
    }
}
