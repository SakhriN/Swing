package org.example;

import javax.swing.*;
import org.example.layout.Tackin;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");

        JFrame jFrame = new JFrame("Demo Layout");
        jFrame.setSize(1200,500);
        jFrame.setLocationRelativeTo(null);
        jFrame.add(new Tackin().getJPanel());
        jFrame.setVisible(true);

//        SwingUtilities.invokeLater(() -> new Tackin());

    }
}