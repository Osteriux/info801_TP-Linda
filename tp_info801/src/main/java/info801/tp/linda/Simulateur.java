package info801.tp.linda;

import java.util.Random;

public class Simulateur extends Thread {
    private static final int DELAY = 500;

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
                float min = 0.5f;
                float max = 2.5f;

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

                simuEau = simuEau + capteurEau.getValue();
                simuMethane = simuMethane + capteurMethane.getValue();
                simuMonoxide = simuMonoxide + capteurMethane.getValue();

                if(simuEau < 0) simuEau = 0;
                if(simuMethane < 0) simuMethane = 0;
                if(simuMonoxide < 0) simuMonoxide = 0;

                capteurEau.setValue(simuEau);
                capteurMethane.setValue(simuMethane);
                capteurMonoxide.setValue(simuMonoxide);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
