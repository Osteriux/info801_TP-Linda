package info801.tp.linda;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

public class SurveillanceGazHaut extends Thread {

    private Space espace;

    public SurveillanceGazHaut(Space espace) {
        super();
        this.espace = espace;
    }

    @Override
    public void run() {
        while (true) {
            try{
                espace.query(new ActualField(App.DETECTION_GAZ_H)); // On attend la détection de gaz haute
                Object[] level_ch4 = espace.query(new ActualField(App.METHANE), new FormalField(Float.class));
                Object[] level_co = espace.query(new ActualField(App.MONOXYDE), new FormalField(Float.class));
                if((float) level_ch4[1] >= App.SEUIL_METHANE_H || (float) level_co[1] >= App.SEUIL_MONOXYDE_H){ // Si le niveau de gaz est supérieur au seuil
                    espace.put(App.VENTILATEUR+"_active"); // On active le ventilateur
                    espace.get(new ActualField(App.DETECTION_GAZ_H)); // On retire la détection de gaz haute
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
