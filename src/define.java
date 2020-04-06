import java.io.FileWriter;
import java.io.IOException;

public class define implements worker {
    public define(){}
    public void todo(String[] command, workContext wc){
        wc.addef(command[1], command[2]);
    };
}
class comment implements worker {
    public comment(){}
    public void todo(String[] command, workContext wc){
        //System.out.println("comment was used");
    };
}
class pop implements worker {
    public pop(){}
    public void todo(String[] command, workContext wc){
        wc.popstack();
    };
}
class push implements worker {
    public push(){}
    public void todo(String[] command, workContext wc){
        wc.pushstack(command[1]);
    };
}
class plus implements worker {
    public plus(){}
    public void todo(String[] command, workContext wc){
        String var1 = wc.popstack();
        String var2 = wc.popstack();
        double v1, v2;
        try{
            v1 = Double.parseDouble(var1);
        }
        catch(NumberFormatException err){
            v1 = wc.getdef(var1);
        }
        try{
            v2 = Double.parseDouble(var2);
        }
        catch(NumberFormatException err){
            v2 = wc.getdef(var2);
        }
        double res = v1 + v2;
        wc.pushstack(String.valueOf(res));
    };
}
class minus implements worker {
    public minus(){}
    public void todo(String[] command, workContext wc){
        String var1 = wc.popstack();
        String var2 = wc.popstack();
        double v1, v2;
        try{
            v1 = Double.parseDouble(var1);
        }
        catch(NumberFormatException err){
            v1 = wc.getdef(var1);
        }
        try{
            v2 = Double.parseDouble(var2);
        }
        catch(NumberFormatException err){
            v2 = wc.getdef(var2);
        }
        double res = v2 - v1;
        wc.pushstack(String.valueOf(res));
    };
}
class del implements worker {
    public del(){}
    public void todo(String[] command, workContext wc){
        String var1 = wc.popstack();
        String var2 = wc.popstack();
        double v1, v2;
        try{
            v1 = Double.parseDouble(var1);
        }
        catch(NumberFormatException err){
            v1 = wc.getdef(var1);
        }
        try{
            v2 = Double.parseDouble(var2);
        }
        catch(NumberFormatException err){
            v2 = wc.getdef(var2);
        }
        double res;
        if (v1 != 0) res = v2 / v1;
        else res = 0;
        wc.pushstack(String.valueOf(res));
    };
}
class umnoj implements worker {
    public umnoj(){}
    public void todo(String[] command, workContext wc){
        String var1 = wc.popstack();
        String var2 = wc.popstack();
        double v1, v2;
        try{
            v1 = Double.parseDouble(var1);
        }
        catch(NumberFormatException err){
            v1 = wc.getdef(var1);
        }
        try{
            v2 = Double.parseDouble(var2);
        }
        catch(NumberFormatException err){
            v2 = wc.getdef(var2);
        }
        double res = v2 * v1;
        wc.pushstack(String.valueOf(res));
    };
}
class sqrt implements worker {
    public sqrt(){}
    public void todo(String[] command, workContext wc){
        String var1 = wc.popstack();
        double v1;
        try{
            v1 = Double.parseDouble(var1);
        }
        catch(NumberFormatException err){
            v1 = wc.getdef(var1);
        }
        //System.out.println(v1);
        double res = Math.sqrt(v1);
        wc.pushstack(String.valueOf(res));
    };
}
class print implements worker {
    public print(){}
    public void todo(String[] command, workContext wc){
        String var1 = wc.popstack();
        double v1;
        try{
            v1 = Double.parseDouble(var1);
        }
        catch(NumberFormatException err){
            v1 = wc.getdef(var1);
        }
        try {
            FileWriter out = new FileWriter("res.txt");
            out.write(String.valueOf(v1));
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(v1);
    };
}
