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
            System.out.println("PRINT JAVA:::: " + s + ": " + o);
            //PyObject clientPy = interp.getValue("client", PyObject.class);
            //System.out.println(clientPy.getAttr("volume"));
            this.lgtv.metadata.put(s,o);
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

}
