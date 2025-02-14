package info801.tp.linda;

import org.jspace.ActualField;
import org.jspace.Space;

public class Logger extends Thread{

    private Capteur capteurEau;
    private Capteur capteurMethane;
    private Capteur capteurMonoxide;
    private Machine pompe;
    private Machine ventilateur;
    private Space espace;
    private int detectionEau = 1;
    private int detectionGaz = 0;

    public Logger(Capteur capteurEau, Capteur capteurMethane, Capteur capteurMonoxide, Machine pompe, Machine ventilateur, Space espace) {
        this.capteurEau = capteurEau;
        this.capteurMethane = capteurMethane;
        this.capteurMonoxide = capteurMonoxide;
        this.pompe = pompe;
        this.ventilateur = ventilateur;
        this.espace = espace;
    }

    public void Log(){
        System.out.println("================================================");
        System.out.println("================ STATUT DRAINAGE ===============");
        System.out.println("================================================");
        System.out.println(" - Niveau eau : "+capteurEau.getValue());
        System.out.println(" - Niveau methane : "+capteurMethane.getValue());
        System.out.println(" - Niveau monoxide : "+capteurMonoxide.getValue());
        System.out.println("");
        System.out.println(" - Detaction eau : "+(detectionEau>0?"HAUT":(detectionEau<0?"BAS":"AUCUNE")));
        System.out.println(" - Detection gaz : "+(detectionGaz>0?"HAUT":(detectionGaz<0?"BAS":"AUCUNE")));
        System.out.println("");
        System.out.println(" - Pompe : "+(pompe.isActive()?"ON":"OFF"));
        System.out.println(" - Ventilateur : "+(ventilateur.isActive()?"ON":"OFF"));
        System.out.println("================================================");
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(App.DELAY);
                Object[] deh = espace.queryp(new ActualField(App.DETECTION_EAU_H));
                Object[] deb = espace.queryp(new ActualField(App.DETECTION_EAU_B));
                if(deh!=null && deb!=null){
                    throw new RuntimeException("Erreur de détection d'eau haute et basse simultanée");
                }else if(deh!=null){
                    detectionEau = 1;
                }else if(deb!=null){
                    detectionEau = -1;
                }else{
                    detectionEau = 0;
                }
                Object[] dgh = espace.queryp(new ActualField(App.DETECTION_GAZ_H));
                Object[] dgb = espace.queryp(new ActualField(App.DETECTION_GAZ_B));
                if(dgh!=null && dgb!=null){
                    throw new RuntimeException("Erreur de détection de gaz haute et basse simultanée");
                }else if(dgh!=null){
                    detectionGaz = 1;
                }else if(dgb!=null){
                    detectionGaz = -1;
                }else{
                    detectionGaz = 0;
                }
                Log();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
