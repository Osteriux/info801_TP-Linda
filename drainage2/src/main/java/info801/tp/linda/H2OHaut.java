package info801.tp.linda;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

public class H2OHaut extends Thread {

    private Space espace;

    public H2OHaut(Space espace) {
        super();
        this.espace = espace;
    }

    @Override
    public void run() {
        while (true) {
            try{
                espace.query(new ActualField(App.DETECTION_EAU_H)); // On attend la détection d'eau haute
                Object[] level_eau = espace.query(new ActualField(App.EAU), new FormalField(Float.class));
                if((float) level_eau[1] >= App.SEUIL_EAU_H){ // Si le niveau d'eau est supérieur au seuil
                    espace.get(new ActualField(App.DETECTION_EAU_H)); // On retire la détection d'eau haute
                    espace.put(App.EAU_H_DETECTE); // On signale la détection d'eau haute
                }
                sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
