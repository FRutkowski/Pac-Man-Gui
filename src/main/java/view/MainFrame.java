package view;

import model.Data;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        super("Turn-Based Pac-Man");
        Data data = new Data();
        MainMenu mainMenuPanel = new MainMenu(this, data);
        add(mainMenuPanel);
        setPreferredSize(new Dimension(950, 630));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
