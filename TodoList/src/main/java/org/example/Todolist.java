package org.example;


import lombok.Data;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

@Data
public class Todolist {

    private JPanel panel, panelbas;
    private JTextField inputname = new JTextField(20);
    private JButton delete, add, finish;
    private JTable tb;
    private JScrollPane scrollPane;
    private String task;
    private int id = 2;

    String rows[][] = {{"Manger une fraise", String.valueOf(true)}};

    String column[] = {"TACHE", "STATUS"};
    public Todolist() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());


        panelbas = new JPanel();
        panelbas.setLayout(new FlowLayout());
        add = new JButton("Add");
        add.addActionListener((e) -> addTask());

        delete = new JButton("Delete");
        delete.addActionListener(e -> deleteTask());

        finish = new JButton("Finish");
        panelbas.add(add);
        panelbas.add(delete);
        panelbas.add(finish);

        tb = new JTable(new DefaultTableModel(rows,column));
        TableColumn statusColumn = tb.getColumnModel().getColumn(1);
        tb.removeColumn(statusColumn);
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
                    }
                }
            }
        });
        panel.add(inputname, BorderLayout.NORTH);
        panel.add(tb,BorderLayout.CENTER);
        panel.add(panelbas,BorderLayout.SOUTH);

    }
    public void addTask() {
        // Récupérer les valeurs des champs du formulaire
        task = inputname.getText();

        DefaultTableModel model = (DefaultTableModel) tb.getModel();

        boolean status = false;
        String test[] = {task, String.valueOf(status)};
        id++;
        model.addRow(test);
        inputname.setText("");
    }

    public void deleteTask() {
        int selectedRow = tb.getSelectedRow();
        if (selectedRow != -1) { // Vérifie si une ligne est sélectionnée
            DefaultTableModel model = (DefaultTableModel) tb.getModel();
            model.removeRow(selectedRow); // Supprime la ligne sélectionnée
        } else {
            JOptionPane.showMessageDialog(null, "Aucune tâche sélectionnée", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void finishTask() {
        int selectedRow = tb.getSelectedRow();
        if (selectedRow != -1) { // Vérifie si une ligne est sélectionnée
            DefaultTableModel model = (DefaultTableModel) tb.getModel();
            model.removeRow(selectedRow); // Supprime la ligne sélectionnée
        } else {
            JOptionPane.showMessageDialog(null, "Aucune tâche sélectionnée", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

}

