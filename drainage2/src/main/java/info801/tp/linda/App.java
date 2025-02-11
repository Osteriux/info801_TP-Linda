package info801.tp.linda;

import org.jspace.FormalField;
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

    public static final String DETECTION_EAU_H = "detection_eau_h";
    public static final String EAU_H_DETECTE = "eau_h_detecte";

    public static void main(String[] args) throws InterruptedException {
        Space space = new SequentialSpace();
        
        space.put("Hello World!");
        Object[] tuple = space.get(new FormalField(String.class));				
        System.out.println(tuple[0]);
        
    }
}