package info801.tp.linda;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

public class H2OBas extends Thread {

    private Space espace;

    public H2OBas(Space espace) {
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
                    espace.put(App.DETECTION_EAU_H); // On détecte l'eau haute
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
