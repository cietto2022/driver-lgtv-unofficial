package scenario.drivers.lgtv;

import jep.JepException;
import scenario.drivers.lgtv.driver.LGTV;

import java.util.ArrayList;
import java.util.List;

public class Main {

    Boolean connected = false;
    Boolean on = false;
    List<String> availableCommands = new ArrayList<>();

    private void testCommands(LGTV lgtv) throws JepException, InterruptedException {
        Thread.sleep(1000);
        System.out.println("before");
        lgtv.volumeUpReq();
        Thread.sleep(1000);
        lgtv.volumeUpReq();
        Thread.sleep(1000);
        lgtv.volumeUpReq();
        Thread.sleep(1000);
        lgtv.volumeUpReq();
        Thread.sleep(1000);
        lgtv.setMuteReq();
        Thread.sleep(1000);
        System.out.println("after");

        // publisher.sleep ainda permite receber metadados da TV, enquanto Thread.sleep trava completamente o código
    }

    public static void main(String[] args) throws JepException, InterruptedException {
        Main main = new Main();
        LGTV lgtv = new LGTV();
        lgtv.setupLGTV();
        main.testCommands(lgtv);
    }
}
