package org.example.view;

import org.example.view.dialog.DeleteDialog;
import org.example.view.dialog.DisplayDialog;
import org.example.view.dialog.InsertDialog;
import org.example.view.dialog.UpdateDialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private JPanel jPanel;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    mainFrame.setSize(425, 200);
                    mainFrame.setTitle("Main Frame");
                    mainFrame.setLocationRelativeTo(null);
                    mainFrame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


   public MainFrame() {

        jPanel = new JPanel();

        jPanel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(jPanel);
        jPanel.setLayout(null);


        JButton btnInsert = new JButton("Insert");
        btnInsert.setBounds(36,23, 100, 25);

       JButton btnUpdate = new JButton("Update");
       btnUpdate.setBounds(156,23, 100, 25);

       JButton btnDelete = new JButton("Delete");
       btnDelete.setBounds(276,23, 100, 25);

       JButton btnDisplay = new JButton("Display");
       btnDisplay.setBounds(156,73, 100, 25);

        btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InsertDialog dialog = new InsertDialog();
//                dialog.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
                dialog.setLocationRelativeTo(jPanel);
                dialog.setVisible(true);
            }
        });

       btnUpdate.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               UpdateDialog dialog = new UpdateDialog();
//                dialog.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
               dialog.setLocationRelativeTo(jPanel);
               dialog.setVisible(true);
           }
       });

       btnDelete.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               DeleteDialog dialog = new DeleteDialog();
//                dialog.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
               dialog.setLocationRelativeTo(jPanel);
               dialog.setVisible(true);
           }
       });

       btnDisplay.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               DisplayDialog dialog = new DisplayDialog();
//                dialog.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
               dialog.setLocationRelativeTo(jPanel);
               dialog.setVisible(true);
           }
       });



        jPanel.add(btnInsert);
        jPanel.add(btnDisplay);
        jPanel.add(btnDelete);
        jPanel.add(btnUpdate);


    }


}
