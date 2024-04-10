package org.example.view.dialog;

import org.example.dao.ContactDao;
import org.example.model.Contact;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;


public class DisplayDialog extends JDialog {
    private JScrollPane scrollPane;
    private JTable tb;
    String rows[][] = {};
    String column[] = {"ID", "NOM", "NUMBER"};


    public DisplayDialog() {

        setTitle("Update Dialog");

        JPanel contentPanel;
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());

        ContactDao contactDao = new ContactDao();
        List<Contact> contactList = contactDao.getContact();
        int count = contactList.size();
        int i = 0;
        String[][] rows = new String[count][3]; // Supposons que chaque contact a trois colonnes (id, name, number)

// Parcourez chaque contact et ajoutez ses données au tableau
        for (Contact contact : contactList) {
            rows[i][0] = Integer.toString(contact.getId()); // Convertissez l'ID en String
            rows[i][1] = contact.getName();
            rows[i][2] = contact.getNumber();
            i++;
        }

        String column[] = {"ID", "NOM", "EMAIL", "GENRE"};

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

        contentPanel.add(scrollPane);

        setContentPane(contentPanel);
        setVisible(true);
        pack();
    }

}
