package scenario.drivers.lgtv;

import jep.Interpreter;
import jep.JepException;

public class Publisher {

    Interpreter interp;
    public Publisher(Interpreter interp) throws JepException {
        this.interp = interp;
        String pythonScriptFullPath = "./python/scenario_lgtv.py";
        this.interp.runScript(pythonScriptFullPath);
        this.interp.exec("lg_tv = LG_TV()");
    }

    public void connect() throws JepException {
        this.interp.exec("lg_tv._loop.run_until_complete(lg_tv.connect())");
    }

    public void setListener(Listener listener) throws JepException {
        interp.set("java_listener", listener);
        interp.exec("lg_tv._listener = java_listener");
    }

    public void getAttribute(){

    }
}
