package info801.tp.linda;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

public class H2O_bas extends Thread {

    private Space espace;

    public H2O_bas(Space espace) {
        super();
        this.espace = espace;
    }

    @Override
    public void run() {
        while (true) {
            try{
                espace.query(new ActualField(App.DETECTION_EAU_B)); // On attend la détection d'eau basse
                Object[] level_eau = espace.query(new ActualField(App.EAU), new FormalField(Float.class));
                if((float) level_eau[1] <= App.SEUIL_EAU_B){ // Si le niveau d'eau est inférieur au seuil
                    espace.put(App.POMPE+"_desactive"); // On désactive la pompe
                    espace.put(App.VENTILATEUR+"_desactive"); // On désactive le ventilateur
                    espace.get(new ActualField(App.DETECTION_EAU_B)); // On retire la détection d'eau basse
                    espace.put(App.EAU_B_DETECTE); // On signale la détection d'eau basse
                }
                sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
