package info801.tp.linda;

import org.jspace.FormalField;
import org.jspace.SequentialSpace;
import org.jspace.Space;

public class App {
    public static final String EAU = "eau";
	public static final String METHANE = "methane";
	public static final String MONOXYDE = "monoxyde";

	public static final String POMPE = "pompe";
	public static final String POMPE_ACTIVE = POMPE+"_active";
	public static final String POMPE_DESACTIVE = POMPE+"_desactive";
	public static final String VENTILATEUR = "ventilateur";
	public static final String VENTILATEUR_ACTIVE = VENTILATEUR+"_active";
	public static final String VENTILATEUR_DESACTIVE = VENTILATEUR+"_desactive";

	public static final float SEUIL_EAU_H = 200;
	public static final float SEUIL_EAU_B = 100;
	public static final float SEUIL_METHANE_H = 10;
	public static final float SEUIL_METHANE_B = 5;
	public static final float SEUIL_MONOXYDE_H = 20;
	public static final float SEUIL_MONOXYDE_B = 10;

    public static final String DETECTION_EAU_H = "detection_eau_h";
    public static final String EAU_H_DETECTE = "eau_h_detecte";
	public static final String DETECTION_EAU_B = "detection_eau_b";
	public static final String EAU_B_DETECTE = "eau_b_detecte";
	public static final String DETECTION_GAZ_H = "detection_gaz_h";
	public static final String DETECTION_GAZ_B = "detection_gaz_b";

    public static void main(String[] args) throws InterruptedException {
        Space space = new SequentialSpace();
        
        space.put(EAU, 150);
		space.put(METHANE, 7);
		space.put(MONOXYDE, 15);
		space.put(DETECTION_EAU_H);

		Commande commande = new Commande(space);
		Machine pompe = new Machine(POMPE, space);
		Machine ventilateur = new Machine(VENTILATEUR, space);
		Capteur capteur_eau = new Capteur(EAU, space, 150);
		Capteur capteur_methane = new Capteur(METHANE, space, 7);
		Capteur capteur_monoxyde = new Capteur(MONOXYDE, space, 15);
		H2OHaut h2o_haut = new H2OHaut(space);
		H2OBas h2o_bas = new H2OBas(space);
		Simulateur simulateur = new Simulateur(capteur_eau, capteur_methane, capteur_monoxyde, pompe, ventilateur);
		Logger logger = new Logger(capteur_eau, capteur_methane, capteur_monoxyde, pompe, ventilateur);

		pompe.start();
		ventilateur.start();
		capteur_eau.start();
		capteur_methane.start();
		capteur_monoxyde.start();
		h2o_haut.start();
		h2o_bas.start();
		simulateur.start();
		commande.start();
		logger.start();

		pompe.join();
		ventilateur.join();
		capteur_eau.join();
		capteur_methane.join();
		capteur_monoxyde.join();
		h2o_haut.join();
		h2o_bas.join();
		simulateur.join();
		commande.join();
		logger.join();
    }
}