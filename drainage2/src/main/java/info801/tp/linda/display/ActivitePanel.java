package info801.tp.linda.display;

import javax.swing.*;

import org.jspace.Space;

import info801.tp.linda.Machine;

public class ActivitePanel extends JPanel {

    public ActivitePanel(Space espace, Machine pompe, Machine ventilateur) {
        super();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        DetectionPanel detectionPanel = new DetectionPanel(espace);
        add(detectionPanel);

        MachinePanel machinePanel = new MachinePanel(pompe, ventilateur);
        add(machinePanel);
    }
}
