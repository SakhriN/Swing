package org.example;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        JFrame jFrame1 = new JFrame("Demo Layout 1");
        jFrame1.setSize(1200,500);
        jFrame1.setLocationRelativeTo(null);
        jFrame1.add(new Todolist().getPanel());
        jFrame1.setVisible(true);

    }
}
