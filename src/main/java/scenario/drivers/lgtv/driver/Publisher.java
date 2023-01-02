package scenario.drivers.lgtv.driver;

import jep.Interpreter;
import jep.JepException;
import jep.SharedInterpreter;

import java.util.concurrent.ThreadLocalRandom;

public class Publisher{
    private Interpreter interp;
    public Publisher(Interpreter interp) throws JepException {
        this.interp = interp;
        String pythonScriptFullPath = "./python/scenario_lgtv.py";
        this.interp.runScript(pythonScriptFullPath);
        this.interp.exec("lg_tv = LG_TV()");
        this.interp.exec("lg_tv._loop.run_until_complete(lg_tv.create_lgtv())");
    }

    public void connect() throws JepException {
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

    public void sleep(){
        this.interp.exec("lg_tv._loop.run_until_complete(lg_tv.sleep())");
    }
}
