package view;

import model.Data;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {
    public MainFrame() throws IOException {
        super("Turn-Based Pac-Man");
        Data data = new Data();
        MainMenu mainMenuPanel = new MainMenu(this, data);
        add(mainMenuPanel);
        setPreferredSize(new Dimension(900, 630));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
