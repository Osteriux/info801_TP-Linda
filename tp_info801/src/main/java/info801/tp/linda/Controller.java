package info801.tp.linda;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

public class Controller extends Thread {

    private Space espace;

    public Controller(Space espace) {
        this.espace = espace;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
                // on recupere les valeurs des differentes variables
                Object[] q_eau = espace.query(new ActualField(App.EAU), new FormalField(Float.class));
                Object[] q_methane = espace.query(new ActualField(App.METHANE), new FormalField(Float.class));
                Object[] q_monoxide = espace.query(new ActualField(App.MONOXYDE), new FormalField(Float.class));
                Object[] q_pompe = espace.query(new ActualField(App.POMPE), new FormalField(Boolean.class));
                Object[] q_ventilateur = espace.query(new ActualField(App.VENTILATEUR), new FormalField(Boolean.class));
                
                if((boolean) q_pompe[1]) { // si la pompe est allumée
                    if((float) q_methane[1] > App.SEUIL_METHANE_H
                    && (float) q_monoxide[1] > App.SEUIL_MONOXYDE_H) {
                        // si le methane ou le monoxide sont trop haut
                        if(!(boolean) q_ventilateur[1]) {
                            // => on allume le ventilateur si il n'est pas deja allumé
                            espace.get(new ActualField(App.VENTILATEUR), new FormalField(Boolean.class));
                            espace.put(App.VENTILATEUR, true);
                        }
                    }

                    if((float) q_eau[1] < App.SEUIL_EAU_B){
                        // si l'eau est suffisament basse
                        // => on eteint la pompe
                        espace.get(new ActualField(App.POMPE), new FormalField(Boolean.class));
                        espace.put(App.POMPE, false);
                    }
                } else { // si la pompe est eteinte
                    if((float) q_eau[1] > App.SEUIL_EAU_H
                    && (float) q_methane[1] < App.SEUIL_METHANE_B
                    && (float) q_monoxide[1] < App.SEUIL_MONOXYDE_B) {
                        // si l'eau est trop haute et que le methane et le monoxide sont suffisament bas
                        // => on allume la pompe
                        espace.get(new ActualField(App.POMPE), new FormalField(Boolean.class));
                        espace.put(App.POMPE, true);
                    }

                    if((float) q_methane[1] > App.SEUIL_METHANE_H
                    || (float) q_monoxide[1] > App.SEUIL_MONOXYDE_H) {
                        // si le methane ou le monoxide sont trop haut
                        if(!(boolean) q_ventilateur[1]) {
                            // => on allume le ventilateur si il n'est pas deja allumé
                            espace.get(new ActualField(App.VENTILATEUR), new FormalField(Boolean.class));
                            espace.put(App.VENTILATEUR, true);
                        }
                    }
                }

                if((boolean) q_ventilateur[1]) { // si le ventilateur est allumé
                    if((float) q_methane[1] < App.SEUIL_METHANE_B
                    && (float) q_monoxide[1] < App.SEUIL_MONOXYDE_B) {
                        // si le methane et le monoxide sont suffisament bas
                        // => on éteint le ventilateur
                        espace.get(new ActualField(App.VENTILATEUR), new FormalField(Boolean.class));
                        espace.put(App.VENTILATEUR, false);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
