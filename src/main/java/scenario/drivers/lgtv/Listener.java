package scenario.drivers.lgtv;

import jep.Interpreter;
import jep.python.PyObject;
import java.util.Map;

public class Listener implements DriverListener {

    LGTV lgtv;
    Interpreter interp;

    public Listener(LGTV lg, Interpreter interp) {
        this.lgtv = lg;
        this.interp = interp;
    }

    @Override
    public void notify(Map<String, Object> value) {
        value.forEach((s, o) -> {
            //System.out.println("JAVA:::: " + s + ": " + o);
            this.lgtv.metadata.put(s,o);
            if(s.matches("Is on") && o.toString().matches("true")){
                System.out.println(o);
                System.out.println("TV IS STILL ON!");
            } else if (s.matches("Is on") && o.toString().matches("false")){
                System.out.println("TV IS not ON!");
            }
        });
    }
    @Override
    public void notifyUser(String s) {
        System.out.println(s);
    }

    @Override
    public void notifyError(String s){
        // send to Log?
        notifyUser("Erro!");
    }

    public void processMetadata(String s, Object o){
        switch (s){
            case "Is on":
                if (o.toString().matches("true")){
                    System.out.println("Tv is on");
                } else {
                    System.out.println("Tv is off");
                }
                break;
            case "Is connected":
                if (o.toString().matches("true")){
                    System.out.println("Tv is connected");
                } else {
                    System.out.println("Tv is not connected");
                }
                break;
        }
    }

}
