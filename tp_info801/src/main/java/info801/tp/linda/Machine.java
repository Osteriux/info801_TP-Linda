package info801.tp.linda;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

public class Machine extends Thread {

    private String nom;
    private Space espace;

    public Machine(String nom, Space espace) {
        this.nom = nom;
        this.espace = espace;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                Object[] q_machine = espace.query(new ActualField(nom), new FormalField(Boolean.class));
                System.out.println("Machine " + nom + " : " + ((Boolean) q_machine[1] ? "ON" : "OFF"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
