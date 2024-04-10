package org.example;

import lombok.Data;
import org.w3c.dom.Text;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

@Data
public class ContactList {

    private JPanel jPanel;
    private JScrollPane scrollPane;
    private JTable tb;
    private JLabel label1, label2;


    JRadioButton radioButton1 = new JRadioButton("HOMME");
    JRadioButton radioButton2 = new JRadioButton("FEMME");
    JRadioButton radioButton3 = new JRadioButton("CANARD");

    JButton createButton = new JButton("Créer");

    String rows[][] = {{"1", "Pierre", "test@test.fr", "HOMME"}, {"2", "Cynthia", "test@test.fr", "HOMME"}, {"3", "Sacha", "test@test.fr", "HOMME"}, {"4", "Nassim", "test@test.fr", "CANARD"}};

    String column[] = {"ID", "NOM", "EMAIL", "GENRE"};

    int id = 5;
    String name;
    String email;
    String genre;
    JTextField inputname = new JTextField(20);
    JTextField inputmail = new JTextField(20);

    ButtonGroup group;

    public ContactList() {
        jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        JPanel panel = new JPanel();
        label1 = new JLabel("NOM :");
        jPanel.add(label1);
        jPanel.add(inputname);
        name = inputname.getText();
        label2 = new JLabel("EMAIL :");
        jPanel.add(label2);
        jPanel.add(inputmail);
        email = inputmail.getText();


        createButton.addActionListener((e) -> ClickApplication());
        jPanel.add(createButton);


        group = new ButtonGroup();

        group.add(radioButton1);
        group.add(radioButton2);
        group.add(radioButton3);

        panel.setLayout(new GridLayout(3, 1));
        panel.add(radioButton1);
        panel.add(radioButton2);
        panel.add(radioButton3);
        genre = "";

        jPanel.add(panel);

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

        jPanel.add(scrollPane);


    }

    public void ClickApplication() {
        // Récupérer les valeurs des champs du formulaire
        name = inputname.getText();
        email = inputmail.getText();
        if (radioButton1.isSelected()) {
            genre = radioButton1.getText();
        } else if (radioButton2.isSelected()) {
            genre = radioButton2.getText();
        } else if (radioButton3.isSelected()) {
            genre = radioButton3.getText();
        } else {
            genre = "UNKNOWN";
        }
        System.out.println(name + email + genre);
        // Ajouter ces valeurs à votre modèle de JTable
        System.out.println(tb.getModel().getClass());
        DefaultTableModel model = (DefaultTableModel) tb.getModel();
        String test[] = {String.valueOf(id), name, email, genre};
        id++;
        model.addRow(test);
        inputname.setText("");
        inputmail.setText("");
        group.clearSelection();


    }

}
