package org.example;

import javax.swing.*;
import java.awt.*;

public class Tackin {
    private JPanel calculatricePanel;
    private JLabel blackScreen;
    private GridBagLayout bagLayout;
    private GridBagConstraints bagConstraints;
    private String[] buttons = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", " "};

    public Tackin() {
        calculatricePanel = new JPanel();
        // calculatricePanel.setSize(600,1200);
        bagLayout = new GridBagLayout();
        calculatricePanel.setLayout(bagLayout);
        bagConstraints = new GridBagConstraints();
        bagConstraints.fill = GridBagConstraints.BOTH;
    }
}
