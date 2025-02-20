package info801.tp.linda.display;

import javax.swing.*;
import java.awt.*;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

import info801.tp.linda.App;

public class LevelPanel extends JPanel {
    private Space espace;
    private JLabel eauLevelStr;
    private JLabel eauLevelVal;
    private JLabel methaneLevelStr;
    private JLabel methaneLevelVal;
    private JLabel monoxideLevelStr;
    private JLabel monoxideLevelVal;

    public LevelPanel(Space espace) {
        super();
        this.espace = espace;

        setLayout(new GridLayout(3, 2));

        eauLevelStr = new JLabel("Niveau d'eau : ");
        eauLevelStr.setFont(DrainageFrame.FONT);
        add(eauLevelStr);
        eauLevelVal = new JLabel(".");
        eauLevelVal.setFont(DrainageFrame.FONT);
        add(eauLevelVal);

        methaneLevelStr = new JLabel("Niveau de methane : ");
        methaneLevelStr.setFont(DrainageFrame.FONT);
        add(methaneLevelStr);
        methaneLevelVal = new JLabel(".");
        methaneLevelVal.setFont(DrainageFrame.FONT);
        add(methaneLevelVal);

        monoxideLevelStr = new JLabel("Niveau de monoxyde : ");
        monoxideLevelStr.setFont(DrainageFrame.FONT);
        add(monoxideLevelStr);
        monoxideLevelVal = new JLabel(".");
        monoxideLevelVal.setFont(DrainageFrame.FONT);
        add(monoxideLevelVal);

        update();
    }
    
    private void update(){
        Timer timer = new Timer(App.DELAY, e -> {
            try {
            Object[] q_eau = espace.query(new ActualField(App.EAU), new FormalField(Float.class));
            Object[] q_methane = espace.query(new ActualField(App.METHANE), new FormalField(Float.class));
            Object[] q_monoxide = espace.query(new ActualField(App.MONOXYDE), new FormalField(Float.class));

            eauLevelVal.setText(String.valueOf((float) q_eau[1]));
            if((float) q_eau[1] >= App.SEUIL_EAU_H){
                eauLevelVal.setForeground(Color.RED);
            }else if((float) q_eau[1] <= App.SEUIL_EAU_B){
                eauLevelVal.setForeground(Color.GREEN);
            }else{
                eauLevelVal.setForeground(Color.ORANGE);
            }

            methaneLevelVal.setText(String.valueOf((float) q_methane[1]));
            if((float) q_methane[1] >= App.SEUIL_METHANE_H){
                methaneLevelVal.setForeground(Color.RED);
            }else if((float) q_methane[1] <= App.SEUIL_METHANE_B){
                methaneLevelVal.setForeground(Color.GREEN);
            }else{
                methaneLevelVal.setForeground(Color.ORANGE);
            }

            monoxideLevelVal.setText(String.valueOf((float) q_monoxide[1]));
            if((float) q_monoxide[1] >= App.SEUIL_MONOXYDE_H){
                monoxideLevelVal.setForeground(Color.RED);
            }else if((float) q_monoxide[1] <= App.SEUIL_MONOXYDE_B){
                monoxideLevelVal.setForeground(Color.GREEN);
            }else{
                monoxideLevelVal.setForeground(Color.ORANGE);
            }

            } catch (InterruptedException ex) {
            ex.printStackTrace();
            }
        });
        timer.start();
    }
}
