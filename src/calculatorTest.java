import javafx.concurrent.Worker;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class calculatorTest {

    @Test
    public void calc1() {
        calculator.calc("f.txt", "testcalc1.txt");
        Scanner test, res;
        try {
            res = new Scanner(new File("res.txt"));
            test = new Scanner(new File("testres1.txt"));
            while (test.hasNextLine()) {
                String line1 = test.nextLine();
                String line2 = res.nextLine();
                assertEquals("Unexpected string value", line1, line2);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void create1() {
        Factory f = new Factory("f.txt");
        worker w1 = f.create("PUSH");
        worker w2 = new push();
        assertEquals("Unexpected worker ex", w1.getClass().getName(), w2.getClass().getName());
        w1 = f.create("POP");
        w2 = new pop();
        assertEquals("Unexpected worker ex", w1.getClass().getName(), w2.getClass().getName());
        w1 = f.create("*");
        w2 = new umnoj();
        assertEquals("Unexpected worker ex", w1.getClass().getName(), w2.getClass().getName());
        w1 = f.create("-");
        w2 = new minus();
        assertEquals("Unexpected worker ex", w1.getClass().getName(), w2.getClass().getName());        w1 = f.create("+");
        w2 = new plus();
        assertEquals("Unexpected worker ex", w1.getClass().getName(), w2.getClass().getName());        w1 = f.create("SQRT");
        w2 = new sqrt();
        assertEquals("Unexpected worker ex", w1.getClass().getName(), w2.getClass().getName());        w1 = f.create("DEFINE");
        w2 = new define();
        assertEquals("Unexpected worker ex", w1.getClass().getName(), w2.getClass().getName());        w1 = f.create("/");
        w2 = new del();
        assertEquals("Unexpected worker ex", w1.getClass().getName(), w2.getClass().getName());        w1 = f.create("PRINT");
        w2 = new print();
        assertEquals("Unexpected worker ex", w1.getClass().getName(), w2.getClass().getName());        w1 = f.create("#");
        w2 = new comment();
        assertEquals("Unexpected worker ex", w1.getClass().getName(), w2.getClass().getName());    }
}