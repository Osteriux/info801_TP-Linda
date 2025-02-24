package info801.tp.linda;

import org.jspace.ActualField;
import org.jspace.Space;

public class Machine extends Thread {

    private String nom;
    private Space espace;
    private boolean active;

    public Machine(String nom, Space espace) {
        this.espace = espace;
        active = false;
        this.nom = nom;
    }


    public boolean isActive(){
        return active;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //active
                espace.get(new ActualField(nom + App.ACTIVE));
                active = true;
                //desactive
                espace.get(new ActualField(nom + App.DESACTIVE));
                active = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
