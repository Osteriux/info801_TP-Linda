package info801.tp.linda;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

public class Commande extends Thread {

    private Space espace;

    public Commande(Space espace) {
        this.espace = espace;
    }

    @Override
    public void run() {
        while (true) {
            try {
                espace.query(new ActualField(App.DETECTION_EAU_H));
                Object[] q_methane = espace.query(new ActualField(App.METHANE), new FormalField(Float.class));
                Object[] q_monoxide = espace.query(new ActualField(App.MONOXYDE), new FormalField(Float.class));

                if((float) q_methane[1] < App.SEUIL_METHANE_H && (float) q_monoxide[1] < app.SEUIL_METHANE_H){
                    espace.put(App.POMPE_ACTIVE);
                    espace.put(App.DETECTION_EAU_B);
                    espace.put(App.DETECTION_GAZ_H);
                    
                } else if((float) q_methane[1] >= App.SEUIL_METHANE_H || (float) q_monoxide[1] < App.SEUIL_MONOXYDE_H){
                    espace.put(App.VENTILATEUR_ACTIVE);
                    espace.put(App.DETECTION_GAZ_B);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
