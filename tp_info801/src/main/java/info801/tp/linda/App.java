package info801.tp.linda;

import org.jspace.SequentialSpace;
import org.jspace.Space;

public class App {

	public static final String EAU = "eau";
	public static final String METHANE = "methane";
	public static final String MONOXIDE = "monoxide";

	public static final float SEUIL_EAU = 100;
	public static final float SEUIL_METHANE = 10;
	public static final float SEUIL_MONOXIDE = 20;
	public static void main(String[] argv) throws InterruptedException {
		Space espace = new SequentialSpace();

		espace.put(EAU, (float) 50);
		espace.put(METHANE, (float) 5);
		espace.put(MONOXIDE, (float) 10);

		Capteur capteurEau = new Capteur(EAU, espace, 1.0f);
		Capteur capteurMethane = new Capteur(METHANE, espace, 0.1f);
		Capteur capteurMonoxide = new Capteur(MONOXIDE, espace, 0.2f);

		capteurEau.start();
		capteurMethane.start();
		capteurMonoxide.start();

		capteurEau.join();
		capteurMethane.join();
		capteurMonoxide.join();
	}

}