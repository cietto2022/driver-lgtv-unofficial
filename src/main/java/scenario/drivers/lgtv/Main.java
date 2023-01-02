package scenario.drivers.lgtv;

import jep.Interpreter;
import jep.JepException;
import jep.SharedInterpreter;
import scenario.drivers.lgtv.driver.LGTV;
import scenario.drivers.lgtv.driver.Listener;
import scenario.drivers.lgtv.driver.Publisher;

import java.util.ArrayList;
import java.util.List;

public class Main {

    Boolean connected = false;
    Boolean on = false;
    List<String> availableCommands = new ArrayList<>();

    private void testCommands(LGTV lgtv) throws JepException, InterruptedException {

        lgtv.volumeUp();
        lgtv.volumeUp();
        lgtv.volumeUp();
        lgtv.volumeUp();
        lgtv.volumeUp();
        lgtv.volumeUp();
        lgtv.volumeUp();
        lgtv.volumeUp();
        lgtv.volumeUp();
        lgtv.volumeUp();
        lgtv.volumeUp();
        lgtv.volumeUp();
        lgtv.volumeUp();
        lgtv.volumeUp();
        lgtv.volumeUp();
        lgtv.volumeUp();
        Thread.sleep(10000);

        // publisher.sleep ainda permite receber metadados da TV, enquanto Thread.sleep trava completamente o código
    }

    public static void main(String[] args) throws JepException, InterruptedException {
        Main main = new Main();
        LGTV lgtv = new LGTV();
        lgtv.setupLGTV();
        main.testCommands(lgtv);
    }
}
