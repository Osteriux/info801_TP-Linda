package info801.tp.linda;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

public class Machine extends Thread {

    private String nom;
    private Space espace;
    private boolean active;

    public Machine(String nom, Space espace) {
        this.nom = nom;
        this.espace = espace;
        active = false;
    }

    public boolean isActive(){
        return active;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                Object[] q_machine = espace.query(new ActualField(nom), new FormalField(Boolean.class));
                active = (Boolean) q_machine[1];
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
