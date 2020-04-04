import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class workContext {
    private HashMap<String, Double> defineTable;
    private List<String> stack;

    workContext(){
        defineTable = new HashMap<String, Double>();
        stack = new ArrayList<String>();
    }
    void addef(String var, String val){
        defineTable.put(var, Double.parseDouble(val));
    }
    double getdef(String var){
        double out;
        if (defineTable.containsKey(var)) {
            out = defineTable.get(var);
        }
        else out  = 0;
        return out;
    }
    void pushstack(String var){
        stack.add(var);
    }
    String popstack(){
        int length = stack.size();
        String out = stack.remove(length-1);
        return out;
    }
}


