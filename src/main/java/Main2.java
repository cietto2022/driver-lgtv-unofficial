import scenario.drivers.lgtv.driver.LGTV;
import scenario.drivers.lgtv.driver.Listener;
import scenario.drivers.lgtv.driver.Publisher;
import scenario.drivers.lgtv.sampleGUI.Data;
import scenario.drivers.lgtv.sampleGUI.GUI;

public class Main2 {

    public static void main(String[] args) throws InterruptedException {
        Data data = new Data();
        Publisher publisher = new Publisher(data);
        publisher.start();
        LGTV lgtv = new LGTV(publisher);
        Listener listener = new Listener(lgtv);
        publisher.setListener(listener);
        publisher.connect();
        Thread.sleep(5000);
        lgtv.notify("VOLUME_UP");
        Thread.sleep(1000);
        lgtv.notify("VOLUME_UP");
        Thread.sleep(1000);
        lgtv.notify("VOLUME_UP");
        Thread.sleep(1000);
        lgtv.notify("VOLUME_UP");
    }
}
