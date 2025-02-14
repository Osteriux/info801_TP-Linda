package info801.tp.linda.display;

import javax.swing.*;
import java.awt.*;
import org.jspace.Space;
import info801.tp.linda.Machine;

public class DrainageFrame extends JFrame {
    private static final int WIDTH = 450;
    private static final int HEIGHT = 200;
    public static final Font FONT = new Font("DrainageFont", Font.BOLD, 18);

    public DrainageFrame(Space espace, Machine pompe, Machine ventilateur) {
        super("Drainage");

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        LevelPanel levelDisplay = new LevelPanel(espace);
        add(levelDisplay, BorderLayout.NORTH);

        ActivitePanel activiteDisplay = new ActivitePanel(espace, pompe, ventilateur);
        add(activiteDisplay, BorderLayout.SOUTH);

        setVisible(true);
    }

}
