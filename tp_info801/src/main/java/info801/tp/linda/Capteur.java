package info801.tp.linda;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

public class Capteur extends Thread {

    private String nom;
    private Space espace;
    private float value;

    public Capteur(String nom, Space espace, float value) {
        this.nom = nom;
        this.espace = espace;
        this.value = value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                espace.get(new ActualField(nom), new FormalField(Float.class));
                System.out.println("Capteur " + nom + " : " + value);
                espace.put(nom, value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
