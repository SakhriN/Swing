package org.example.view.dialog;

import org.example.dao.ContactDao;
import org.example.model.Contact;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateDialog extends JDialog {



    public UpdateDialog(){
        JPanel contentPanel;
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());

        JButton searchbutton = new JButton("Search");
        JButton jButton = new JButton("OK");

        JLabel textes;
        JTextField idfield = new JTextField(10);
        JTextField namefield = new JTextField(10);
        JTextField numberfield = new JTextField(10);

        textes = new JLabel("Id :");
        contentPanel.add(textes);
        contentPanel.add(idfield);
        searchbutton.setSize(100,100);
        contentPanel.add(searchbutton);

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
                ContactDao contactDao = new ContactDao();
                int id = Integer.parseInt(idfield.getText());
                Contact contact = contactDao.getOneContact(id);
                String name = namefield.getText();
                String number = numberfield.getText();
                contact.setName(name);
                contact.setNumber(number);
                int count = contactDao.updateContact(contact);
                if(count>0){
                    JOptionPane.showMessageDialog(null, "Contact Modified Succesfully");
                }else{
                    JOptionPane.showMessageDialog(null, "WTF ARE YOU DOING ????");
                }
            }
        });

        searchbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ContactDao contactDao = new ContactDao();

                int id = Integer.parseInt(idfield.getText());

                Contact contact = contactDao.getOneContact(id);
                if (contact!=null){
                    namefield.setText(contact.getName());
                    numberfield.setText(contact.getNumber());
                }else{
                    JOptionPane.showMessageDialog(null, "Eh fdp, donne un vrai id !!!!");
                }
            }
        });


        setContentPane(contentPanel);
        setVisible(true);
        pack();


    }


}

