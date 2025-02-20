package info801.tp.linda.display;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.Timer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import info801.tp.linda.App;
import info801.tp.linda.Machine;

public class MachinePanel extends JPanel {
    private Machine pompe;
    private Machine ventilateur;
    private JLabel pompeStr;
    private JLabel pompeVal;
    private JLabel ventilateurStr;
    private JLabel ventilateurVal;

    public MachinePanel(Machine pompe, Machine ventilateur){
        super();
        this.pompe = pompe;
        this.ventilateur = ventilateur;

        setLayout(new GridLayout(2, 2));

        pompeStr = new JLabel("Pompe : ");
        pompeStr.setFont(DrainageFrame.FONT);
        add(pompeStr);
        pompeVal = new JLabel("OFF");
        pompeVal.setFont(DrainageFrame.FONT);
        add(pompeVal);

        ventilateurStr = new JLabel("Ventilateur : ");
        ventilateurStr.setFont(DrainageFrame.FONT);
        add(ventilateurStr);
        ventilateurVal = new JLabel("OFF");
        ventilateurVal.setFont(DrainageFrame.FONT);
        add(ventilateurVal);

        update();
    }

    private void update(){
        Timer timer = new Timer(App.DELAY, e -> {
            pompeVal.setText(pompe.isActive() ? "ON" : "OFF");
            if(pompe.isActive()){
                pompeVal.setForeground(Color.GREEN);
            }else{
                pompeVal.setForeground(Color.RED);
            }

            ventilateurVal.setText(ventilateur.isActive() ? "ON" : "OFF");
            if(ventilateur.isActive()){
                ventilateurVal.setForeground(Color.GREEN);
            }else{
                ventilateurVal.setForeground(Color.RED);
            }
            
        });
        timer.start();
    }
    
}
