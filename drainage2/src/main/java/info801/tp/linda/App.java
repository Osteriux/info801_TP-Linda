package info801.tp.linda;

import org.jspace.SequentialSpace;
import org.jspace.Space;

import info801.tp.linda.display.DrainageFrame;

public class App {
	public static int DELAY = 500;

    public static final String EAU = "eau";
	public static final String METHANE = "methane";
	public static final String MONOXYDE = "monoxyde";

	public static final String POMPE = "pompe";
	public static final String VENTILATEUR = "ventilateur";
	public static final String ACTIVE = "_active";
	public static final String DESACTIVE = "_desactive";

	public static final float SEUIL_EAU_H = 200;
	public static final float SEUIL_EAU_B = 100;
	public static final float SEUIL_METHANE_H = 15;
	public static final float SEUIL_METHANE_B = 10;
	public static final float SEUIL_MONOXYDE_H = 30;
	public static final float SEUIL_MONOXYDE_B = 20;

    public static final String DETECTION_EAU_H = "detection_eau_h";
    public static final String EAU_H_DETECTE = "eau_h_detecte";
	public static final String DETECTION_EAU_B = "detection_eau_b";
	public static final String DETECTION_GAZ_H = "detection_gaz_h";
	public static final String DETECTION_GAZ_B = "detection_gaz_b";

	private static Space space;
	public static boolean use_gui = false;

    public static void main(String[] args) throws InterruptedException {
		for (String string : args) {
			if(string.equals("--gui")
			|| string.equals("-g")
			|| string.equals("--use-gui")
			|| string.equals("--window")
			|| string.equals("-w")) {
				use_gui = true;
			}
		}

        space = new SequentialSpace();
        
        space.put(EAU, (float) SEUIL_EAU_B);
		space.put(METHANE, (float) SEUIL_METHANE_B/2);
		space.put(MONOXYDE, (float) SEUIL_MONOXYDE_B/2);
		space.put(DETECTION_EAU_H);

		Commande commande = new Commande(space);
		Machine pompe = new Machine(POMPE, space);
		Machine ventilateur = new Machine(VENTILATEUR, space);
		Capteur capteur_eau = new Capteur(EAU, space, SEUIL_EAU_B);
		Capteur capteur_methane = new Capteur(METHANE, space, SEUIL_METHANE_B/2);
		Capteur capteur_monoxyde = new Capteur(MONOXYDE, space, SEUIL_MONOXYDE_B/2);
		H2OHaut h2o_haut = new H2OHaut(space);
		H2OBas h2o_bas = new H2OBas(space);
		GazBas gaz_bas = new GazBas(space);
		SurveillanceGazHaut gaz_haut = new SurveillanceGazHaut(space);
		Simulateur simulateur = new Simulateur(capteur_eau, capteur_methane, capteur_monoxyde, pompe, ventilateur);
		
		pompe.start();
		ventilateur.start();
		capteur_eau.start();
		capteur_methane.start();
		capteur_monoxyde.start();
		h2o_haut.start();
		h2o_bas.start();
		gaz_bas.start();
		gaz_haut.start();
		commande.start();
		simulateur.start();
		
		if(use_gui){
			DELAY = 5;
			simulateur.setModif(0.01f);
			new DrainageFrame(space, pompe, ventilateur);
		}else{
			Logger logger = new Logger(capteur_eau, capteur_methane, capteur_monoxyde, pompe, ventilateur, space);
			logger.start();
			logger.join();
		}

		pompe.join();
		ventilateur.join();
		capteur_eau.join();
		capteur_methane.join();
		capteur_monoxyde.join();
		h2o_haut.join();
		h2o_bas.join();
		gaz_bas.join();
		gaz_haut.join();
		simulateur.join();
		commande.join();
    }
}