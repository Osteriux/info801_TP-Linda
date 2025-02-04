package info801.tp.linda;

import java.util.Random;

public class Simulateur extends Thread {
    private static final int DELAY = 300;

    private Capteur capteurEau;
    private Capteur capteurMethane;
    private Capteur capteurMonoxide;
    private Machine pompe;
    private Machine ventilateur;

    public Simulateur(Capteur capteurEau, Capteur capteurMethane, Capteur capteurMonoxide, Machine pompe, Machine ventilateur) {
        this.capteurEau = capteurEau;
        this.capteurMethane = capteurMethane;
        this.capteurMonoxide = capteurMonoxide;
        this.pompe = pompe;
        this.ventilateur = ventilateur;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(DELAY);
                Random r = new Random();
                float min = 1.5f;
                float max = 3.5f;

                float simuEau = min + r.nextFloat() * (max - min);
                float simuMethane = min + r.nextFloat() * (max - min);
                float simuMonoxide = min + r.nextFloat() * (max - min);

                if(pompe.isActive()){
                    simuEau *= -1;
                }
                if(ventilateur.isActive()){
                    simuMethane *= -1;
                    simuMonoxide *= -1;
                }

                capteurEau.setValue(simuEau + capteurEau.getValue());
                capteurMethane.setValue(simuMethane + capteurMethane.getValue());
                capteurMonoxide.setValue(simuMonoxide + capteurMonoxide.getValue());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
