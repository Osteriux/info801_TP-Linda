package info801.tp.linda;

public class Logger extends Thread{
    private static final int DELAY = 500;

    private Capteur capteurEau;
    private Capteur capteurMethane;
    private Capteur capteurMonoxide;
    private Machine pompe;
    private Machine ventilateur;

    public Logger(Capteur capteurEau, Capteur capteurMethane, Capteur capteurMonoxide, Machine pompe, Machine ventilateur) {
        this.capteurEau = capteurEau;
        this.capteurMethane = capteurMethane;
        this.capteurMonoxide = capteurMonoxide;
        this.pompe = pompe;
        this.ventilateur = ventilateur;
    }

    public void Log(){
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("================================================");
        System.out.println("================ STATUT DRAINAGE ===============");
        System.out.println("================================================");
        System.out.println(" - Niveau eau : "+capteurEau.getValue());
        System.out.println(" - Niveau methane : "+capteurMethane.getValue());
        System.out.println(" - Niveau monoxide : "+capteurMonoxide.getValue());
        System.out.println("");
        System.out.println(" - Pompe : "+(pompe.isActive()?"ON":"OFF"));
        System.out.println(" - Ventilateur : "+(ventilateur.isActive()?"ON":"OFF"));
        System.out.println("================================================");
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(DELAY);
                Log();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
