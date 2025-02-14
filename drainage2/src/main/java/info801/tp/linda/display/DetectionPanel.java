package info801.tp.linda.display;

import javax.swing.*;

import org.jspace.ActualField;
import org.jspace.Space;

import info801.tp.linda.App;

import java.awt.*;

public class DetectionPanel extends JPanel {
    private Space espace;
    private JLabel detectionEauStr;
    private JLabel detectionEauVal;
    private JLabel detectionGazStr;
    private JLabel detectionGazVal;
    
    private int detectionEau = 1;
    private int detectionGaz = 0;

    public DetectionPanel(Space espace){
        super();
        this.espace = espace;

        setLayout(new GridLayout(2, 2));

        detectionEauStr = new JLabel("Detection d'eau : ");
        detectionEauStr.setFont(DrainageFrame.FONT);
        add(detectionEauStr);
        detectionEauVal = new JLabel(".");
        detectionEauVal.setFont(DrainageFrame.FONT);
        add(detectionEauVal);

        detectionGazStr = new JLabel("Detection de gaz : ");
        detectionGazStr.setFont(DrainageFrame.FONT);
        add(detectionGazStr);
        detectionGazVal = new JLabel(".");
        detectionGazVal.setFont(DrainageFrame.FONT);
        add(detectionGazVal);

        update();
    }

    private void update(){
        Timer timer = new Timer(App.DELAY, e -> {
            try {
                Object[] deh = espace.queryp(new ActualField(App.DETECTION_EAU_H));
                Object[] deb = espace.queryp(new ActualField(App.DETECTION_EAU_B));
                if(deh!=null && deb!=null){
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
                }else if(dgh!=null){
                    detectionGaz = 1;
                }else if(dgb!=null){
                    detectionGaz = -1;
                }else{
                    detectionGaz = 0;
                }
    
                detectionEauVal.setText(detectionEau>0?"HAUT":(detectionEau<0?"BAS":"AUCUNE"));
                detectionGazVal.setText(detectionGaz>0?"HAUT":(detectionGaz<0?"BAS":"AUCUNE"));
            } catch (InterruptedException err) {
                err.printStackTrace();
            }
        });
        timer.start();
    }
    
}
