package scenario.drivers.lgtv;

import jep.Interpreter;
import jep.JepException;
import jep.SharedInterpreter;
import scenario.drivers.lgtv.sampleGUI.GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    Boolean connected = false;
    Boolean on = false;
    List<String> availableCommands = new ArrayList<>();


    private void test(Interpreter interp) throws JepException {
        Publisher publisher = new Publisher(interp);
        LGTV lgtv = new LGTV(publisher);
        Listener listener = new Listener(lgtv);
        publisher.setListener(listener);
        publisher.connect();
        GUI lgtvGUI = new GUI(lgtv);
        lgtvGUI.showJPanelDemo();
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
