package scenario.drivers.lgtv.driver;

import jep.Interpreter;
import jep.SharedInterpreter;

import java.util.HashMap;
import java.util.Map;

public class LGTV {

    Map<String, Object> metadata = new HashMap<>();
    Publisher publisher;
    Interpreter interp;
    public LGTV() {
        this.interp = new SharedInterpreter();
    }

    public void setupLGTV(){
        this.publisher = new Publisher(this.interp);
        //LGTV atv = new LGTV(publisher);
        Listener listener = new Listener(this);
        publisher.setListener(listener);
        publisher.connect();
    }

    public void processMetadata(String s, Object o){
        switch (s){
            case "Is on":
                if (o.toString().matches("true")){
                    System.out.println("Tv is on");
                } else {
                    System.out.println("Tv is off");
                    publisher.connect();
                }
                break;
            case "Is connected":
                if (o.toString().matches("true")){
                    System.out.println("Tv is connected");
                } else {
                    System.out.println("Tv is not connected");
                    publisher.connect();
                }
                break;
            case "Volume":
                System.out.println("Volume = " + o);
                break;
        }
    }

    public void volumeUp(){
        this.publisher.button("VOLUMEUP");
    }

}
