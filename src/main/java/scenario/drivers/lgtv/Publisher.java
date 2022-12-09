package scenario.drivers.lgtv;

import jep.Interpreter;
import jep.JepException;

public class Publisher {
    
    Interpreter interp;


    public Publisher(Interpreter interp) throws JepException {
        this.interp = interp;
        String pythonScriptFullPath = "./python/scenario_apple_tv.py";
        this.interp.runScript(pythonScriptFullPath);
        this.interp.exec("lg_tv = ScenarioAppleTV('192.168.11.38')");
    }

    public void connect() {

    }
}
