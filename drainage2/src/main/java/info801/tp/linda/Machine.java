package info801.tp.linda;

import org.jspace.ActualField;
import org.jspace.Space;

public class Machine extends Thread {

    private Space espace;
    private boolean active;
    private String active_str;
    private String desactive_str;

    public Machine(String nom, Space espace) {
        this.espace = espace;
        active = false;
        active_str = nom+"_active";
        desactive_str = nom+"_desactive";
    }


    public boolean isActive(){
        return active;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //active
                espace.get(new ActualField(active_str));
                active = true;
                //desactive
                espace.get(new ActualField(desactive_str));
                active = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
