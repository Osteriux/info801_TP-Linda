package info801.tp.linda;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

public class GazBas extends Thread {

    private Space espace;

    public GazBas(Space espace) {
        super();
        this.espace = espace;
    }

    @Override
    public void run() {
        while (true) {
            try{
                espace.query(new ActualField(App.DETECTION_GAZ_B)); // On attend la détection de gaz bas
                Object[] level_ch4 = espace.query(new ActualField(App.METHANE), new FormalField(Float.class));
                Object[] level_co = espace.query(new ActualField(App.MONOXYDE), new FormalField(Float.class));
                if((float) level_ch4[1] <= App.SEUIL_METHANE_B || (float) level_co[1] <= App.SEUIL_MONOXYDE_B){ // Si le niveau de gaz est inférieur au seuil
                    espace.put(App.POMPE+"_active"); // On active la pompe
                    espace.put(App.DETECTION_EAU_B); // On détecte l'eau basse
                    espace.get(new ActualField(App.DETECTION_GAZ_B)); // On retire la détection de gaz bas
                }
                sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
