package scenario.drivers.lgtv.driver;

import jep.Interpreter;
import jep.JepException;
import jep.SharedInterpreter;
import scenario.drivers.lgtv.sampleGUI.Data;

import java.util.concurrent.ThreadLocalRandom;

public class Publisher extends Thread{
    private Data load;
    private Interpreter interp;
    public Publisher(Data data) throws JepException {
        this.load = data;
        this.interp = new SharedInterpreter();
        String pythonScriptFullPath = "./python/scenario_lgtv.py";
        this.interp.runScript(pythonScriptFullPath);
        this.interp.exec("lg_tv = LG_TV()");
        this.interp.exec("lg_tv._loop.run_until_complete(lg_tv.create_lgtv())");
    }

    public void connect() throws JepException {
        //this.interp.exec("lg_tv.client = WebOsClient(HOST, CLIENT_KEY)");
        this.interp.exec("lg_tv._loop.run_until_complete(lg_tv.connect())");
    }

    public void setListener(Listener listener) throws JepException {
        this.interp.set("java_listener", listener);
        this.interp.exec("lg_tv._listener = java_listener");
    }

    public void button(String name){
        this.interp.set("buttonName", name);
        this.interp.exec("lg_tv._loop.run_until_complete(lg_tv.client.button(buttonName))");
    }

    public void volume_up(){
        this.interp.exec("lg_tv._loop.run_until_complete(lg_tv.volume_up())");
    }


    @Override
    public void run() {
        while (true) {
            for (String receivedMessage = load.receive();
                 !"End".equals(receivedMessage);
                 receivedMessage = load.receive()) {

                System.out.println("receivedMessage = " + receivedMessage);

                if (receivedMessage.equals("VOLUME_UP")) {
                    this.interp.exec("lg_tv._loop.run_until_complete(lg_tv.volume_up())");
                }

                //Thread.sleep() to mimic heavy server-side processing
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Thread Interrupted");
                }
            }
        }
    }
}
