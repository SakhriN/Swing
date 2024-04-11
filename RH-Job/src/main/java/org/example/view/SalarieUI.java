package org.example.view;

import lombok.Data;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.plaf.PanelUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Data
public class SalarieUI extends PanelUI {
    private JPanel jPanel;
    private JPanel buttonPanel;
    private JScrollPane scrollPane;
    private JTable tb;
    private JButton add, edit, delete, departement;

    String rows[][] = {};

    String column[] = {"ID", "NOM", "NOMBRE DE DEPARTEMENT"};

    public SalarieUI() {

        jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        add =new JButton("Ajouter");
        add.setSize(100, 100);
        edit =new JButton("Modifier");
        edit.setSize(100, 100);
        delete =new JButton("Supprimer");
        delete.setSize(100, 100);
        departement =new JButton("Departements");
        departement.setSize(100, 100);



        buttonPanel.add(add);
        buttonPanel.add(edit);
        buttonPanel.add(delete);
        buttonPanel.add(departement);


        tb = new JTable(new DefaultTableModel(rows, column));

        tb.setBounds(30, 40, 200, 200);

        scrollPane = new JScrollPane(tb);


        tb.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int row = tb.getSelectedRow();

                    if (row < 0) {
                        System.out.println("Il n'y a rien");
                    } else {

                        int modelRow = tb.convertRowIndexToModel(row);

                        System.out.println(String.format("Info selected in: %d. Dans model : %d. Autre info %s, %s, %s", row,
                                modelRow, tb.getModel().getValueAt(modelRow, 0),
                                tb.getModel().getValueAt(modelRow, 1),
                                tb.getModel().getValueAt(modelRow, 2)));

                    }

                }

            }
        });

        jPanel.add(scrollPane, BorderLayout.CENTER);
        jPanel.add(buttonPanel, BorderLayout.SOUTH);

        departement.addActionListener(new ActionListener() {
                                       @Override
                                       public void actionPerformed(ActionEvent e) {
                                           JPanel newPanel;
                                           newPanel = new JPanel();
                                           newPanel.setLayout(new BorderLayout());
                                           DepartementUI departementUI = new DepartementUI();
                                           jPanel.setUI(departementUI);
                                       }
                                   }
        );

    }


}
