package info801.tp.linda;

import org.jspace.SequentialSpace;
import org.jspace.Space;

public class App {

	public static final String EAU = "eau";
	public static final String METHANE = "methane";
	public static final String MONOXYDE = "monoxyde";

	public static final String POMPE = "pompe";
	public static final String VENTILATEUR = "ventilateur";

	public static final float SEUIL_EAU_H = 200;
	public static final float SEUIL_EAU_B = 100;
	public static final float SEUIL_METHANE_H = 10;
	public static final float SEUIL_METHANE_B = 5;
	public static final float SEUIL_MONOXYDE_H = 20;
	public static final float SEUIL_MONOXYDE_B = 10;
	public static void main(String[] argv) throws InterruptedException {
		Space espace = new SequentialSpace();

		espace.put(EAU, (float) 50);
		espace.put(METHANE, (float) 5);
		espace.put(MONOXYDE, (float) 10);

		espace.put(POMPE, false);
		espace.put(VENTILATEUR, false);

		Capteur capteurEau = new Capteur(EAU, espace, 50.0f);
		Capteur capteurMethane = new Capteur(METHANE, espace, 5.0f);
		Capteur capteurMonoxide = new Capteur(MONOXYDE, espace, 10.0f);

		Machine machinePompe = new Machine(POMPE, espace);
		Machine machineVentilateur = new Machine(VENTILATEUR, espace);

		Controller controller = new Controller(espace);

		capteurEau.start();
		capteurMethane.start();
		capteurMonoxide.start();
		machinePompe.start();
		machineVentilateur.start();
		controller.start();

		capteurEau.join();
		capteurMethane.join();
		capteurMonoxide.join();
		machinePompe.join();
		machineVentilateur.join();
		controller.join();
	}

}