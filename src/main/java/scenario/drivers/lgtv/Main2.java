package scenario.drivers.lgtv;

import jep.Interpreter;
import jep.JepException;
import jep.SharedInterpreter;
import scenario.drivers.lgtv.sampleGUI.GUI;

import java.util.ArrayList;
import java.util.List;

public class Main2 {

    Boolean connected = false;
    Boolean on = false;
    List<String> availableCommands = new ArrayList<>();


    private void test(Interpreter interp) throws JepException {
        GUI lgtvGUI = new GUI();
        Publisher publisher = new Publisher(interp);
        LGTV lgtv = new LGTV(publisher);
        Listener listener = new Listener(lgtv);
        publisher.setListener(listener);
        publisher.connect();
        lgtvGUI.showJPanelDemo();
    }

    public static void main(String[] args) throws JepException {
        Main2 main = new Main2();
        try (Interpreter interp = new SharedInterpreter();) {
            main.test(interp);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
