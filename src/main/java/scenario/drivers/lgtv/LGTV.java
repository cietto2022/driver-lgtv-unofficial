package scenario.drivers.lgtv;

import java.util.HashMap;
import java.util.Map;

public class LGTV {

    public volatile int update;
    Map<String, Object> metadata = new HashMap<>();
    Publisher publisher;
    String command;
    public LGTV(Publisher publisher) {
        this.publisher = publisher;
    }

    public void processMetadata(String s, Object o){
        switch (s){
            case "Is on":
                if (o.toString().matches("true")){
                    System.out.println("Tv is on");
                } else {
                    System.out.println("Tv is off");
                    publisher.reconnect();
                }
                break;
            case "Is connected":
                if (o.toString().matches("true")){
                    System.out.println("Tv is connected");
                } else {
                    System.out.println("Tv is not connected");
                    publisher.reconnect();
                }
                break;
            case "Volume":
                System.out.println("Volume = " + o);
                break;
        }
    }

    public void processCommand(){
        //System.out.println("inside process command s =" + this.command);
        //this.publisher.button(this.command);
    }

    public void notify(String s){
        this.command = s;
        this.processCommand();
    }

    public int getUpdate() {
        return update;
    }

    public void setUpdate(int update) {
        this.update = update;
    }

}
