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
                espace.get(new ActualField(App.EAU_H_DETECTE));
                Object[] q_methane = espace.query(new ActualField(App.METHANE), new FormalField(Float.class));
                Object[] q_monoxide = espace.query(new ActualField(App.MONOXYDE), new FormalField(Float.class));
                float niveau_methane = (float) q_methane[1];
                float niveau_monoxide = (float) q_monoxide[1];
                System.out.println(niveau_methane);
                System.out.println(niveau_monoxide);

                if(niveau_methane < App.SEUIL_METHANE_H && niveau_monoxide < App.SEUIL_METHANE_H){
                    espace.put(App.POMPE_ACTIVE);
                    espace.put(App.DETECTION_EAU_B);
                    espace.put(App.DETECTION_GAZ_H);

                } else if(niveau_methane >= App.SEUIL_METHANE_H || niveau_monoxide < App.SEUIL_MONOXYDE_H){
                    espace.put(App.VENTILATEUR_ACTIVE);
                    espace.put(App.DETECTION_GAZ_B);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
