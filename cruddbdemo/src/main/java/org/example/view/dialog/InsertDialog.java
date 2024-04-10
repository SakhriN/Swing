package org.example.view.dialog;

import org.example.dao.ContactDao;
import org.example.model.Contact;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertDialog extends JDialog {


    public InsertDialog() {

        setTitle("Update Dialog");

        JPanel contentPanel;
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());

        JTextField namefield, numberfield;
        JLabel textes;
        JButton jButton = new JButton("OK");

        namefield = new JTextField(10);
        numberfield = new JTextField(10);

        textes = new JLabel("NOM :");
        contentPanel.add(textes);
        contentPanel.add(namefield);

        textes = new JLabel("NUMERO DE TELEPHONE :");
        contentPanel.add(textes);
        contentPanel.add(numberfield);

        jButton.setSize(100,100);
        contentPanel.add(jButton);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Contact contat = new Contact();
                int id = (int) Math.round(Math.random()*2048+1);
                String name = namefield.getText();
                String number = numberfield.getText();
                contat.setId(id);
                contat.setName(name);
                contat.setNumber(number);
                ContactDao contactDao = new ContactDao();
                int count = contactDao.addContact(contat);
                if (count > 0) {
                    JOptionPane.showMessageDialog(null, "Operation succeeded");
                } else {
                    JOptionPane.showMessageDialog(null, "Operation Failed");
                }
            }
        });

setContentPane(contentPanel);
setVisible(true);
pack();

    }

}
