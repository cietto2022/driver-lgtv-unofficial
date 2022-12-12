package scenario.drivers.lgtv;

import jep.Interpreter;
import jep.JepException;
import jep.SharedInterpreter;

public class Main {

    Boolean connected = false;
    Boolean on = false;

    private void test(Interpreter interp) throws JepException, InterruptedException {
        LGTV atv = new LGTV();
        Listener listener = new Listener(atv, interp);
        Publisher publisher = new Publisher(interp);
        publisher.setListener(listener);
        publisher.connect();
        Thread.sleep(5000);
        System.out.println("HERE !!!!!!!!!!!");
        publisher.button("VOLUMEUP");

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
