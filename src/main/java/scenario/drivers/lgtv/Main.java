package scenario.drivers.lgtv;

import jep.JepException;
import scenario.drivers.lgtv.driver.LGTV;
import scenario.drivers.lgtv.driver.Listener;
import scenario.drivers.lgtv.driver.Publisher;
import scenario.drivers.lgtv.sampleGUI.Data;
import scenario.drivers.lgtv.sampleGUI.GUI;
import scenario.drivers.lgtv.sampleGUI.Sender;

import java.util.ArrayList;
import java.util.List;

public class Main {

    Boolean connected = false;
    Boolean on = false;
    List<String> availableCommands = new ArrayList<>();

    private void test() throws JepException {
        Data data = new Data();
        Publisher publisher = new Publisher(data);
        publisher.start();
        LGTV lgtv = new LGTV(publisher);
        Listener listener = new Listener(lgtv);
        publisher.setListener(listener);
        publisher.connect();
        GUI lgtvGUI = new GUI(lgtv, data);
        lgtvGUI.start();
        //Thread sender = new Thread(new Sender(data));
        //sender.start();
    }

    public static void main(String[] args) throws JepException {
        Main main = new Main();
        main.test();





        //receiver.start();
        //try (Interpreter interp = new SharedInterpreter()) {
        //    main.test(interp);
        //} catch (Throwable e) {
        //    e.printStackTrace();
        //}
    }
}
