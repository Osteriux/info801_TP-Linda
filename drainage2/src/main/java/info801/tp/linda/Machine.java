package info801.tp.linda;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

public class Machine extends Thread {

    private String nom;
    private Space espace;
    private boolean active;
    private String active_str;
    private String desactive_str;

    public Machine(String nom, Space espace) {
        this.nom = nom;
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
                espace.get(new ActualField(active_str), new FormalField(String.class));
                active = true;
                //desactive
                espace.get(new ActualField(desactive_str), new FormalField(String.class));
                active = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
