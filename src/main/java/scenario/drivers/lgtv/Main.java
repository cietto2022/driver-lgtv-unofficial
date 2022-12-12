package scenario.drivers.lgtv;

import jep.Interpreter;
import jep.JepException;
import jep.SharedInterpreter;

public class Main {

    private void test(Interpreter interp) throws JepException {
        LGTV atv = new LGTV();
        Listener listener = new Listener(atv);
        Publisher publisher = new Publisher(interp);
        publisher.setListener(listener);
        publisher.connect();
    }

    public static void main(String[] args) throws JepException {
        Main main = new Main();
        try (Interpreter interp = new SharedInterpreter();) {
            main.test(interp);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
