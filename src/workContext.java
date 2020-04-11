import java.io.IOException;
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
    double getdef(String var) throws IOException {
        double out = 0;

        if (defineTable.containsKey(var)) {
            out = defineTable.get(var);
        } else throw new IOException("no element's definition");

        return out;
    }
    void pushstack(String var){
        stack.add(var);
    }
    String popstack() throws IOException {
        int length = stack.size();
        if (length < 1) throw new IOException("tried to get element, but stack is empty");
        String out = stack.remove(length-1);
        return out;
    }
}


